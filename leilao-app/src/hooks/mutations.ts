import type { EmpresaInputType, EmpresaType } from "@/schemas/empresa";
import {
  createEmpresa,
  deleteEmpresa,
  updateEmpresa,
} from "@/services/empresaService";
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

export function useCreateEmpresa() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: createEmpresa,

    onSuccess() {
      queryClient.invalidateQueries({
        queryKey: ["empresas"],
      });
    },
  });
}

export function useUpdateEmpresa() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ id, body }: { id: number; body: EmpresaInputType }) =>
      updateEmpresa(id, body),

    onSuccess() {
      queryClient.invalidateQueries({
        queryKey: ["empresas"],
      });
    },
  });
}

export function useDeleteEmpresa() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: deleteEmpresa,

    onSuccess() {
      queryClient.invalidateQueries({
        queryKey: ["empresas"],
      });
    },
  });
}
