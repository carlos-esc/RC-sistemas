package com.carlosesc.recsystem.entity.assinatura;

import com.carlosesc.recsystem.entity.cliente.Cliente;
import com.carlosesc.recsystem.entity.servico.Servico;
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
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @CreatedDate
    private Date criacao;

    @NotBlank
    @LastModifiedDate
    private Date ultimaAlteracao;

    @NotNull
    private Integer aniversario;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "cliente_servico_fk"))
    private Servico servico;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "cliente_assinatura_fk"))
    private Cliente cliente;

    public Assinatura(Integer aniversario,
                      Servico servico,
                      Cliente cliente) {
        this.aniversario = aniversario;
        this.servico = servico;
        this.cliente = cliente;
        this.status = Status.ATIVO;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getAniversario() {
        return aniversario;
    }

    public Servico getServico() {
        return servico;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
