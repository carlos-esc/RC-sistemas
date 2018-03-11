package com.carlosesc.recsystem.entity.servico;

import com.carlosesc.recsystem.entity.EntityClass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Servico extends EntityClass implements Serializable {

    @NotNull
    private BigDecimal valor;

    @NotBlank
    private String descricao;

    public Servico(@NotNull BigDecimal valor, @NotBlank String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public Servico(Long id, @NotNull BigDecimal valor, @NotBlank String descricao) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
    }
}
