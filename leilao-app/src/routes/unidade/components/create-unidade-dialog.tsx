import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";

import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useCriaUnidade } from "@/hooks/mutations";
import { unidadeSchema, type unidadeInputType } from "@/schemas/unidade";

type Props = {
  open: boolean;
  onOpenChange: (open: boolean) => void;
};

export function CreateUnidadeDialog({ open, onOpenChange }: Props) {
  const createMutation = useCriaUnidade();

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<unidadeInputType>({
    resolver: zodResolver(unidadeSchema),
    defaultValues: {
      nome: "",
    },
  });

  async function onSubmit(values: unidadeInputType) {
    await createMutation.mutateAsync(values);
    reset();
    onOpenChange(false);
  }

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Nova Unidade</DialogTitle>
        </DialogHeader>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <Input placeholder="Nome" {...register("nome")} />

            {errors.nome && (
              <p className="text-sm text-red-500">{errors.nome.message}</p>
            )}
          </div>

          <Button type="submit" disabled={createMutation.isPending}>
            {createMutation.isPending ? "Adicionando..." : "Adicionar"}
          </Button>
        </form>
      </DialogContent>
    </Dialog>
  );
}
