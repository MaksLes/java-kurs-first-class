package TreeSetTrap.model;

public class Player {

    private final String nickname;
    private final int score;

    public Player(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    public String getNickname() { return nickname; }
    public int getScore() { return score; }

    @Override
    public String toString() {
        return String.format("%-10s | %d pkt", nickname, score);
    }
}
