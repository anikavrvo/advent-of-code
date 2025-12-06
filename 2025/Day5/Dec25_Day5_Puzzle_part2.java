import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dec25_Day5_Puzzle_part2 {
    public static void main(String[] args) {
        // Use "Resolve-Path -Path "C:\Users\Username\Documents\file.txt" -Relative" 
        // to get relative file path
        String inputFilePath = "..\\..\\..\\5Dec_Puzzle_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculateFreshIngredientIDs(inputLines);
                
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } 
    }

    private static void calculateFreshIngredientIDs(List<String> inputLines){
        List<String> freshIngredientRanges = new ArrayList<>();

        for (int i=0; i<177; i++){
            freshIngredientRanges.add(inputLines.get(i));
        }
        System.out.println("Fresh ingredient ranges: "+freshIngredientRanges);

        List<long[]> freshIngredientEndpoints = new ArrayList<>();
        for (int i=0; i<freshIngredientRanges.size(); i++){
            String range = freshIngredientRanges.get(i);
            String[] rangeEndpoints = range.split("-");
            long[] rangeEndpointslongs = new long[]{Long.parseLong(rangeEndpoints[0]), Long.parseLong(rangeEndpoints[1])};
            freshIngredientEndpoints.add(rangeEndpointslongs);
        }

        freshIngredientEndpoints.sort((firstRange,secondRange)->Long.compare(firstRange[0], secondRange[0]));
        List<long[]> mergedEndpoints = new ArrayList<>();
        long currentStart = freshIngredientEndpoints.get(0)[0];
        long currentEnd = freshIngredientEndpoints.get(0)[1];

        for (int i = 1; i < freshIngredientEndpoints.size(); i++) {
            long start = freshIngredientEndpoints.get(i)[0];
            long end = freshIngredientEndpoints.get(i)[1];

            if (start <= currentEnd + 1) {
                currentEnd = Math.max(currentEnd, end);
            } else {
                mergedEndpoints.add(new long[]{currentStart, currentEnd});
                currentStart = start;
                currentEnd = end;
            }
        }
        mergedEndpoints.add(new long[]{currentStart, currentEnd});

        long totalDistinct = 0;
        for (long[] r : mergedEndpoints) {
            totalDistinct += (r[1] - r[0] + 1);
        }

        System.out.println("Number of distinct fresh ingredients: "+totalDistinct);
    }
}
