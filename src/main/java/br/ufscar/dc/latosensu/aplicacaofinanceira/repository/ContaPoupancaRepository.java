package br.ufscar.dc.latosensu.aplicacaofinanceira.repository;

import br.ufscar.dc.latosensu.aplicacaofinanceira.model.Conta;
import br.ufscar.dc.latosensu.aplicacaofinanceira.model.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca, Long> {
    
    @Query("select conta from Conta conta where conta.numero = :numero")
    Conta findByNumero(@Param("numero") Integer numero);
    
    @Query("select conta from Conta conta where conta.numero = :numero and conta.id <> :id")
    Conta findByNumeroAndDifferentId(@Param("numero") Integer numero, @Param("id") Long id);   
}
