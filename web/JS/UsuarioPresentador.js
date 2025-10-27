import { Usuario } from './UsuarioModelo.js';

export class UsuarioPresentador {
    constructor(vista) {
        this.vista = vista;
    }

    async registrarUsuario() {
        const nombre = this.vista.getNombre();
        const contrasenia = this.vista.getContrasenia();
        const email = this.vista.getEmail();

        if (!nombre || !contrasenia || !email) {
            alert("Por favor llena todos los campos");
            return;
        }

        const usuario = new Usuario(null,nombre, contrasenia, email);
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
    }

    cargarUsuario() {
        const lastUser = localStorage.getItem("lastUser");
        if (!lastUser) {
            alert("No hay usuario logueado. Redirigiendo al login...");
            window.location.href = "index.html";
            return null;
        }

        const usuario = JSON.parse(lastUser);
        this.vista.mostrarUsuario(usuario);
        return usuario;
    }

    async actualizarUsuario(usuario, nuevosDatos) {
        // crear nuevo objeto Usuario combinando datos actuales + nuevos
        const actualizado = new Usuario(
                usuario.idUsuario,
                nuevosDatos.nombreUsuario || usuario.nombreUsuario,
                nuevosDatos.contrasenia || usuario.contrasenia,
                nuevosDatos.email || usuario.email,
                usuario.estatus
                );

        try {
            const response = await fetch(
                    "http://localhost:8080/ProyectoArquitectura/api/usuario/updateUsuario",
                    {
                        method: "POST",
                        headers: {"Content-Type": "application/x-www-form-urlencoded"},
                        body: `datosUsuario=${encodeURIComponent(JSON.stringify(actualizado))}`
                    }
            );

            const data = await response.json();

            if (data.validate) {
                alert("Perfil actualizado correctamente");
                localStorage.setItem("lastUser", JSON.stringify(actualizado));
                this.vista.mostrarUsuario(actualizado);
            } else {
                alert(data.message || "Error al actualizar el usuario");
            }
        } catch (error) {
            console.error("Error al actualizar perfil:", error);
            alert("No se pudo conectar con el servidor");
        }
    }

    cerrarSesion() {
        if (confirm("¿Deseas cerrar sesión?")) {
            localStorage.removeItem("lastUser");
            window.location.href = "index.html";
        }
    }
    
    async loginUsuario(nombre, contrasenia) {
    if (!nombre || !contrasenia) {
      throw new Error("Por favor completa todos los campos.");
    }

    const loginResponse = await fetch(
      "http://localhost:8080/ProyectoArquitectura/api/login/validarLogin",
      {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `nombre=${encodeURIComponent(nombre)}&contrasenia=${encodeURIComponent(contrasenia)}`
      }
    );

    const loginData = await loginResponse.json();
    if (loginData.status !== "success") {
      throw new Error(loginData.message || "Credenciales incorrectas");
    }

    // 🔸 Obtener datos del usuario
    const userResponse = await fetch(
      `http://localhost:8080/ProyectoArquitectura/api/usuario/getByName?nombreUsuario=${encodeURIComponent(nombre)}`
    );

    const userData = await userResponse.json();
    if (!userData || !userData.idUsuario) {
      throw new Error("Usuario no encontrado en la base de datos.");
    }

    // Guardar en localStorage
    localStorage.setItem("lastUser", JSON.stringify(userData));

    return userData;
  }
  
  async getAllUsuarios() {
  try {
    const response = await fetch("http://localhost:8080/ProyectoArquitectura/api/usuario/getAllUsuario");
    const data = await response.json();

    if (!Array.isArray(data)) {
      alert("No se pudo obtener la lista de usuarios.");
      return;
    }

    // Mostrar los usuarios usando la vista
    this.vista.mostrarUsuarios(data);
  } catch (error) {
    console.error("Error al obtener usuarios:", error);
    alert("Error al conectar con el servidor.");
  }
}

}

