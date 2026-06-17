import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Unidades from "./unidade/Unidades";
import Leiloes from "./leilao/Leiloes";
import Empresas from "./empresa/Empresas";
import EmpresaForm from "./empresa/EmpresaFormPage";
import ErrorPage from "@/components/error-page";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "empresas",
        element: <Empresas />,
      },
      {
        path: "empresa",
        element: <EmpresaForm />,
      },
      {
        path: "empresa/:id",
        element: <EmpresaForm />,
      },
      {
        path: "leiloes",
        element: <Leiloes />,
      },
      {
        path: "unidades",
        element: <Unidades />,
      },
    ],
  },
]);
