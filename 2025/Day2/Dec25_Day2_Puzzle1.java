import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dec25_Day2_Puzzle1 {
    public static void main(String[] args) {
        String inputFilePath = "./2Dec25_Puzzle1_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                System.out.println("Number of input lines should be 1: "+inputLines.size());
                calculateSumOfInvalidProductIDs(inputLines.get(0));

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
    }

    private static void calculateSumOfInvalidProductIDs(String inputString){
        String[] intervals = inputString.split(",");
        List<Long> invalidIDs = new ArrayList<>();
        Long sumOfInvalidIDs = 0L;

        for (String interval : intervals){
            String[] intervalEndpoints = interval.split("-");
            Long startInteger = Long.parseLong(intervalEndpoints[0]);
            Long endInteger = Long.parseLong(intervalEndpoints[1]);

            for (long i=startInteger; i<endInteger + 1; i++){
                String numberAsString = String.valueOf(i);
                int numberLength = numberAsString.length();
                //part 2:
                for (int numberDivisor= 2; numberDivisor < numberLength+1; numberDivisor++){
                    if ((numberLength % numberDivisor)==0){
                        List<String> divisions = new ArrayList<>();
                        int startIndex = 0;
                        int endIndex = numberLength/numberDivisor;
                        //System.out.println("Number: "+numberAsString+" and check: "+endIndex*numberDivisor);
                        while (startIndex<numberLength){
                            divisions.add(numberAsString.substring(startIndex, endIndex));
                            startIndex = endIndex;
                            endIndex += numberLength/numberDivisor;
                            //System.out.println("Number: "+numberAsString+" and divisions: "+divisions);
                        }
                        if (areAllDivisionsEqual(divisions)==true){
                            invalidIDs.add(i);
                            System.out.println("Invalid ID: "+i);
                        }
                    }
                }
                //only for halving:
                // if ((numberLength % 2 == 0)){
                //     String firstHalf = numberAsString.substring(0, numberLength/2);
                //     String secondHalf = numberAsString.substring(numberLength/2);

                //     if (firstHalf.equals(secondHalf)){
                //         invalidIDs.add(i);
                //         System.out.println("Invalid ID: "+i);
                //     }
                // }
            }
        }

        List<Long> uniqueInvalidIDs = invalidIDs.stream().distinct().sorted().collect(Collectors.toList());
        for (Long uniqueInvalidID : uniqueInvalidIDs){
            System.out.println("Long: "+uniqueInvalidID);
            sumOfInvalidIDs += uniqueInvalidID;
        }

        System.out.println("Final sum: "+sumOfInvalidIDs);
    }

    private static boolean areAllDivisionsEqual(List<String> divisions){
        String firstDivision = divisions.get(0);
        for (String division : divisions){
            if (!division.equals(firstDivision)){
                return false;
            }
        }
        return true;
    }
}