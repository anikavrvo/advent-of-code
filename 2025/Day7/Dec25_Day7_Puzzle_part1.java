import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Dec25_Day7_Puzzle_part1 {
    public static void main(String[] args) {
        // Use "Resolve-Path -Path "C:\Users\Username\Documents\file.txt" -Relative" 
        // to get relative file path
        String inputFilePath = "..\\..\\..\\7Dec_Puzzle_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculate(inputLines);
                
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } 
    }

    private static void calculate(List<String> inputLines){
        
    }
}
