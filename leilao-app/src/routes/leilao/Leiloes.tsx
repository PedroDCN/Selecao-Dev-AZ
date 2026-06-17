import { useLeiloes } from "@/hooks/queries";
import { LeilaoTable } from "./components/leilao-table";

export default function Leiloes() {
  const { data, isLoading, error } = useLeiloes();

  if (isLoading) {
    return <p>Carregando...</p>;
  }

  if (error) {
    return <p>Erro ao carregar os Leilões</p>;
  }
  return (
    <div className="max-w-275 mx-auto px-4 py-2 container space-y-4 pb-32">
      <h1 className="text-2xl font-bold">Leilões</h1>
      <div className="overflow-hidden overflow-x-auto border bg-white shadow-sm">
        <LeilaoTable leiloes={data ?? []} />
      </div>
    </div>
  );
}
