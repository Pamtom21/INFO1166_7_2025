class EnviarMensaje {
  constructor(chatRepository) {
    this.chatRepository = chatRepository;
  }

  async execute(mensajeData) {
    return await this.chatRepository.save(mensajeData);
  }
}

module.exports = EnviarMensaje;