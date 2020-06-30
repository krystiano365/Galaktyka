package krystian;

import java.util.Arrays;
/*
-------> y = width
|
|
\/ x = height
*/

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

    public static void main(String[] args) {
        int size = 5;
        char direction = 'E';

        width = size + 2;
        height = size + 3;
        drawingTable = new char[height][width];
        //initialize with spaces
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                drawingTable[x][y] = ' ';
            }
        }


        int maxY = width - 1;
        int maxX = height - 1;
        int x = 0, y = 0;
        while (true) {

            if(!directionHasChanged){
                drawingTable[x][y] = '*';
            }

            /*
            // change direction if encounters a star
            try{
                if (direction == 'E' && drawingTable[x][y+2] == '*') direction = 'S';
                if (direction == 'W' && drawingTable[x][y-2] == '*') direction = 'N';
                if (direction == 'S' && drawingTable[x+2][y] == '*') direction = 'W';
                if (direction == 'N' && drawingTable[x-2][y] == '*') direction = 'E';
            } catch (Exception e) {
                System.out.println(e.toString());
                continue;
            }*/

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
    }
}
