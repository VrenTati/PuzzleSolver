import java.io.*;
import java.util.*;

public class PuzzleSolver {
    public static void main(String[] args) {
        PuzzleSolver puzzleSolver = new PuzzleSolver();
        puzzleSolver.run();
    }

    public void run() {
        List<String> fragments = readFragmentsFromFile("source.txt");

        if (fragments.isEmpty()) {
            System.out.println("No fragments found!");

            return;
        }

        int[][] adjacencyMatrix = buildAdjacencyMatrix(fragments);

        List<Integer> longestPath = findLongestPathInGraph(adjacencyMatrix);

        System.out.println("Longest Path in Graph:");
        System.out.println(longestPath);

        printSolution(longestPath, fragments);
    }

    private void printSolution(List<Integer> longestPath, List<String> fragments) {
        for (Integer fragmentIndex : longestPath) {
            System.out.print(fragments.get(fragmentIndex));
        }
    }

    private List<String> readFragmentsFromFile(String fileName) {
        List<String> fragments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                fragments.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return fragments;
    }

    private int[][] buildAdjacencyMatrix(List<String> fragments) {
        int n = fragments.size();
        int[][] adjacencyMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            String endPart = fragments.get(i).substring(fragments.get(i).length() - 2);

            for (int j = 0; j < n; j++) {
                String startPart = fragments.get(j).substring(0, 2);

                if (endPart.equals(startPart)) {
                    adjacencyMatrix[i][j] = 1;
                }
            }
        }

        return adjacencyMatrix;
    }

    private List<Integer> findLongestPathInGraph(int[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;
        List<Integer> longestPath = new ArrayList<>();

        for (int start = 0; start < n; start++) {
            List<Integer> currentLongestPath = new ArrayList<>();
            dfs(start, adjacencyMatrix, currentLongestPath);

            //If you want to see the progress - uncomment this 2 lines
//            System.out.println(start);
//            System.out.println("Longest Path: " + longestPath);

            if (currentLongestPath.size() > longestPath.size()) {
                longestPath = new ArrayList<>(currentLongestPath);
            }
        }

        return longestPath;
    }

    private void dfs(int start, int[][] adjacencyMatrix, List<Integer> longestPath) {
        int n = adjacencyMatrix.length;
        Stack<List<Integer>> stack = new Stack<>();

        stack.push(Collections.singletonList(start));

        while (!stack.isEmpty()) {
            List<Integer> path = stack.pop();
            int current = path.get(path.size() - 1);

            if (path.size() > longestPath.size()) {
                longestPath.clear();
                longestPath.addAll(path);
            }

            for (int next = 0; next < n; next++) {
                if (adjacencyMatrix[current][next] == 1 && !path.contains(next)) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(next);
                    stack.push(newPath);
                }
            }
        }
    }
}
