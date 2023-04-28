package com.mihov.parser;

import java.util.*;

public class Parser {

    private static Map<String, String> dealMap = new LinkedHashMap<>();
    private static final StringBuilder stringBuilder = new StringBuilder();
    private static final int TOKEN_GROUP_LENGTH = 2;
    private static final String DELIMITER = "|";
    private static final String COMMA = ",";
    private static final String MAKE_DEAL = "|md|";
    private static final String NEW_LINE = "\n";
    private static final String MAKE_BID = "|mb|";
    private static final String PLAY_CARD = "|pc|";
    private static final String PLAY_GAME = "|pg|";
    private static final String SET_VUlNERABILITY = "|sv|";
    private static final String BOARD_HEADER = "|rh||ah|Board 1";
    private static final String PRELIMINARY = "pn|South,West,North,East|st|";
    private static final String PASS = "p";
    private static final String DOUBLE = "d";
    private static final String REDOUBLE = "r";

    public static String parse(String rawBboText) {

        StringTokenizer stringTokenizer = new StringTokenizer(rawBboText, "&");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            System.out.println(nextToken);
            dealMap.put(nextToken.substring(0, 1), nextToken.substring(2));
        }

        List<String> tokenizedPlay = tokenizePlayAndBids(dealMap.get("p"), TOKEN_GROUP_LENGTH);

        String lin = stringBuilder
                .append(PRELIMINARY)
                .append(BOARD_HEADER)
                .append(MAKE_DEAL)
                .append(getDealerAsNumber(dealMap.get("d")))
                .append(dealMap.get("s")).append(COMMA)
                .append(dealMap.get("w")).append(COMMA)
                .append(dealMap.get("n")).append(COMMA)
                .append(dealMap.get("e")).append(COMMA)
                .append(SET_VUlNERABILITY)
                .append(dealMap.get("v"))
                .append(getBidsAsLin(tokenizePlayAndBids(dealMap.get("a"))))
                .append(getPlayAsLin(tokenizedPlay))
                .append(DELIMITER)
                .append(NEW_LINE)
                .toString();
        System.out.println(lin);
        return lin;
    }

    private static String getBidsAsLin(List<String> tokenizedBids) {
        StringBuilder bld = new StringBuilder();
        String bidsAsLin = "";
        for (int index = 0; index < tokenizedBids.size(); index++) {
            bidsAsLin = bld
                    .append(MAKE_BID)
                    .append(tokenizedBids.get(index))
                    .toString();
        }
        return bidsAsLin;
    }

    private static String getPlayAsLin(List<String> tokenizedPlay) {
        StringBuilder bld = new StringBuilder();
        String playAsLin = "";
        for (int index = 0; index < tokenizedPlay.size(); index++) {
            playAsLin = PLAY_GAME + bld
                    .append(PLAY_CARD)
                    .append(tokenizedPlay.get(index))
                    .append(checkIfNeedToAppendPg(index))
                    .toString();
        }
        return playAsLin;
    }

    private static String checkIfNeedToAppendPg(int index) {
        if (index >= 3 && index % 3 == 0) {
            return PLAY_GAME;
        }
        return "";
    }


    public static List<String> tokenizePlayAndBids(String text, int groupLen) {
        String[] results = text.split("(?<=\\G.{" + groupLen + "})");

        return Arrays.asList(results);
    }

    public static List<String> tokenizePlayAndBids(String text) {
        List<String> results = new ArrayList<>();

        for (int index = 0; index < text.length(); index++) {
            char charAtIndex = text.charAt(index);
            if (Character.isDigit(charAtIndex)) {
                results.add(text.substring(index, index + 2));
            }else if (charAtIndex == 'p' || charAtIndex == 'd' || charAtIndex == 'r') {
                results.add(text.substring(index, index + 1));
            }
        }

        return results;
    }

    private static String getDealerAsNumber(String vulnerability) {
        String result = "";
        switch (vulnerability) {
            case "s":
                result = "1";
                break;
            case "w":
                result = "2";
                break;
            case "n":
                result = "3";
                break;
            case "e":
                result = "4";
                break;
        }

        return result;
    }
}
