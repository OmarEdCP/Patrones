
import { UsuarioPresentador } from "./UsuarioPresentador.js";

document.addEventListener("DOMContentLoaded", () => {
  
  const vista = {
    mostrarUsuarios: (usuarios) => {
      const tbody = document.getElementById("usuariosBody");
      tbody.innerHTML = "";

      usuarios.forEach((u) => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
          <td>${u.idUsuario || "-"}</td>
          <td>${u.nombreUsuario || "-"}</td>
          <td>${u.email || "-"}</td>
          <td>${u.estatus || "-"}</td>
        `;
        tbody.appendChild(fila);
      });
    }
  };

  const presentador = new UsuarioPresentador(vista);
  const user = JSON.parse(localStorage.getItem("lastUser"));
  console.log(user);
  document.getElementById("userEmail").textContent = user.email || "";
  presentador.getAllUsuarios();

  document.getElementById("logoutBtn").addEventListener("click", () => {
    presentador.cerrarSesion();
  });
});
