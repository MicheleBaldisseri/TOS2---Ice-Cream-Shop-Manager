////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TakeAwayBillImplTest {
    
    private List<MenuItem> itemsOrdered;
    private TakeAwayBillImpl testBill; 
    private User user; 
    
    @Before
    public void setup() {
        itemsOrdered = new ArrayList<MenuItem>();
        testBill = new TakeAwayBillImpl();
        user = new User("Lele97","Michele","Baldisseri",LocalDate.of(1997, 5, 12));
    }
    
    @Test
    public void calcoloDelTotaleTest(){
        
        itemsOrdered.add(new MenuItem( ItemType.Budino, "Budino Pinguino",3.00));
        itemsOrdered.add(new MenuItem( ItemType.Bevanda, "The", 2.00));
        itemsOrdered.add(new MenuItem( ItemType.Gelato, "Coppa Nafta", 4.00));
        
        assertEquals(9, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }
    
    @Test(expected=TakeAwayBillException.class)
    public void calcoloDelTotaleConListaOrdiniVuotaTest() {
        
        testBill.getOrderPrice(itemsOrdered, user);
    }
    
    @Test(expected=TakeAwayBillException.class)
    public void calcoloDelTotaleConListaOrdiniNullaTest() {
        itemsOrdered = null;
        testBill.getOrderPrice(itemsOrdered, user);
    }
    
}