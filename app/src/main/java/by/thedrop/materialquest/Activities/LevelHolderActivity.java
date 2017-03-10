package by.thedrop.materialquest.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import by.thedrop.materialquest.Constants.Constants;
import by.thedrop.materialquest.Levels.Level_2;
import by.thedrop.materialquest.R;

public class LevelHolderActivity extends AppCompatActivity {

    private static Fragment currentLevel;

    private TextView mNoLevels;

    private static FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_holder);

        setTheme();

        currentLevel = Constants.levels.get(MainActivity.currentLevel - 1).getFragment();

        mFragmentManager = getSupportFragmentManager();
        if (currentLevel != null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.activity_level_holder, currentLevel)
                    .commit();
        } else {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }

        mNoLevels = (TextView) findViewById(R.id.no_levels);
        mNoLevels.setTypeface(MainActivity.fontFutura);
    }

    public static void changeLevel(int currentLevelNumber) {
        if (currentLevel != null) {
            mFragmentManager.beginTransaction()
                    .detach(currentLevel)
                    .commit();
        }
        if (currentLevelNumber + 1 < Constants.levels.size()) {
            MainActivity.setCurrentLevel(currentLevelNumber + 1);
            currentLevel = Constants.levels.get(MainActivity.currentLevel - 1).getFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.activity_level_holder, currentLevel)
                    .commit();
        }
    }

    private void setTheme() {
        if (!MainActivity.lightTheme) {
            findViewById(R.id.activity_level_holder).setBackgroundColor(getResources().getColor(R.color.backgroundGreyMain));
        } else {
            findViewById(R.id.activity_level_holder).setBackgroundColor(getResources().getColor(R.color.defaultGrey));
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (currentLevel instanceof Level_2) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_VOLUME_UP:
                    ((Level_2) currentLevel).onKeyVolumeUp();
                    return true;
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    ((Level_2) currentLevel).onKeyVolumeDown();
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
