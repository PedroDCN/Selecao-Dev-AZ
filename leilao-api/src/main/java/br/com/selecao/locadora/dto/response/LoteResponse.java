package br.com.selecao.locadora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteResponse {
    private Long id;
    private Long numeroLote;
    private String descricao;
    private BigDecimal quantidade;
    private BigDecimal valorInicial;
    private Long unidade;
    private Long leilao;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
