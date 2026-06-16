package br.com.selecao.locadora.entity;

import br.com.selecao.locadora.entity.pk.CompradorPK;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comprador")
public class Comprador {
    @EmbeddedId
    private CompradorPK id;

    @ManyToOne()
    @MapsId("empresa")
    @JoinColumn(
            name = "empresa",
            foreignKey = @ForeignKey(name = "empresa_comp_fk")
    )
    private Empresa empresa;

    @ManyToOne()
    @MapsId("leilao")
    @JoinColumn(
            name = "leilao",
            foreignKey = @ForeignKey(name = "leilao_comp_fk")
    )
    private Leilao leilao;
}

