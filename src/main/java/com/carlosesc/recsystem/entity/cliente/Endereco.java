package com.carlosesc.recsystem.entity.cliente;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@EqualsAndHashCode
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer numero;

    @NotBlank
    private String descricao;

    @NotBlank
    private String cep;

    @NotBlank
    @CreatedDate
    private Date criacao;

    @NotBlank
    @LastModifiedDate
    private Date ultimaAlteracao;

    public Endereco(Integer numero, String descricao, String cep) {
        this.numero = numero;
        this.descricao = descricao;
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCep() {
        return cep;
    }
}
