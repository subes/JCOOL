package org.ytoh.configurations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.ytoh.configurations.InRangeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * A property constraint restricting the property content to a certain range.
 *
 * @author ytoh
 */
@Constraint(validatedBy = InRangeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {

    /**
     * The lower bound of the range.
     *
     * @return double value representing the lower bound
     */
    double from();

    /**
     * The upper bound of the range.
     *
     * @return double value representing the upper bound
     */
    double to();

    Class<?>[] groups() default {};

    String message() default "property not in range <{from},{to}>";

	Class<? extends Payload>[] payload() default {};
}
