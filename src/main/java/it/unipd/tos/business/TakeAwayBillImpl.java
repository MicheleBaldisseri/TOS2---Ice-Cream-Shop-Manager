////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import java.util.List;

public class TakeAwayBillImpl implements TakeAwayBill {
    
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        double total = 0;
        
        if(itemsOrdered == null) {
            throw new TakeAwayBillException("Lista nulla");
        }

        if(itemsOrdered.isEmpty()) {
            throw new TakeAwayBillException("Lista ordini vuota");
        }
        
        for (MenuItem item : itemsOrdered) {
            total += item.getPrice();   
        }
        
        return total;
    }
}