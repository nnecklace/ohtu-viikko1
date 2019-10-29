package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanTilavuuden() {
        Varasto tyhja = new Varasto(-1.0);
        assertEquals(0.0, tyhja.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonAlkuTilvauudellaJaSaldolla() {
        Varasto v = new Varasto(10.0, 12.0);
        assertEquals(10.0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(10.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonNollaAlkuTilvauudellaJaNollaSaldolla() {
        Varasto v = new Varasto(-1.0, -1.0);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonAlkuTilvauudellaJaSallitullaSaldolla() {
        Varasto v = new Varasto(5.0, 3.0);
        assertEquals(5.0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(3.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVirheVarastoon() {
        varasto.lisaaVarastoon(-1.0);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaEnemmanKunMahtuu() {
        varasto.lisaaVarastoon(100);

        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void otaVirheellinenVarastosta() {
        double actual = varasto.otaVarastosta(-1.0);

        assertEquals(0.0, actual, vertailuTarkkuus);
    }

    @Test
    public void otaKaikkiMitaVoi() {
        double actualSaldo = varasto.getSaldo();
        double actualValue = varasto.otaVarastosta(1000.0);

        assertEquals(actualValue, actualSaldo, vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void testToString() {
        Varasto v = new Varasto(10);
        assertEquals("saldo  0.0, vielä tilaa 10.0", v.toString());
    }
}
