export class Usuario {
  constructor(idUsuario, nombreUsuario, contrasenia, email, estatus = 1) {
    this.idUsuario = idUsuario;
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
    this.email = email;
    this.estatus = estatus;
  }
}
