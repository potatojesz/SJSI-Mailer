package org.sjsi.service;

import org.sjsi.service.enums.Gender;

public interface GenderPickerService {

    Gender predictGender(String name);
}
