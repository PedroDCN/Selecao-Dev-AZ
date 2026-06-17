import { UnidadeRow } from "./unidade-row";
import type { unidadeType } from "@/schemas/unidade";

type Props = {
  unidades: unidadeType[];
};

export function UnidadeTable({ unidades }: Props) {
  return (
    <table className="w-full overflow-hidden border bg-white shadow-sm">
      <thead className="bg-slate-100">
        <tr>
          <th className="px-4 py-3 text-left text-sm font-semibold text-neutral-700">
            ID
          </th>
          <th className="px-4 py-3 text-left text-sm font-semibold text-neutral-700">
            Nome
          </th>
          <th className="px-4 py-3 text-right text-sm font-semibold text-neutral-700">
            Ações
          </th>
        </tr>
      </thead>

      <tbody className="divide-y divide-neutral-200">
        {unidades.map((u) => (
          <UnidadeRow key={u.id} unidade={u} />
        ))}
      </tbody>
    </table>
  );
}
