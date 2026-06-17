import type { EmpresaInputType } from "@/schemas/empresa";
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
import type { AxiosError } from "axios";
import { toast } from "sonner";

export function useCriaUnidade() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: createUnidade,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["unidades"] });
      toast.success("Unidade criada");
    },
    onError: (error: AxiosError) => {
      toast.error("Erro na criação da unidade: " + error?.response?.data);
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
      toast.success("Unidade editada");
    },
    onError: (error: AxiosError) => {
      toast.error("Erro na edição da unidade: " + error?.response?.data);
    },
  });
}

export function useDeleteUnidade() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: deleteUnidade,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["unidades"] });
      toast.success("Unidade deletada");
    },
    onError: (error: AxiosError) => {
      toast.error("Erro na deleção da unidade: " + error?.response?.data);
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
      toast.success("Empresa criada");
    },
    onError: (error: AxiosError) => {
      toast.error("Erro na criação da empresa: " + error?.response?.data);
    },
  });
}

export function useUpdateEmpresa() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ id, body }: { id: number; body: EmpresaInputType }) =>
      updateEmpresa(id, body),

    onSuccess(updatedEmpresa) {
      queryClient.invalidateQueries({
        queryKey: ["empresas"],
      });
      queryClient.setQueryData(["empresa", updatedEmpresa?.id], updatedEmpresa);
      toast.success("Empresa editada");
    },
    onError: (error: AxiosError) => {
      toast.error("Erro na edição da empresa: " + error?.response?.data);
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
      toast.success("Empresa deletada");
    },
    onError: (error: AxiosError) => {
      toast.error("Erro na deleção da empresa: " + error?.response?.data);
    },
  });
}
