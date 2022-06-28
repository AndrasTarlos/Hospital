package ch.bzz.hospital.annotation;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Equipment;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <h1>UniqueEquipmentNameValidator</h1>
 *
 * @author Andras Tarlos
 * @since 2022.06.28
 * @version 0.1
 *
 * a validator
 */
public class UniqueEquipmentNameValidator implements ConstraintValidator<UniqueEquipmentName, String> {
    /**
     *
     * @param s String
     * @param constraintValidatorContext ConstraintValidatorContext
     * @return boolean
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        for (Equipment e: DataHandler.getEquipmentList()) {
            if (e.getName().equals(s)) {
                return false;
            }
        }

        return true;
    }
}