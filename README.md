# 🏟️ Stadium Java — Console-Based Stadium Management System

> A Java console application for managing stadium operations including teams, players, matches, and seating.

Manage a complete stadium environment from the command line. This Java application models the core entities of a sports venue — teams, players, matches, and seats — using object-oriented principles. Navigate through a menu-driven interface to create teams, register players, schedule matches, and manage seating arrangements. All data persists through file system read/write operations, so your stadium state is preserved between sessions. The project demonstrates solid OOP fundamentals including encapsulation, inheritance, polymorphism, and composition through a real-world domain model.

## 📦 What's Inside

- 🏟️ Stadium entity with capacity, name, and location attributes
- ⚽ Team management — create, view, and edit teams with rosters
- 👤 Player registration with name, number, position, and team assignment
- 📅 Match scheduling with home/away teams, date, and time
- 💺 Seat management — sections, rows, and individual seat tracking
- 🎫 Seat availability checking for match events
- 📋 Menu-driven console interface for all operations
- 💾 File system persistence — read and write data to local files
- 🔄 Full CRUD operations on teams, players, and matches
- 📊 Display formatted reports of teams, players, and match schedules
- 🧱 Clean OOP class hierarchy with encapsulation and composition
- 🗂️ Organised package structure separating models, services, and I/O

## 🛠️ Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Principles-blue?style=flat)
![File I/O](https://img.shields.io/badge/File_I/O-Persistence-green?style=flat)
![Console](https://img.shields.io/badge/Console-Application-grey?style=flat)

## 🏗️ Architecture

The project follows standard **OOP design** with clear separation between data models, business logic, and I/O:

```
stadium_java/
├── models/
│   ├── Stadium.java                  # Stadium entity (name, location, capacity)
│   ├── Team.java                     # Team entity (name, roster of players)
│   ├── Player.java                   # Player entity (name, number, position)
│   ├── Match.java                    # Match entity (home team, away team, date, time)
│   └── Seat.java                     # Seat entity (section, row, number, availability)
├── services/
│   ├── TeamService.java              # CRUD logic for teams
│   ├── PlayerService.java            # CRUD logic for players
│   ├── MatchService.java             # CRUD logic for matches
│   └── SeatService.java             # Seat availability and assignment logic
├── io/
│   ├── FileReader.java               # Read data from text files on startup
│   ├── FileWriter.java               # Write data to text files on save
│   └── ConsoleMenu.java              # Menu display and user input handling
├── utils/
│   ├── Validator.java                # Input validation helpers
│   └── Formatter.java               # Output formatting for console display
└── Main.java                         # Entry point — initialises data and starts menu loop
```

## 📱 Screens

| Menu Option | Description |
|-------------|-------------|
| 🏟️ **Stadium Info** | View stadium name, location, and total capacity |
| ⚽ **Manage Teams** | Create, list, edit, and delete teams |
| 👤 **Manage Players** | Register players, assign to teams, view rosters |
| 📅 **Manage Matches** | Schedule matches, set home/away teams, view calendar |
| 💺 **Manage Seats** | Check seat availability, assign seats to sections |
| 📊 **Reports** | View formatted summaries of teams, players, and upcoming matches |
| 💾 **Save & Exit** | Write all data to files and close the application |

## 🔄 How It Works

1. **Startup** — `Main.java` launches the application; `FileReader` loads any previously saved data from text files into memory
2. **Menu Loop** — `ConsoleMenu` displays the main menu; user selects an option by entering a number
3. **CRUD Operations** — Each service class (TeamService, PlayerService, etc.) handles create, read, update, and delete for its entity
4. **Validation** — User inputs are validated before creating or modifying entities (e.g., player number uniqueness, team name required)
5. **In-Memory State** — All entities live in collections during runtime; services operate on these collections
6. **Reporting** — `Formatter` produces clean console output tables for teams, rosters, match schedules, and seat maps
7. **Persistence** — On save or exit, `FileWriter` serialises all entities to structured text files; on next launch, `FileReader` reconstructs them

## 🚀 How to Run

```bash
# 1. Clone the repository
git clone https://github.com/VidiPT89/stadium_java.git
cd stadium_java

# 2. Compile the Java source files
javac -d out src/**/*.java

# 3. Run the application
java -cp out Main

# Or, if using an IDE:
# Open the project in IntelliJ IDEA or Eclipse
# Run Main.java
```

## 📝 Notes

- The project is a **console application** — all interaction happens through `System.in` and `System.out`, no GUI
- File persistence uses **plain text files** rather than a database, keeping the project dependency-free
- The OOP design demonstrates **encapsulation** (private fields with getters/setters), **composition** (teams contain players), and clean **separation of concerns**
- Data files are human-readable text, making it easy to inspect or manually edit saved state
- The menu system uses a **loop-and-switch pattern** — the application runs until the user explicitly chooses to exit
- Input validation prevents common issues like duplicate player numbers or empty team names

---

Developed by **David Arsénio Martins** — *"Vidi"*
