import { UsuarioPresentador } from "./UsuarioPresentador.js";

document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  const presentador = new UsuarioPresentador();

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const nombre = document.getElementById("user").value.trim();
    const contrasenia = document.getElementById("pass").value.trim();

    try {
      const usuario = await presentador.loginUsuario(nombre, contrasenia);
      alert("Inicio de sesión exitoso.");
      window.location.href = "Dashboard.html";
    } catch (error) {
      alert(error.message || "Error al iniciar sesión.");
    }
  });
});
