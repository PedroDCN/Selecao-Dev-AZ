package br.com.selecao.locadora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaResponse {
    private Long id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
