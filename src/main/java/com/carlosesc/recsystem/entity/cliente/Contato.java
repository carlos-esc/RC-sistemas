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
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @CreatedDate
    private Date criacao;

    @NotBlank
    @LastModifiedDate
    private Date ultimaAlteracao;

    @NotBlank
    private String numero;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "cliente_contato_fk"))
    private Cliente cliente;

    public Contato(@NotBlank String numero) {
        this.numero = numero;
    }
}
