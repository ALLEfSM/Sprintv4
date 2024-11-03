package br.com.fiap.insights.model;

import java.math.BigDecimal;
import java.util.List;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter@Setter
@NoArgsConstructor
@Table(name="PRODUTO")
public class Produto {

    @Id
    @GeneratedValue
    @Column(name="produto_id", nullable = false)
    private Long id;


    @Size(min = 3, max = 90)
    @Column(name="nome")
    @NotBlank(message = "nome é um campo obrigatório")
    private String nome;

    @Min(value = 0, message = "O valor não pode ser abaixo de zero")
    @Column(name="valor")
    @NotNull(message="valor é um campo obrigatório")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name="id_opiniao")
    private List<Opiniao> opiniaoLits;

}