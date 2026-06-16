import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
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
import { useDeleteUnidade, useUpdateUnidade } from "@/hooks/mutations";
import type { unidadeType } from "@/schemas/unidade";

type Props = {
  unidade: unidadeType;
};

export function UnidadeRow({ unidade }: Props) {
  const [nome, setNome] = useState(unidade.nome);

  const updateMutation = useUpdateUnidade();
  const deleteMutation = useDeleteUnidade();

  return (
    <tr className="flex">
      <td>{unidade.id}</td>

      <td className="grow px-6">
        <Input value={nome} onChange={(e) => setNome(e.target.value)} />
      </td>

      <td className="flex gap-2">
        <Button
          onClick={() =>
            updateMutation.mutate({
              id: unidade.id,
              nome,
            })
          }
          disabled={updateMutation.isPending || deleteMutation.isPending}
        >
          {updateMutation.isPending ? "Salvando..." : "Salvar"}
        </Button>

        <AlertDialog>
          <AlertDialogTrigger asChild>
            <Button
              variant="destructive"
              disabled={updateMutation.isPending || deleteMutation.isPending}
            >
              Deletar
            </Button>
          </AlertDialogTrigger>

          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>Deletar unidade?</AlertDialogTitle>
            </AlertDialogHeader>

            <AlertDialogFooter>
              <AlertDialogCancel>Cancelar</AlertDialogCancel>

              <AlertDialogAction
                onClick={() => deleteMutation.mutate(unidade.id)}
                disabled={updateMutation.isPending || deleteMutation.isPending}
              >
                Confirmar
              </AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>

        {/* <Button onClick={() => deleteMutation.mutate(unidade.id)}>
          {deleteMutation.isPending ? "Deletando..." : "Deletar"}
        </Button> */}
      </td>
    </tr>
  );
}
