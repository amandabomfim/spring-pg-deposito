package br.com.fiap.springpgdeposito.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ITEM_ESTOCADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEstocado {
    @Id
    @Column(name = "ID_ITEM_ESTOCADO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITEM_ESTOCADO")
    @SequenceGenerator(name = "SQ_ITEM_ESTOCADO", sequenceName = "SQ_ITEM_ESTOCADO", allocationSize = 1)
    private Long id;

    private String numeroDeSerie;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "DEPOSITO", referencedColumnName = "ID_DEPOSITO", foreignKey = @ForeignKey(name = "FK_DEPOSITO_ITEM_ESTOCADO"))
    private Deposito deposito;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO_ITEM_ESTOCADO"))
    private Produto produto;

}


