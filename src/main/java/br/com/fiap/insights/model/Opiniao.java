package br.com.fiap.insights.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="OPINIAO")
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="opiniao_id")
    private Long id;

    @Column(name="comentario", nullable = false)
    @Size(max=300)
    @NotBlank(message = "Nome é obrigatório")
    private String comentario;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="id_produto")
    private Produto produto;

    
}
