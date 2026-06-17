import { formatCurrency, formatDateTime } from "@/lib/utils";
import type { LeilaoType } from "@/schemas/leilao";

type Props = {
  leilao: LeilaoType;
};

export function LeilaoRow({ leilao }: Props) {
  return (
    <tr className="transition-colors hover:bg-slate-50">
      {/* <td>{leilao.id}</td> */}
      <td className="px-4 py-3 font-medium text-slate-900">
        {leilao.razaoSocial}
      </td>

      <td className="px-4 py-3 text-slate-600">
        {formatDateTime(leilao.inicioPrevisto)}
      </td>

      <td className="px-4 py-3 text-right font-medium text-green-700">
        {formatCurrency(leilao.valorTotal)}
      </td>
    </tr>
  );
}
