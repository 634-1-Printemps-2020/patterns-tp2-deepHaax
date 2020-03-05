package game;

import materials.Coin;
import materials.CoinState;
import player.Player;
import utils.Statistics;

import java.util.*;

public class Game {

    private Rules rules;
    private Coin coin;
    private Map<Player, List<CoinState>> history;

    private float averageToWin = 0;
    private int fewerMovesToWin = 1000;
    private int mostMovesToWin = 0;
    private int totalNumberMoves = 0;

    private Statistics statistics = new Statistics(averageToWin, fewerMovesToWin, mostMovesToWin, totalNumberMoves);

    public Game() { history = new HashMap<>(); }

    /**
     * Ajouter un nouveau joueur au jeu
     *
     * @param player le nouveau joueur
     */
    public void addPlayer(Player player) {
        List<CoinState> listCoinState = new ArrayList<>();
        history.put(player, listCoinState);
    }

    /**
     * Faire joueur tous les joueurs et stocker chaque partie dans history
     */
    public void play() {
        coin = Coin.getInstance();

        for (Map.Entry<Player, List<CoinState>> entry : history.entrySet()) {
            int nbHeads = 0;

            while(nbHeads < 3){
                entry.getKey().play(coin);

                //Stats for totalNumberMoves
                totalNumberMoves++;

                if (coin.getState() == CoinState.HEADS){
                    nbHeads++;
                }
                entry.getValue().add(coin.getState());
            }

            //Stats for mostMovesToWin
            if (entry.getValue().size() > mostMovesToWin){
                mostMovesToWin = entry.getValue().size();
            }

            //Stats for fewerMovesToWin
            if (entry.getValue().size() < fewerMovesToWin){
                fewerMovesToWin = entry.getValue().size();
            }
        }
    }

    /**
     * Calculer des statistiques de la partie précédente
     *
     * @return Statistics
     */
    public Statistics getStatistics() {

        statistics.setFewerMovesToWin(fewerMovesToWin);
        statistics.setMostMovesToWin(mostMovesToWin);
        statistics.setTotalNumberMoves(totalNumberMoves);

        //Stats for averageToWin
        averageToWin = totalNumberMoves/history.size();
        statistics.setAverageToWin(averageToWin);

        return statistics;
    }

    /**
     * Obtenir l'historique de tous les joueurs de la partie précédente
     *
     * @return Map contenant chaque joueur et la liste des ses lancers
     */
    public Map<Player, List<CoinState>> getHistory() {
      return history;
    }


    /**
     * Obtenir l'historique d'un joueur spécifique
     *
     * @param player instance du joueur pour lequel on veut avoir l'historique
     * @return la liste des lancers d'un joueur
     */
    public List<CoinState> getSpecificHistory(Player player) {
        for (Map.Entry<Player, List<CoinState>> entry : history.entrySet()) {
            if(entry.getKey().equals(player)){
                return entry.getValue();
            }
        }
        return null;
    }

}
