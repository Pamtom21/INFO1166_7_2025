// infrastructure/repositories/ChatRepositoryMemory.js
const ChatRepository = require("../../domain/repositories/ChatRepository");

class ChatRepositoryMemory extends ChatRepository {
  constructor() {
    super();
    this.chats = new Map(); // key: idChat, value: array de mensajes
  }

  async save(mensaje) {
    if (!mensaje.idChat) throw new Error("El mensaje debe tener un idChat");

    if (!this.chats.has(mensaje.idChat)) {
      this.chats.set(mensaje.idChat, []);
    }

    this.chats.get(mensaje.idChat).push(mensaje);
    return mensaje;
  }

  async findByChatId(idChat) {
    return this.chats.get(idChat) || [];
  }

  async update(idChat, index, data) {
    // index: posición del mensaje dentro del chat
    if (!this.chats.has(idChat)) return null;
    const mensajes = this.chats.get(idChat);
    if (index < 0 || index >= mensajes.length) return null;
    Object.assign(mensajes[index], data);
    return mensajes[index];
  }
}

module.exports = ChatRepositoryMemory;
