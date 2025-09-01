# RatGame

RatGame is a Java-based game where players strategise around rats, levels, weapons, scores, and custom challenges. Built with Java and JavaFX, it features UI controllers, level design, rat behaviors, and user score management.

---

## Table of Contents

- [Features](#features)  
- [Getting Started](#getting-started)  
  - [Prerequisites](#prerequisites)  
  - [Setup & Running the Game](#setup--running-the-game)  
- [Project Structure](#project-structure)  

---

## Features

- **Multiple Levels**: Built-in levels and support for custom level creation.  
- **JavaFX Interface**: Modern user interface leveraging FXML controllers.  
- **Rat AI**: Behavior logic for rat spawning and movement.  
- **Score Tracking**: Player profiles and high score system.  
- **Weapons System**: In-game tools or items to affect gameplay.  

---

## Getting Started

### Prerequisites

- Java SDK (e.g., Java 11 or newer)  
- JavaFX SDK compatible with your Java version  
- Optional: IDE such as IntelliJ IDEA or Eclipse

### Setup & Running the Game

1. Clone the repository:
   ```bash
   git clone https://github.com/thecliffes/RatGame.git
   cd RatGame

   RatGame/
├── src/
│   ├── Controller.java
│   ├── Level.java
│   ├── Rat.java
│   ├── RatSpawn.java
│   ├── Weapon.java
│   ├── Score.java
│   ├── HighScore.java
│   ├── PlayerProfile.java
│   ├── MessageOfTheDay.java
│   ├── MapView.java
│   ├── RatWriter.java
│   └── CustomLevelSelector.java
├── CustomLevels/          # Directory to load or save custom levels
├── levels/                # Default level definitions
├── Weapons/               # Weapon definitions or assets (if images are used)
├── Images/                # Assets used in the UI or levels
├── *.fxml                 # JavaFX UI layout files
└── RatGame.iml            # Project file (likely IntelliJ)
