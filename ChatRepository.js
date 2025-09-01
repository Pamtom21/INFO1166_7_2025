class ChatRepository {
  async save(mensaje) {
    throw new Error("Método save() debe ser implementado");
  }

  async findByChatId(chatId) {
    throw new Error("Método findByChatId() debe ser implementado");
  }

  async findBetweenUsers(user1, user2) {
    throw new Error("Método findBetweenUsers() debe ser implementado");
  }

  async updateMessage(idMensaje, data) {
    throw new Error("Método updateMessage() debe ser implementado");
  }

  async deleteMessage(idMensaje) {
    throw new Error("Método deleteMessage() debe ser implementado");
  }
}

module.exports = ChatRepository;
