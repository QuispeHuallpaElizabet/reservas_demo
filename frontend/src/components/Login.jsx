import { useState } from "react";
import { iniciarSesion } from "../services/api";
import "../App.css";

const datosIniciales = {
  correo: "",
  contrasena: "",
};

function Login() {
  const [formulario, setFormulario] = useState(datosIniciales);
  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("");
  const [enviando, setEnviando] = useState(false);

  function manejarCambio(evento) {
    const { name, value } = evento.target;

    setFormulario({
      ...formulario,
      [name]: value,
    });
  }

  async function manejarEnvio(evento) {
    evento.preventDefault();
    if (enviando) return;

    setMensaje("");
    setTipoMensaje("");

    const credenciales = {
      correo: formulario.correo.trim(),
      contrasena: formulario.contrasena,
    };

    if (!credenciales.correo || !credenciales.contrasena) {
      setMensaje("Ingresa tu correo y contraseña.");
      setTipoMensaje("error");
      return;
    }

    try {
      setEnviando(true);

      const respuesta = await iniciarSesion(credenciales);

      // Guarda el token para usarlo en las siguientes peticiones
      if (respuesta?.token) {
        localStorage.setItem("token", respuesta.token);
      }

      setMensaje("Inicio de sesión exitoso.");
      setTipoMensaje("exito");
      setFormulario(datosIniciales);

      // Aquí podrías redirigir, por ejemplo:
      // window.location.href = "/dashboard";
    } catch (error) {
      setMensaje(error.message);
      setTipoMensaje("error");
    } finally {
      setEnviando(false);
    }
  }

  return (
    <main className="pagina-login">
      <section className="tarjeta-login">
        <header className="encabezado">
          <h1>Sportify</h1>
          <p>Ingresa con tu cuenta para continuar.</p>
        </header>

        <form onSubmit={manejarEnvio} aria-busy={enviando}>
          <div className="campo">
            <label htmlFor="correo">Correo electrónico</label>
            <input
              id="correo"
              name="correo"
              autoComplete="email"
              disabled={enviando}
              type="email"
              value={formulario.correo}
              onChange={manejarCambio}
              placeholder="nombre@correo.com"
              required
            />
          </div>

          <div className="campo">
            <label htmlFor="contrasena">Contraseña</label>
            <input
              id="contrasena"
              name="contrasena"
              autoComplete="current-password"
              disabled={enviando}
              type="password"
              value={formulario.contrasena}
              onChange={manejarCambio}
              placeholder="Ingresa tu contraseña"
              required
            />
          </div>

          <button type="submit" disabled={enviando}>
            {enviando ? "Ingresando..." : "Iniciar sesión"}
          </button>

          {mensaje && (
            <p
              className={`mensaje ${tipoMensaje}`}
              role={tipoMensaje === "error" ? "alert" : "status"}
            >
              {mensaje}
            </p>
          )}
        </form>

        <footer>
          ¿No tienes una cuenta? <a href="/registro">Regístrate</a>
        </footer>
      </section>
    </main>
  );
}

export default Login;


