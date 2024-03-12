package br.com.fiap.springpgdeposito.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_DEPOSITO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deposito {
    @Id
    @Column(name = "ID_DEPOSITO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEPOSITO")
    @SequenceGenerator(name = "SQ_DEPOSITO", sequenceName = "SQ_DEPOSITO", allocationSize = 1)
    private Long id;

    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ENDERECO", referencedColumnName = "ID_ENDERECO", foreignKey = @ForeignKey(name = "FK_ENDERECO_ITEM_ESTOCADO"))
    private Endereco endereco;
}
