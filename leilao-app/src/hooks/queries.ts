import { getEmpresa, getEmpresas } from "@/services/empresaService";
import { getLeiloes } from "@/services/leilaoService";
import { getUnidades } from "@/services/unidadeService";
import { useQuery } from "@tanstack/react-query";

export function useUnidades() {
  return useQuery({
    queryKey: ["unidades"],
    queryFn: getUnidades,
  });
}

export function useEmpresas() {
  return useQuery({
    queryKey: ["empresas"],
    queryFn: getEmpresas,
  });
}

export function useEmpresa(id?: number) {
  return useQuery({
    queryKey: ["empresa", id],
    queryFn: () => getEmpresa(id!),
    enabled: !!id,
    refetchOnWindowFocus: false,
    refetchOnReconnect: false,
  });
}

export function useLeiloes() {
  return useQuery({
    queryKey: ["leiloes"],
    queryFn: getLeiloes,
  });
}
