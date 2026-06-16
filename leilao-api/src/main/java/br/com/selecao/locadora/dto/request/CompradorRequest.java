package br.com.selecao.locadora.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompradorRequest {
    private Long empresa;
    private Long leilao;
}
