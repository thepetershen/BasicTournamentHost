package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SingleElimEvent implements Event {

    private String name; 
    private Map<String, User> players = new HashMap();
    private ArrayList<Match> allMatches = new ArrayList<>();
    private ArrayList<Match> curMatches = new ArrayList<>();
    private boolean eventBegan;
    private boolean eventEnded;

    private Match head;

    public SingleElimEvent(String name) {
        this.name = name;
        eventBegan = false;
        eventEnded = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createDraw() {
        if (eventBegan) {
            System.out.println("Sorry this event has begun already");
            return;
        }

        if (players.size() == 0 || players.size() == 1) {
            System.out.println("There are not enough players within this tournament");
            return;
        }
        ArrayList<ArrayList<User>> seeding = createInitialMatches(players);
        allMatches = new ArrayList<>();
        curMatches = new ArrayList<>();

        head = new Match(null, null);
        Queue<Match> toExpand = new LinkedList<>();
        toExpand.add(head);
        allMatches.add(head);
        int count = 2;
        while (!(isPowerOfTwo(count) && count >= players.size())) {
            Match current = toExpand.poll();
            current.setPrevMatch1(new Match(null, null));
            current.setPrevMatch2(new Match(null, null));
            allMatches.add(current.getPrevMatch1());
            allMatches.add(current.getPrevMatch2());
            toExpand.add(current.getPrevMatch1());
            toExpand.add(current.getPrevMatch2());
            count += 2;
        }

        for (int i = 0; i <= allMatches.size()/2; i++) {
            curMatches.add(allMatches.get(allMatches.size() - 1 - i));
        }

        for (int i = 0; i < seeding.get(0).size(); i++) {
            if (i% 2 == 1) { //this is just to alternate, creating a cool pattern
                curMatches.get(i).setPlayer1(seeding.get(0).get(i));
                curMatches.get(i).setPlayer2(seeding.get(1).get(i));
            } else {
                curMatches.get(i).setPlayer1(seeding.get(1).get(i));
                curMatches.get(i).setPlayer2(seeding.get(0).get(i));
            }

        }
        ArrayList<Match> x = new ArrayList<>(curMatches);
        for (Match cur: x) {
            setWinner(cur);
        }
        
    }
    //helper method for createBracket for readability
    private boolean isPowerOfTwo(int count) {
        return (Math.log(count) / Math.log(2) % 1 == 0);
    }


    //creates the initial matches. First shuffles the players, then creates a list of players that will not be opponents. Then, it creates the matches, alternating between the two lists.

    private ArrayList<ArrayList<User>> createInitialMatches(Map<String, User> players) {
        ArrayList<User> nonOpponentPlayers = new ArrayList<>();
        ArrayList<User> copyOfPlayers = new ArrayList<>(players.values());
        Collections.shuffle(copyOfPlayers);
        int x = highestPowerOfTwo(copyOfPlayers.size());
        for (int i = 0; i < x; i++) {
            nonOpponentPlayers.add(copyOfPlayers.get(0));
            copyOfPlayers.remove(0);
        }

        while (copyOfPlayers.size() != nonOpponentPlayers.size()) {
            copyOfPlayers.add(null);
        }
        Collections.shuffle(copyOfPlayers);
        /*  
        ArrayList<Match> matches = new ArrayList<>();
        for (int i = 0; i < nonOpponentPlayers.size(); i++) {
            if (i%2 == 0) matches.add(new Match(nonOpponentPlayers.get(i), copyOfPlayers.get(i))); //this is just to alternate, creating a cool pattern
            else matches.add(new Match(copyOfPlayers.get(i), nonOpponentPlayers.get(i)));
        }
        */

        ArrayList<ArrayList<User>> answer = new ArrayList<>();
        answer.add(nonOpponentPlayers);
        answer.add(copyOfPlayers);
        return answer;
        
    }

    private int highestPowerOfTwo(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Input must be greater than 0");
        }
        int power = 1;
        while (power < n) {
            power *= 2; // Equivalent to power *= 2
        }
        return power/2; // Divide by 2 to get the highest power of two less than or equal to n
    }

    private void setWinner(Match match) {


        Match nextMatch = getNextMatch(match, head);
        if (!curMatches.contains(match)) {
            System.out.println("This match is not currently being played");
            return;
        }

        if (match.getPlayer1() != null && match.getPlayer2() != null) return;
        

        if (match.getPlayer1() == null && match.getPlayer2() == null) return;

        User winner;
        if (match.getPlayer1() != null) winner= match.getPlayer1();
        else winner = match.getPlayer2();

        match.setWinner(winner);

        if (nextMatch != null) {
            if (nextMatch.getPrevMatch1() == match) {
                nextMatch.setPlayer1(winner);
            } else {
                nextMatch.setPlayer2(winner);
            }

            

        }  
        curMatches.remove(match);

        if (nextMatch != null && nextMatch.getPlayer1() != null && nextMatch.getPlayer2() != null) {
            curMatches.add(nextMatch);
        }



    }

    @Override
    //prints the bracket.
    public void printDraw() {
        if (head == null) {
            System.out.println("No matches have been created yet");
            return;
        }
        System.out.println(name);
        printBracketRecursive(head, getDepth(head));
    }

    /*
     * This is essentially and in-order print of a tree, using recursion. Depth measures how far the node is from the bottom, creating a nice spacing. 
     * The twist is that if the node is a leaf node, it has to print both the players and the winner, not just the winner.
     */

    private void printBracketRecursive(Match node, int depth) {
        
        String spacing = "                          ";

        if (node.getPrevMatch1() != null) {
            printBracketRecursive(node.getPrevMatch1(), depth - 1);
        } else if (node.getPlayer1() == null) {
            System.out.print("BYE");
            System.out.println("-".repeat(spacing.length() - "BYE".length() + 1));
        } else {
             System.out.print(node.getPlayer1().getName());
            System.out.println("-".repeat(spacing.length() - node.getPlayer1().getName().length() + 1));
        }

        if(node.getWinner() == null) {
            System.out.print(spacing.repeat(depth));
            System.out.print("|" + "_________");
            if (node != head) System.out.print("-".repeat(spacing.length() - "_________".length()));
            System.out.println();
        } else {
            System.out.print( spacing.repeat(depth));
            System.out.print("|" + node.getWinner().getName());
            if (node != head) System.out.print("-".repeat(spacing.length() - node.getWinner().getName().length()));
            System.out.println();

        }

        if (node.getPrevMatch2() != null) {
            printBracketRecursive(node.getPrevMatch2(), depth - 1);
        } else if (node.getPlayer2() == null) {
            System.out.print("BYE");
            System.out.println("-".repeat(spacing.length() - "BYE".length() + 1));
        } else {
            System.out.print(node.getPlayer2().getName());
            System.out.println("-".repeat(spacing.length() - node.getPlayer2().getName().length() + 1));
            
        }

    }

    //helper method for printBracket for calculating depth
    private int getDepth(Match head) {
        return getDepth(head, new HashSet<>());
    }
    
    private int getDepth(Match head, Set<Match> visited) {
        if (head == null) {
            return 0;
        }
        if (visited.contains(head)) {
            throw new IllegalStateException("Circular reference detected in the match tree");
        }
        visited.add(head);
        return 1 + getDepth(head.getPrevMatch1(), visited);
    }

    @Override
    public void setWinner(Match match, User winner) {
        Match nextMatch = getNextMatch(match, head);
        if (!curMatches.contains(match)) {
            System.out.println("This match is not currently being played");
            return;
        }
        if (match.getPlayer1() != winner && match.getPlayer2() != winner) {
            System.out.println("This player is not in this match");
            return;
        }

        if (eventEnded) {
            System.out.println("Event has ended");
            return;
        }

        eventBegan = true;


        match.setWinner(winner);
        curMatches.remove(match);
        if (nextMatch != null) {
            if (nextMatch.getPrevMatch1() == match) {
                nextMatch.setPlayer1(winner);
            } else {
                nextMatch.setPlayer2(winner);
            }
        } else {
            eventEnded = true;
        }

        if (nextMatch != null && nextMatch.getPlayer1() != null && nextMatch.getPlayer2() != null) {
            curMatches.add(nextMatch);
        }
    }

    @Override
    public ArrayList<Match> getAllMatches() {
        return allMatches;
    }

    @Override
    public ArrayList<Match> getCurMatches() {
        return curMatches;
    }

    @Override
    public void addPlayer(User toAdd) {
        if (eventBegan){
            System.out.println("Sorry this event has begun already");
            return;
        }
        if (!players.containsKey(toAdd.getName())) players.put(toAdd.getName(), toAdd);
    }

    @Override
    public Map<String, User>getPlayers() {
        return players;
    }

    public Match getHead() {
        return head;
    }

    private Match getNextMatch(Match desired, Match current) {
        if (current.getPrevMatch1() == desired || current.getPrevMatch2() == desired) {
            return current;
        } else if (current.getPrevMatch1() != null && current.getPrevMatch2() != null) {
            if (getNextMatch(desired, current.getPrevMatch1()) != null) return getNextMatch(desired, current.getPrevMatch1());
            if (getNextMatch(desired, current.getPrevMatch2()) != null) return getNextMatch(desired, current.getPrevMatch2());
        }
        return null;
    }

}
