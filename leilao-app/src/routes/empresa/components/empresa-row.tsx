import { Button } from "@/components/ui/button";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import { useDeleteEmpresa } from "@/hooks/mutations";
import type { EmpresaType } from "@/schemas/empresa";
import { useNavigate } from "react-router-dom";

type Props = {
  empresa: EmpresaType;
};

export function EmpresaRow({ empresa }: Props) {
  const navigate = useNavigate();
  const deleteMutation = useDeleteEmpresa();

  return (
    <tr className="transition-colors hover:bg-slate-50">
      <td className="px-4 py-3 text-slate-900">{empresa.cnpj}</td>
      <td className="px-4 py-3 text-slate-900">{empresa.razaoSocial}</td>
      <td className="px-4 py-3 text-slate-900">{empresa.telefone}</td>
      <td className="px-4 py-3 text-slate-900">{empresa.email}</td>

      <td className="px-4 py-3 flex gap-2 text-right">
        <Button
          onClick={() => navigate(`/empresa/${empresa.id}`)}
          disabled={deleteMutation.isPending}
        >
          Editar
        </Button>
        <AlertDialog>
          <AlertDialogTrigger asChild>
            <Button variant="destructive" disabled={deleteMutation.isPending}>
              Deletar
            </Button>
          </AlertDialogTrigger>

          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>Deletar empresa?</AlertDialogTitle>
            </AlertDialogHeader>

            <AlertDialogFooter>
              <AlertDialogCancel>Cancelar</AlertDialogCancel>

              <AlertDialogAction
                onClick={() => deleteMutation.mutate(empresa.id)}
                disabled={deleteMutation.isPending}
              >
                Confirmar
              </AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>
      </td>
    </tr>
  );
}
