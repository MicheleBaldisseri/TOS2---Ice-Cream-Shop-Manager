////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Order;
import it.unipd.tos.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TakeAwayBillImplTest {
    
    private List<MenuItem> itemsOrdered;
    private TakeAwayBillImpl testBill; 
    private User user;
    private LocalTime oreSeiMezza;
    
    @Before
    public void setup() {
        
        itemsOrdered = new ArrayList<MenuItem>();
        testBill = new TakeAwayBillImpl();
        oreSeiMezza = LocalTime.of(18,30,00,00);
        user = new User("Lele97","Michele","Baldisseri",LocalDate.of(1997, 5, 12));
    }
    
    @Test
    public void calcoloDelTotaleTest(){
        
        itemsOrdered.add(new MenuItem( ItemType.Budino, "Budino Pinguino",3.00));
        itemsOrdered.add(new MenuItem( ItemType.Bevanda, "The", 2.50));
        itemsOrdered.add(new MenuItem( ItemType.Gelato, "Coppa Nafta", 5.00));
        
        assertEquals(10.5, testBill.getOrderPrice(itemsOrdered,user), 0.0);
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
    
    @Test
    public void totaleConScontoSulMenoCaroSePiùDiCinqueGelatiTest() {
        
        for(int i=0; i<6; i++) {
            itemsOrdered.add(new MenuItem( ItemType.Gelato, "Coppa Nafta",3.00));
        }       
        assertEquals(16.5, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }
    
    @Test
    public void totaleConScontoSeOltre50euroDiSpesaTest() {
        
        itemsOrdered.add(new MenuItem( ItemType.Gelato, "Torta Gelato",25.00));
        itemsOrdered.add(new MenuItem( ItemType.Gelato, "Torta Gelato",25.00));
        itemsOrdered.add(new MenuItem( ItemType.Budino, "Budino Pinguino",3.00));
        itemsOrdered.add(new MenuItem( ItemType.Bevanda, "The",2.00));
       
        assertEquals(49.5, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }
    
    @Test(expected=TakeAwayBillException.class)
    public void oltreTrentaElementiPerOrdineTest() {
        
        for(int i=0; i<35; i++) {
            itemsOrdered.add(new MenuItem( ItemType.Gelato, "Coppa Nafta",3.00));
        }
        
        testBill.getOrderPrice(itemsOrdered, user);
    }
    
    @Test
    public void totaleConCommissioneSeMenoDi10euroTest() {
        
        itemsOrdered.add(new MenuItem( ItemType.Budino, "Budino Pinguino",3.00));
        itemsOrdered.add(new MenuItem( ItemType.Bevanda, "The",2.00));
       
        assertEquals(5.5, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }
    
    @Test
    public void ordiniRegalatiAdUtentiMinorenniTest() {
        
        //lista con tutti gli ordini
        List<Order> ordini = new ArrayList<Order>();
        //itemsOrdered -> lista elementi in un ordine, ne creo una uguale per tutti gli ordini
        itemsOrdered.add(new MenuItem( ItemType.Budino, "Budino Pinguino",3.00));
        itemsOrdered.add(new MenuItem( ItemType.Bevanda, "The",2.00));
        //11 utenti minorenni
        String[] nomi =new String[]{"Mike", "Elton", "Adri", "Alby",
                        "Jon", "Bert", "Chri", "Luis", "Sal", "Eddy", "Astro"};
        User user = null;
        //inserisco 11 ordini
        for (int i = 0; i < 11; i++) {
            //nuovo utente minorenne
            user = new User(nomi[i]+"_test",nomi[i],"prova",LocalDate.of(2010, 5, 12));;
            //aggiungo il suo ordine
            ordini.add(new Order(itemsOrdered, user,  oreSeiMezza, testBill.getOrderPrice(itemsOrdered, user)));
        }
        
        List<Order> ordiniGratis = testBill.getFreeOrders(ordini);
        int numeroOrdiniRegalati = 0;

        for (Order ord : ordiniGratis) {
            if(ord.getPrice() == 0) {
                numeroOrdiniRegalati++;
            }
        }
        //controllo che siano stati regalati 10 ordini di minorenni nell'orario statbilito
        assertEquals(10,numeroOrdiniRegalati); 
    }
    
    //si assume che se non vengono effettuate almeno 10 ordinazioni non vengono regalati ordini
    @Test(expected=TakeAwayBillException.class)
    public void ordiniRegalatiSeMenoDi10ordiniTest() { 
        
        //lista con tutti gli ordini
        List<Order> ordini = new ArrayList<Order>();
        //itemsOrdered -> lista elementi in un ordine, ne creo una uguale per tutti gli ordini
        itemsOrdered.add(new MenuItem( ItemType.Bevanda, "The",2.00));
        //3 utenti minorenni
        String[] nomi =new String[]{"Mike", "Elton", "Adri"};
        User user = null;
        //inserisco 3 ordini
        for (int i = 0; i < 3; i++) {
            //nuovo utente minorenne
            user = new User(nomi[i]+"_test",nomi[i],"prova",LocalDate.of(2010, 5, 12));;
            //aggiungo il suo ordine
            ordini.add(new Order(itemsOrdered, user,  oreSeiMezza, testBill.getOrderPrice(itemsOrdered, user)));
        }
        
        testBill.getFreeOrders(ordini);
    }
}