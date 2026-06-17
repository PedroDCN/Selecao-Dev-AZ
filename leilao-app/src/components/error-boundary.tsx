import { Component, type PropsWithChildren } from "react";

type State = {
  hasError: boolean;
  error?: Error;
};

export class ErrorBoundary extends Component<PropsWithChildren, State> {
  state: State = {
    hasError: false,
  };

  static getDerivedStateFromError(error: Error): State {
    return {
      hasError: true,
      error,
    };
  }

  componentDidCatch(error: Error, errorInfo: React.ErrorInfo) {
    console.error("Application Error:", error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      return (
        <div className="flex min-h-screen items-center justify-center">
          <div className="space-y-4 text-center">
            <h1 className="text-2xl font-bold">Ocorreu um erro inesperado</h1>

            <p className="text-muted-foreground">Tente recarregar a página.</p>

            <button
              className="rounded border px-4 py-2"
              onClick={() => window.location.reload()}
            >
              Recarregar
            </button>
          </div>
        </div>
      );
    }

    return this.props.children;
  }
}
