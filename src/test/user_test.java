package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import main.User; 
import main.Tournament; 

public class user_test {
    
    @Test
    public void test_createUser() {
        User person = new User("Nathan", "Peter1");
        User person2 = new User("John", "Peter1");
        assertEquals("Nathan", person.getName());
        assertEquals("John", person2.getName());
    }

    @Test
    public void test_getNameLength() {
        User person = new User("Nathan", "Peter1");
        User person2 = new User("John", "Peter");
        assertEquals(6, person.getNameLength());
        assertEquals(4, person2.getNameLength());
    }
    @Test
    public void test_checkPassword() {
        User person = new User("Nathan", "Peter1");
        User person2 = new User("John", "Peter");
        assertEquals(true, person.checkPassword("Peter1"));
        assertEquals(false, person2.checkPassword("Pete"));
    }

    @Test
    public void test_addTournament() {
        User person = new User("Nathan", "Peter1");
        User person2 = new User("John", "Peter");
        Tournament tourney = new Tournament("WA Open");
        Tournament tourney2 = new Tournament("OR Closed");
        person.addTournament(tourney);
        person2.addTournament(tourney);
        person2.addTournament(tourney2);
        assertEquals("WA Open", person.getTournament().get(0).getName());
        assertEquals("OR Closed", person2.getTournament().get(1).getName());
        assertTrue(person.playedTournament(tourney));
        assertFalse(person.playedTournament(tourney2));
        assertTrue(person2.playedTournament(tourney2));
    }


}
