package FieldEngineFX.ArcadeMods;

/**
 * Created by Ben on 11/2/2015.
 */
public class PlayerArchive {
    private String name;
    private int score;

    public PlayerArchive(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
