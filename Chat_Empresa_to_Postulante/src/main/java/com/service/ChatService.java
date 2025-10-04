package com.service;
import com.application.dto.MensajeDTO;
import com.domain.model.Mensaje;
import com.domain.model.Chat;
import com.domain.model.Usuario;
import com.domain.repository.MensajeRepository;
import com.domain.repository.Chat_Repository;
import com.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final MensajeRepository mensajeRepository;
    private final Chat_Repository chatRepository;
    private final UsuarioRepository usuarioRepository;

    public ChatService(MensajeRepository mensajeRepository,
                       Chat_Repository chatRepository,
                       UsuarioRepository usuarioRepository) {
        this.mensajeRepository = mensajeRepository;
        this.chatRepository = chatRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Recibe un DTO, construye un Mensaje y lo guarda en la base de datos.
     */
    public Mensaje enviarMensaje(Chat chat, MensajeDTO dto, Usuario remitente, Usuario destinatario) {
        if (chat == null) {
            chat = new Chat();
            chat.setRemitente(remitente.getNombre());
            chat.setDestinatario(destinatario.getNombre());
            chat.setFechaHora(LocalDateTime.now());
            chatRepository.save(chat); // Guardar chat antes de asignar mensajes
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setContenido(dto.getContenido());
        mensaje.setFechaHora(LocalDateTime.now());
        mensaje.setChat(chat);

        chat.addMensaje(mensaje);

        return mensajeRepository.save(mensaje);
    }

    /**
     * Obtiene todos los mensajes entre dos usuarios específicos.
     */
    public List<Mensaje> obtenerMensajes(Long remitenteId, Long destinatarioId) {
        // Buscar usuarios por ID
        Usuario remitente = usuarioRepository.findById(remitenteId)
                .orElseThrow(() -> new IllegalArgumentException("Remitente no encontrado"));
        Usuario destinatario = usuarioRepository.findById(destinatarioId)
                .orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado"));

        // Buscar el chat entre estos dos usuarios
        Chat chat = chatRepository.findAll()
                .stream()
                .filter(c -> c.getRemitente().equals(remitente.getNombre()) &&
                             c.getDestinatario().equals(destinatario.getNombre()))
                .findFirst()
                .orElse(null);

        if (chat == null) {
            return List.of(); // No hay chat, devolver lista vacía
        }

        // Retornar solo los mensajes de ese chat
        return mensajeRepository.findByChatIdChat(chat.getIdChat());
    }
}