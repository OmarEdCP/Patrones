// Archivo: JS/dashboard.js
import { UsuarioPresentador } from './UsuarioPresentador.js';

document.addEventListener("DOMContentLoaded", () => {
  const vista = {
    mostrarUsuario: (usuario) => {
      document.getElementById("nombre").value = usuario.nombreUsuario || "";
      document.getElementById("email").value = usuario.email || "";
      document.getElementById("userEmail").textContent = usuario.email || "";
    },
    obtenerDatosFormulario: () => ({
      nombreUsuario: document.getElementById("nombre").value.trim(),
      contrasenia: document.getElementById("password").value.trim(),
      email: document.getElementById("email").value.trim()
    })
  };

  const presentador = new UsuarioPresentador(vista);
  const usuario = presentador.cargarUsuario();

  // Al enviar el formulario
  document.getElementById("formPerfil").addEventListener("submit", (e) => {
    e.preventDefault();
    const nuevosDatos = vista.obtenerDatosFormulario();
    presentador.actualizarUsuario(usuario, nuevosDatos);
  });

  // Cerrar sesión
  document.getElementById("logoutBtn").addEventListener("click", () => {
    presentador.cerrarSesion();
  });
});
