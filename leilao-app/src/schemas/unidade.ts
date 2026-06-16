import { z } from "zod";

export const unidadeSchema = z.object({
  nome: z
    .string()
    .min(1, "O nome é obrigatório")
    .max(128, "O nome deve ter no máximo 128 caracteres"),
});

export type unidadeInputType = z.infer<typeof unidadeSchema>;

export type unidadeType = unidadeInputType & { id: number };
