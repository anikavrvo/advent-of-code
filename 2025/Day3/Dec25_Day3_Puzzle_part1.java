import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Dec25_Day3_Puzzle_part1 {
    public static void main(String[] args){
        // Use "Resolve-Path -Path "C:\Users\Username\Documents\file.txt" -Relative" 
        // to get relative file path
        String inputFilePath = "..\\..\\..\\3Dec_Puzzle_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculateTotalOutputJoltage(inputLines);
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } 
    }

    private static void calculateTotalOutputJoltage(List<String> inputLines){
        Integer totalJoltage = 0;
        for (String bank : inputLines){
            totalJoltage += calculateLargestJoltageForBank(bank);
        }
        System.out.println("Total joltage: "+totalJoltage);
    }

    private static Integer calculateLargestJoltageForBank(String bank){
        List<Integer> bankNumericDigits = convertBankDigitsToNumericDigits(bank);

        Integer maxInteger = getMaxInteger(bankNumericDigits);
        String maxJoltageString = "";

        if (bankNumericDigits.indexOf(maxInteger)==bank.length()-1){
            String subString = bank.substring(0,bank.length()-1);
            List<Integer> subStringNumericDigits = convertBankDigitsToNumericDigits(subString);
            Integer nextMaxInteger = getMaxInteger(subStringNumericDigits);
            maxJoltageString = nextMaxInteger.toString().concat(maxInteger.toString());
        } else {
            String subString = bank.substring(bankNumericDigits.indexOf(maxInteger)+1);
            List<Integer> subStringNumericDigits = convertBankDigitsToNumericDigits(subString);
            Integer nextMaxInteger = getMaxInteger(subStringNumericDigits);
            maxJoltageString = maxInteger.toString().concat(nextMaxInteger.toString());
        }
        return Integer.parseInt(maxJoltageString);
    }

    private static Integer getMaxInteger(List<Integer> bankNumericDigits){
        Optional<Integer> maxInteger = bankNumericDigits.stream().max(Comparator.naturalOrder());
        if (maxInteger.isEmpty()){
            System.err.println("Error reading bank: " + bankNumericDigits);
            return 0;
        }
        return maxInteger.get();
    }

    private static List<Integer> convertBankDigitsToNumericDigits(String bank){
        List<Integer> bankNumericDigits = new ArrayList<>();
        for (int i=0; i<bank.length(); i++){
            bankNumericDigits.add(Character.getNumericValue(bank.charAt(i)));
        }
        return bankNumericDigits;
    }
}