package by.thedrop.materialquest.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import by.thedrop.materialquest.Constants.Constants;
import by.thedrop.materialquest.R;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "APP_SETTINGS";
    public static final String LIGHT_THEME = "LIGHT_THEME";
    public static final String CURRENT_LEVEL = "CURRENT_LEVEL";
    public static final String MAX_AVAILABLE_LEVEL = "MAX_AVAILABLE_LEVEL";

    public static boolean lightTheme;

    public static int currentLevel;
    public static int maxAvailableLevel;

    public static Typeface fontFutura;


    public static TextView currentLevelIs;
    private Button run;
    private ImageButton levels;
    public static SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSharedPreferences != null) {
            lightTheme = mSharedPreferences.getBoolean(LIGHT_THEME, false);
            currentLevel = mSharedPreferences.getInt(CURRENT_LEVEL, 1);
            maxAvailableLevel = mSharedPreferences.getInt(MAX_AVAILABLE_LEVEL, 1);
        } else {
            lightTheme = true;
        }
        fontFutura = Typeface.createFromAsset(getAssets(), "fonts/futura.ttf");
        /*currentLevel = 1;
        maxAvailableLevel = 1;*/


        levels = (ImageButton) findViewById(R.id.main_levels);
        run = (Button) findViewById(R.id.main_run_button);
        currentLevelIs = (TextView) findViewById(R.id.main_current_level);
        currentLevelIs.setText(getString(R.string.levelis, currentLevel));

        setTheme();

        Constants.initLevels();

        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelHolderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.main_switch);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                lightTheme = !lightTheme;
                setTheme();
            }
        });
    }

    public static void setCurrentLevel(int currentLevel) {
        MainActivity.currentLevel = currentLevel;
        if (currentLevel >= maxAvailableLevel) {
            maxAvailableLevel = currentLevel;
        }
    }

    private void setTheme() {
        if (!lightTheme) {
            ((SwitchCompat) findViewById(R.id.main_switch)).setChecked(true);
            findViewById(R.id.activity_main).setBackgroundColor(getResources().getColor(R.color.backgroundGreyMain));
            currentLevelIs.setTextColor(getResources().getColor(R.color.defaultGrey));
            findViewById(R.id.main_levels).setBackgroundColor(getResources().getColor(R.color.backgroundGreyMain));
        } else {
            ((SwitchCompat) findViewById(R.id.main_switch)).setChecked(false);
            findViewById(R.id.activity_main).setBackgroundColor(getResources().getColor(R.color.defaultGrey));
            currentLevelIs.setTextColor(getResources().getColor(R.color.buttonGrey));
            findViewById(R.id.main_levels).setBackgroundColor(getResources().getColor(R.color.defaultGrey));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(LIGHT_THEME, lightTheme);
        editor.putInt(CURRENT_LEVEL, currentLevel);
        editor.putInt(MAX_AVAILABLE_LEVEL, maxAvailableLevel);
        editor.apply();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        currentLevelIs.setText(getString(R.string.levelis, currentLevel));
    }
}
