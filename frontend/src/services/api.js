const API_URL =
  (import.meta.env.VITE_API_URL || "http://localhost:8080/api").replace(/\/+$/, "");

export async function registrarUsuario(usuario) {
  let respuesta;

  try {
    respuesta = await fetch(`${API_URL}/usuarios/registro`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(usuario),
    });
  } catch {
    throw new Error("No se pudo conectar con el servidor. Intenta nuevamente.");
  }

  const contentType = respuesta.headers.get("content-type");

  const cuerpo = await respuesta.text();
  let datos = null;

  if (cuerpo && (contentType?.includes("application/json") || contentType?.includes("+json"))) {
    try {
      datos = JSON.parse(cuerpo);
    } catch {
      throw new Error("El servidor devolvió una respuesta inválida.");
    }
  }

  if (!respuesta.ok) {
    throw new Error(
      datos?.mensaje || datos?.message || datos?.detail || "No se pudo registrar al usuario."
    );
  }

  return datos;
}


export async function iniciarSesion(credenciales) {
  let respuesta;

  try {
    respuesta = await fetch(`${API_URL}/usuarios/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(credenciales),
    });
  } catch {
    throw new Error("No se pudo conectar con el servidor. Intenta nuevamente.");
  }

  const contentType = respuesta.headers.get("content-type");

  const cuerpo = await respuesta.text();
  let datos = null;

  if (cuerpo && (contentType?.includes("application/json") || contentType?.includes("+json"))) {
    try {
      datos = JSON.parse(cuerpo);
    } catch {
      throw new Error("El servidor devolvió una respuesta inválida.");
    }
  }

  if (!respuesta.ok) {
    throw new Error(
      datos?.mensaje || datos?.message || datos?.detail || "Credenciales incorrectas."
    );
  }

  return datos;
}