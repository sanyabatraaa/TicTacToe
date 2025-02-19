import java.util.*;
public class Proj{
    static class TicTacToe{
        static char[][] board;
        public TicTacToe(){
            board=new char[3][3];
            initBoard();
        }
        void initBoard(){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    board[i][j]=' ';
                }
            }
        }
        static void displayBoard(){
            System.out.println("-------------");
            for(int i=0;i<3;i++){
                System.out.print("| ");
                for(int j=0;j<3;j++){
                   System.out.print(board[i][j]+" | ");
                }
                System.out.println();
                System.out.println("-------------");
            }
        }
        static void placeMark(int row,int col,char mark){
            if(row>=0 && row<=2 && col>=0 && col<=2) board[row][col]=mark;
            else System.out.println("Invalid Input");
        }

        static boolean checkColWin(){
            for(int j=0;j<=2;j++){
                if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]) return true;  
            }
            return false;
        }
        static boolean checkRowWin(){
            for(int i=0;i<=2;i++){
                if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) return true;
            }
            return false;
        }
        static boolean checkDiagonalWin(){
            if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] || board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][2]) return true;
            return false;
        }
        static boolean checkDraw(){
            for(int i=0;i<=2;i++){
                for(int j=0;j<=2;j++){
                    if(board[i][j]==' ') return false;
                }
            }
            return true;
        }
    }
    abstract static class Player{
        String name;
        char mark;
        abstract void makeMove();
        boolean isValidMove(int row,int col){
            if(row>=0 && row<=2 && col>=0 && col<=2){
                if(TicTacToe.board[row][col]!=' ') return false;
            }
            return true;
        }
    }
    static class AIPlayer extends Player{
        AIPlayer(String name,char mark){
            this.name=name;
            this.mark=mark;
        }
        void makeMove(){
            Scanner sc=new Scanner(System.in);
            int row,col;
            do{
                Random r=new Random();
                row=r.nextInt(3);
                col=r.nextInt(3);
            }while(!isValidMove(row,col));
            TicTacToe.placeMark(row,col,mark);
        }
    }
    static class HumanPlayer extends Player{
        HumanPlayer(String name,char mark){
            this.name=name;
            this.mark=mark;
        }
        void makeMove(){
            Scanner sc=new Scanner(System.in);
            int row,col;
            do{
                System.out.println("Enter row and col");
                row=sc.nextInt();
                col=sc.nextInt();
            }while(!isValidMove(row,col));
            TicTacToe.placeMark(row,col,mark);
        }
        
    }  
    public static void main(String[] args){
        TicTacToe t=new TicTacToe();
        HumanPlayer p1=new HumanPlayer("A",'X');
        AIPlayer p2=new AIPlayer("B",'O');
        Player cp;
        cp=p1;
        while(true){
            System.out.println(cp.name +" turn");
            cp.makeMove();
            TicTacToe.displayBoard();
            if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonalWin() ){
                System.out.println(cp.name + " wins");
                break;
            }else if(TicTacToe.checkDraw()){
                System.out.println("Draw");
                break;
            }    
            else{
                if(cp==p1){
                    cp=p2;
                }else{
                    cp=p1;
                }
            }
        }

    }   
    
}
