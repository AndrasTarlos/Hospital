package ch.bzz.hospital.annotation;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Client;
import ch.bzz.hospital.model.Equipment;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;


public class UniqueEquipmentNameValidator implements ConstraintValidator<UniqueEquipmentName, String> {
/*
    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {

        if (strings == null){
            return true;
        }

        HashSet<String> hashSet = new HashSet<>(strings);

        Boolean ret = false;

        if(strings.size() == hashSet.size()){
            ret = true;
        }

        return ret;

    }
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