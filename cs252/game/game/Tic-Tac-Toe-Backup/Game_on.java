import java.util.*;
import java.io.*;
import java.awt.*;
import java.net.*;
/**
 * Tic-Tac-Toe
 * 
 * Currently Implemented
 * 
 * 2-D
 * 3-D
 * 4-D
 * 
 * To Do:
 * Firebase
 * Database
 * Gui
 * 
 * Zachary M Soehren
 * 
 * zsoehren@purdue.edu
 * 
 */

public class Game_on{
  private int size = 3;
  
  public static void main(String args[]){
    new Game_on();
  }
  
  public Game_on(){
    Scanner stdin = new Scanner(System.in);
    while(true){
      System.out.println("Host: ");
      String host = "data.cs.purdue.edu";//stdin.next();
      System.out.println("port: ");
      int port = 5432;//stdin.nextInt();
      Socket server;
      PrintWriter out;
      BufferedReader in;
      int player;
      String response;
      
      
      try{
        server = new Socket(host, port);
        out = new PrintWriter(server.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
      }catch(IOException e){
        System.out.println("Error!");
        return;
      }
      
      try{
        response = in.readLine();
        if(Integer.parseInt(response)==1){
          player = 1;
        }else{
          player = 2;
        }
        int input;
        if(player == 1){
          response = in.readLine();
          if(Integer.parseInt(response)!=2){
            System.out.println("Player two has not connected successfully.");
          }
          
          System.out.println("Please select game type");
          System.out.println("1: 2-D game");
          System.out.println("2: 3-D game");
          System.out.println("3: 4-D game");
          input = 0;
          do{
            input = stdin.nextInt();
            if(input == 1){
              out.println("1");
              game_mode_1(stdin, player, server, out, in);
              return;
            }else if(input == 2){
              out.println("2");
              game_mode_2(stdin, player, server, out, in);
              return;
            }else if(input == 3){
              out.println("3");
            game_mode_3(stdin, player, server, out, in);
            return;
          }else{
            input = 0;
            System.out.println("Invalid input");
          }
          }while(input == 0);
        }else{
          input = Integer.parseInt(in.readLine());
          if(input == 1){
            game_mode_1(stdin, player, server, out, in);
            return;
          }else if(input == 2){
            game_mode_2(stdin, player, server, out, in);
            return;
          }else if(input == 3){
            game_mode_3(stdin, player, server, out, in); 
            return;
          }
        }
      }catch(IOException e){
      }
    }
  }
  
  private void game_mode_1(Scanner stdin, int player, Socket server, PrintWriter out, BufferedReader in){
    try{
      System.out.println("Starting 2-D game");
      int level;
      if(player == 1){
        System.out.println("How large do you want the board?");
        boolean invalid_input;
        do{
          level = stdin.nextInt();
          if(level < 3){
            System.out.println("That board is too small, please choose 3 or 4");
          }else if(level > 4){
            System.out.println("That board is too big, please choose 3 or 4");
          }
        }while(level < 3 || level > 4);
        out.println(Integer.toString(level));
      }else{
        level = Integer.parseInt(in.readLine());
      }
      size = level;
      
      
      
      Board_2d board = new Board_2d(level);
      int xloc, yloc, has_won;
      System.out.println("Current Board:");
      System.out.println("");
      board.print_Board();
      int turn = 2;
      Boolean invalid_input;
      
      while(true){
        turn=turn%2+1;
        if(turn == player){
          System.out.println("Please take your turn");
          do{
            invalid_input = false;
            xloc = stdin.nextInt();
            yloc = stdin.nextInt();
            if(xloc>=size || xloc < 0 || yloc >= size || yloc < 0){
              invalid_input = true;
            }else if(board.piece_at(xloc, yloc)!=0){
              invalid_input = true;
            }
          }while(invalid_input);
          out.println(Integer.toString(xloc));
          out.println(Integer.toString(yloc));
        }else{
          System.out.println("Waiting on other player");
          xloc = Integer.parseInt(in.readLine());
          if(xloc==-1){
        	  System.out.println("The other player has exited the game early");
        	  System.out.println("You won a hollow victory.");
        	  try{
        		  out.close();
        		  in.close();
        		  server.close();
        	  }catch(Exception f){
        	  }
        	  return;
          }
          yloc = Integer.parseInt(in.readLine());
          System.out.println("Other player has moved");
        }
        has_won = board.add_piece(xloc, yloc, turn);
        if(has_won > 0){
          System.out.print("Player "+turn+" has won with "+has_won+" tic-tac-toe");
          if(has_won > 1){
            System.out.print("s");
          }
          System.out.println(".");
          board.print_Board();
          out.println("Bye.");
          return;
        }
        board.print_Board();
      }
    }catch(IOException e){
    }
  }
  
  
    private void game_mode_2(Scanner stdin, int player, Socket server, PrintWriter out, BufferedReader in){
    try{
      System.out.println("Starting 3-D game");
      int level;
      if(player == 1){
        System.out.println("How large do you want the board?");
        boolean invalid_input;
        do{
          level = stdin.nextInt();
          if(level < 3){
            System.out.println("That board is too small, please choose 3 or 4");
          }else if(level > 4){
            System.out.println("That board is too big, please choose 3 or 4");
          }
        }while(level < 3 || level > 4);
        out.println(Integer.toString(level));
      }else{
        level = Integer.parseInt(in.readLine());
      }
      size = level;
      
      
      
      Board_3d board = new Board_3d(level);
      int xloc, yloc, zloc, has_won;
      System.out.println("Current Board:");
      System.out.println("");
      board.print_Board();
      int turn = 2;
      Boolean invalid_input;
      
      while(true){
        turn=turn%2+1;
        if(turn == player){
          System.out.println("Please take your turn");
          do{
            invalid_input = false;
            xloc = stdin.nextInt();
            yloc = stdin.nextInt();
            zloc = stdin.nextInt();
            if(xloc>=size || xloc < 0 || yloc >= size || yloc < 0 || zloc >= size || zloc < 0){
              invalid_input = true;
            }else if(board.piece_at(xloc, yloc, zloc)!=0){
              invalid_input = true;
            }
          }while(invalid_input);
          out.println(Integer.toString(xloc));
          out.println(Integer.toString(yloc));
          out.println(Integer.toString(zloc));
        }else{
          System.out.println("Waiting on other player");
          xloc = Integer.parseInt(in.readLine());
          yloc = Integer.parseInt(in.readLine());
          zloc = Integer.parseInt(in.readLine());
          System.out.println("Other player has moved");
        }
        has_won = board.add_piece(xloc, yloc, zloc, turn);
        if(has_won > 0){
          System.out.print("Player "+turn+" has won with "+has_won+" tic-tac-toe");
          if(has_won > 1){
            System.out.print("s");
          }
          System.out.println(".");
          board.print_Board();
          out.println("Bye.");
          return;
        }
        board.print_Board();
      }
    }catch(IOException e){
    }
  }
  
  private void game_mode_3(Scanner stdin, int player, Socket server, PrintWriter out, BufferedReader in){
    try{
      System.out.println("Starting 4-D game");
      int level;
      if(player == 1){
        System.out.println("How large do you want the board?");
        boolean invalid_input;
        do{
          level = stdin.nextInt();
          if(level < 3){
            System.out.println("That board is too small, please choose 3 or 4");
          }else if(level > 4){
            System.out.println("That board is too big, please choose 3 or 4");
          }
        }while(level < 3 || level > 4);
        out.println(Integer.toString(level));
      }else{
        level = Integer.parseInt(in.readLine());
      }
      size = level;
      
      
      
      Board_4d board = new Board_4d(level);
      int xloc, yloc, zloc, hloc, has_won;
      System.out.println("Current Board:");
      System.out.println("");
      board.print_Board();
      int turn = 2;
      Boolean invalid_input;
      
      while(true){
        turn=turn%2+1;
        if(turn == player){
          System.out.println("Please take your turn");
          do{
            invalid_input = false;
            xloc = stdin.nextInt();
            yloc = stdin.nextInt();
            zloc = stdin.nextInt();
            hloc = stdin.nextInt();
            if(xloc>=size || xloc < 0 || yloc >= size || yloc < 0 || zloc >= size || zloc < 0 || hloc >= size || hloc < 0){
              invalid_input = true;
            }else if(board.piece_at(xloc, yloc, zloc, hloc)!=0){
              invalid_input = true;
            }
          }while(invalid_input);
          out.println(Integer.toString(xloc));
          out.println(Integer.toString(yloc));
          out.println(Integer.toString(zloc));
          out.println(Integer.toString(hloc));
        }else{
          System.out.println("Waiting on other player");
          xloc = Integer.parseInt(in.readLine());
          yloc = Integer.parseInt(in.readLine());
          zloc = Integer.parseInt(in.readLine());
          hloc = Integer.parseInt(in.readLine());
          System.out.println("Other player has moved");
        }
        has_won = board.add_piece(xloc, yloc, zloc, hloc, turn);
        if(has_won > 0){
          System.out.print("Player "+turn+" has won with "+has_won+" tic-tac-toe");
          if(has_won > 1){
            System.out.print("s");
          }
          System.out.println(".");
          board.print_Board();
          out.println("Bye.");
          return;
        }
        board.print_Board();
      }
    }catch(IOException e){
    }
  }
}


        
    