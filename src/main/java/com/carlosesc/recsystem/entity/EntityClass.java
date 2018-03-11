package com.carlosesc.recsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class EntityClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonIgnore
    @NotNull
    @CreatedDate
    private Date criacao;

    @JsonIgnore
    @NotNull
    @LastModifiedDate
    private Date ultimaAlteracao;

    public Long getId() {
        return id;
    }
}
