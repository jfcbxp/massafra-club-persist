package com.massafra.club.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ORCAMENTOITEM")
public class OrcamentoItem {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "REC")
    private Integer id;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "EMPRESA")
    private String empresa;
    @Column(name = "PRODUTO")
    private String produto;
    @Column(name = "DESCRICAOPRODUTO")
    private String descricaoProduto;
    @Column(name = "QUANTIDADE", scale = 2)
    private BigDecimal quantidade;
    @Column(name = "PRECO", scale = 2)
    private BigDecimal preco;
    @Column(name = "TOTAL", scale = 2)
    private BigDecimal total;
    @Column(name = "ESTOQUE_LOJA", scale = 2)
    private BigDecimal estoque;
    @Column(name = "DESCONTO", scale = 2)
    private BigDecimal desconto;
    @Column(name = "VALORDESCONTO", scale = 2)
    private BigDecimal valorDesconto;
    @Column(name = "TIPOENTREGA")
    private String tipoEntrega;
    @Column(name = "CODIGO_BARRAS")
    private String codigoBarras;
    @Column(name = "ARMAZEM")
    private String armazem;

    @Column(name = "PRECOTABELA", scale = 2)
    private BigDecimal precoTabela;

    @Column(name = "ITEM")
    private String item;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "empresa", insertable = false, updatable = false)
    @JoinColumn(name = "numero", referencedColumnName = "numero", insertable = false, updatable = false)
    private Orcamento orcamentoCab;

    @Transient
    public BigDecimal getTotalComDesconto() {
        return this.getPrecoTabela().multiply(this.getQuantidade()).subtract(this.getValorDesconto());
    }

    @Transient
    public BigDecimal getPrecoComDesconto() {
        return this.getTotalComDesconto().divide(this.getQuantidade(), 2, RoundingMode.HALF_UP);
    }

}
