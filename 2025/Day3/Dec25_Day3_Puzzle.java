import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Dec25_Day3_Puzzle {
    public static void main(String[] args){
        String inputFilePath = "./3Dec25_Puzzle1_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
    }
}