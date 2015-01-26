package com.willem.tHelpers;

/**
 * Created by wellis on 1/4/2015.
 */
public interface IActivityRequestHandler {
    public void showAds(boolean show);
    public void signIn();
    public void signOut();
    public void rateGame();
    public void submitScore(long score);
    public void showScores();
    public boolean isSignedIn();
    public void updateAchievements(long score);
    public void showAchievements();
}
