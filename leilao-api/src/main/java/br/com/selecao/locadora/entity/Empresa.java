package br.com.selecao.locadora.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "razaosocial", nullable = false)
    private String razaoSocial;
    @Column(unique = true, nullable = false)
    private String cnpj;

    private String logradouro;
    private String municipio;
    private String numero;
    private String complemento;
    private String bairro;

    private String telefone;
    @Column(nullable = false)
    private String email;
    private String site;
    @Column(unique = true, nullable = false)
    private String usuario;
    private String senha;

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
