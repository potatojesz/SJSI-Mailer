package org.sjsi.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.sjsi.service.GenderPickerService;
import org.sjsi.service.enums.Gender;

public class StandardSexPickerServiceTest {

    private GenderPickerService genderPicker = new StandardGenderPickerService();

    @Test
    public void testNames() {
        Assert.assertEquals(Gender.MALE, genderPicker.predictGender("Kuba"));
        Assert.assertEquals(Gender.FEMALE, genderPicker.predictGender("Anna"));
        Assert.assertEquals(Gender.FEMALE, genderPicker.predictGender("Inez"));
    }
}
