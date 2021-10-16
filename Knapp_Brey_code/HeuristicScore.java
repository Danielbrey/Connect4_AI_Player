/*
Heuristic Score
Takes in a board (6x7 Array of Strings), the player ("X" or "O") a agent's type ("defensive", "offensive", etc), and calculates the score
*/

import java.lang.Math;

public class HeuristicScore {

  public static int CalculateScore(PlayerEnum[][] board, String agent, PlayerEnum player){

    //Learn features of board
    int[] potentials = CountPotentials(board, player);
    int numOurOnes = potentials[0];
    int numOurTwos = potentials[1];
    int numOurThrees = potentials[2];
    int numOurFours = potentials[3];
    int numTheirOnes = potentials[4];
    int numTheirTwos = potentials[5];
    int numTheirThrees = potentials[6];
    int numTheirFours = potentials[7];
    // System.out.println("our 4s: " + numOurFours);
    // System.out.println("their 4s: " + numTheirFours);

    //Calculate and return score based on agent
    if(agent.equals("Offensive")){
      return 100* (2*numOurOnes + 5*numOurTwos + 20*numOurThrees  + 1000000*numOurFours - (numTheirOnes + numTheirTwos + 1000*numTheirThrees + 1000000 * numTheirFours));
    } else if(agent.equals("Defensive")){
      return numOurOnes + 2*numOurTwos + 10*numOurThrees + 1000000*numOurFours - (numTheirOnes + 20*numTheirTwos + 1000*numTheirThrees + 1000000 * numTheirFours);
    } else if(agent.equals("Balanced")){
      return numOurOnes + 3*numOurTwos + 15*numOurThrees + 1000000*numOurFours - (numTheirOnes + 2*numTheirTwos + 1000*numTheirThrees + 1000000 * numTheirFours);
    } else if(agent.equals("Location")){
      int[] locationValues = CheckLocation(board, player);
      int ourDistanceFromCenter = locationValues[0];
      int theirDistanceFromCenter = locationValues[1];
      int numOurTop = locationValues[2];
      int numTheirTop = locationValues[3];

      return numOurOnes + 3*numOurTwos + 15*numOurThrees + 1000000*numOurFours - (numTheirOnes + 2*numTheirTwos + 100*numTheirThrees + 1000000 * numTheirFours) + 10*(numOurTop - numTheirTop) - 10 *(ourDistanceFromCenter - theirDistanceFromCenter);
    }
    return -1;
  }





  public static int[] CountPotentials(PlayerEnum[][] board, PlayerEnum player){

    PlayerEnum opponent;
    if(player == PlayerEnum.PLAYER1){
      opponent = PlayerEnum.PLAYER2;
    } else{
      opponent = PlayerEnum.PLAYER1;
    }

    int numOurOnes = 0;
    int numOurTwos = 0;
    int numOurThrees = 0;
    int numOurFours = 0;
    int numTheirOnes = 0;
    int numTheirTwos = 0;
    int numTheirThrees = 0;
    int numTheirFours = 0;

    //Iterate through board, looking for potential lines
    for(int i = 0; i < 6; i++){
      for(int j = 0; j < 7; j++){
          if(i > 2){
            int resultUpUs = CheckFour(board, "up", player, i, j);
            if(resultUpUs == 1){
              numOurOnes ++;
            } else if(resultUpUs == 2){
              numOurTwos ++;
            } else if(resultUpUs == 3){
              numOurThrees ++;
            } else if(resultUpUs == 4){
              numOurFours ++;
            }
            int resultUpThem = CheckFour(board, "up", opponent, i, j);
            if(resultUpThem == 1){
              numTheirOnes ++;
            } else if(resultUpThem == 2){
              numTheirTwos ++;
            } else if(resultUpThem == 3){
              numTheirThrees ++;
            } else if(resultUpThem == 4){
              numTheirFours ++;
            }
          }
          if(j <= 3){
            int resultRightUs = CheckFour(board, "right", player, i, j);
            if(resultRightUs == 1){
              numOurOnes ++;
            } else if(resultRightUs == 2){
              numOurTwos ++;
            } else if(resultRightUs == 3){
              numOurThrees ++;
            } else if(resultRightUs == 4){
              numOurFours ++;
            }
            int resultRightThem = CheckFour(board, "right", opponent, i, j);
            if(resultRightThem > 3)
              System.out.println(resultRightThem);
            if(resultRightThem == 1){
              numTheirOnes ++;
            } else if(resultRightThem == 2){
              numTheirTwos ++;
            } else if(resultRightThem == 3){
              numTheirThrees ++;
            } else if(resultRightThem == 4){
              numTheirFours ++;
            }
          }
          if((i > 2) && (j<=3)){
            int resultDiagonalUpUs = CheckFour(board, "diagonalUp", player, i, j);
            if(resultDiagonalUpUs == 1){
              numOurOnes ++;
            } else if(resultDiagonalUpUs == 2){
              numOurTwos ++;
            } else if(resultDiagonalUpUs == 3){
              numOurThrees ++;
            } else if(resultDiagonalUpUs == 4){
              numOurFours ++;
            }
            int resultDiagonalUpThem = CheckFour(board, "diagonalUp", opponent, i, j);
            if(resultDiagonalUpThem == 1){
              numTheirOnes ++;
            } else if(resultDiagonalUpThem == 2){
              numTheirTwos ++;
            } else if(resultDiagonalUpThem == 3){
              numTheirThrees ++;
            } else if(resultDiagonalUpThem == 4){
              numTheirFours ++;

            }
          }
          if((i < 3) && (j<=3)){
            int resultDiagonalDownUs = CheckFour(board, "diagonalDown", player, i, j);
            if(resultDiagonalDownUs == 1){
              numOurOnes ++;
            } else if(resultDiagonalDownUs == 2){
              numOurTwos ++;
            } else if(resultDiagonalDownUs == 3){
              numOurThrees ++;
            } else if(resultDiagonalDownUs == 4){
              numOurFours ++;
            }
            int resultDiagonalDownThem = CheckFour(board, "diagonalDown", opponent, i, j);
            if(resultDiagonalDownThem == 1){
              numTheirOnes ++;
            } else if(resultDiagonalDownThem == 2){
              numTheirTwos ++;
            } else if(resultDiagonalDownThem == 3){
              numTheirThrees ++;
            } else if(resultDiagonalDownThem == 4){
              numTheirFours ++;
            }
          }
      }
    }

    int[] potentials = {numOurOnes, numOurTwos, numOurThrees, numOurFours, numTheirOnes, numTheirTwos, numTheirThrees, numTheirFours};
    return potentials;
  }





