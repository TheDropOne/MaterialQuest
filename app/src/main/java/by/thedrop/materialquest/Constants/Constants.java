package by.thedrop.materialquest.Constants;

import java.util.ArrayList;

import by.thedrop.materialquest.Templates.Level;

/**
 * Created by Semen on 08-Mar-17.
 */

public class Constants {
    public static ArrayList<Level> levels = new ArrayList<>();
    public static ArrayList<Level> availableLevels = new ArrayList<>();

    public static void initLevels(){
        for (int i = 1; i < 31; i++) {
            levels.add(new Level(i));
        }
        for (int i = 1; i < 8; i++) {
            availableLevels.add(new Level(i));
        }
    }
}
