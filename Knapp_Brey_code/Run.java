public class Run {

  public static void main(String[] args){

    Board board = new Board(6, 7);

    Player p1 = new MiniMax(5, "Offensive");
    Player p2 = new MiniMax(5, "Defensive");

    GameController controller = new GameController(board, p1, p2);

    try{
      controller.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
