package main;
//hi
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tournament {
    private String name;
    private Map<String, Event> allEvents= new HashMap<>();
    private ArrayList<Class<? extends Event>> possibleEvents = new ArrayList<>();



    public Tournament(String name) {
        this.name = name;
        this.possibleEvents.add(SingleElimEvent.class);
    }

    public Map<String, User> getPlayers() {
        Map<String, User> answer = new HashMap<>();
        for (Event x: allEvents.values()) {
            Map<String, User> y = x.getPlayers();
            for(User z: y.values()) {
                answer.put(z.getName(), z);
            }
        }
        return answer;
    }

    public String getName () {
        return name;
    }

    public void addPlayer(String eventName, User player) {
        if(allEvents.keySet().contains(eventName)) allEvents.get(eventName).addPlayer(player);
        else System.out.println("no such event");
    }

    public void addPlayer(String eventName, ArrayList<User> toAdd) {
        for (User x: toAdd) {
            this.addPlayer(eventName, x);
        }
    }

    public ArrayList<Match> getAllMatches() {
        ArrayList<Match> answer = new ArrayList<>();
        for (Event x: allEvents.values()) {
            answer.addAll(x.getAllMatches());
        }
        return answer;
    }

    public ArrayList<Match> getCurMatches() {
        ArrayList<Match> answer = new ArrayList<>();
        for (Event x: allEvents.values()) {
            answer.addAll(x.getCurMatches());
        }
        return answer;
    }

    public Map<String, Event> getAllEvents () {
        return allEvents;
    }


    public void setWinner(Match match, User winner) {
        Event toAdd = null;
        for(Event x: allEvents.values()) {
            if(x.getCurMatches().contains(match)) toAdd = x;
        }

        if (toAdd == null){
            System.out.println("Sorry, not a current match in any events");
            return;
        }

        toAdd.setWinner(match, winner);
    }

    public void createEvent(Class<? extends Event> type, String name) {

        if (!possibleEvents.contains(type)) {
            System.out.println("Invalid event type");
            return;
        }

        try {
            // Create a new instance of the event class
            Event event = type.getDeclaredConstructor(String.class).newInstance(name);
            addEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create event of type: " + name);
        }
    }
    private void addEvent(Event event) {
        if(allEvents.putIfAbsent(event.getName(), event) != null) System.out.println("This event already exist");
    }

    public void createDraw(String eventName) {
        if (allEvents.get(eventName) != null) allEvents.get(eventName).createDraw();
    }

    public void printDraw(String eventName) {
        if (name != null) System.out.print(this.name + " ");
        if (allEvents.get(eventName) != null) allEvents.get(eventName).printDraw();
    }


    
}
