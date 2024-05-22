package com.listeners.tictacgame;



public class TicTacLogic {
    //singleton    
    private static TicTacLogic game;
    //"real" atributes
    private String[] board;

    
    // private constructor for the singleton
    private TicTacLogic() {
        this.board = startBoard();
    }

    // get the instace of the class
    public static TicTacLogic getInstance() {
        if (game == null) {
            game = new TicTacLogic();
        }

        return game;
    }

    // just start the board with the standard value
    private String[] startBoard() {
        String[] startBoard = new String[10];

        for(int i= 0; i < startBoard.length;i++) 
            startBoard[i] = " ";

        return startBoard;
    }

    //make play and validate them, with is valide put the type in position and if not validate return false
    public Boolean makePlay(int position, String type) {
        if(board[position] == " ") {
            board[position] = type;
            return true;
        }

        return false;
    }

    //verify if the type entry has a case of win, return true if yes and false if no
    public Boolean verifyWin(String type) {
        //verify the lines
        for(int i=0; i < 9;i+=3) {
            if(board[i] == type && board[i+1] == type && board[i+2] == type)
                return true;
        }
        //verify the collumns
        for(int i=0; i < 3;i++) {
            if(board[i] == type &&  board[i+3] == type && board[i+6] == type)
                return true;
        }
        // veirfy primary diagonal
        if(board[0]==type && board[4]==type && board[8]==type)
            return true;
        //verify secondary diagonal
        if(board[2]==type && board[4]==type && board[6]==type)
            return true;

        return false;
    }

    //this is the minmax funtion who analyze all possible movements in the game 
    private int minMax(Boolean isMaximazing) {
        //verify if the game is ended and return the result
        if(verifyWin("X") == true)
            return -1;
        if(verifyWin("O") == true)
            return 1;
        if(boardIsFull() == true)
            return 0;
        // if its max moment
        if(isMaximazing == true) {
            int bestScore = Integer.MIN_VALUE;
            //analizing all possible movments 
            for(int i=0; i < board.length;i++) {
                if(this.board[i] == " ") {
                    board[i] = "O";
                    int score = minMax(false);
                    board[i] = " ";
                    if(score > bestScore) 
                        bestScore = score;
                }
            
            }
        return bestScore;
        // if is the min moment
        } else {
            int bestScore = Integer.MAX_VALUE;
            //analizing all possible movments 
            for(int i=0; i < board.length;i++) {
                if(this.board[i] == " ") {
                    board[i] = "X";
                    int score = minMax(true);
                    board[i] = " ";
                    if(score < bestScore) 
                        bestScore = score;
                }
            
            }
        return bestScore;
        }

    }
    //function to the machine make movement, starting from the first possible move in the game and calling the minmax to 
    //explore the other possible plays starting from the first
    public void machineMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for(int i =0; i < board.length; i++) {
            if(this.board[i] == " ") {
                board[i] = "O";
                int score = minMax(false);
                board[i] = " ";
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        board[move] = "O";
    }
    //verify if the board is full 
    public boolean boardIsFull() {
        for(int i=0; i < board.length;i++) {
            if(this.board[i]==" ")
                return false;
        }
        return true;
    }

    //make a string buffer tom print the board in the discord 
    public StringBuffer printBoard(){
        StringBuffer printBoard = new StringBuffer();

        for(int i =0; i < this.board.length - 1;i++) {
            if((i+1) % 3 == 0){ 
                printBoard.append(this.board[i]);
                printBoard.append("\n");
            } else{
            printBoard.append(this.board[i] + "|");
            }
        }

        return printBoard;
    }

    public void restartGame(){
        TicTacLogic.game = null;
    }
    

}
