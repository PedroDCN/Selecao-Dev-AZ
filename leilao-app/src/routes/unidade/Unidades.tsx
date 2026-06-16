import { Button } from "@/components/ui/button";
import { UnidadeTable } from "./components/unidade-table";
import { useUnidades } from "@/hooks/queries";
import { useState } from "react";
import { CreateUnidadeDialog } from "./components/create-unidade-dialog";

export default function Unidades() {
  const [open, setOpen] = useState(false);
  const { data, isLoading, error } = useUnidades();

  if (isLoading) {
    return <p>Carregando...</p>;
  }

  if (error) {
    return <p>Erro ao carregar as unidades</p>;
  }

  return (
    <div className="max-w-275 mx-auto px-4 py-2 container space-y-4">
      <h1 className="text-2xl font-bold">Unidades</h1>
      <Button onClick={() => setOpen(true)}>Nova Unidade</Button>
      <div className="container">
        <UnidadeTable unidades={data ?? []} />
      </div>
      <CreateUnidadeDialog open={open} onOpenChange={setOpen} />
    </div>
  );
}
