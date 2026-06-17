import { useEmpresa } from "@/hooks/queries";
import { useParams } from "react-router-dom";
import EmpresaForm from "./components/empresa-form";

export default function EmpresaFormPage() {
  const { id } = useParams();
  const isEdit = id !== undefined;
  const empresaQuery = useEmpresa(id ? Number(id) : undefined);

  if (isEdit && empresaQuery.isLoading) {
    return <div className="p-12">Carregando empresa...</div>;
  }

  if (isEdit && empresaQuery.error) {
    return <div className="p-12">Erro ao carregar empresa</div>;
  }

  return <EmpresaForm empresa={empresaQuery.data} isEdit={isEdit} />;
}
