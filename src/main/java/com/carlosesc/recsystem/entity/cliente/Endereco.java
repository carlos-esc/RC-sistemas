package com.carlosesc.recsystem.entity.cliente;

import com.carlosesc.recsystem.entity.EntityClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
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
