import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dec25_Day3_Puzzle_part2 {
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
        Long totalJoltage = 0L;
        for (String bank : inputLines){
            totalJoltage += calculateLargestJoltageForBank(bank);
        }
        System.out.println("Total joltage: "+totalJoltage);
    }

    // shameless ChatGPT solution, because I need to work
    private static long calculateLargestJoltageForBank(String bank) {
        StringBuilder result = new StringBuilder(12);
        int needed = 12;
        int start = 0;

        while (needed > 0) {
            // Search for the best digit from 9 → 0
            for (char d = '9'; d >= '0'; d--) {
                int pos = bank.indexOf(d, start);
                if (pos == -1) continue;

                // Check if using this digit leaves enough remaining digits
                int remaining = bank.length() - pos - 1;
                if (remaining >= needed - 1) {
                    result.append(d);
                    start = pos + 1; // continue after this digit
                    needed--;
                    break;
                }
            }
        }

        return Long.parseLong(result.toString());
    }

    // Solution that didn't work:
    
    // private static Long calculateLargestJoltageForBank(String bank){
    //     String maxJoltageString = "";

    //     for (int digit= 9; digit>0; digit--){
    //         String maxJoltageStartingWithDigit = getMaxJoltageStartingWithDigit(bank,digit);
    //         if (maxJoltageStartingWithDigit.length()==12){
    //             maxJoltageString = maxJoltageStartingWithDigit;
    //             break;
    //         }
    //     }
    //     return Long.parseLong(maxJoltageString);
    // }

    private static String getMaxJoltageStartingWithDigit(String bank, int digit){
        String maxJoltageStringStartingWithDigit = "";
        int start = bank.indexOf(Integer.toString(digit));
        if (start == -1) return maxJoltageStringStartingWithDigit;

        String subString = bank.substring(bank.indexOf(Integer.toString(digit)));
        List<Integer> bankNumericDigits = convertBankDigitsToNumericDigits(subString);

        boolean[] markDigitsAsTrue = new boolean[subString.length()];
        int trackingDigit = digit;

        while (lessThan12DigitsTrue(markDigitsAsTrue) && containsFalse(markDigitsAsTrue) && trackingDigit>0){
            for (int i=subString.length()-1; i>=0; i--){
                if (subString.charAt(i) == (char) ('0' + trackingDigit)
                    && !markDigitsAsTrue[i]
                    && lessThan12DigitsTrue(markDigitsAsTrue)) 
                {
                    markDigitsAsTrue[i] = true;
                }
            }
            trackingDigit--;
        }

        for (int i=0; i<subString.length(); i++){
            if (markDigitsAsTrue[i]==true){
                maxJoltageStringStartingWithDigit += bankNumericDigits.get(i);
                //maxJoltageStringStartingWithDigit.concat(bankNumericDigits.get(i).toString());
            }
        }

        return maxJoltageStringStartingWithDigit;
    }

    private static boolean lessThan12DigitsTrue(boolean[] markDigitsAsTrue){
        int numberOfTrueDigits = 0;
        for (int i=0; i<markDigitsAsTrue.length; i++){
            if (markDigitsAsTrue[i]==true){
                numberOfTrueDigits += 1;
            }
        }
        return numberOfTrueDigits < 12;
    }

    private static boolean containsFalse(boolean[] markDigitsAsTrue){
        for (int i=0; i<markDigitsAsTrue.length; i++){
            if (markDigitsAsTrue[i]==false){
                return true;
            }
        }
        return false;
    }

    private static List<Integer> convertBankDigitsToNumericDigits(String bank){
        List<Integer> bankNumericDigits = new ArrayList<>();
        for (int i=0; i<bank.length(); i++){
            bankNumericDigits.add(Character.getNumericValue(bank.charAt(i)));
        }
        return bankNumericDigits;
    }
}