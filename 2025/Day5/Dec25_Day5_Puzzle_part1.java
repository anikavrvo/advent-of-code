import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dec25_Day5_Puzzle_part1 {
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
        List<Long> availableIngredients = new ArrayList<>();

        for (int i=0; i<177; i++){
            freshIngredientRanges.add(inputLines.get(i));
        }
        System.out.println("Fresh ingredient ranges: "+freshIngredientRanges);

        for (int i=178; i<inputLines.size(); i++){
            availableIngredients.add(Long.parseLong(inputLines.get(i)));
        }
        System.out.println("Available ingredients: "+availableIngredients);

        List<Long[]> freshIngredients = new ArrayList<>();
        for (int i=0; i<freshIngredientRanges.size(); i++){
            String range = freshIngredientRanges.get(i);
            String[] rangeEndpoints = range.split("-");
            Long[] rangeEndpointsLongs = new Long[]{Long.parseLong(rangeEndpoints[0]), Long.parseLong(rangeEndpoints[1])};
            freshIngredients.add(rangeEndpointsLongs);
        }

        List<Long> availableFreshIngredients = new ArrayList<>();

        for (Long availableIngredient : availableIngredients){
            for (Long[] range : freshIngredients){
                if(range[0]<=availableIngredient && availableIngredient<=range[1]){
                    availableFreshIngredients.add(availableIngredient);
                }
            }
        }

        long numberOfAvailableFreshIngredients = availableFreshIngredients.stream().distinct().count();

        System.out.println("Number of available fresh ingredients: "+numberOfAvailableFreshIngredients);
    }
}
