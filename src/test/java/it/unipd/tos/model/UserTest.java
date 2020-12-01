////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Test;

public class UserTest {
    
    private User Michele = new User("Lele97","Michele","Baldisseri",LocalDate.of(1997, 5, 12));
    
    @Test(expected = IllegalArgumentException.class)
    public void costruttoreNicknameNulloTest() {
        new User(null,"Michele","Baldisseri",LocalDate.of(1997, 5, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreNomeNulloTest() {
        new User("Lele97",null,"Baldisseri",LocalDate.of(1997, 5, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreCognomeNulloTest() {
        new User("Lele97","Michele",null,LocalDate.of(1997, 5, 12));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void costruttoreDataNullaTest() {
        new User("Lele97","Michele","Baldisseri",null);
    }

    @Test
    public void getNameTest() {
        assertEquals(Michele.getName(), "Michele");
    }
    
    @Test
    public void getNicknameTest(){
        assertEquals(Michele.getNickname(), "Lele97");
    }
    
    @Test
    public void getSurnameTest() {
        assertEquals(Michele.getSurname(), "Baldisseri");
    }
    
    @Test
    public void getAgeTest() {
        assertEquals(Michele.getAge(), 23);  
    }
}