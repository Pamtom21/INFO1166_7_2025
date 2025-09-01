// test/test.js

// Importar repositorios de infraestructura (rutas corregidas)
const PostulanteRepositoryMemory = require("../infrastructure/repositories/PostulanteRepositoryMemory");
const EmpresaRepositoryMemory = require("../infrastructure/repositories/EmpresaRepositoryMemory");
const ChatRepositoryMemory = require("../infrastructure/repositories/ChatRepositoryMemory");

// Importar casos de uso
const CrearPostulante = require("../application/usecases/CrearPostulante");
const CrearEmpresa = require("../application/usecases/CrearEmpresa");
const EnviarMensaje = require("../application/usecases/EnviarMensaje");

// Crear instancias de repositorios
const postulanteRepo = new PostulanteRepositoryMemory();
const empresaRepo = new EmpresaRepositoryMemory();
const chatRepo = new ChatRepositoryMemory();

// Crear instancias de casos de uso
const crearPostulante = new CrearPostulante(postulanteRepo);
const crearEmpresa = new CrearEmpresa(empresaRepo);
const enviarMensaje = new EnviarMensaje(chatRepo);

// Función de prueba
async function testSistema() {
  // 1️⃣ Crear un postulante
  const postulante = await crearPostulante.execute({
    rut: "12345678-9",
    nombre: "Jaziel",
    apellido: "Román",
    fechaNacimiento: "2005-08-31",
    direccion: "Calle Falsa 123",
    AFP: "Habitat"
  });
  console.log("✅ Postulante creado:", postulante);

  // 2️⃣ Crear una empresa
  const empresa = await crearEmpresa.execute({
    id: "E001",
    nombre: "Tech Corp",
    direccion: "Avenida Siempre Viva 742",
    rubro: "Tecnología",
    vacante: 2,
    cargo: "Desarrollador",
    descripcion: "Desarrollador Full Stack",
    fechaLimite: "2025-12-31"
  });
  console.log("✅ Empresa creada:", empresa);

  // 3️⃣ Enviar un mensaje de chat
  const mensaje = await enviarMensaje.execute({
    id: "M001",
    idChat: "C001",
    remitente: postulante.rut,
    destinatario: empresa.id,
    fechaHora: new Date(),
    mensaje: "Hola, estoy interesado en la vacante de Desarrollador."
  });
  console.log("✅ Mensaje enviado:", mensaje);

  // 4️⃣ Ver todos los mensajes en el chat
  const chat = await chatRepo.findByChatId("C001");
  console.log("✅ Chat completo:", chat);
}

// Ejecutar prueba
testSistema();
