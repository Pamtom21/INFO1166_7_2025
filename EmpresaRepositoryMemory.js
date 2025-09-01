// infrastructure/repositories/EmpresaRepositoryMemory.js
const EmpresaRepository = require("../../domain/repositories/EmpresaRepository");

class EmpresaRepositoryMemory extends EmpresaRepository {
  constructor() {
    super();
    this.empresas = new Map();
  }

  async save(empresa) {
    this.empresas.set(empresa.id, empresa);
    return empresa;
  }

  async findById(id) {
    return this.empresas.get(id) || null;
  }

  async update(id, data) {
    if (!this.empresas.has(id)) return null;
    const empresa = this.empresas.get(id);
    Object.assign(empresa, data);
    this.empresas.set(id, empresa);
    return empresa;
  }
}

module.exports = EmpresaRepositoryMemory;
