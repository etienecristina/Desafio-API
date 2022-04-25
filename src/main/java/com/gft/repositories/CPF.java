package com.gft.repositories;

import com.gft.validation.CpfValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

public interface CPF {
    @Documented
    @Constraint(validatedBy = CpfValidator.class)
    @Target({ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Cpf {

        String message() default "Documento Inválido";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
