package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import main.User;
import main.Match;
import main.SingleElimEvent;
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

    @Test 
    public void test_getAllMatches() {
        User player1 = new User("Alice", "");
        User player2 = new User("Bob", "");
        User player3 = new User("Jake", "");
        User player4 = new User("JohnNNNNNNNNNnn", "");
        ArrayList<User> testPlayers = new ArrayList<>();
        testPlayers.add(player1);
        testPlayers.add(player2);
        testPlayers.add(player4);
        testPlayers.add(player3);
        Tournament tournament = new Tournament("Test Tournament");
        tournament.createEvent(SingleElimEvent.class, "MS");
        tournament.addPlayer("MS", testPlayers);
        tournament.createDraw("MS");

        ArrayList<Match> cur = tournament.getCurMatches();
        while (!cur.isEmpty()) {
            tournament.setWinner(cur.get(0), cur.get(0).getPlayer1());
            cur = tournament.getCurMatches();
        }

        tournament.printDraw("MS");

        ArrayList<Match> allMatches= player1.getAllMatches().get(tournament);
        for(Match x: allMatches) {
            System.out.println(x.toString());
        }

        tournament.createEvent(SingleElimEvent.class, "WS");
        tournament.addPlayer("WS", testPlayers);
        tournament.createDraw("WS");

        cur = tournament.getCurMatches();
        while (!cur.isEmpty()) {
            tournament.setWinner(cur.get(0), cur.get(0).getPlayer1());
            cur = tournament.getCurMatches();
        }

        tournament.printDraw("WS");

        allMatches= player1.getAllMatches().get(tournament);
        for(Match x: allMatches) {
            System.out.println(x.toString());
        }
    }


}
