package com.carlosesc.recsystem.entity.servico;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EqualsAndHashCode
@ToString
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private BigDecimal valor;

    @NotBlank
    private String descricao;

    @NotBlank
    @CreatedDate
    private Date criacao;

    @NotBlank
    @LastModifiedDate
    private Date ultimaAlteracao;

    public Servico(@NotNull BigDecimal valor, @NotBlank String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
