package br.ufscar.dc.latosensu.aplicacaofinanceira.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CepValidator implements ConstraintValidator<Cep, String> {

    @Override
    public void initialize(Cep constraintAnnotation) {}

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep == null || cep.length() != 9 || !cep.matches("\\d{5}-\\d{3}")) {
            return false;
        }

        return true;
    }
}
