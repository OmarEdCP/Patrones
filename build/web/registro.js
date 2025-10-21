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

    const usuario = {
      nombreUsuario: nombre,
      email: email,
      contrasenia: password
    };

    const formData = new URLSearchParams();
    formData.append("datosUsuario", JSON.stringify(usuario));

    try {
      const response = await fetch(
        "http://localhost:8080/ProyectoArquitectura/api/usuario/insertUsuario",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          body: formData.toString()
        }
      );

      const data = await response.json();
      console.log("Datos", data);

    if (data.validate) {
    alert(data.message);
} else {
    alert(data.message);
}

    } catch (err) {
      console.error("Error al conectar con el backend:", err);
      alert("No se pudo conectar con el servidor.");
    }
  });
});
