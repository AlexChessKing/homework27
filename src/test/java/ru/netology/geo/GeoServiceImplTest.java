package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;

public class GeoServiceImplTest {
    @Test
    void test_init_by_ip() {
        GeoService geoService = new GeoServiceImpl();
        Assertions.assertEquals(RUSSIA, geoService.byIp("172.132.23.13").getCountry());
        Assertions.assertEquals(RUSSIA, geoService.byIp(MOSCOW_IP).getCountry());
        Assertions.assertEquals("Lenina", geoService.byIp("172.0.32.11").getStreet());
        Assertions.assertEquals("New York", geoService.byIp("96.132.23.13").getCity());
        Assertions.assertEquals(USA, geoService.byIp(NEW_YORK_IP).getCountry());
        Assertions.assertEquals(32, geoService.byIp("96.44.183.149").getBuiling());
        Assertions.assertNull(geoService.byIp(LOCALHOST).getStreet());
    }
}
