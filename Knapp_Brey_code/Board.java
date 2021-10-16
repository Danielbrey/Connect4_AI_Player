import java.util.ArrayList;

public class Board{

  int height;
  int width;
  PlayerEnum[][] board;

  public Board(int h, int w) {
    this.height = h;
    this.width = w;
    this.board = new PlayerEnum[height][width];

    this.init();
  }

  public void init() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.board[i][j] = PlayerEnum.EMPTY;
      }
    }
  }

  private Board copyBoard() {
    Board boardCopy = new Board(height, width);

    for (int i=0; i<height; i++) {
      for (int j=0; j<width; j++) {
        boardCopy.board[i][j] = this.board[i][j];
      }
    }
    return boardCopy;
  }

  public ArrayList<Board> ExpandChildren(PlayerEnum nextPlayer) {

    ArrayList<Board> children = new ArrayList<Board>();

    Board boardClone = this.copyBoard();

    for(int j = 0; j < boardClone.width; j++) {
      //For each column, create the child
      for(int i = 1; i <= boardClone.height; i++) {
        if(boardClone.board[height-i][j] == PlayerEnum.EMPTY) {
          boardClone.board[height-i][j] = nextPlayer;
          children.add(boardClone.copyBoard());
          boardClone.board[height-i][j] = PlayerEnum.EMPTY;
          break;
        }
      }
    }
    return children;
  }

  public boolean isWinRow(int row) {
		for(int col = 0; col<width - 3; col ++){
      PlayerEnum firstCell = board[row][col];
		  if (firstCell != PlayerEnum.EMPTY) {
        if((board[row][col+1] == firstCell) && (board[row][col+2] == firstCell) && board[row][col+3] == firstCell){
          return true;
        }
		  }
    }
    return false;
	}

  public boolean isWinCol(int col) {
    for(int row = 0; row <height - 3; row ++){
      PlayerEnum firstCell = board[row][col];
		  if (firstCell != PlayerEnum.EMPTY) {
        if((board[row+1][col] == firstCell) && (board[row+2][col] == firstCell) && board[row+3][col] == firstCell){
          return true;
        }
		  }
    }
    return false;
	}

  public boolean isWinUpperDiag() {
    for(int row = height-1; row > height - 4; row --){
      for(int col = 0; col < width - 3; col ++){
        PlayerEnum firstCell = board[row][col];
        if (firstCell != PlayerEnum.EMPTY) {
          if((board[row-1][col+1] == firstCell) && (board[row-2][col+2] == firstCell) && board[row-3][col+3] == firstCell){
            return true;
          }
        }
      }
    }
    return false;
	}

  public boolean isWinLowerDiag() {
    for(int col = 0; col < width-3; col++) {
      for (int row = 0; row < height-3; row++) {
        PlayerEnum firstCell = board[row][col];
        if (firstCell != PlayerEnum.EMPTY) {
          if((board[row+1][col+1] == firstCell) && (board[row+2][col+2] == firstCell) && (board[row+3][col+3] == firstCell)){
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isFull() {
    for (int i=0; i<height; i++) {
      for (int j=0; j<width; j++) {
        if (board[i][j] == PlayerEnum.EMPTY)
          return false;
      }
    }
    return true;
  }

  public GameStatusEnum gameStatus() {
		for (int i = 0; i < height; i++) {
			if (isWinRow(i)) {
				return GameStatusEnum.PLAYER_WON;
			}
		}
    for (int i = 0; i < width; i++) {
      if (isWinCol(i)) {
        return GameStatusEnum.PLAYER_WON;
      }
    }
		if (isWinLowerDiag() || isWinUpperDiag()) {
			return GameStatusEnum.PLAYER_WON;
		}
		if (isFull()) {
			return GameStatusEnum.DRAW;
		}

		return GameStatusEnum.ONGOING;
	}


  public void printBoard(){
    for(int i = 0; i < height; i ++){
      for(int j = 0; j < width; j ++){
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

}
