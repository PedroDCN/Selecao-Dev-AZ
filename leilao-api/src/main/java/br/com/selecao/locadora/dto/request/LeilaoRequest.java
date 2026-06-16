package br.com.selecao.locadora.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeilaoRequest {
    private Long codigo;
    private String descricao;
    private Long vendedor;
    private LocalDateTime inicioPrevisto;
}
