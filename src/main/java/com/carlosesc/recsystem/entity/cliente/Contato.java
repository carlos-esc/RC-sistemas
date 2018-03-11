package com.carlosesc.recsystem.entity.cliente;

import com.carlosesc.recsystem.entity.EntityClass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Contato extends EntityClass {

    @NotBlank
    private String numero;

    public Contato(@NotBlank String numero) {
        this.numero = numero;
    }
}
