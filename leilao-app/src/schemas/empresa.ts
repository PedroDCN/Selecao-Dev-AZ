import { z } from "zod";

export const empresaSchema = z.object({
  razaoSocial: z.string().min(1),
  cnpj: z.string().min(1),
  logradouro: z.string().optional(),
  municipio: z.string().optional(),
  numero: z.string().optional(),
  complemento: z.string().optional(),
  bairro: z.string().optional(),
  telefone: z.string().optional(),
  email: z.email(),
  site: z.string().optional(),
  usuario: z.string().min(1),
  senha: z.string().optional(),
});

export type EmpresaInputType = z.infer<typeof empresaSchema>;

export type EmpresaType = EmpresaInputType & { id: number };
