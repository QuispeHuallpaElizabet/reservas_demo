import Registro from "./components/Registro";
import Login from "./components/Login";

function App() {
  const ruta = window.location.pathname;

  if (ruta === "/login") {
    return <Login />;
  }

  return <Registro />;
}

export default App;