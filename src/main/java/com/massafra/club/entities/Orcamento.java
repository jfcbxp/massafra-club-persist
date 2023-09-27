package com.massafra.club.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ORCAMENTOCAB")
public class Orcamento {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "REC")
    private Integer id;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "EMPRESA")
    private String empresa;
    @Column(name = "VENDEDOR")
    private String vendedor;
    @Column(name = "NOMEVENDEDOR")
    private String nomeVendedor;
    @Column(name = "CLIENTE")
    private String cliente;
    @Column(name = "LOJA")
    private String loja;
    @Column(name = "NOMECLIENTE")
    private String nomeCliente;
    @Column(name = "STATUSORCAMENTO")
    private String statusOrcamento;
    @Column(name = "TIPOORCAMENTO")
    private String tipoOrcamento;
    @Column(name = "TOTALBRUTO", scale = 2)
    private BigDecimal totalBruto;
    @Column(name = "DESCONTO", scale = 2)
    private BigDecimal desconto;
    @Column(name = "VALORDESCONTO", scale = 2)
    private BigDecimal valorDesconto;
    @Column(name = "TOTALLIQUIDO", scale = 2)
    private BigDecimal totalLiquido;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @Column(name = "TOTALMERCADORIA", scale = 2)
    private BigDecimal totalMercadoria;
    @Column(name = "NOTA_FISCAL")
    private String notaFiscal;
    @Column(name = "DOCUMENTO_PEDIDO")
    private String documentoPedido;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orcamentoCab", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrcamentoItem> itens;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orcamentoCab", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrcamentoPag> pagamentos;

}
