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
public class Contato extends EntityClass {

    @NotBlank
    private String numero;

//    @NotNull
//    @ManyToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(foreignKey = @ForeignKey(name = "cliente_contato_fk"))
//    @JsonIgnore
//    private Cliente cliente;

    public Contato(@NotBlank String numero) {
        this.numero = numero;
    }
}
