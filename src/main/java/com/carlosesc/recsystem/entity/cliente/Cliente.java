package com.carlosesc.recsystem.entity.cliente;

import com.carlosesc.recsystem.entity.EntityClass;
import com.carlosesc.recsystem.entity.assinatura.Assinatura;
import com.carlosesc.recsystem.entity.servico.Servico;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class Cliente extends EntityClass {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID", foreignKey = @ForeignKey(name = "cliente_assinatura_fk"))
    private List<Assinatura> assinaturas = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "cliente_endereco_fk"))
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENTE_ID", foreignKey = @ForeignKey(name = "cliente_contato_fk"))
    private List<Contato> contatos;

    public Cliente(String nome,
                   String cpf,
                   String email,
                   Endereco endereco,
                   List<Contato> contatos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.contatos = contatos;
        this.endereco = endereco;
    }

    public void assinar(Servico servico) {
        int aniversario = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        assinaturas.add(new Assinatura(aniversario, servico));
    }
}
