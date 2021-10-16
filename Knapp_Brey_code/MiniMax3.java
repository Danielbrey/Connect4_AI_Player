import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniMax3 extends Player{

  String id;
  // int currentDepth;

  public MiniMax(int maxDepth, String stratID) {
    super(maxDepth);
    this.id = stratID;
  }

  public double MiniMaxSearch(Board currentBoard, boolean maxTurn, PlayerEnum player, int currentDepth){ // agent input
    //return column, move = number of moves down tree, maxmin is max if 1, min if 0
    // System.out.println("here");
    moveCounter++;
    // currentDepth++;
    PlayerEnum opponent;
    if(player == PlayerEnum.PLAYER1){
      opponent = PlayerEnum.PLAYER2;
    } else{
      opponent = PlayerEnum.PLAYER1;
    }

    // System.out.println("depth: " + currentDepth);

    GameStatusEnum gameStatusBefore = currentBoard.gameStatus();

    // System.out.println(gameStatus);
    if(gameStatusBefore != GameStatusEnum.ONGOING) {
      return CalculateScore(currentBoard.board, id, opponent);
    }

    if(currentDepth >= maxDepth){
      // System.out.println("3s: ");
      double score = CalculateScore(currentBoard.board, id, player); //column, then score (0 since doesn't matter)
      return score;
    }

    PlayerEnum nextPlayer = nextPlayer(player);
    ArrayList<Board> Children = currentBoard.ExpandChildren(nextPlayer);
    // Children.get(0).printBoard();
    // Children.forEach((move) -> {
    //   move.printBoard();
    // });
    List<Double> scores = new ArrayList<Double>();
    for (Board move : Children) {
      double score;

      GameStatusEnum gameStatusAfter = move.gameStatus();
      if(gameStatusBefore != GameStatusEnum.ONGOING) {
         score = CalculateScore(move.board, id, player);
      }

      score = MiniMaxSearch(move, maxTurn, nextPlayer, currentDepth+1);
      scores.add(score);
    }
    return Collections.max(scores);
    /*if(!maxTurn){ //max = 1
      return Collections.max(scores);
    } else {
      return Collections.min(scores);
    }*/
  }

  @Override
  protected double treeEval(Board board, PlayerEnum nextPlayer) {
    // currentDepth = 0;
    return MiniMaxSearch(board, true, nextPlayer, 1);
  }
}
