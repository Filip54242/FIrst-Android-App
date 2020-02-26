package com.example.lost_game;

import java.util.*;

public class ImageRandomiser {


    private String[] avalableColors = new String[]{
            "RED", "BLUE", "BLACK", "LIGHTBLUE", "YELLOW", "GREEN"
    };
    private String[] directions = new String[]{
            "RIGHT", "LEFT", "TOP", "BOTTOM"
    };
    private Set<CoordinatePair> coordinateSet;

    public class CoordinatePair {
        public int x;
        public int y;

        CoordinatePair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            CoordinatePair _obj = (CoordinatePair) obj;
            return this.x == _obj.x && this.y == _obj.y;
        }
    }

    public ImageContainer container;
    private int starColumn;
    private int startRow;

    public int getStartRow()
    {
        return startRow;
    }

    public int getStartColumn()
    {
        return starColumn;
    }


    private String[] getTheTwoColors() {
        Random rand = new Random();
        String[] neededColors = new String[2];
        neededColors[0] = neededColors[1] = avalableColors[rand.nextInt(6)];
        while (neededColors[0].equals(neededColors[1])) {
            neededColors[1] = avalableColors[rand.nextInt(6)];
        }

        return neededColors;
    }

    private String getDirection() {
        Random rand = new Random();
        return directions[rand.nextInt(4)];
    }

    private void makeRandomCoordinates(int firstBound, int secondBound, String color) {
        Random rand = new Random();
        CoordinatePair pair = new CoordinatePair(-1, -1);

        while (pair.x < 0 || pair.x > 5 || pair.y < 0 || pair.y > 5 || coordinateSet.contains(pair)) {
            pair.x = startRow + rand.nextInt(firstBound) - secondBound;
            pair.y = starColumn + rand.nextInt(firstBound) - secondBound;
        }
        container.matrix[pair.x][pair.y] = getDirection() + "," + color;
        coordinateSet.add(pair);
    }
    private void setEmpty()
    {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                container.matrix[row][column] = "EMPTY";
            }
        }
    }


    private Boolean checkMatrix()
    {
        int count=0;
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                if(!container.matrix[row][column].equals( "EMPTY")){
                    count++;
                }
            }
        }
        return count==5;
    }

    public void generateMatrix() {
        Random rand = new Random();
        container = new ImageContainer();
        container.matrix = new String[6][6];
        coordinateSet = new HashSet<>();
        String[] colors = getTheTwoColors();

        setEmpty();
        while(!checkMatrix()){
           setEmpty();
        switch (rand.nextInt(4)){


            case 0:
                starColumn = 2;
                startRow = 2;
            break;
            case 1:
                starColumn = 2;
                startRow = 3;
                break;
            case 2:
                starColumn = 3;
                startRow = 2;
                break;
            case 3:
                starColumn = 3;
                startRow = 3;
                break;
        }

        container.correctMove = container.matrix[startRow][starColumn] = getDirection();
        container.matrix[startRow][starColumn] += "," + colors[0];
        coordinateSet.add(new CoordinatePair(startRow, starColumn));
        makeRandomCoordinates(3, 1, colors[1]);
        makeRandomCoordinates(3, 1, colors[1]);

        makeRandomCoordinates(5, 2, colors[1]);
        makeRandomCoordinates(5, 2, colors[1]);


    }
    }

}



