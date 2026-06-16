package br.com.selecao.locadora.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lote")
public class Lote {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numerolote")
    private Long numeroLote;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private BigDecimal quantidade;
    @Column(name = "valorinicial", columnDefinition = "NUMERIC(16,2)")
    private BigDecimal valorInicial;

    @ManyToOne()
    @JoinColumn(name = "unidade",
            nullable = false,
            foreignKey = @ForeignKey(name = "unidade_lote_fk")
    )
    private Unidade unidade;

    @ManyToOne()
    @JoinColumn(
            name = "leilao",
            nullable = false,
            foreignKey = @ForeignKey(name = "leilao_lote_fk")
    )
    private Leilao leilao;

    @Column(name = "createdat", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updatedat", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
