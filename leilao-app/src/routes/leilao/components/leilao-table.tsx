import { LeilaoRow } from "./leilao-row";
import type { LeilaoType } from "@/schemas/leilao";

type Props = {
  leiloes: LeilaoType[];
};

export function LeilaoTable({ leiloes }: Props) {
  return (
    <table className="w-full">
      <thead>
        <tr className="flex justify-between">
          <th>Razão Social</th>
          <th>Início Previsto</th>
          <th>Valor Total</th>
        </tr>
      </thead>

      <tbody>
        {leiloes.map((l) => (
          <LeilaoRow key={l.id} leilao={l} />
        ))}
      </tbody>
    </table>
  );
}
