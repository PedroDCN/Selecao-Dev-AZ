package br.com.selecao.locadora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeResponse {
    private Long  id;
    private String nome;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
