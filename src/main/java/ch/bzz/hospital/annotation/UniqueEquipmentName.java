package ch.bzz.hospital.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEquipmentNameValidator.class)
@Target({ElementType.PARAMETER})
@Documented
public @interface UniqueEquipmentName {
    String message() default "multiple equipment have the same name: {value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload () default {};

}
