package com.massafra.club.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ORCAMENTOPAG")
public class OrcamentoPag {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "REC")
    private Integer id;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "EMPRESA")
    private String empresa;
    @Column(name = "FORMA")
    private String forma;
    @Column(name = "VALOR", scale = 2)
    private BigDecimal valor;
    @Column(name = "PARCELAS")
    private BigDecimal parcelas;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "empresa", insertable = false, updatable = false)
    @JoinColumn(name = "numero", referencedColumnName = "numero", insertable = false, updatable = false)
    private Orcamento orcamentoCab;
}
