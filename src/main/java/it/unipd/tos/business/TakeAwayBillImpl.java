////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.Order;
import it.unipd.tos.model.ItemType;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class TakeAwayBillImpl implements TakeAwayBill {
    
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        double total = 0;
        double min = 1000;
        int gelati = 0;
        boolean notBevanda = false;
        
        if(itemsOrdered == null) {
            throw new TakeAwayBillException("Lista nulla");
        }

        if(itemsOrdered.isEmpty()) {
            throw new TakeAwayBillException("Lista ordini vuota");
        }
        
        //se l'ordine ha +30 elementi allora errore
        if(itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("Limite ordine superato");
        }
        
        for (MenuItem item : itemsOrdered) {
            double current = item.getPrice();
            
            if(!item.getType().equals(ItemType.Bevanda)) {
                notBevanda = true;
                if(item.getType().equals(ItemType.Gelato)) {
                    gelati++;
                }
            }

            if(current<min) {
                min=current;
            }
            total += current;
        }
        
        //sconto del 50% sul meno caro se +5 gelati
        if(gelati > 5) {
            total -= 0.5*min;
        }
        
        //sconto del 10% se totale>50€ di budini e gelati
        if(total>50 && notBevanda) {
            total -= total*0.1;
        }
        
        if(total<10) {
            total += 0.5;
        }
        
        return total;
    }
    
    public List<Order> getFreeOrders(List<Order> ordini) throws TakeAwayBillException {
        
        List<Order> ordiniGratis = new ArrayList<Order>();
        
        for (int i = 0; i < ordini.size(); i++) {
            
            if(ordini.get(i).getUser().getAge()<18 && //se minorenne
             ordini.get(i).getOrarioOrdine().isAfter(LocalTime.of(18,00,00,00)) && //dopo le 18
             ordini.get(i).getOrarioOrdine().isAfter(LocalTime.of(18,00,00,00))){ //prima delle 19
                
                ordiniGratis.add(ordini.get(i)); //ordine in regalo
            }
        }

        if(ordiniGratis.size() > 9){
            
            for(int i=0; i<10; i++) {
              //numero a caso tra 1 e numero ordini
              int randomIndex = (int)(ordiniGratis.size() * Math.random());
              if(ordiniGratis.get(randomIndex).getPrice() == 0) {
                  i--;
              }
              else {
              ordiniGratis.get(randomIndex).setPrice(0);
              }
            }
        }
        else {
            throw new TakeAwayBillException("Ordini insufficienti per regali");
        }
        
        return ordiniGratis;
    }
}