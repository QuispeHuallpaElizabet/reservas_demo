import { Link } from "react-router-dom";
import { useState } from "react";
import { registrarUsuario } from "../services/api";
import "../App.css";


const datosIniciales = {
  nombres: "",
  apellidos: "",
  telefono: "",
  correo: "",
  contrasena: "",
  confirmarContrasena: "",
};

function Registro() {
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

    if (formulario.contrasena !== formulario.confirmarContrasena) {
      setMensaje("Las contraseñas no coinciden.");
      setTipoMensaje("error");
      return;
    }

    const usuario = {
      nombres: formulario.nombres.trim(),
      apellidos: formulario.apellidos.trim(),
      telefono: formulario.telefono.trim(),
      correo: formulario.correo.trim(),
      contrasena: formulario.contrasena,
    };

    if (!usuario.nombres || !usuario.apellidos) {
      setMensaje("Los nombres y apellidos no pueden contener solo espacios.");
      setTipoMensaje("error");
      return;
    }

    try {
      setEnviando(true);

      await registrarUsuario(usuario);

      setMensaje("Usuario registrado correctamente.");
      setTipoMensaje("exito");
      setFormulario(datosIniciales);
    } catch (error) {
      setMensaje(error.message);
      setTipoMensaje("error");
    } finally {
      setEnviando(false);
    }
  }

  return (
    <main className="pagina-registro">
      <section className="tarjeta-registro">
        <header className="encabezado">
          <h1>Sportify</h1>
          <p>Crea tu cuenta para empezar a gestionar.</p>
        </header>

        <form onSubmit={manejarEnvio} aria-busy={enviando}>
          <div className="campo">
            <label htmlFor="nombres">Nombres</label>
            <input
              id="nombres"
              name="nombres"
              autoComplete="given-name"
              disabled={enviando}
              type="text"
              value={formulario.nombres}
              onChange={manejarCambio}
              placeholder="Ingresa tus nombres"
              required
            />
          </div>

          <div className="campo">
            <label htmlFor="apellidos">Apellidos</label>
            <input
              id="apellidos"
              name="apellidos"
              autoComplete="family-name"
              disabled={enviando}
              type="text"
              value={formulario.apellidos}
              onChange={manejarCambio}
              placeholder="Ingresa tus apellidos"
              required
            />
          </div>

          <div className="campo">
            <label htmlFor="telefono">Teléfono</label>
            <input
              id="telefono"
              name="telefono"
              autoComplete="tel"
              disabled={enviando}
              type="tel"
              value={formulario.telefono}
              onChange={manejarCambio}
              placeholder="Ingresa tu teléfono"
              pattern="[0-9]{9}"
              title="El teléfono debe contener 9 números"
              required
            />
          </div>

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
              autoComplete="new-password"
              disabled={enviando}
              type="password"
              value={formulario.contrasena}
              onChange={manejarCambio}
              placeholder="Mínimo 8 caracteres"
              minLength="8"
              required
            />
          </div>

          <div className="campo">
            <label htmlFor="confirmarContrasena">
              Confirmar contraseña
            </label>

            <input
              id="confirmarContrasena"
              name="confirmarContrasena"
              autoComplete="new-password"
              disabled={enviando}
              type="password"
              value={formulario.confirmarContrasena}
              onChange={manejarCambio}
              placeholder="Repite tu contraseña"
              minLength="8"
              required
            />
          </div>

          <button type="submit" disabled={enviando}>
            {enviando ? "Registrando..." : "Registrarse"}
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
          ¿Ya tienes una cuenta? <Link to="/login">Inicia sesión</Link>
        </footer>
      </section>
    </main>
  );
}

export default Registro;
