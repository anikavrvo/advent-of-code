import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Dec25_Day9_Puzzle_part2 {
    public static void main(String[] args) {
        // Use "Resolve-Path -Path "C:\Users\Username\Documents\file.txt" -Relative" 
        // to get relative file path
        String inputFilePath = "..\\..\\..\\9Dec_Puzzle_input.txt";

        try {
                List<String> inputLines = Files.readAllLines(Paths.get(inputFilePath));
                calculateLargestGreenAndRedRectangleArea(inputLines);
                
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } 
    }

    private static void calculateLargestGreenAndRedRectangleArea(List<String> inputLines){
        long largestRectangleArea = 0;

        int maxXCoord = getMaxXCoord(inputLines);
        int maxYCoord = getMaxYCoord(inputLines);
        String[][] movieTheaterFloor = new String[maxXCoord][maxYCoord];

        //fill in reds
        for (int i=0; i<inputLines.size(); i++){
            String[] coord = inputLines.get(i).split(",");
            movieTheaterFloor[Integer.parseInt(coord[0])][Integer.parseInt(coord[1])] = "X";
        }

        //fill in adjacent greens
        for (int i=0; i<maxXCoord; i++){
            for (int j=0; j<maxYCoord; j++){
                if (xIsBetweenReds(i, movieTheaterFloor) || yIsBetweenReds(i, j, movieTheaterFloor)){
                    movieTheaterFloor[i][j] = "O";
                }
            }
        }

        //fill in interior greens
        for (int i=0; i<maxXCoord; i++){
            for (int j=0; j<maxYCoord; j++){
                if (isSurroundedByRedsOrGreens(i, j, movieTheaterFloor)){
                    movieTheaterFloor[i][j] = "O";
                }
            }
        }

        for (int i=0; i<inputLines.size(); i++){
            String[] firstCoord = inputLines.get(i).split(",");
            for (int j=i+1; j<inputLines.size(); j++){
                String[] secondCoord = inputLines.get(j).split(",");
                long rectangleArea = calculateRectangleArea(firstCoord, secondCoord);
                if (rectangleArea > largestRectangleArea && rectangleIsRedAndGreenOnly(firstCoord, secondCoord, movieTheaterFloor)){
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

    private static boolean rectangleIsRedAndGreenOnly(String[] firstCoord, String[] secondCoord, String[][] movieTheaterFloor){
        int minXCoord = Math.min(Integer.parseInt(firstCoord[0]), Integer.parseInt(secondCoord[0]));
        int maxXCoord = Math.max(Integer.parseInt(firstCoord[0]), Integer.parseInt(secondCoord[0]));
        int minYCoord = Math.min(Integer.parseInt(firstCoord[1]), Integer.parseInt(secondCoord[1]));
        int maxYCoord = Math.max(Integer.parseInt(firstCoord[1]), Integer.parseInt(secondCoord[1]));

        for (int i=minXCoord; i<maxXCoord; i++){
            for (int j=minYCoord; j<maxYCoord; j++){
                if (!movieTheaterFloor[i][j].equals("X") && !movieTheaterFloor[i][j].equals("O")){
                    return false;
                }
            }
        }

        return true;
    }

    private static int getMaxXCoord(List<String> inputLines){
        int maxXCoord = 0;

        for (int i=0; i<inputLines.size(); i++){
            String[] coord = inputLines.get(i).split(",");
            if (Long.parseLong(coord[0]) > maxXCoord){
                maxXCoord = Integer.parseInt(coord[0]);
            }
        }

        return maxXCoord;
    }

    private static int getMaxYCoord(List<String> inputLines){
        int maxYCoord = 0;

        for (int i=0; i<inputLines.size(); i++){
            String[] coord = inputLines.get(i).split(",");
            if (Long.parseLong(coord[1]) > maxYCoord){
                maxYCoord = Integer.parseInt(coord[1]);
            }
        }

        return maxYCoord;
    }

    private static boolean xIsBetweenReds(int x, String[][] movieTheaterFloor){
        String[] row = movieTheaterFloor[x];
        boolean redToTheLeft = false;
        boolean redToTheRight = false;
        for (int i=0; i<row.length; i++){
            if (i<x && (row[i].equals("X"))){
                redToTheLeft = true;
            }
            if (i>x && (row[i].equals("X"))){
                redToTheRight = true;
            }
        }
        return redToTheLeft && redToTheRight;
    }

    private static boolean yIsBetweenReds(int x, int y, String[][] movieTheaterFloor){
        boolean redToTheTop = false;
        boolean redToTheBottom = false;
        for (int i=0; i<movieTheaterFloor.length; i++){
            if (i<y && (movieTheaterFloor[x][i].equals("X"))){
                redToTheTop = true;
            }
            if (i>x && (movieTheaterFloor[x][i]).equals("X")){
                redToTheBottom = true;
            }
        }
        return redToTheTop && redToTheBottom;
    }

    private static boolean isSurroundedByRedsOrGreens(int x, int y, String[][] movieTheaterFloor){
        String[] row = movieTheaterFloor[x];
        boolean redToTheLeft = false;
        boolean redToTheRight = false;
        for (int i=0; i<row.length; i++){
            if (i<x && (row[i].equals("X") || row[i].equals("O"))){
                redToTheLeft = true;
            }
            if (i>x && (row[i].equals("X") || row[i].equals("O"))){
                redToTheRight = true;
            }
        }

        boolean redToTheTop = false;
        boolean redToTheBottom = false;
        for (int i=0; i<movieTheaterFloor.length; i++){
            if (i<y && (movieTheaterFloor[x][i].equals("X") || movieTheaterFloor[x][i].equals("O"))){
                redToTheTop = true;
            }
            if (i>x && (movieTheaterFloor[x][i]).equals("X") || movieTheaterFloor[x][i].equals("O")){
                redToTheBottom = true;
            }
        }

        return redToTheLeft && redToTheRight && redToTheTop && redToTheBottom;
    }
}
