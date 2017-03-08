package by.thedrop.materialquest.Activities;

import android.content.Intent;
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

    private static boolean lightTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton levels = (ImageButton) findViewById(R.id.main_levels);
        Button run = (Button) findViewById(R.id.main_run_button);
        final TextView currentLevel = (TextView) findViewById(R.id.main_current_level);

        Constants.initLevels();

        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.main_switch);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!lightTheme) {
                    findViewById(R.id.activity_main).setBackgroundColor(getResources().getColor(R.color.backgroundGreyMain));
                    currentLevel.setTextColor(getResources().getColor(R.color.defaultGrey));
                    findViewById(R.id.main_levels).setBackgroundColor(getResources().getColor(R.color.backgroundGreyMain));
                    lightTheme = !lightTheme;
                } else {
                    findViewById(R.id.activity_main).setBackgroundColor(getResources().getColor(R.color.defaultGrey));
                    currentLevel.setTextColor(getResources().getColor(R.color.buttonGrey));
                    findViewById(R.id.main_levels).setBackgroundColor(getResources().getColor(R.color.defaultGrey));
                    lightTheme = !lightTheme;
                }
            }
        });
    }
}
