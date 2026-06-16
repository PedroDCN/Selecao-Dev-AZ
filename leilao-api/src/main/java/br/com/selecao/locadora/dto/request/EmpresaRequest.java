package br.com.selecao.locadora.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequest {
    private String razaoSocial;
    private String cnpj;
    private String logradouro;
    private String municipio;
    private String numero;
    private String complemento;
    private String bairro;
    private String email;
    private String site;
    private String usuario;
    private String senha;
}
