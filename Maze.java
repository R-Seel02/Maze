package MazeProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {

    public static final char START = 'S';
    public static final char FINISH = 'F';
    public static final char WALL = 'X';
    public static final char CLEAR = ' ';
    public static final char PATH = 'P';
    public static final char VISIT = 'V';

    public static void main(String args[]) {

        String inputLine;
        Scanner fileInput;

        char[][] maze = new char[7][20];
        char ch;

        int fileWidth = 0;
        int fileHeight = 0;
        int endCol = 0;
        int startCol = 0;
        File inFile = new File("MazeProject/maze.txt");

        try {

            fileInput = new Scanner(inFile); // instance variables 
            fileWidth = fileInput.nextInt();
            fileHeight = fileInput.nextInt();
            endCol = fileInput.nextInt();
            // 0 is assumed for the ending row

            fileInput.nextInt();
            startCol = fileInput.nextInt();

            fileInput.nextLine();

            maze = new char[fileHeight][fileWidth]; // takes in the height and width of the maze (any maze) and puts the information into a 2d array

            for (int row = 0; row < fileHeight; row++) {
                if (fileInput.hasNextLine()) {
                    String finLine = fileInput.nextLine();
                    for (int col = 0; col < fileWidth; col++) {
                        if (row == 0) {
                            if (finLine.charAt(col) == CLEAR) { // if first row, define finish cell
                                maze[row][col] = FINISH;
                            } else {
                                maze[row][col] = finLine.charAt(col);
                            }
                        } else if (row == fileHeight - 1) {
                            if (finLine.charAt(col) == CLEAR) { // if last row, define start cell
                                maze[row][col] = START;
                            } else {
                                maze[row][col] = finLine.charAt(col);
                            }
                        } else {
                            maze[row][col] = finLine.charAt(col); // if any other row in between, populate the maze with
                                                                  // the contents of the column
                        }
                    }
                }
            }
            mazePrint(maze);

            move(maze, maze.length-1, startCol);
            

            fileInput.close();

        } // end try
        catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1); // IO error; exit program
        } // end catch

        System.out.println("end of program");
    }

    public static void mazePrint(char[][] arrayToPrint) {
        // .length is the lenght of file, [0].length is the width of file
        for (int row = 0; row < arrayToPrint.length; row++) { // iterate over height
            for (int col = 0; col < arrayToPrint[0].length; col++) { // iterate over width
                System.out.print(arrayToPrint[row][col]); // populate the empty line with contents of the maze
            }
            System.out.println(); // start a new blank line every row
        }

    }

    public static boolean goNorth(char[][] maze, int row, int col) { // logic for going north
        boolean success = false; //boolean variable responsible for tracking movement success  logic is idential for the rest of the go"" methods
        System.out.println(maze[row-1][col]);
        if (maze[row - 1][col] == CLEAR ||maze[row - 1][col] == FINISH ) { //checks if the next row is open and ready to be moved in 
            row = row - 1;
            maze[row][col] = PATH; //prints the P char that shows where we have moved
            success = true;
           
        } else {
            success = false;

        }
        return success;

    }

    public static boolean goSouth(char[][] maze, int row, int col) {
        boolean success = false;
        if (maze[row + 1][col] == CLEAR) { //if the movement south is open then we move there
            row = row + 1;
            maze[row][col] = PATH;
            success = true; 
           

        } else {
            success = false;

        }
        return success;

    }

    public static boolean goWest(char[][] maze, int row, int col) {
        boolean success = false;
        if (maze[row][col-1] == CLEAR) {
            col = col - 1;
            maze[row][col] = PATH;
            success = true;
            
        } else {
            success = false;

        }
        return success;

    }

    public static boolean goEast(char[][] maze, int row, int col) {
        boolean success = false;
        if (maze[row][col + 1] == CLEAR) {
            col = col + 1;
            maze[row][col] = PATH;
            success = true;
            
        } else {
            success = false;

        }
        return success;

    }
    
    public static void move(char [][] maze, int row,int col){ //move is responsible for applying the methods for each direction, every case is uses the logic 
        mazePrint(maze);
        System.out.println(row);
        if (row == 0){
            return;
        }
        if (goNorth(maze, row, col)){
            move(maze, row-1, col);
            
        } else if( goEast(maze, row, col)){
            move(maze, row, col+1);
        }
        else if( goWest(maze, row, col)){
            move(maze, row, col-1);
        }
        else if(goSouth(maze, row, col)){
            move(maze, row+1, col);
        }
    }

}


