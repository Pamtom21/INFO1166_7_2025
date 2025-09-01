class PostulanteRepository {
  async save(postulante) {
    throw new Error("Método save() debe ser implementado");
  }

  async findById(rut) {
    throw new Error("Método findById() debe ser implementado");
  }

  async update(rut, data) {
    throw new Error("Método update() debe ser implementado");
  }

  async delete(rut) {
    throw new Error("Método delete() debe ser implementado");
  }

  async findAll() {
    throw new Error("Método findAll() debe ser implementado");
  }
}

module.exports = PostulanteRepository;
