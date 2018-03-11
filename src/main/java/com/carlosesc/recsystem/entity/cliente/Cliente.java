package com.carlosesc.recsystem.entity.cliente;

import com.carlosesc.recsystem.entity.EntityClass;
import com.carlosesc.recsystem.entity.assinatura.Assinatura;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Cliente extends EntityClass {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID", foreignKey = @ForeignKey(name = "cliente_assinatura_fk"))
    private List<Assinatura> assinaturas;

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
                   List<Assinatura> assinaturas,
                   List<Contato> contatos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.assinaturas = assinaturas;
        this.contatos = contatos;
        this.endereco = endereco;
    }
}
