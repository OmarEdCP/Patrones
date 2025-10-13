document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const nombre = document.getElementById("nombre").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("pass2").value.trim();

    if (!nombre || !email || !password) {
      alert("Por favor, completa todos los campos.");
      return;
    }

    // Construimos el objeto del usuario
    const usuario = {
      nombreUsuario: nombre,
      email: email,
      contrasenia: password,
    };

    // Lo convertimos a JSON y lo mandamos como parte de un formulario
    const formData = new URLSearchParams();
    formData.append("datosUsuario", JSON.stringify(usuario));

    try {
      const response = await fetch("http://localhost:8080/ProyectoArquitectura/api/usuario/insertUsuario", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
        body: formData.toString(),
      });

      if (response.ok) {
        const data = await response.json();
        alert("Usuario registrado correctamente: " + data.nombreUsuario);
        form.reset();
      } else {
        const error = await response.text();
        alert("Error en el servidor: " + error);
      }
    } catch (err) {
      console.error("Error al conectar con el backend:", err);
      alert("No se pudo conectar con el servidor.");
    }
  });
});



