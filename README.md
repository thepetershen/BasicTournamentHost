# BasicTournament

BasicTournament is a Java-based application for managing tournaments with support for single-elimination events. It allows users to create tournaments, add players, generate brackets, and manage matches.

## Features

- Create tournaments with customizable names.
- Add single-elimination events to tournaments.
- Add players to events and generate tournament brackets.
- Automatically handle byes for non-power-of-two player counts.
- Print tournament brackets in a structured format.
- Set match winners and progress through the tournament.
- Support for multiple events within a single tournament.

## Folder Structure

The project is organized as follows:

- `src/`: Contains the source code.
  - `main/`: Core application logic, including classes for tournaments, events, matches, and users.
  - `test/`: Unit tests for the application.
- `lib/`: Contains external dependencies (e.g., JUnit for testing).
- `bin/`: Compiled output files.
- `.vscode/`: VS Code-specific settings.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- [JUnit 4.13.2](https://junit.org/junit4/) for running tests.

### Running the Application

1. Clone the repository or download the source code.
2. Open the project in Visual Studio Code.
3. Build the project:
   ```sh
   javac -d bin -sourcepath src -cp "lib/*" src/main/Program.java