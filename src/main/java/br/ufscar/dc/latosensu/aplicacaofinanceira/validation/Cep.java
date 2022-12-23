package br.ufscar.dc.latosensu.aplicacaofinanceira.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CepValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cep {
    public static final String DEFAULT_MESSAGE = "O CEP fornecido é inválido";
    
    String message() default DEFAULT_MESSAGE;
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
