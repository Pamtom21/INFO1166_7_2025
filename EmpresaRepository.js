class EmpresaRepository {
  async save(empresa) {
    throw new Error("Método save() debe ser implementado");
  }

  async findById(id) {
    throw new Error("Método findById() debe ser implementado");
  }

  async update(id, data) {
    throw new Error("Método update() debe ser implementado");
  }

  async delete(id) {
    throw new Error("Método delete() debe ser implementado");
  }

  async findAll() {
    throw new Error("Método findAll() debe ser implementado");
  }
}

module.exports = EmpresaRepository;
