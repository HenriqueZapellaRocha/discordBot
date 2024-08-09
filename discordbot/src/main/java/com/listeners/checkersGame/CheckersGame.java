package com.listeners.checkersGame;

public class CheckersGame {

    private static CheckersGame checkersGame;
    private String[][] board;

    private CheckersGame() {
        this.board = createBoard();
    }

    public static CheckersGame getInstance() {

        if(checkersGame == null) {
            return new CheckersGame();
        } else {
            return checkersGame;
        }
    }

    private String[][] createBoard() {
        String [][] board = {{"-", "1", "-","1","-","1","-","1"},
                            {"1", "-", "1","-","1","-","1","-"}, 
                            {"-", "1", "-","1","-","1","-","1"},
                            {"-", "-", "-","-","-","-","-","-"},
                            {"-", "-", "-","-","-","-","-","-"},
                            {"-", "2", "-","2","-","2","-","2"},
                            {"2", "-", "2","-","2","-","2","-"}, 
                            {"-", "2", "-","2","-","2","-","2"},
                            };
        return board;
    }

    public void printBoard() {
        for(int i=0; i < 8; i++){
            for(int j=0; j < 8;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
         }
    }

    

    public static CheckersGame getCheckersGame() {
        return checkersGame;
    }

    public String[][] getBoard() {
        return board;
    }

    
}
