// infrastructure/repositories/PostulanteRepositoryMemory.js
const PostulanteRepository = require("../../domain/repositories/PostulanteRepository");

class PostulanteRepositoryMemory extends PostulanteRepository {
  constructor() {
    super();
    this.postulantes = new Map();
  }

  async save(postulante) {
    this.postulantes.set(postulante.rut, postulante);
    return postulante;
  }

  async findById(rut) {
    return this.postulantes.get(rut) || null;
  }

  async update(rut, data) {
    if (!this.postulantes.has(rut)) return null;
    const postulante = this.postulantes.get(rut);
    Object.assign(postulante, data);
    this.postulantes.set(rut, postulante);
    return postulante;
  }
}

module.exports = PostulanteRepositoryMemory;
