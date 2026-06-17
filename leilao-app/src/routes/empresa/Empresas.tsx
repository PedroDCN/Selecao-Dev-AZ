import { Button } from "@/components/ui/button";
import { useEmpresas } from "@/hooks/queries";
import { useNavigate } from "react-router-dom";
import { EmpresaTable } from "./components/empresa-table";

export default function Empresas() {
  const navigate = useNavigate();

  const { data, isLoading, error } = useEmpresas();

  if (isLoading) {
    return <p>Carregando...</p>;
  }

  if (error) {
    return <p>Erro ao carregar as Empresas</p>;
  }

  return (
    <div className="max-w-275 mx-auto px-4 py-2 container space-y-4 pb-32">
      <h1 className="text-2xl font-bold">Empresas</h1>
      <Button onClick={() => navigate("/empresa")}>Nova Empresa</Button>
      <div className="overflow-hidden overflow-x-auto border bg-white shadow-sm">
        <EmpresaTable empresas={data ?? []} />
      </div>
    </div>
  );
}
