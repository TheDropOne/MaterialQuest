package by.thedrop.materialquest.Templates;

import android.support.v4.app.Fragment;

/**
 * Created by Semen on 08-Mar-17.
 */

public class Level {
    int number;
    Fragment mFragment;

    public Level(int number) {
        this.number = number;
    }

    public Level(int number, Fragment fragment) {
        this.number = number;
        this.mFragment = fragment;
    }

    public int getNumber() {
        return number;
    }

    public Fragment getFragment() {
        return mFragment;
    }
}
