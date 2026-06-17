// pages/error-page.tsx

import { isRouteErrorResponse, useRouteError } from "react-router-dom";

export default function ErrorPage() {
  const error = useRouteError();

  if (isRouteErrorResponse(error)) {
    return (
      <div className="p-6">
        <h1>Erro {error.status}</h1>

        <p>{error.statusText}</p>
      </div>
    );
  }

  return (
    <div className="p-6">
      <h1>Algo deu errado</h1>
    </div>
  );
}
