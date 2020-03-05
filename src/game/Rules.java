package game;

import materials.CoinState;

import java.util.List;

public class Rules {

  private static Rules uniqueInstance = null;

  private Rules() {
  }

  /**
   * Cette méthode permet de déterminer si une suite d'états de pièce permet de gagner à une partie
   * @param states liste d'états pour un joueur
   * @return true si un joueur a gagné, false sinon
   */
  public boolean checkWin(List<CoinState> states) {
    int nbHeads = 0;

    for (CoinState coinState : states) {
      if(coinState.equals(CoinState.HEADS)){
        nbHeads++;
      }
    }

    if (nbHeads >= 3){
      return true;
    }else {
      return false;
    }
  }

  public static Rules getInstance() {
    if (uniqueInstance == null) {uniqueInstance = new Rules();}
    return uniqueInstance;
  }
}
