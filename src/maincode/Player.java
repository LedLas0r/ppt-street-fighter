package maincode;

public class Player {
    private String name;
    private int choice;
    private int nVictories;
    private int nDefeats;
    private int health;

    public Player(String name) {
        this.name = name;
        this.health = 3;  // Se inicia con 3 vidas
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public int getnVictories() {
        return nVictories;
    }

    public int getnDefeats() {
        return nDefeats;
    }

    public void increaseNVictories() {
        nVictories++;
    }

    public void increaseNDefeats() {
        nDefeats++;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth() {
        if (health > 0) {
            health--;
        }
    }

    public void resetHealth() {
        health = 3;
    }

    public double getWinrate() {
        int totalGames = nVictories + nDefeats;
        return totalGames == 0 ? 0 : (double) nVictories / totalGames;
    }

}
