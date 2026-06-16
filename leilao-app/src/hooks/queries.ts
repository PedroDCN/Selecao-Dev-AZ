import { getUnidades } from "@/services/unidadeService";
import { useQuery } from "@tanstack/react-query";

export function useUnidades() {
  return useQuery({
    queryKey: ["unidades"],
    queryFn: getUnidades,
  });
}
