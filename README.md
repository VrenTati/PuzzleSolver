# PuzzleSolver

PuzzleSolver is a Java program that finds the **longest path** in a directed graph constructed from a list of strings. Each string represents a vertex, and an edge exists between two vertices if the last two characters of one string match the first two characters of another. The program outputs the longest path and concatenates the strings along this path.

## Features

- Reads input strings from a file (`source.txt`).
- Constructs a directed graph using an adjacency matrix.
- Finds the longest path in the graph using an iterative Depth-First Search (DFS) approach.
- Outputs the concatenated strings of the longest path.

---

## How It Works

1. **Input**: The program reads strings from `source.txt`, with one string per line.
2. **Graph Construction**: An adjacency matrix represents the directed graph:
   - If the last two characters of a string match the first two of another, an edge is created between them.
3. **Longest Path Calculation**: The program uses iterative DFS to explore all possible paths and determines the longest path.
4. **Output**:
   - Indices of vertices in the longest path.
   - The concatenated result of the strings along the longest path.
