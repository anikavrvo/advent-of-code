import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dec25_Day1_Puzzle1 {
    public static void main(String[] args) {
        String inputFilePath = "./1Dec25_Puzzle1_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculatePassword(inputLines);

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
    }

    private static void calculatePassword(List<String> inputLines){
        Integer dialInteger = 50;
        Integer finalPassword = 0;
        Integer pointsAtZeroDuringRotation = 0;

        List<Integer> dialOutputs = new ArrayList<>();

        for (String instruction : inputLines){
            Integer rotations = Integer.parseInt(instruction.substring(1));
            if (instruction.charAt(0)=='L'){
                int firstStep = (dialInteger == 0) ? 100 : dialInteger;
                if (rotations >= firstStep) {
                    pointsAtZeroDuringRotation += 1 + (rotations - firstStep) / 100;
                }

                dialInteger = moveLeft(dialInteger, rotations);
            }
            if (instruction.charAt(0)=='R'){
                if (dialInteger + rotations >= 100){
                    pointsAtZeroDuringRotation += (dialInteger + rotations) / 100;
                }

                dialInteger = moveRight(dialInteger, rotations);
            }
            dialOutputs.add(dialInteger);

            if (dialInteger==0) {
                finalPassword++;
            }

        }

        System.out.println("Dial outputs: ");
        System.out.println(dialOutputs);
        System.out.println("Final password: ");
        System.out.println(finalPassword);
        System.out.println("Number of times it points at 0 during rotation: ");
        System.out.println(pointsAtZeroDuringRotation);
    }

    private static Integer moveLeft(Integer dialInteger,Integer rotations){
        return ((dialInteger - rotations) % 100 + 100) % 100;
    }

    private static Integer moveRight(Integer dialInteger, Integer rotations){
        return ((dialInteger + rotations) % 100 + 100) % 100;
    }
}
