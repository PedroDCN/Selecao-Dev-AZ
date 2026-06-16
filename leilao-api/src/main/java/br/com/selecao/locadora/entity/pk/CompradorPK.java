package br.com.selecao.locadora.entity.pk;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CompradorPK implements Serializable {
    private Long empresa;
    private Long leilao;
}