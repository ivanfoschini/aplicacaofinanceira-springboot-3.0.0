package br.ufscar.dc.latosensu.aplicacaofinanceira.repository;

import br.ufscar.dc.latosensu.aplicacaofinanceira.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
