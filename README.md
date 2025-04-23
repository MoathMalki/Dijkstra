# GitHub README for Dijkstra's Algorithm Project

## Project Title
Dijkstra's Algorithm Visualization for Capital Cities Pathfinding

## Project Description
This Java application visualizes Dijkstra's algorithm for finding the shortest path between capital cities on a world map. The project features:

- Interactive world map with capital city markers
- GUI for selecting source and destination cities
- Visualization of the shortest path between selected cities
- Distance calculation using geographical coordinates
- Reset functionality to clear selections

## Technologies Used
- Java 8+
- JavaFX for GUI
- Dijkstra's algorithm implementation
- Geographic distance calculations

## Features
- **Interactive Map**: Click on city markers to select source and destination
- **Combo Box Selection**: Alternatively use dropdown menus to select cities
- **Path Visualization**: Shows the shortest path with connecting lines
- **Distance Display**: Calculates and displays total distance in kilometers
- **Responsive UI**: Clean interface with reset and exit functionality

## How to Run
1. Ensure you have Java 8 or later installed
2. Clone this repository
3. Compile and run the `Main.java` file
4. The application requires a data file named `data.txt.txt` with city coordinates

## Data Format
The data file should contain:
1. First line: Number of cities and number of edges
2. Subsequent lines: City data in format `name longitude latitude`
3. Followed by edges in format `city1 city2`

