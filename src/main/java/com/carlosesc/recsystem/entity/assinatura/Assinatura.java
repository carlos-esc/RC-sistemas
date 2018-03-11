package com.carlosesc.recsystem.entity.assinatura;

import com.carlosesc.recsystem.entity.EntityClass;
import com.carlosesc.recsystem.entity.cliente.Cliente;
import com.carlosesc.recsystem.entity.servico.Servico;
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
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Assinatura extends EntityClass {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Integer aniversario;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "cliente_servico_fk"))
    private Servico servico;

    public Assinatura(Integer aniversario,
                      Servico servico) {
        this.aniversario = aniversario;
        this.servico = servico;
        this.status = Status.ATIVO;
    }
}
