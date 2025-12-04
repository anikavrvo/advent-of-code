import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dec25_Day4_Puzzle_part2 {
    public static List<String> inputLines = new ArrayList<>();

    public static void main(String[] args) {
        // Use "Resolve-Path -Path "C:\Users\Username\Documents\file.txt" -Relative" 
        // to get relative file path
        String inputFilePath = "..\\..\\..\\4Dec_Puzzle_input.txt";

        try {
                inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculateNumberOfRemovableRolls();
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } 
    }

    private static void calculateNumberOfRemovableRolls(){
        Integer numberOfRemovableRolls = 0;
        Integer totalShelfSpaces = inputLines.size()*inputLines.get(0).length();

        while (numberOfRemovableRolls < totalShelfSpaces){
            Integer numberOfAccessibleRollsThatsRemoved = calculateNumberOfAccessibleRolls();
            numberOfRemovableRolls += numberOfAccessibleRollsThatsRemoved;
            if(numberOfAccessibleRollsThatsRemoved==0){
                break;
            }
        }

        System.out.println("Total shelf spaces: "+totalShelfSpaces);
        System.out.println("Total number of removable rolls: "+numberOfRemovableRolls);
    }

    private static Integer calculateNumberOfAccessibleRolls(){
        Integer numberOfAccessibleRolls = 0;
        int shelfIndex = 0;
        for (String shelf : inputLines){
            String newShelf = shelf;
            for (int i = 0; i<shelf.length(); i++){
                if (shelf.charAt(i)=='@'){
                    Integer surroundingRolls = getSurroundingRolls(i, shelfIndex);
                    if (surroundingRolls <4){
                        numberOfAccessibleRolls++;
                        newShelf = newShelf.substring(0,i)+"X"+ newShelf.substring(i+1);
                    }
                } 
            }
            inputLines.set(shelfIndex, newShelf);
            shelfIndex++;
        }
        System.out.println("Number of accessible rolls: "+numberOfAccessibleRolls);
        return numberOfAccessibleRolls;
    }

    private static Integer getSurroundingRolls(int i, int shelfIndex){
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
