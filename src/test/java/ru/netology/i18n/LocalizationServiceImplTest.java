package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {
    @Test
    void test_return_text() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(RUSSIA));
        Assertions.assertEquals("Welcome", localizationService.locale(GERMANY));
        Assertions.assertEquals("Welcome", localizationService.locale(USA));
        Assertions.assertEquals("Welcome", localizationService.locale(BRAZIL));
    }
}
