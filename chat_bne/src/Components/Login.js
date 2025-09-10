import React, { useState } from 'react';
import axios from 'axios';
import './css/Login.css';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const [showRegister, setShowRegister] = useState(false);
  const [nombre, setNombre] = useState('');
  const [tipo, setTipo] = useState('');
  const [registerError, setRegisterError] = useState('');
  const [registerSuccess, setRegisterSuccess] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setIsSubmitting(true);
    try {
      const res = await axios.post('https://info1166-7-2025.onrender.com/auth/login', { email, password });
      alert('Token: ' + res.data.token);
    } catch (err) {
      if (err.response && err.response.data) {
        setError(err.response.data.message || 'Credenciales incorrectas');
      } else {
        setError('Error de conexión');
      }
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setRegisterError('');
    setRegisterSuccess('');

    // Validación previa
    if (!nombre || !email || !password || !tipo) {
      setRegisterError('Todos los campos son obligatorios');
      return;
    }

    setIsSubmitting(true);
    try {
      await axios.post('https://info1166-7-2025.onrender.com/auth/register', {
        nombre,
        email,
        password,
        tipo
      });

      setRegisterSuccess('Usuario registrado correctamente');
      setTimeout(() => setRegisterSuccess(''), 5000);

      setNombre('');
      setEmail('');
      setPassword('');
      setTipo('');
    } catch (err) {
      if (err.response && err.response.data) {
        setRegisterError(err.response.data.message || 'Error al registrar');
      } else {
        setRegisterError('Error de conexión');
      }
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div>
      {!showRegister ? (
        <form className="login-form" onSubmit={handleSubmit}>
          <h2>Login</h2>
          <input type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} required />
          <input type="password" placeholder="Contraseña" value={password} onChange={e => setPassword(e.target.value)} required />
          <button type="submit" disabled={isSubmitting}>Ingresar</button>
          {error && <p>{error}</p>}
          <button type="button" className="switch-btn" onClick={() => setShowRegister(true)}>
            ¿No tienes cuenta? Regístrate
          </button>
        </form>
      ) : (
        <form className="login-form" onSubmit={handleRegister}>
          <h2>Registro</h2>
          <input type="text" placeholder="Nombre" value={nombre} onChange={e => setNombre(e.target.value)} required />
          <input type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} required />
          <input type="password" placeholder="Contraseña" value={password} onChange={e => setPassword(e.target.value)} required />
          <select value={tipo} onChange={e => setTipo(e.target.value)} required>
            <option value="">Selecciona tipo de usuario</option>
            <option value="EMPRESA">Empresa</option>
            <option value="POSTULANTE">Postulante</option>
          </select>
          <button type="submit" disabled={isSubmitting}>Registrarse</button>
          {registerError && <p>{registerError}</p>}
          {registerSuccess && <p style={{ color: 'green' }}>{registerSuccess}</p>}
          <button type="button" className="switch-btn" onClick={() => setShowRegister(false)}>
            ¿Ya tienes cuenta? Inicia sesión
          </button>
        </form>
      )}
    </div>
  );
}

export default Login;