package me.dio.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.domain.model.enums.StatusLancamento;
import me.dio.domain.model.enums.TipoLancamento;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lancamento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "dia", nullable = false)
    private Integer dia;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;


    @Column(name = "data_lancamento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datalancamento;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro = LocalDate.now();

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @Column(name = "status", nullable = true)
    @Enumerated(EnumType.STRING)
    private StatusLancamento status;
}
