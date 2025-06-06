package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import main.User;
import main.Match;
import main.SingleElimEvent;
import main.Tournament; 

public class singleElimEvent_test {
    private Tournament tournament;
    private ArrayList<User> testPlayers;
    
    @Before
    public void setUp() {
        User player1 = new User("Alice", "");
        User player2 = new User("Bob", "");
        testPlayers = new ArrayList<>();
        testPlayers.add(player1);
        testPlayers.add(player2);
        tournament = new Tournament("Test Tournament");
        tournament.createEvent(SingleElimEvent.class, "MS");
        tournament.addPlayer("MS", testPlayers);
    }

    @Test
    public void test_addTournament() {
        Tournament newTournament = new Tournament("Summer Championship");
        assertEquals("Summer Championship", newTournament.getName());
        assertEquals("Test Tournament", tournament.getName());
    }

    @Test
    public void test_addPlayersToTournament() {
        assertEquals(tournament.getPlayers().get("Alice").getName(), "Alice");
        assertEquals(tournament.getPlayers().get("Bob").getName(), "Bob");
        assertEquals(tournament.getPlayers().get("Alice").getTournament().get(0), tournament);
    }

    @Test 
    public void test_createBracket() {
        Tournament tournament2 = new Tournament(null);
        tournament2.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 17; i++) {
            tournament2.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament2.createDraw("MS");
        assertEquals(tournament2.getAllMatches().size(), 31);

        Tournament tournament3 = new Tournament(null);
        tournament3.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 27; i++) {
            tournament3.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament3.createDraw("MS");
        assertEquals(tournament2.getAllMatches().size(), 31);

        Tournament tournament4 = new Tournament(null);
        tournament4.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 47; i++) {
            tournament4.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament4.createDraw("MS");
        assertEquals(tournament4.getAllMatches().size(), 63);

        Tournament tournament5 = new Tournament(null);
        tournament5.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 2; i++) {
            tournament5.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament5.createDraw("MS");
        assertEquals(tournament5.getAllMatches().size(), 1);

        Tournament tournament6 = new Tournament(null);
        tournament6.createEvent(SingleElimEvent.class, "MS");
        tournament6.addPlayer("MS", new User("Peter", null));
        tournament6.createDraw("MS");
        assertEquals(tournament6.getAllMatches().size(), 0);

        Tournament tournament7 = new Tournament(null);
        tournament7.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 65000; i++) {
            tournament7.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament7.createDraw("MS");
        assertEquals(tournament7.getAllMatches().size(), 65535);
        /* 
        Tournament tournament4 = new Tournament(null);
        for(int i = 0; i < 47; i ++) {
            tournament4.addPlayer(new User("Peter" + i, null));
        }
        tournament4.createBracket();
        assertEquals(tournament4.getAllMatches().size(), 63);
        */
    }
    
    @Test 
    public void test_printBracket(){
        Tournament tournament2 = new Tournament(null);
        tournament2.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 16; i++) {
            tournament2.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament2.createDraw("MS");
        tournament2.printDraw("MS");

        Tournament tournament3 = new Tournament(null);
        tournament3.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 24; i++) {
            tournament3.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament3.createDraw("MS");
        tournament3.printDraw("MS");

        Tournament tournament4 = new Tournament(null);
        tournament4.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 32; i++) {
            tournament4.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament4.createDraw("MS");
        tournament4.printDraw("MS");

        Tournament tournament5 = new Tournament(null);
        tournament5.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 3; i++) {
            tournament5.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament5.createDraw("MS");
        tournament5.printDraw("MS");

        Tournament tournament6 = new Tournament(null);
        tournament6.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 2; i++) {
            tournament6.addPlayer("MS", new User("Peter" + i, null));
        }
        tournament6.createDraw("MS");
        tournament6.printDraw("MS");

        Tournament tournament7 = new Tournament(null);
        tournament6.createEvent(SingleElimEvent.class, "MS");
        tournament6.createDraw("MS");
        tournament6.printDraw("MS");

        Tournament tournament8 = new Tournament(null);
        tournament6.createDraw("MS");
        tournament6.printDraw("MS");


    }


    @Test 
    public void test_printBracketMultipleEvents(){
        Tournament tournament2 = new Tournament(null);
        tournament2.createEvent(SingleElimEvent.class, "MS");
        tournament2.createEvent(SingleElimEvent.class, "WS");
        for (int i = 0; i < 21; i++) {
            tournament2.addPlayer("MS", new User("Peter"+i, null));
        }

        for (int i = 0; i < 7; i++) {
            tournament2.addPlayer("WS", new User("Peter"+i, null));
        }
        tournament2.createDraw("MS");
        tournament2.printDraw("MS");

        tournament2.createDraw("WS");
        tournament2.printDraw("WS");

        assertEquals(tournament2.getAllMatches().size(), 38);

    }

    @Test
    public void test_setWinner() {

        Tournament tournament3 = new Tournament(null);
        tournament3.createEvent(SingleElimEvent.class, "MS");
        for (int i = 0; i < 8; i++) {
            tournament3.addPlayer("MS",new User("peter"+i, null));
        }
        tournament3.createDraw("MS");
        ArrayList<Match> cur = tournament3.getCurMatches();
        while (!cur.isEmpty()) {
            tournament3.setWinner(cur.get(0), cur.get(0).getPlayer1());
            cur = tournament3.getCurMatches();
        }
        tournament3.printDraw("MS");
        
        Tournament tournament4 = new Tournament("WASHINGTON OPEN");
        tournament4.createEvent(SingleElimEvent.class, "MS");
        tournament4.createEvent(SingleElimEvent.class, "WS");

        for (int i = 0; i < 11; i++) {
            tournament4.addPlayer("MS", new User("peter"+i, null));
        }

        for (int i = 0; i < 8; i++) {
            tournament4.addPlayer("WS", new User("peter"+i, null));
        }
        tournament4.createDraw("MS");
        tournament4.createDraw("WS");

        cur = tournament4.getCurMatches();
        while (!cur.isEmpty()) {
            tournament4.setWinner(cur.get(0), cur.get(0).getPlayer1());
            cur = tournament4.getCurMatches();
        }
        tournament4.printDraw("MS");
        tournament4.printDraw("WS");

        

    }


   
}