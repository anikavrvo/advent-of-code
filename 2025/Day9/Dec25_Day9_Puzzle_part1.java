import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Dec25_Day9_Puzzle_part1 {
    public static void main(String[] args) {
        // Use "Resolve-Path -Path "C:\Users\Username\Documents\file.txt" -Relative" 
        // to get relative file path
        String inputFilePath = "..\\..\\..\\9Dec_Puzzle_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculateLargestRectangleArea(inputLines);
                
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } 
    }

    private static void calculateLargestRectangleArea(List<String> inputLines){
        long largestRectangleArea = 0;

        for (int i=0; i<inputLines.size(); i++){
            String[] firstCoord = inputLines.get(i).split(",");
            for (int j=i+1; j<inputLines.size(); j++){
                String[] secondCoord = inputLines.get(j).split(",");
                long rectangleArea = calculateRectangleArea(firstCoord, secondCoord);
                if (rectangleArea > largestRectangleArea){
                    largestRectangleArea = rectangleArea;
                }
            }
        }

        System.out.println("Largest rectangle area: "+largestRectangleArea);
    }

    private static long calculateRectangleArea(String[] firstCoord, String[] secondCoord){
        long length = Math.abs(Long.parseLong(firstCoord[0]) - Long.parseLong(secondCoord[0]))+1;
        long width = Math.abs(Long.parseLong(firstCoord[1]) - Long.parseLong(secondCoord[1]))+1;
        return length*width;
    }
}
