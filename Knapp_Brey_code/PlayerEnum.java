public enum PlayerEnum {

  PLAYER1 {
    public String toString() {
      return "X";
    }
  },

  PLAYER2 {
    public String toString() {
      return "O";
    }
  },

  EMPTY {
    public String toString() {
      return "-";
    }
  },
}
