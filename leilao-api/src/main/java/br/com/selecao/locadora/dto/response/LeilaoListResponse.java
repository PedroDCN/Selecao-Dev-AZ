package br.com.selecao.locadora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeilaoListResponse {
    private Long id;
    private String razaoSocial;
    private LocalDateTime inicioPrevisto;
    private BigDecimal valorTotal;

}
