package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;

public class MessageSenderImplTest {
    GeoService geoService;
    LocalizationService localizationService;
    MessageSender messageSender;
    Location loc;

    @BeforeEach
    void beforeEach() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
        System.out.println("Перед каждым тестом");
    }

    @Test
    void test_send_is_russia() {
        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(loc = new Location("Moscow", RUSSIA, "Lenina", 15));

        Mockito.when(localizationService.locale(loc.getCountry()))
                .thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP);

        String expected = "Добро пожаловать";
        String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_send_is_usa() {
        Mockito.when(geoService.byIp("96.69.123.321"))
                .thenReturn(loc = new Location("New York", USA, null,  0));

        Mockito.when(localizationService.locale(loc.getCountry()))
                .thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.69.123.321");

        String expected = "Welcome";
        String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);
    }
}
