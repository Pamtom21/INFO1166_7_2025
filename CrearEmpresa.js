class CrearEmpresa {
  constructor(empresaRepository) {
    this.empresaRepository = empresaRepository;
  }

  async execute(empresaData) {
    return await this.empresaRepository.save(empresaData);
  }
}

module.exports = CrearEmpresa;