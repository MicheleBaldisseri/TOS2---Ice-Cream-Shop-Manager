////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MenuItemTest {
    
    private MenuItem Budino;
    private MenuItem Bevanda;
    private MenuItem Gelato;
    
    @Before
    public void setup() {
        Budino = new MenuItem( ItemType.Budino, "Budino Pinguino",3.00);
        Bevanda = new MenuItem(ItemType.Bevanda, "The", 2.00);
        Gelato = new MenuItem( ItemType.Gelato, "Coppa Nafta", 4.00);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void costruttoreTipologiaElementoNulloTest() {
        new MenuItem(null, "The", 3.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreNomeElementoNulloTest() {
        new MenuItem(ItemType.Budino, null, 4D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttorePrezzoElementoNegativoTest() {
        new MenuItem(ItemType.Bevanda, "The", -3.00);
    }

    @Test
    public void getNameTest() {
        assertEquals("Budino Pinguino", Budino.getName());
        assertEquals("The", Bevanda.getName());
        assertEquals("Coppa Nafta", Gelato.getName());
    }

    @Test
    public void getPriceTest() {
        assertEquals(3.00, Budino.getPrice(), 0.0);
        assertEquals(2.00, Bevanda.getPrice(), 0.0);
        assertEquals(4.00, Gelato.getPrice(), 0.0);
    }

    @Test
    public void getTypeTest() {
        assertEquals(ItemType.Budino, Budino.getType());
        assertEquals(ItemType.Bevanda, Bevanda.getType());
        assertEquals(ItemType.Gelato, Gelato.getType());
    }
}