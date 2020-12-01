////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import java.util.List;

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
        
        //se spesa inferiore 10€ commissione di 50cent
        if(total<10) {
            total += 0.5;
        }
        
        return total;
    }
}