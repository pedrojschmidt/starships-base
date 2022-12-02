package starships.configuration;

import javafx.scene.input.KeyCode;
import starships.ObjectStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {

    private final int amountOfPlayers;
    private final int livesPerPlayer;
    private final int amountOfAsteroids;
    private final Map<String, KeyCode> keyboardConfiguration;
    private final Map<String, ObjectStyle> styles;

    public Configuration() {
        List<String> lines = getLines("app\\src\\main\\java\\starships\\configuration\\ConfigurationFile");
        Map<String, String> optionsMap = generateMap(lines);

        amountOfPlayers = Integer.parseInt(optionsMap.get("amountOfPlayers"));
        livesPerPlayer = Integer.parseInt(optionsMap.get("livesPerPlayer"));
        amountOfAsteroids = Integer.parseInt(optionsMap.get("amountOfAsteroids"));
        keyboardConfiguration = getKeyBoardMap(optionsMap.get("keyBoardSettings"));
        styles = getStyles(optionsMap.get("styles"));
    }

    private Map<String, String> generateMap(List<String> lines) {
        Map<String, String> map = new HashMap<>();
        for (String line : lines){
            String[] split = line.split(":");
            map.put(split[0], split[1]);
        }
        return map;
    }

    private Map<String, KeyCode> getKeyBoardMap(String keyBoardSettings) {
        Map<String, KeyCode> map = new HashMap<>();
        if (keyBoardSettings != null) {
            String[] split = keyBoardSettings.split(";");
            for (String s : split) {
                String[] innerSplit = s.split("=");
                map.put(innerSplit[0], getKeyCode(innerSplit[1]));
            }
        }
        return map;
    }

    private KeyCode getKeyCode(String str){
        return switch (str){
            case "W" -> KeyCode.W;
            case "S" -> KeyCode.S;
            case "A" -> KeyCode.A;
            case "D" -> KeyCode.D;
            case "Q" -> KeyCode.Q;
            case "E" -> KeyCode.E;
            case "SPACE" -> KeyCode.SPACE;
            case "UP" -> KeyCode.UP;
            case "DOWN" -> KeyCode.DOWN;
            case "LEFT" -> KeyCode.LEFT;
            case "RIGHT" -> KeyCode.RIGHT;
            case "NUMPAD1" -> KeyCode.NUMPAD1;
            case "NUMPAD2" -> KeyCode.NUMPAD2;
            case "ENTER" -> KeyCode.ENTER;
            default -> KeyCode.M;
        };
    }

    private Map<String, ObjectStyle> getStyles(String styles) {
        Map<String, ObjectStyle> map = new HashMap<>();
        String[] split = styles.split(";");
        for (String s : split) {
            String[] innerSplit = s.split("=");
            map.put(innerSplit[0], getStyle(innerSplit[1]));
        }
        return map;
    }

    private ObjectStyle getStyle(String str) {
        return switch (str){
            case "SPACESHIP1" -> ObjectStyle.SPACESHIP1;
            case "SPACESHIP2" -> ObjectStyle.SPACESHIP2;
            default -> ObjectStyle.SPACESHIP3;
        };
    }

    public List<String> getLines(String directory) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(directory))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public int getLivesPerPlayer() {
        return livesPerPlayer;
    }

    public int getAmountOfAsteroids() {
        return amountOfAsteroids;
    }

    public Map<String, KeyCode> getKeyboardConfiguration() {
        return keyboardConfiguration;
    }

    public Map<String, ObjectStyle> getStyles() {
        return styles;
    }
}
