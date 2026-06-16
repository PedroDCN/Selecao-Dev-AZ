import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useCreateEmpresa, useUpdateEmpresa } from "@/hooks/mutations";
import { useEmpresa } from "@/hooks/queries";
import { empresaSchema, type EmpresaInputType } from "@/schemas/empresa";
import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect, useRef } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";

export default function EmpresaForm() {
  const navigate = useNavigate();
  const { id } = useParams();
  const initialized = useRef(false);
  const isEdit = id !== undefined;
  const empresaQuery = useEmpresa(id ? Number(id) : undefined);
  const updateMutation = useUpdateEmpresa();
  const createMutation = useCreateEmpresa();

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors, isSubmitting },
  } = useForm<EmpresaInputType>({
    resolver: zodResolver(empresaSchema),
    defaultValues: {
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

  useEffect(() => {
    if (empresaQuery.data && !initialized.current) {
      reset({
        razaoSocial: empresaQuery.data.razaoSocial,
        cnpj: empresaQuery.data.cnpj,
        logradouro: empresaQuery.data.logradouro ?? "",
        municipio: empresaQuery.data.municipio ?? "",
        numero: empresaQuery.data.numero ?? "",
        complemento: empresaQuery.data.complemento ?? "",
        bairro: empresaQuery.data.bairro ?? "",
        telefone: empresaQuery.data.telefone ?? "",
        email: empresaQuery.data.email,
        site: empresaQuery.data.site ?? "",
        usuario: empresaQuery.data.usuario,
        senha: empresaQuery.data.senha ?? "",
      });
      initialized.current = true;
    }
  }, [empresaQuery.data, reset]);

  async function onSubmit(values: EmpresaInputType) {
    if (isEdit) {
      await updateMutation.mutateAsync({
        id: Number(id),
        body: values,
      });
    } else {
      await createMutation.mutateAsync(values);
    }

    navigate("/empresas");
  }

  if (isEdit && empresaQuery.isLoading) {
    return <div className="p-12">Carregando empresa...</div>;
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
