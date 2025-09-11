import React from "react";

function Profile({ profile, chats, onSelectChat, perfilesDisponibles }) {
  const token = localStorage.getItem("token");

  const iniciarChat = async (destinatarioId) => {
    if (!profile?.id || !token) return;

    // 🔹 Verificar si ya existe un chat con ese usuario
    const chatExistente = chats.find(
      (chat) =>
        (chat.remitente?.id === profile.id && chat.destinatario?.id === destinatarioId) ||
        (chat.destinatario?.id === profile.id && chat.remitente?.id === destinatarioId)
    );

    if (chatExistente) {
      onSelectChat(chatExistente);
      return;
    }

    // 🔹 Si no existe, crear nuevo chat
    try {
      const res = await fetch("http://localhost:8080/api/chat/crear", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          remitenteId: profile.id,
          destinatarioId,
        }),
      });

      if (!res.ok) {
        const errorText = await res.text();
        throw new Error(`Error al crear el chat: ${errorText}`);
      }

      const nuevoChat = await res.json();
      // 🔹 Aseguramos que tenga remitente y destinatario completos
      if (!nuevoChat.remitente) nuevoChat.remitente = profile;
      if (!nuevoChat.destinatario) {
        const destinatario = perfilesDisponibles.find((u) => u.id === destinatarioId);
        nuevoChat.destinatario = destinatario || { id: destinatarioId, nombre: "Usuario" };
      }

      onSelectChat(nuevoChat);
    } catch (err) {
      console.error("Error iniciando chat:", err);
    }
  };

  const getNombreOtro = (chat) => {
    if (!profile) return "";
    if (chat.remitente?.id === profile.id) return chat.destinatario?.nombre || "Desconocido";
    return chat.remitente?.nombre || "Desconocido";
  };

  return (
    <div className="p-4 bg-white rounded shadow h-full overflow-y-auto">
      <h2 className="text-xl font-bold mb-4">Bienvenido, {profile?.nombre}</h2>

      <h3 className="text-md font-semibold mb-2">Chats existentes:</h3>
      <ul className="mb-4">
        {chats.length === 0 && <li>No tienes chats</li>}
        {chats.map((chat) => (
          <li key={chat.id} className="mb-1">
            <button
              className="text-blue-600 hover:underline"
              onClick={() => onSelectChat(chat)}
            >
              Chat con {getNombreOtro(chat)}
            </button>
          </li>
        ))}
      </ul>

      <h3 className="text-md font-semibold mb-2">Iniciar nuevo chat con:</h3>
      <ul>
        {perfilesDisponibles.length === 0 && <li>No hay usuarios disponibles</li>}
        {perfilesDisponibles.map((usuario) => (
          <li key={usuario.id} className="mb-1">
            <button
              className="text-green-600 hover:underline"
              onClick={() => iniciarChat(usuario.id)}
            >
              {usuario.nombre}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Profile;



