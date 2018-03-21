package com.rednineteen;

import java.util.LinkedHashMap;

public class Game implements Cloneable {

    // Use to keep a reference of who is player 1 and player 2 respectively
    private String player1;
    private String player2;

    // By default game is not tie-break
    private boolean isTieBreak = false;
    // Keeps reference of the number of the game on a set
    private int gameNumber;
    // Game points map containing string of player name and points for each player for this game.
    private LinkedHashMap<String, Integer> points;

    public Game(String p1, String p2, int gameNum) {
        player1     = p1;
        player2     = p2;
        gameNumber  = gameNum;
        isTieBreak  = false;
        points      = new LinkedHashMap<>();
        points.put(player1, 0);
        points.put(player2, 0);
    }

    /**
     * Constructor needed for cloning objects.
     * @param player1
     * @param player2
     * @param isTieBreak
     * @param gameNumber
     * @param points
     */
    public Game(String player1, String player2, boolean isTieBreak, int gameNumber, LinkedHashMap<String, Integer> points) {
        this.player1    = player1;
        this.player2    = player2;
        this.isTieBreak = isTieBreak;
        this.gameNumber = gameNumber;
        this.points     = points;
    }

    public void pointWonBy(String player) {
        if (points.containsKey(player) && !isGameFinished())
            points.put(player, points.get(player) + 1);
    }

    /**
     * Checks if this game has finished making sure there was one winner.
     * @return
     */
    public boolean isGameFinished() {
        int p1Points = points.get(player1);
        int p2Points = points.get(player2);
        int gamePointMargin = isTieBreak ? 7 : 4;
        return ((p1Points >= gamePointMargin) && (p1Points - p2Points >= 2)) || ((p2Points >= gamePointMargin) && (p2Points - p1Points >= 2));
    }

    /**
     * Returns the string name for the current winner of this game. If current game is tie then it returns a special string
     * @return
     */
    public String getGameWinner() {
        int p1Points = points.get(player1);
        int p2Points = points.get(player2);
        return (p1Points > p2Points) ? player1 : (p2Points > p1Points) ? player2 : "TIE";
    }

    /**
     * Returns the player number of points for this game
     * @param player  name
     * @return
     */
    public int getPlayerScore(String player) {
        return points.getOrDefault(player, 0); // Return 0 as default
    }

    public void setTieBreak(boolean tieBreak) {
        isTieBreak = tieBreak;
    }

    /**
     * Get the game score in the requested format
     * @return
     */
    public String getScore() {
        int p1Points = points.getOrDefault(player1,0);
        int p2Points = points.getOrDefault(player2,0);

        if (isGameFinished())
            return "";
        else if (isTieBreak)
            return "Tie-Break " + p1Points + "-" + p2Points;
        else if (p1Points >= 3 && p1Points == p2Points )
            return "Deuce";
        else if (p1Points >= 3 && p1Points - p2Points == 1)
            return "Advantage " + player1;
        else if (p2Points >= 3 && p2Points - p1Points == 1)
            return "Advantage " + player2;
        else
            return getPointName(p1Points) + "-" + getPointName(p2Points);

    }

    /**
     * Returns the special Tennis string representation of game points. love, fifteen, thirty, forty
     * @param point
     * @return
     */
    private String getPointName(int point) {
        String name = "";
        switch (point) {
            case 1:
                name = "15";
                break;
            case 2:
                name = "30";
                break;
            case 3:
                name = "40";
                break;
            default:
                name = String.valueOf(point);
                break;
        }
        return name;
    }

    @Override
    public String toString() {
        return player1 + ": " +points.getOrDefault(player1, 0) + " | " + player2 + ": " + points.getOrDefault(player2, 0);
    }

    @Override
    protected Game clone() {
        try {
            super.clone();
            return new Game(player1, player2, isTieBreak, gameNumber, points);
        } catch (Exception e) {
            return new Game(player1, player2, 0);
        }
    }
}
