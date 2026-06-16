import type { LeilaoType } from "@/schemas/leilao";
import { api } from "./api";

export async function getLeiloes() {
  const response = await api.get<LeilaoType[]>("/leilao");
  return response.data;
}
