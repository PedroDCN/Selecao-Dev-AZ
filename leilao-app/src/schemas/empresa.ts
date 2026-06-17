import { z } from "zod";

export const empresaSchema = z.object({
  razaoSocial: z
    .string()
    .min(1, "campo obrigatório")
    .max(64, "máximo de 64 caracteres"),
  cnpj: z
    .string()
    .min(1, "campo obrigatório")
    .max(32, "máximo de 32 caracteres"),
  logradouro: z.string().max(64, "máximo de 64 caracteres").optional(),
  municipio: z.string().max(64, "máximo de 64 caracteres").optional(),
  numero: z.string().max(10, "máximo de 10 carcteres").optional(),
  complemento: z.string().max(64, "máximo de 64 caracteres").optional(),
  bairro: z.string().max(64, "máximo de 64 caracteres").optional(),
  telefone: z
    .string()
    .max(32, "máximo de 32 caracteres")
    .refine(
      (tel) => {
        const telefoneSoNumero = tel.replace(/\D/g, "");
        return (
          telefoneSoNumero.length >= 10 &&
          telefoneSoNumero.length <= 11 &&
          !telefoneSoNumero.startsWith("0")
        );
      },
      { error: "Número de telefone inválido. Deve conter 10 ou 11 dígitos." },
    )
    .optional(),
  email: z
    .email("email inválido")
    .min(1, "campo obrigatório")
    .max(254, "máximo de 254 caracteres"),
  site: z
    .url("site inválido, insira uma URL válida (ex: https://www.exemplo.com)")
    .max(254, "máximo de 254 caracteres")
    .optional(),
  usuario: z
    .string()
    .min(1, "campo obrigatório")
    .max(20, "máximo de 20 caracteres"),
  senha: z.string().max(128, "máximo de 128 caracteres").optional(),
});

export type EmpresaInputType = z.infer<typeof empresaSchema>;

export type EmpresaType = EmpresaInputType & { id: number };
