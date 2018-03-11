package com.carlosesc.recsystem.entity.assinatura;

import com.carlosesc.recsystem.entity.EntityClass;
import com.carlosesc.recsystem.entity.servico.Servico;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
