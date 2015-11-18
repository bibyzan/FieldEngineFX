package FieldEngineFX.ArcadeMods;

import FieldEngineFX.Player;

/**
 * Created by Ben on 11/2/2015.
 */
public class ArcadePlayer extends Player {
    protected int score = 0;

    public void changeScore(int change) {
        score += change;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
