# React + Vite

## Registro de usuarios

`App.jsx` muestra `Registro.jsx`, que valida los campos y llama a `registrarUsuario` en `src/services/api.js`.

Para configurar el servidor, copia `.env.example` a `.env.local` y ajusta `VITE_API_URL`. Reinicia Vite después de cambiar esta variable. Por defecto se usa `http://localhost:8080/api`.

El frontend espera `POST /api/usuarios/registro` con `Content-Type: application/json` y este cuerpo:

```json
{
  "nombres": "Ana",
  "apellidos": "Diaz",
  "telefono": "987654321",
  "correo": "ana@example.com",
  "contrasena": "ejemplo123"
}
```

La confirmación de contraseña solo se valida en el formulario y no se envía. El formulario exige teléfono de 9 dígitos y contraseña de al menos 8 caracteres.

Se acepta una respuesta exitosa HTTP 2xx con JSON o cuerpo vacío. Para errores se muestra `mensaje`, `message` o `detail` de la respuesta JSON, o un mensaje genérico.

**Integración pendiente:** el backend del repositorio todavía no implementa este endpoint ni configura la conexión a PostgreSQL. El equipo de backend debe implementar el registro, validar los datos, guardar la contraseña mediante hash y permitir el origen del frontend mediante CORS. Debe confirmar también el nombre JSON `contrasena`: la columna SQL se llama `contraseña`, y su mapeo corresponde al backend. Hasta entonces no se puede comprobar el guardado real de usuarios.

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.
