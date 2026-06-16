import { useLeiloes } from "@/hooks/queries";
import { LeilaoTable } from "./components/leilao-table";

export default function Leiloes() {
  const { data, isLoading, error } = useLeiloes();

  if (isLoading) {
    return <p>Carregando...</p>;
  }

  if (error) {
    return <p>Erro ao carregar as Empresas</p>;
  }
  return (
    <div className="max-w-275 mx-auto px-4 py-2 container space-y-4">
      <h1 className="text-2xl font-bold">Empresas</h1>
      <div className="container">
        <LeilaoTable leiloes={data ?? []} />
      </div>
    </div>
  );
}
