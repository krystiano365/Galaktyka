/*
-------> y = width
|
|
\/ x = height
*/

import java.util.Map;

public class Galaktyka {

    static boolean directionHasChanged = false;
    static boolean directionHasPreviouslyChanged = false;
    static int width;
    static int height;
    static char[][] drawingTable;

    static void printArray() {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                System.out.print(drawingTable[x][y]);
            }
            System.out.print('\n');
        }
    }

    static boolean areStarsAhead(int x, int y) {
        try{
            if(drawingTable[x][y] == '*') return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    static int[] pickInitialPoint(char direction){
        switch (direction) {
            case 'W': return (new int[] {height-1, width-1});
            case 'E': return (new int[] {0, 0});
            case 'N': return (new int[] {height-1, 0});
            case 'S': return (new int[] {0, width-1});
            default: return new int[] {-1, -1};
        }
    }

    public static void main(String[] args) {
        if (args.length != 1){ //checking how many arguments were passed
            System.out.println("klops");
            return;
        }
        String str = new String(args[0]);
        if (!str.matches("\\d{1,4}[NESW]{1}")) {
            System.out.println("klops");
            return;
        }
        int size = Integer.parseInt(str.replaceAll("\\D", ""));
        if (size < 1 || size > 1000){
            System.out.println("klops");
            return;
        }
        char direction = str.charAt(str.length() - 1);

        if (direction == 'N' || direction == 'S'){
            width = size + 3;
            height = size + 2;
        } else {
            width = size + 2;
            height = size + 3;
        }

        drawingTable = new char[height][width];
        //initialize with spaces
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                drawingTable[x][y] = ' ';
            }
        }

        int maxY = width - 1;
        int maxX = height - 1;
        int x = pickInitialPoint(direction)[0], y = pickInitialPoint(direction)[1];
        int starCounter = 0;
        while (true) {

            if(!directionHasChanged){
                drawingTable[x][y] = '*';
                starCounter++;
            }

            try {
                // move forward:
                switch (direction) {
                    case 'W':
                        if (areStarsAhead(x, y-2) || y == 0){
                            direction = 'N';
                            directionHasChanged = true;
                        }
                        else {
                            y--;
                            directionHasChanged = false;
                        }
                        break;
                    case 'E':
                        if (areStarsAhead(x, y+2) || y == maxY){
                            direction = 'S';
                            directionHasChanged = true;
                        }
                        else {
                            y++;
                            directionHasChanged = false;
                        }
                        break;
                    case 'N':
                        if (areStarsAhead(x-2, y) || x == 0){
                            direction = 'E';
                            directionHasChanged = true;
                        }
                        else {
                            x--;
                            directionHasChanged = false;
                        }
                        break;
                    case 'S':
                        if (areStarsAhead(x+2, y) || x == maxX){
                            direction = 'W';
                            directionHasChanged = true;
                        }
                        else {
                            x++;
                            directionHasChanged = false;
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if(!directionHasChanged) directionHasPreviouslyChanged = false;

            if(directionHasPreviouslyChanged){
                break;
            }

            if (directionHasChanged) {
                directionHasPreviouslyChanged = true;
            }
        }
        printArray();
        System.out.println(width*height - starCounter);
    }
}
