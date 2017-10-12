package com.codewars;

public class BattleField {
    static int[][] fieldBase;
    static boolean properArrangement = true;
    public static boolean fieldValidator(int[][] field) {
        fieldBase = field;

        properArrangement = arrayDimensionsCheck(field);
        properArrangement = shipCounter(field);
        findShips(fieldBase);
        return properArrangement;
    }
    public static void findShips(int[][] field) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 1) {
                    if (field[i][j + 1] == 1) {
                        markHorizontalValidation(i, j, field);
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 1) {
                    if (field[i + 1][j] == 1) {
                        markVerticalValidation(i, j, field);
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 1) {
                    field[i][j] = 2;
                    checkParticularCell(i, j, field);
                }
            }
        }
    }
    public static void markHorizontalValidation(int row, int column, int[][] field) {
        for (int i = 0; field[row][column + i] == 1; i++) {
            field[row][column + i] = 2;
        }
        for (int i = 0; field[row][column + i] == 2; i++) {
            checkCellHorizontal(row, column, field);
        }
    }
    public static void markVerticalValidation(int row, int column, int[][] field) {
        for (int i = 0; field[row + i][column] == 1; i++) {
            field[row + i][column] = 2;
        }
        for (int i = 0; field[row + i][column] == 2; i++) {
            checkCellVertical(row, column, field);
        }
    }
    public static void checkCellHorizontal(int row, int column, int[][] field) {
        int i = 0;
        while (field[row][column + i] == 2) {
            checkParticularCell(row, column + i, field);
            i++;
        }
    }

    public static void checkCellVertical(int row, int column, int[][] field) {
        int i = 0;
        while (field[row + i][column] == 2) {
            checkParticularCell(row + i, column, field);
            i++;
        }
    }
    public static void checkParticularCell(int row, int column, int[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    if (field[row - 1 + i][column - 1 + j] == 1) {
                        properArrangement = false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
    }
    public static boolean arrayDimensionsCheck(int field[][]) {
        for (int i = 9; i < 10; i++) {
            for (int j = 10; j < 11; j++) {
                try {
                    if (field[i][j] == 0 || field[i][j] == 1) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return true;
    }
   /* public static void singleCells(int row, int column, int[][] field) {
        field[row][column] = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    if (field[row - 1 + i][column - 1 + j] != 0) {
                        properArrangement = false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        field[row][column] = 2;
    } */
    public static boolean shipCounter(int field[][]) {
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 1) {
                    counter++;
                }
            }
        }
        if (counter == 20) {
            return true;
        } else {
            return false;
        }
    }
}