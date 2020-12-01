////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.util.List;
import java.time.LocalTime;

public class Order {
    List<MenuItem> listaElementi; 
    User utente;
    LocalTime orarioOrdine;
    double price; 
    
    public Order(List<MenuItem> listaElementi, 
            User utente,
            LocalTime orarioOrdine,
            double price) {
        if(listaElementi.isEmpty()) {
            throw new IllegalArgumentException("Lista vuota");
        }
        if(utente == null) {
            throw new IllegalArgumentException("Utente non valido");
        }
        if(orarioOrdine == null) {
            throw new IllegalArgumentException("Orario nullo");
        }
        this.listaElementi = listaElementi; 
        this.utente = utente;
        this.orarioOrdine = orarioOrdine;
        this.price = price;
    }
    
    public double getPrice() {
        return price; 
    }
    
    public LocalTime getOrarioOrdine() {
        return orarioOrdine;
    }
    
    public User getUser() {
        return utente;
    }
    
    public List<MenuItem> getListaElementi(){
        return listaElementi;
    }
    
    public void setPrice(double newPrice) {
        price = newPrice;
    }
}

