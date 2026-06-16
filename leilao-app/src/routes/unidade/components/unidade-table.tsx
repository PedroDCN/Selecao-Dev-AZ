import { UnidadeRow } from "./unidade-row";
import type { unidadeType } from "@/schemas/unidade";

type Props = {
  unidades: unidadeType[];
};

export function UnidadeTable({ unidades }: Props) {
  return (
    <table className="w-full">
      <thead>
        <tr className="flex justify-between">
          <th>ID</th>
          <th>Nome</th>
          <th>Ações</th>
        </tr>
      </thead>

      <tbody>
        {unidades.map((u) => (
          <UnidadeRow key={u.id} unidade={u} />
        ))}
      </tbody>
    </table>
  );
}
