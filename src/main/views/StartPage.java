package views;

public class StartPage {
    public static String[] startPage() {
        String[] gameType = new String[3];
        DisplayPrompts.welcomeMessage();
        gameType[0] = UserGameInput.getGameType();
        gameType[1] = UserGameInput.getUserDifficulty();
        return gameType;
    }
}
