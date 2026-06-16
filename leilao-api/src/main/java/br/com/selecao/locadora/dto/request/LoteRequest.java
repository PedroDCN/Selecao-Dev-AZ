package br.com.selecao.locadora.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteRequest {
    private Long numeroLote;
    private String descricao;
    private BigDecimal quantidade;
    private BigDecimal valorInicial;
    private Long unidade;
    private Long leilao;
}
