package com.carlosesc.recsystem.entity.cliente;

import com.carlosesc.recsystem.entity.assinatura.Assinatura;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@EqualsAndHashCode
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    @NotBlank
    @CreatedDate
    private Date criacao;

    @NotBlank
    @LastModifiedDate
    private Date ultimaAlteracao;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "cliente")
    private List<Assinatura> assinaturas;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "enderecoFk"))
    private Endereco endereco;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Contato> contatos;

    public Cliente(String nome,
                   String cpf,
                   String email,
                   List<Assinatura> assinaturas,
                   List<Contato> contatos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.assinaturas = assinaturas;
        this.contatos = contatos;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public List<Assinatura> getAssinaturas() {
        return assinaturas;
    }

    public List<Contato> getContatos() {
        return contatos;
    }
}
