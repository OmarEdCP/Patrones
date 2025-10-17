document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const nombre = document.getElementById("user").value.trim();
    const contrasenia = document.getElementById("pass").value.trim();

    if (!nombre || !contrasenia) {
      alert("Por favor completa todos los campos.");
      return;
    }

    try {
      // Validar login
      const loginResponse = await fetch(
        "http://localhost:8080/ProyectoArquitectura/api/login/validarLogin",
        {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: `nombre=${encodeURIComponent(nombre)}&contrasenia=${encodeURIComponent(contrasenia)}`
        }
      );

      const loginData = await loginResponse.json();

      if (loginData.status === "success") {
        alert(loginData.message);

        const userResponse = await fetch(
          `http://localhost:8080/ProyectoArquitectura/api/usuario/getByName?nombreUsuario=${encodeURIComponent(nombre)}`
        );

        const userData = await userResponse.json();
        console.log("Datos usuario:", userData);

        if (userData && userData.idUsuario) {
          localStorage.setItem("lastUser", JSON.stringify(userData));
          window.location.href = "./Dashboard.html";
        } else {
          alert("Usuario no encontrado en la base de datos.");
        }

      } else {
        alert(loginData.message || "Credenciales incorrectas");
      }

    } catch (error) {
      console.error("Error en la conexión:", error);
      alert("No se pudo conectar con el servidor.");
    }
  });
});
