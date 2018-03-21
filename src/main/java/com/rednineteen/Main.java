package com.rednineteen;

public class Main {

    public static void main(String[] args) {

        String res;
        Match match = new Match("player 1", "player 2");
        match.pointWonBy("player 1");
        match.pointWonBy("player 2");
        // this will return "0-0, 15-15"
        res = match.score();
        System.out.println("ENTER: " + res);

        match.pointWonBy("player 1");
        match.pointWonBy("player 1");
        // this will return "0-0, 40-15"
        res = match.score();
        System.out.println("ENTER: " + res);

        match.pointWonBy("player 2");
        match.pointWonBy("player 2");
        // this will return "0-0, Deuce"
        res = match.score();
        System.out.println("ENTER: " + res);

        match.pointWonBy("player 1");
        // this will return "0-0, Advantage player 1"
        res = match.score();
        System.out.println("ENTER: " + res);

        match.pointWonBy("player 1");
        // this will return "1-0"
        res = match.score();
        System.out.println("ENTER: " + res);

    }
}
