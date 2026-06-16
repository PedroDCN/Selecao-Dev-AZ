import type { EmpresaType } from "@/schemas/empresa";
import { EmpresaRow } from "./empresa-row";

type Props = {
  empresas: EmpresaType[];
};

export function EmpresaTable({ empresas }: Props) {
  return (
    <table className="w-full">
      <thead>
        <tr className="flex justify-between">
          <th>CNPJ</th>
          <th>RazãoSocial</th>
          <th>telefone</th>
          <th>email</th>
          <th>Ações</th>
        </tr>
      </thead>

      <tbody>
        {empresas.map((u) => (
          <EmpresaRow key={u.id} empresa={u} />
        ))}
      </tbody>
    </table>
  );
}
