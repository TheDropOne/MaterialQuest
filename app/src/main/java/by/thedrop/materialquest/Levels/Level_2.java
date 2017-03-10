package by.thedrop.materialquest.Levels;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import by.thedrop.materialquest.Activities.LevelHolderActivity;
import by.thedrop.materialquest.Activities.MainActivity;
import by.thedrop.materialquest.R;

public class Level_2 extends Fragment {

    private int animationCount;
    TextView level_2_el2_2;
    TextView level_2_el2_1;
    ImageView next;
    EditText editLabel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_02_level, container, false);

        final TextView levelPassed = (TextView) v.findViewById(R.id.level_2_passed);
        levelPassed.setTypeface(MainActivity.fontFutura);
        levelPassed.setVisibility(View.GONE);
        final TextView levelName = (TextView) v.findViewById(R.id.level_2_name);
        levelName.setText(getString(R.string.levelis, 2));
        levelName.setTypeface(MainActivity.fontFutura);
        next = (ImageView) v.findViewById(R.id.level_2_next);
        next.setVisibility(View.GONE);


        editLabel = (EditText) v.findViewById(R.id.level_2_el_edit);
        editLabel.setVisibility(View.GONE);
        level_2_el2_1 = (TextView) v.findViewById(R.id.level_2_el2_1);
        level_2_el2_2 = (TextView) v.findViewById(R.id.level_2_el2_2);
        final TextView level_2_elX = (TextView) v.findViewById(R.id.level_2_elX);
        level_2_el2_1.setTypeface(MainActivity.fontFutura);
        level_2_el2_2.setTypeface(MainActivity.fontFutura);

        final Animation rotateAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.level_2_cross);
        final Animation rotateAnimationTwo = AnimationUtils.loadAnimation(v.getContext(), R.anim.level_2_two);
        level_2_elX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level_2_elX.startAnimation(rotateAnimation);
            }
        });
        level_2_el2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level_2_el2_1.startAnimation(rotateAnimationTwo);
            }
        });
        level_2_el2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level_2_el2_2.startAnimation(rotateAnimationTwo);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt((String) level_2_el2_1.getText()) * Integer.parseInt((String) level_2_el2_2.getText()) == Integer.parseInt(editLabel.getText().toString())) {

                    Toast.makeText(getContext(), R.string.level_2_second, Toast.LENGTH_SHORT).show();
                    levelName.setVisibility(View.VISIBLE);
                    levelPassed.setVisibility(View.VISIBLE);

                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LevelHolderActivity.changeLevel(2);
                        }
                    }, 2000);
                } else {
                    Toast.makeText(getContext(), R.string.level_2_first, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    public void onKeyVolumeUp() {
        level_2_el2_2.setText(String.valueOf(Integer.parseInt((String) level_2_el2_2.getText()) + 1));
        if (Integer.parseInt((String) level_2_el2_2.getText()) > 10 || Integer.parseInt((String) level_2_el2_1.getText()) < 1) {
            editLabel.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
    }

    public void onKeyVolumeDown() {
        level_2_el2_1.setText(String.valueOf(Integer.parseInt((String) level_2_el2_1.getText()) - 1));
        if (Integer.parseInt((String) level_2_el2_2.getText()) > 10 || Integer.parseInt((String) level_2_el2_1.getText()) < 1) {
            editLabel.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
    }
}
