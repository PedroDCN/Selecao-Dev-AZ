import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import {
  empresaSchema,
  type EmpresaInputType,
  type EmpresaType,
} from "@/schemas/empresa";
import { useCreateEmpresa, useUpdateEmpresa } from "@/hooks/mutations";

type Props = {
  empresa?: EmpresaType;
  isEdit: boolean;
};

export default function EmpresaForm({ empresa, isEdit }: Props) {
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<EmpresaInputType>({
    resolver: zodResolver(empresaSchema),
    defaultValues: empresa
      ? {
          razaoSocial: empresa.razaoSocial,
          cnpj: empresa.cnpj,
          logradouro: empresa.logradouro ?? "",
          municipio: empresa.municipio ?? "",
          numero: empresa.numero ?? "",
          complemento: empresa.complemento ?? "",
          bairro: empresa.bairro ?? "",
          telefone: empresa.telefone ?? "",
          email: empresa.email,
          site: empresa.site ?? "",
          usuario: empresa.usuario,
          senha: empresa.senha ?? "",
        }
      : {
          razaoSocial: "",
          cnpj: "",
          logradouro: "",
          municipio: "",
          numero: "",
          complemento: "",
          bairro: "",
          telefone: "",
          email: "",
          site: "",
          usuario: "",
          senha: "",
        },
  });

  const createMutation = useCreateEmpresa();
  const updateMutation = useUpdateEmpresa();
  const navigate = useNavigate();

  async function onSubmit(values: EmpresaInputType) {
    if (isEdit && empresa) {
      await updateMutation.mutateAsync({
        id: empresa.id,
        body: values,
      });
    } else {
      await createMutation.mutateAsync(values);
    }
    navigate("/empresas");
  }

  return (
    <div className="mx-auto max-w-4xl px-4 py-2 space-y-6">
      <h1 className="text-2xl font-bold">
        {isEdit ? "Editar Empresa" : "Nova Empresa"}
      </h1>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
        <div className="grid grid-cols-2 gap-4">
          <div>
            <label>Razão Social *</label>
            <Input {...register("razaoSocial")} />
            {errors.razaoSocial && (
              <p className="text-sm text-red-500">
                {errors.razaoSocial.message}
              </p>
            )}
          </div>
          <div>
            <label>CNPJ *</label>
            <Input {...register("cnpj")} />
            {errors.cnpj && (
              <p className="text-sm text-red-500">{errors.cnpj.message}</p>
            )}
          </div>
        </div>

        <div className="grid grid-cols-3 gap-4">
          <div>
            <label>Email *</label>
            <Input type="email" {...register("email")} />
            {errors.email && (
              <p className="text-sm text-red-500">{errors.email.message}</p>
            )}
          </div>

          <div>
            <label>Telefone</label>
            <Input {...register("telefone")} />
          </div>

          <div>
            <label>Site</label>
            <Input {...register("site")} />
          </div>
        </div>

        <div>
          <label>Logradouro</label>
          <Input {...register("logradouro")} />
        </div>

        <div className="grid grid-cols-4 gap-4">
          <div>
            <label>Município</label>
            <Input {...register("municipio")} />
          </div>

          <div>
            <label>Número</label>
            <Input {...register("numero")} />
          </div>

          <div>
            <label>Bairro</label>
            <Input {...register("bairro")} />
          </div>

          <div>
            <label>Complemento</label>
            <Input {...register("complemento")} />
          </div>
        </div>

        <div className="grid grid-cols-2 gap-4">
          <div>
            <label>Usuário *</label>
            <Input {...register("usuario")} />
            {errors.usuario && (
              <p className="text-sm text-red-500">{errors.usuario.message}</p>
            )}
          </div>

          <div>
            <label>Senha</label>
            <Input {...register("senha")} />
          </div>
        </div>

        <div className="flex gap-2">
          <Button
            type="submit"
            disabled={
              isSubmitting ||
              createMutation.isPending ||
              updateMutation.isPending
            }
          >
            {createMutation.isPending || updateMutation.isPending
              ? "Salvando..."
              : "Salvar"}
          </Button>

          <Button
            type="button"
            variant="outline"
            onClick={() => navigate("/empresas")}
          >
            Cancelar
          </Button>
        </div>
      </form>
    </div>
  );
}
