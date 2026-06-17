import { LeilaoRow } from "./leilao-row";
import type { LeilaoType } from "@/schemas/leilao";

type Props = {
  leiloes: LeilaoType[];
};

export function LeilaoTable({ leiloes }: Props) {
  return (
    <table className="w-full overflow-hidden border bg-white shadow-sm">
      <thead className="bg-slate-100">
        <tr>
          <th className="px-4 py-3 text-left text-sm font-semibold text-neutral-700">
            Razão Social
          </th>

          <th className="px-4 py-3 text-left text-sm font-semibold text-neutral-700">
            Início Previsto
          </th>

          <th className="px-4 py-3 text-right text-sm font-semibold text-neutral-700">
            Valor Total
          </th>
        </tr>
      </thead>

      <tbody className="divide-y divide-neutral-200">
        {leiloes.map((l) => (
          <LeilaoRow key={l.id} leilao={l} />
        ))}
      </tbody>
    </table>
  );
}
