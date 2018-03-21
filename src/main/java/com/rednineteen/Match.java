package com.rednineteen;

import java.util.*;

public class Match {

    // Use to keep a reference of who is player 1 and player 2 respectively
    private String player1;
    private String player2;

    private Game currentGame;
    private int currentGameNum;
    private LinkedHashMap<String, List<Game>> playerGames;

    public Match(String p1, String p2) {
        player1         = p1;
        player2         = p2;
        playerGames     = new LinkedHashMap<>();
        currentGameNum  = 0;

        playerGames.put(player1, new ArrayList<>());
        playerGames.put(player2, new ArrayList<>());
    }

    public void pointWonBy(String player) {
        // Initialize the new current game if null or if current game has finished.
        if (currentGame == null || currentGame.isGameFinished()) {
            currentGameNum++;
            currentGame = new Game(player1, player2, currentGameNum);
            // Set the game as tie break
            currentGame.setTieBreak(shouldPlayTieBreak());
        }

        // add the game point
        currentGame.pointWonBy(player);
        // If game has finished add it to the list of wined games for the winner player.
        if (currentGame.isGameFinished())
            playerGames.get( currentGame.getGameWinner() ).add(currentGame.clone()); // Just make a copy of the game
    }

    /**
     * Returns the String score representation as requested on the instructions.
     * @return
     */
    public String score() {
        // Gets the set score
        String score = getSetScore();

        // Append the current game score
        if (currentGame != null)
            score = score + ", " + currentGame.getScore();

        return score;
    }

    /**
     * Returns the string representation of the current Set
     * @return
     */
    private String getSetScore() {
        int gamesPlayer1 = playerGames.get(player1).size();
        int gamesPlayer2 = playerGames.get(player2).size();
        return gamesPlayer1 + "-" + gamesPlayer2;
    }

    /**
     * Checks if the current set game should be a tie-break game
     * @return
     */
    public boolean shouldPlayTieBreak() {
        int p1WinGames = playerGames.get(player1).size();
        int p2WinGames = playerGames.get(player2).size();
        return (p1WinGames == 7 && p2WinGames == 6) || (p2WinGames == 7 && p1WinGames == 6);
    }


}
