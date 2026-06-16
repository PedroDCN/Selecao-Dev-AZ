import {
  createUnidade,
  deleteUnidade,
  updateUnidade,
} from "@/services/unidadeService";
import { useMutation, useQueryClient } from "@tanstack/react-query";

export function useCriaUnidade() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: createUnidade,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["unidades"] });
    },
  });
}

export function useUpdateUnidade() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ id, nome }: { id: number; nome: string }) =>
      updateUnidade(id, { nome }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["unidades"] });
    },
  });
}

export function useDeleteUnidade() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: deleteUnidade,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["unidades"] });
    },
  });
}
