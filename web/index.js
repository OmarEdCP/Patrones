document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");

  form.addEventListener("submit", async (e) => {
    e.preventDefault(); // Evita recargar la página

    // Obtener valores
    const nombre = document.getElementById("user").value.trim();
    const contrasenia = document.getElementById("pass").value.trim();

    if (!nombre || !contrasenia) {
      alert("Por favor completa todos los campos.");
      return;
    }

    try {
      // Llamar a tu servicio REST (ajusta la URL a tu backend)
      const response = await fetch("http://localhost:8080/ProyectoArquitectura/api/login/validarLogin", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `nombre=${encodeURIComponent(nombre)}&contrasenia=${encodeURIComponent(contrasenia)}`,
      });

      // Leer respuesta JSON
      const data = await response.json();

      // Analizar respuesta
      if (data.status === "success") {
        alert(data.message);
        // Redirigir si quieres
        window.location.href = "./Dashboard.html";
      } else if (data.status === "fail") {
        alert( data.message);
      } else {
        alert("️ Error: " + data.message);
      }

    } catch (error) {
      console.error("Error en la conexión:", error);
      alert("No se pudo conectar con el servidor.");
    }
  });
});


