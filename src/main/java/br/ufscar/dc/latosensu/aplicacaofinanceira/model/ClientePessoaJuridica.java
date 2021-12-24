package br.ufscar.dc.latosensu.aplicacaofinanceira.model;

import br.ufscar.dc.latosensu.aplicacaofinanceira.validation.Cnpj;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "J")
public class ClientePessoaJuridica extends Cliente {
    
    @NotNull(message = "{clientePessoaJuridicaCnpjNaoPodeSerNulo}")
    @Cnpj(message = "{clientePessoaJuridicaCnpjInvalido}")
    @Column(name = "cnpj", length = 14)
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }    
}
