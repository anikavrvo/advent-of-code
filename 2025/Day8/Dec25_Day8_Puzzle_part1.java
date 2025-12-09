import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Dec25_Day8_Puzzle_part1 {
    public static void main(String[] args) {
        // Use "Resolve-Path -Path "C:\Users\Username\Documents\file.txt" -Relative" 
        // to get relative file path
        String inputFilePath = "..\\..\\..\\8Dec_Puzzle_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculateProductOfThreeLargestCircuits(inputLines);
                
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } 
    }

    private static void calculateProductOfThreeLargestCircuits(List<String> inputLines){
        // calculate first 1000 shortest distances


        // connect circuits based on shortest connections


        // output product of 3 largest circuits sizes
        System.out.println("Product of 3 largest circuit sizes: ");
    }
}
