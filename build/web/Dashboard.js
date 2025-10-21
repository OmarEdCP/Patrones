document.addEventListener("DOMContentLoaded", async () => {
  const lastUser = localStorage.getItem("lastUser");

  if (!lastUser) {
    alert("No hay usuario logueado. Redirigiendo al login...");
    window.location.href = "index.html";
    return;
  }

  const usuario = JSON.parse(lastUser);

  document.getElementById("nombre").value = usuario.nombreUsuario || "";
  document.getElementById("email").value = usuario.email || "";

  document.getElementById("userEmail").textContent = usuario.email || "";

  const formPerfil = document.getElementById("formPerfil");
  formPerfil.addEventListener("submit", async (e) => {
    e.preventDefault();

    // obtenemos los valores actuales del formulario
    const nuevoNombre = document.getElementById("nombre").value.trim();
    const nuevoEmail = document.getElementById("email").value.trim();
    const nuevaPass = document.getElementById("password").value.trim();

    // si el campo está vacío, conserva el valor anterior
    const updatedUser = {
      idUsuario: usuario.idUsuario,
      nombreUsuario: nuevoNombre || usuario.nombreUsuario,
      email: nuevoEmail || usuario.email,
      contrasenia: nuevaPass || usuario.contrasenia
    };

    try {
      const response = await fetch(
        "http://localhost:8080/ProyectoArquitectura/api/usuario/updateUsuario",
        {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: `datosUsuario=${encodeURIComponent(JSON.stringify(updatedUser))}`
        }
      );

      const data = await response.json();
 console.log("Dash", data);

            if (data.validate) {
        alert("Perfil actualizado correctamente");
        localStorage.setItem("lastUser", JSON.stringify(updatedUser));
      } else {
          console.log('No validate');
        alert(data.message);
      }
    } catch (error) {
      console.error("Error al actualizar perfil:", error);
      alert("No se pudo conectar con el servidor");
    }
  });

  // Cerrar sesión
  document.getElementById("logoutBtn").addEventListener("click", () => {
    if (confirm("¿Deseas cerrar sesión?")) {
      localStorage.removeItem("lastUser");
      window.location.href = "index.html";
    }
  });
});
