import type { EmpresaType } from "@/schemas/empresa";
import { EmpresaRow } from "./empresa-row";

type Props = {
  empresas: EmpresaType[];
};

export function EmpresaTable({ empresas }: Props) {
  return (
    <table className="w-full overflow-hidden border bg-white shadow-sm">
      <thead className="bg-slate-100">
        <tr>
          <th className="px-4 py-3 text-left text-sm font-semibold text-neutral-700">
            CNPJ
          </th>
          <th className="px-4 py-3 text-left text-sm font-semibold text-neutral-700">
            RazãoSocial
          </th>
          <th className="px-4 py-3 text-left text-sm font-semibold text-neutral-700">
            telefone
          </th>
          <th className="px-4 py-3 text-left text-sm font-semibold text-neutral-700">
            email
          </th>
          <th className="px-4 py-3 text-right text-sm font-semibold text-neutral-700">
            Ações
          </th>
        </tr>
      </thead>

      <tbody className="divide-y divide-neutral-200">
        {empresas.map((u) => (
          <EmpresaRow key={u.id} empresa={u} />
        ))}
      </tbody>
    </table>
  );
}
