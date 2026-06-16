import { api } from "./api";
import type { unidadeInputType, unidadeType } from "@/schemas/unidade";

export async function getUnidades() {
  const response = await api.get<unidadeType[]>("/unidade");
  return response.data;
}

export async function getUnidade(id: number) {
  const response = await api.get<unidadeType>(`unidade/${id}`);
  return response.data;
}

export async function createUnidade(body: unidadeInputType) {
  const response = await api.post<unidadeType>("/unidade", body);
  return response.data;
}

export async function updateUnidade(id: number, body: unidadeInputType) {
  const response = await api.put<unidadeType>(`/unidade/${id}`, body);
  return response.data;
}

export async function deleteUnidade(id: number) {
  await api.delete(`/unidade/${id}`);
}
