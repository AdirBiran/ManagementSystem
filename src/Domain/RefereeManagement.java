package Domain;

import Data.Database;
import Presentation.Referee;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.*;

public class RefereeManagement {

    private Database database;

    public RefereeManagement(Database database) {
        this.database = database;
    }

    public void sendNotification(Game game, String msg) {
        for(Referee sideRef : game.getSideReferees())
            sideRef.addMessage(new Notice(true, msg));
        game.getMainReferee().addMessage(new Notice(true, msg));
    }

    public boolean appointReferee(String name, String id, String mail, String training) {
        Referee referee = new Referee(name, id, mail, training);
        String randomInitialPassword = generateRandPassword(6);
        //send referee mail to approve?
        return database.addUser(id, randomInitialPassword, referee);
    }

    private String generateRandPassword(int length) {
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseChars = lowerCaseChars.toUpperCase();
        String numbers = "0123456789";
        String passwordAllowedBase = lowerCaseChars + upperCaseChars + numbers;
        String passwordAllow = shuffleString(passwordAllowedBase);
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        String password;

        do{
            password = "";
            for (int i = 0; i < length; i++) {
                int rndCharAt = random.nextInt(passwordAllow.length());
                char rndChar = passwordAllow.charAt(rndCharAt);
                sb.append(rndChar);
            }
            password = sb.toString();
        }
        while(!Checker.isValidPassword(password));


        return password;
    }
    private String shuffleString(String string) {
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        return letters.stream().collect(Collectors.joining());
    }
}
