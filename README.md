# Dalhousie Hockey League - Java Simulation
Dalhousie Hockey League (DHL) is a console-based simulation of the National Hockey League (NHL), implemented in core Java. Developed as part of the CSCI 5308: Advanced Software Development course, the project showcases understanding and implementation of industry focuses technologies, concepts, and paradigms. 

The concepts and technologies used in this project include:

1. Continuous Integration and Delivery (CI / CD)
2. Test Driven Development - JUNIT 5
3. SOLID Principles
4. Clean Code - Rob Martin’s Guidelines
5. Object Oriented Design Patterns
6. Agile and Scrum Methodologies - JIRA
7. Git flow
8. Java build systems - Gradle
9. Cohesion, Coupling, and Reuse
10. Logging and Exception Handling - Log4J

## Concepts and Technologies
A discussion of the concepts learnt and applied in this project is presented in what follows.

### Test-Driven Development
100% code coverage of the business logic was ensured as part of Test-Driven Development (TDD). This was facilitated by the JUNIT 5 unit testing framework. Each developer was responsible for writing and maintaining their own unit tests.

### CI and CD
An automated Ci/CD pipeline was implemented  with GitLabs CI / CD server.  As part of the CD flow, JAR files were built in a Linux Docker image and transferred to a machine on Dalhousie’s OpenStack network.

### SOLiD Principles and Clean Code
The complete codebase was continually refactored to ensure adherence to SOLID principles and Rob Martin’s clean code guidelines. This enabled us to write highly maintainable, modular, and readable code. 

### Cohesion, Coupling, and Programming to Interfaces
Common closure and common reuse principles (CCP - CRP) were followed to minimise coupling between packages while ensuring optimal reuse of classes. Moreover,  coupling between classes was reduced through programming to interfaces and evaluating the code for instances of temporal, cyclical, and stamp couplings.

### Design Patterns
Towards the end of the project, the codebase was refactored to incorporate object-oriented design patterns. These included behavioural patterns such as strategy and observer patterns; and creational patterns such as builder, singleton, and abstract factory.

### Logging and Exception Handling
Log4J was used to log business logic events and exceptions, to the console and file system. The logs were categorized as DEBUG, INFO, WARN, and ERROR. The logging was kept separate from the user’s console interactions, and aimed at debugging.

### Git Flow
Barring some subtle modifications, the git flow was followed by the whole development team. Each feature was developed and tested on its individual feature branch, before being merged with the develop branch. The develop branch was then merged with master and release branches, which was tagged with each version release.

### Agile and SCRUM
SCRUM methodology was followed through out the project’s development to ensure iterative, feedback based continous delievery. Weekly stand-ups were undertaken to discuss features and pitfalls with the product ownser. The project was completed in three epics with clearly outlined and assigned epics.

## Core Features
The game allows a player to simulate NHL’s core aspects and features. These features include high-level aspects of the NHL such as generating schedules, down to low-level aspects such as simulating player injuries and goals.

The core simulation features are listed below:

1. Create leagues, conferences, divisions, teams, players, coaches, general managers, and free agents. The user (or human player) can also create their own team, and pit it against other teams.
2. Generate regular and playoff season schedules.
3. Conduct matches, determine player strategies, track wins and losses at both player and team levels.
4. Generate and observer player and team standings throughout the season.
5. Train, draft, and trade players based on different training and trading strategies. 
6. Injury and retire players based on a probabilistic algorithm. Replace injured players with in-active players from a team roster, and replace retired players with free agents.
7. Observe team, player, coach, and general manager rankings, and give them trophies at the end of the Stanley cup.

The above list of features is by no means exhaustive and does not outline a slew of auxiliary features and aspects of the simulation. 
