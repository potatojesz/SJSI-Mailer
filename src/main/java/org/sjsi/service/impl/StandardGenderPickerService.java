package org.sjsi.service.impl;

import com.google.common.collect.ImmutableSet;
import org.sjsi.service.GenderPickerService;
import org.sjsi.service.enums.Gender;

public class StandardGenderPickerService implements GenderPickerService {
    static final ImmutableSet<String> MALE_NAMES_EXCEPTION = ImmutableSet.<String>builder()
            .add("Kuba")
            .add("Barnaba")
            .build();

    static final ImmutableSet<String>FEALE_NAMES_EXCEPTION = ImmutableSet.<String>builder()
            .add("Inez")
            .add("Neli")
            .add("Nicole")
            .build();

    @Override
    public Gender predictGender(String name) {
        if(new StringBuilder(name.toLowerCase()).reverse().substring(0, 1).equals("a")) {
            if(!MALE_NAMES_EXCEPTION.contains(name)) {
                return Gender.FEMALE;
            }
        }
        if(FEALE_NAMES_EXCEPTION.contains(name)) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }
}
