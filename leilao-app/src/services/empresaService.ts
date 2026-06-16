import type { EmpresaInputType, EmpresaType } from "@/schemas/empresa";
import { api } from "./api";

export async function getEmpresas() {
  const response = await api.get<EmpresaType[]>("/empresa");
  return response.data;
}

export async function getEmpresa(id: number) {
  const response = await api.get<EmpresaType>(`/empresa/${id}`);
  return response.data;
}

export async function createEmpresa(body: EmpresaInputType) {
  const response = await api.post<EmpresaInputType>("/empresa", body);
  return response.data;
}

export async function updateEmpresa(id: number, body: EmpresaInputType) {
  const response = await api.put<EmpresaInputType>(`/empresa/${id}`, body);
  return response.data;
}

export async function deleteEmpresa(id: number) {
  await api.delete(`/empresa/${id}`);
}
