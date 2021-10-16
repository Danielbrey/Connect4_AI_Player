import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniMax extends Player{

  String id;
  // int currentDepth;
  int INF = 100000000;

  public MiniMax(int maxDepth, String stratID) {
    super(maxDepth);
    this.id = stratID;
  }

  public double MiniMaxSearch(Board currentBoard, boolean maxTurn, PlayerEnum player, int currentDepth, PlayerEnum origPlayer){ // agent input
    //return column, move = number of moves down tree, maxmin is max if 1, min if 0
    // System.out.println("here");
    moveCounter++;
    // currentDepth++;
    /*PlayerEnum opponent;
    if(player == PlayerEnum.PLAYER1){
      opponent = PlayerEnum.PLAYER2;
    } else{
      opponent = PlayerEnum.PLAYER1;
    }*/

    // System.out.println("depth: " + currentDepth);

    GameStatusEnum gameStatus = currentBoard.gameStatus();


    // System.out.println(gameStatus);
    PlayerEnum nextPlayer = nextPlayer(player);
    if(gameStatus != GameStatusEnum.ONGOING) {
      return (player == origPlayer ? INF : -1 * INF);
    }

    if(currentDepth >= maxDepth){
      double score = CalculateScore(currentBoard.board, id, player); //column, then score (0 since doesn't matter)
      return score;
    }


    ArrayList<Board> Children = currentBoard.ExpandChildren(nextPlayer);
    // Children.get(0).printBoard();
    // Children.forEach((move) -> {
    //   move.printBoard();
    // });
    List<Double> scores = new ArrayList<Double>();
    for (Board move : Children) {
      double score = MiniMaxSearch(move, !maxTurn, nextPlayer, currentDepth+1, player);
      scores.add(score);
    }
    if(!maxTurn){ //max = 1
      return Collections.min(scores);
    } else {
      return Collections.max(scores);
    }
  }

  @Override
  protected double treeEval(Board board, PlayerEnum nextPlayer) {
    // currentDepth = 0;
    return MiniMaxSearch(board, true, nextPlayer, 1, nextPlayer);
  }
}
