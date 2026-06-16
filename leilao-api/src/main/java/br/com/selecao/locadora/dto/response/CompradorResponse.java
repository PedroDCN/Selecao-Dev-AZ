package br.com.selecao.locadora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompradorResponse {
    private Long empresa;
    private Long leilao;
}
