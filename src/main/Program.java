package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
/* 
 * This main program is written entirely by Copilot, no code from this class will be used in the eventual program as this is simply meant to 
 * showcase the current tourament class
 */
public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Tournament Manager!");

        // Create a tournament
        System.out.print("Enter the name of the tournament: ");
        String tournamentName = scanner.nextLine();
        Tournament tournament = new Tournament(tournamentName);
        System.out.println("Tournament '" + tournamentName + "' created!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Event");
            System.out.println("2. Add Players to Event");
            System.out.println("3. Create Draw");
            System.out.println("4. Print Draw");
            System.out.println("5. Set Match Winner");
            System.out.println("6. View All Players");
            System.out.println("7. Viwer player matches");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Event
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    System.out.println("Available event types: SingleElimEvent");
                    System.out.print("Enter event type: ");
                    String eventType = scanner.nextLine();

                    if (eventType.equals("SingleElimEvent")) {
                        tournament.createEvent(SingleElimEvent.class, eventName);
                        System.out.println("Event '" + eventName + "' of type '" + eventType + "' created!");
                    } else {
                        System.out.println("Invalid event type.");
                    }
                    break;

                case 2: // Add Players to Event
                    System.out.print("Enter event name: ");
                    eventName = scanner.nextLine();
                    System.out.print("Enter the number of players to add: ");
                    int numPlayers = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    ArrayList<User> players = new ArrayList<>();
                    for (int i = 0; i < numPlayers; i++) {
                        System.out.print("Enter player " + (i + 1) + " name: ");
                        String playerName = scanner.nextLine();
                        players.add(new User(playerName, null));
                    }
                    tournament.addPlayer(eventName, players);
                    System.out.println("Players added to event '" + eventName + "'.");
                    break;

                case 3: // Create Draw
                    System.out.print("Enter event name: ");
                    eventName = scanner.nextLine();
                    tournament.createDraw(eventName);
                    System.out.println("Draw created for event '" + eventName + "'.");
                    break;

                case 4: // Print Draw
                    System.out.print("Enter event name: ");
                    eventName = scanner.nextLine();
                    tournament.printDraw(eventName);
                    break;

                case 5: // Set Match Winner
                    System.out.print("Enter event name: ");
                    eventName = scanner.nextLine();
                    Event event = tournament.getAllEvents().get(eventName);
                    if (event == null) {
                        System.out.println("Event not found.");
                        break;
                    }
                    ArrayList<Match> curMatches = event.getCurMatches();
                    if (curMatches.isEmpty()) {
                        System.out.println("No current matches in this event.");
                        break;
                    }
                    System.out.println("Current Matches:");
                    for (int i = 0; i < curMatches.size(); i++) {
                        System.out.println((i + 1) + ". " + curMatches.get(i));
                    }
                    System.out.print("Choose a match (by number): ");
                    int matchIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    if (matchIndex < 0 || matchIndex >= curMatches.size()) {
                        System.out.println("Invalid match number.");
                        break;
                    }
                    Match match = curMatches.get(matchIndex);
                    System.out.println("Players: " + match.getPlayer1().getName() + " vs " + match.getPlayer2().getName());
                    System.out.print("Enter winner's name: ");
                    String winnerName = scanner.nextLine();
                    User winner = match.getPlayer1().getName().equals(winnerName) ? match.getPlayer1() : match.getPlayer2();
                    tournament.setWinner(match, winner);
                    System.out.println("Winner set for match: " + match);
                    break;

                case 6: // View All Players
                    System.out.println("All Players in the Tournament:");
                    for (User user : tournament.getPlayers().values()) {
                        System.out.println(user.getName());
                    }
                    break;
                case 7:
                    System.out.print("Enter player's name: ");
                    String playerName = scanner.nextLine();
                    User player = tournament.getPlayers().get(playerName);
                    if (player == null) {
                        System.out.println("Player not found.");
                        break;
                    }

                    // Display all matches grouped by tournament
                    System.out.println("All Matches for " + player.getName() + ":");
                    Map<Tournament, ArrayList<Match>> allMatches = player.getAllMatches();
                    for (Map.Entry<Tournament, ArrayList<Match>> entry : allMatches.entrySet()) {
                        System.out.println("Tournament: " + entry.getKey().getName());
                        for (Match match1 : entry.getValue()) {
                            System.out.println("  Match: " + match1.toString());
                        }
                    }           

                    // Display current matches
                    System.out.println("\nCurrent Matches for " + player.getName() + ":");
                    ArrayList<Match> curMatches1 = tournament.getCurMatchesForUser(player);
                    if (curMatches1.isEmpty()) {
                        System.out.println("No current matches.");
                    } else {
                        for (Match match1 : curMatches1) {
                            System.out.println("  Match: " + match1.toString());
                        }
                    }
                    break;
                case 8: // Exit
                    System.out.println("Exiting Tournament Manager. Goodbye!");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}