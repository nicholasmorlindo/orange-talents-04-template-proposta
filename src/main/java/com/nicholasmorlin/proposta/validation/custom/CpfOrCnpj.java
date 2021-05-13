package com.nicholasmorlin.proposta.validation.custom;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Repeatable(CpfOrCnpj.List.class)
@Documented

@ConstraintComposition(CompositionType.OR)

@CPF
@CNPJ
public @interface CpfOrCnpj {

    String message() default "O CPF ou CNPJ está inválido";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        CpfOrCnpj[] value();
    }
}
