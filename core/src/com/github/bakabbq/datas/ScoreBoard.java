package com.github.bakabbq.datas;

/**
 * Created by LBQ on 8/7/14.
 */
public class ScoreBoard {
    public int hiScore;
    public int score;
    public int graze;


    public String hiScoreText;
    public String scoreText;

    public ScoreBoard() {
        hiScore = 0;
        score = 0;
        graze = 0;
        increaseScore(0);
    }

    public void increaseScore(int x) {
        score += x;
        scoreText = String.format("%011d", score);
        if (score >= hiScore) {
            hiScore = score;
            hiScoreText = scoreText;
        }
    }
}
