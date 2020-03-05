package materials;
import java.util.Random;

public class Coin {

  private CoinState coinState;
  private final Random random = new Random();

  private static Coin uniqueInstance = null;

  private Coin() {
    this.coinState = CoinState.HEADS;
  }

  /**
   * Change l'état de la pièce.
   * 50% de probabilité d'obtenir HEADS, 50% de probabilité d'obtenir TAILS
   */
  public void throwCoin() {
    if (random.nextBoolean()) {
      coinState = CoinState.HEADS;
    } else {
      coinState = CoinState.TAILS;
    }
  }

  public CoinState getState() {
    return coinState;
  }

  public static Coin getInstance() {
    if (uniqueInstance == null) {uniqueInstance = new Coin();}
    return uniqueInstance;
  }


}
