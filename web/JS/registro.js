import { UsuarioPresentador } from "./UsuarioPresentador.js";

document.addEventListener("DOMContentLoaded", () => {
  const vista = {
    getNombre: () => document.getElementById("nombre").value.trim(),
    getEmail: () => document.getElementById("email").value.trim(),
    getContrasenia: () => document.getElementById("pass2").value.trim(),
    limpiarFormulario: () => document.querySelector("form").reset()
  };

  const presentador = new UsuarioPresentador(vista);

  document.querySelector("form").addEventListener("submit", (e) => {
    e.preventDefault();
    presentador.registrarUsuario();
  });
});
