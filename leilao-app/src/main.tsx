import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import { router } from "./routes/router.tsx";
import "./index.css";
import { ErrorBoundary } from "./components/error-boundary.tsx";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
      refetchOnWindowFocus: false,
      throwOnError: true,
    },
  },
});

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <ErrorBoundary>
        <RouterProvider router={router} />
        <ReactQueryDevtools initialIsOpen={false} />
      </ErrorBoundary>
    </QueryClientProvider>
  </StrictMode>,
);
