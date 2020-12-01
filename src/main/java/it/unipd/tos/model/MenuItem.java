////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class MenuItem {
    ItemType itemType; 
    String name; 
    double price; 
    
    public MenuItem(ItemType itemType, String name, double price) {
        if(itemType == null) {
            throw new IllegalArgumentException("Elemento nullo");
        }
        if(name == null) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if(price <= 0) {
            throw new IllegalArgumentException("Prezzo non valido");
        }
        this.itemType = itemType; 
        this.name = name; 
        this.price = price; 
    }
    
    public double getPrice() {
        return price; 
    }
    
    public ItemType getType() {
        return itemType;
    }
    
    public String getName() {
        return name;
    }
    
}

