import { formatCurrency, formatDateTime } from "@/lib/utils";
import type { LeilaoType } from "@/schemas/leilao";

type Props = {
  leilao: LeilaoType;
};

export function LeilaoRow({ leilao }: Props) {
  return (
    <tr className="flex justify-between">
      {/* <td>{leilao.id}</td> */}
      <td>{leilao.razaoSocial}</td>
      <td>{formatDateTime(leilao.inicioPrevisto)}</td>
      <td>{formatCurrency(leilao.valorTotal)}</td>
    </tr>
  );
}
