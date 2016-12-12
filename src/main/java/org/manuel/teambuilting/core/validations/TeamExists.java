package org.manuel.teambuilting.core.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Manuel on 12/12/2016.
 */
@Constraint(validatedBy = {TeamExistsValidator.class})
@Documented
@Target({ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TeamExists {

    String message() default "{The team does not exist}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