  public static int[] CheckLocation(PlayerEnum[][] board, PlayerEnum player){
    PlayerEnum opponent;
    if(player == PlayerEnum.PLAYER1){
      opponent = PlayerEnum.PLAYER2;
    } else{
      opponent = PlayerEnum.PLAYER1;
    }


    int ourDistanceFromCenter = 0;
    int theirDistanceFromCenter = 0;
    int numOurTop = 0;
    int numTheirTop = 0;

    for(int i = 0; i <7; i++){
      for(int j =0; j <6; j++){
        if(board[j][i] == player){
          ourDistanceFromCenter += Math.abs(i-3);
        } else if(board[j][i] == opponent){
          theirDistanceFromCenter += Math.abs(i-3);
        }
      }
    }

    for(int i=0; i<7; i++){ //for each column, check top piece
      for(int j = 0; j < 6; j++){
        if(board[j][i] != PlayerEnum.EMPTY){
          if(board[j][i] == player){
            numOurTop++;
          } else{
            numTheirTop++;
          }
          break;
        }
      }
    }

    int[] values = {ourDistanceFromCenter, theirDistanceFromCenter, numOurTop, numTheirTop};

    return values;

  }



  public static int CheckFour(PlayerEnum[][] board, String operation, PlayerEnum player, int startY, int startX){
    int numOurs = 0;
    if(operation.equals("up")){
      if(board[startY][startX] == player){
        numOurs++;
      } else if(board[startY][startX] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY-1][startX] == player){
        numOurs++;
      } else if(board[startY-1][startX] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY-2][startX] == player){
        numOurs++;
      } else if(board[startY-2][startX] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY-3][startX] == player){
        numOurs++;
      } else if(board[startY-3][startX] != PlayerEnum.EMPTY){
        return 0;
      }
    } else if(operation.equals("right")){
      if(board[startY][startX] == player){
        numOurs++;
      } else if(board[startY][startX] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY][startX+1] == player){
        numOurs++;
      } else if(board[startY][startX+1] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY][startX+2] == player){
        numOurs++;
      } else if(board[startY][startX+2] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY][startX+3] == player){
        numOurs++;
      } else if(board[startY][startX+3] != PlayerEnum.EMPTY){
        return 0;
      }
    } else if(operation.equals("diagonalUp")){
      if(board[startY][startX] == player){
        numOurs++;
      } else if(board[startY][startX] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY-1][startX+1] == player){
        numOurs++;
      } else if(board[startY-1][startX+1] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY-2][startX+2] == player){
        numOurs++;
      } else if(board[startY-2][startX+2] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY-3][startX+3] == player){
        numOurs++;
      } else if(board[startY-3][startX+3] != PlayerEnum.EMPTY){
        return 0;
      }
    } else if(operation.equals("diagonalDown")){
      if(board[startY][startX] == player){
        numOurs++;
      } else if(board[startY][startX] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY+1][startX+1] == player){
        numOurs++;
      } else if(board[startY+1][startX+1] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY+2][startX+2] == player){
        numOurs++;
      } else if(board[startY+2][startX+2] != PlayerEnum.EMPTY){
        return 0;
      }
      if(board[startY+3][startX+3] == player){
        numOurs++;
      } else if(board[startY+3][startX+3] != PlayerEnum.EMPTY){
        return 0;
      }
    }
    return numOurs;
  }
}
