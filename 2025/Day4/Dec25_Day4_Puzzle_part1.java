import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Dec25_Day4_Puzzle_part1 {
    
    public static void main(String[] args) {
        // Use "Resolve-Path -Path "C:\Users\Username\Documents\file.txt" -Relative" 
        // to get relative file path
        String inputFilePath = "..\\..\\..\\4Dec_Puzzle_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculateNumberOfAccessibleRolls(inputLines);
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } 
    }

    private static void calculateNumberOfAccessibleRolls(List<String> inputLines){
        Integer numberOfAccessibleRolls = 0;
        int shelfIndex = 0;
        for (String shelf : inputLines){
            for (int i = 0; i<shelf.length(); i++){
                if (shelf.charAt(i)=='@'){
                    Integer surroundingRolls = getSurroundingRolls(i, shelfIndex, inputLines);
                    if (surroundingRolls <4){
                        numberOfAccessibleRolls++;
                    }
                } 
            }
            shelfIndex++;
        }
        System.out.println("Number of accessible rolls: "+numberOfAccessibleRolls);
    }

    private static Integer getSurroundingRolls(int i, int shelfIndex, List<String> inputLines){
        Integer surroundingRolls = 0;

        boolean canMoveLeft = false;
        boolean canMoveRight = false;
        boolean canMoveUp = false;
        boolean canMoveDown = false;
        if(i>0){ 
            canMoveLeft = true;
        }
        if(i<inputLines.get(shelfIndex).length()-1){ 
            canMoveRight = true;
        }
        if(shelfIndex>0){ 
            canMoveUp = true;
        }
        if(shelfIndex<inputLines.size()-1){
            canMoveDown = true;
        }

        if(canMoveUp){
            if(inputLines.get(shelfIndex-1).charAt(i)=='@'){
                surroundingRolls++;
            }
            if(canMoveLeft && inputLines.get(shelfIndex-1).charAt(i-1)=='@'){
                surroundingRolls++;
            }
            if(canMoveRight && inputLines.get(shelfIndex-1).charAt(i+1)=='@'){
                surroundingRolls++;
            }
        }
        if(canMoveDown){
            if(inputLines.get(shelfIndex+1).charAt(i)=='@'){
                surroundingRolls++;
            }
            if(canMoveLeft && inputLines.get(shelfIndex+1).charAt(i-1)=='@'){
                surroundingRolls++;
            }
            if(canMoveRight && inputLines.get(shelfIndex+1).charAt(i+1)=='@'){
                surroundingRolls++;
            }
        }
        if(canMoveLeft && inputLines.get(shelfIndex).charAt(i-1)=='@'){
            surroundingRolls++;
        }
        if(canMoveRight && inputLines.get(shelfIndex).charAt(i+1)=='@'){
            surroundingRolls++;
        }

        return surroundingRolls;
    }
}
