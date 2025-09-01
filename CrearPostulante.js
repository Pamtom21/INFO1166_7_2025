class CrearPostulante {
  constructor(postulanteRepository) {
    this.postulanteRepository = postulanteRepository;
  }

  async execute(postulanteData) {
    return await this.postulanteRepository.save(postulanteData);
  }
}

module.exports = CrearPostulante;
