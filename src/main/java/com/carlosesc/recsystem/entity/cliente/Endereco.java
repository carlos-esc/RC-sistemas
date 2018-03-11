package com.carlosesc.recsystem.entity.cliente;

import com.carlosesc.recsystem.entity.EntityClass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Endereco extends EntityClass {

    @NotNull
    private Integer numero;

    @NotBlank
    private String descricao;

    @NotBlank
    private String cep;

    public Endereco(Integer numero, String descricao, String cep) {
        this.numero = numero;
        this.descricao = descricao;
        this.cep = cep;
    }
}
