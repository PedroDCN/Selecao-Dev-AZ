package br.com.selecao.locadora.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leilao")
public class Leilao {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codigo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Long vendedor;
    @Column(name = "inicioprevisto", nullable = false)
    private LocalDateTime inicioPrevisto;

    @OneToMany(mappedBy = "leilao")
    @JsonIgnore
    private List<Lote> lotes = new ArrayList<>();

    @OneToMany(mappedBy = "leilao")
    @JsonIgnore
    private List<Comprador> compradores = new ArrayList<>();

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
