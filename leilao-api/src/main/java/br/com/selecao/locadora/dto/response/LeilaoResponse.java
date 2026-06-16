package br.com.selecao.locadora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeilaoResponse {
    private Long id;
    private Long codigo;
    private String descricao;
    private Long vendedor;
    private LocalDateTime inicioPrevisto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
