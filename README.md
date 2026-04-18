# 🏟️ Stadium Management System — Java

> "The referee is still randomly generated. Just like in real life."

A full stadium event management system built in Java using Object-Oriented Programming. Covers everything: from tickets to hot dogs, including random goals.

---

## ⚽ What it does

- Creates a stadium with **4 sectors** (A, B, C, D) and **100 seats** total
- Schedules a **match** between two teams with squad, coach and referee
- Manages **food stands** (max. 5) with products and stock
- Registers **fans** with a virtual wallet and purchase history
- Allows buying **tickets** with real-time seat matrix visualisation
- **Simulates the match**: 1 second = 1 minute, 10% chance of a goal every 10 minutes, 5-second half-time break
- Generates a **final report** with ticket and food stand revenue

---

## 🗂️ Class Structure

| Class | Responsibility |
|---|---|
| `Main` | Full program flow |
| `Estadio` | System core — aggregates everything |
| `Setor` | 5×5 seat matrix, pricing, availability |
| `Jogo` | Match simulation and final report |
| `Equipa` | Name, city, foundation year, squad, coach |
| `Rolote` | Food stand with products and daily revenue |
| `Produto` | Sellable item with name, price and stock |
| `Adepto` | Fan with wallet, tickets and files on disk |
| `Bilhete` | Links seat + sector + price + purchase date |

---

## 🗃️ File System

Each fan automatically generates their own folder:

```
adeptos/
└── AD001/
    ├── info.txt          ← fan details
    └── bilhetes/
        └── A7.txt        ← ticket details
```

---

## 🔄 Program Flow

```
1. Create Stadium
2. Create Match (2 teams + date + referee)
3. Create first Food Stand
4. Register Fan
5. Buy Tickets
6. Pre-Match Menu:
   ├── Add Food Stand
   ├── Register Fan
   ├── Buy Food
   ├── Buy Tickets
   ├── View Statistics
   └── Start Match → Final Report
```

---

## 🛠️ Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Object_Oriented_Programming-blue?style=flat)

**No external dependencies.** Pure Java. Compile with `javac`, run with `java`.

---

## ▶️ How to run

```bash
# Compile
javac *.java

# Run
java Main
```

---

## 📝 Honest notes

- Goals are randomly generated. Gyökeres may or may not score. Could be a hat-trick.
- Half-time lasts 5 real seconds. Enough time to grab a hot dog.
- Fan IDs are sequential: `AD001`, `AD002`... indefinitely.
- The stadium gets a unique 10-digit random ID on creation. FIFA would approve.
- Maximum 5 food stands per match. Your stomach will thank you.
- Fan wallet does not accept overdraft. Reality check.

---

*Next step: graphical interface. Or not. The terminal has its charm.*
