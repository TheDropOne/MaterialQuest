package by.thedrop.materialquest.Templates;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.thedrop.materialquest.Activities.LevelHolderActivity;
import by.thedrop.materialquest.Activities.MainActivity;
import by.thedrop.materialquest.R;

/**
 * Created by Semen on 08-Mar-17.
 */

public class LevelsRVAdapter extends RecyclerView.Adapter<LevelsRVAdapter.MyViewHolder> {
    private List<Level> levels;

    public LevelsRVAdapter(List<Level> levels) {
        this.levels = levels;
    }

    @Override
    public LevelsRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_level, parent, false);
        return new LevelsRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LevelsRVAdapter.MyViewHolder holder, final int position) {
        final String string = String.valueOf(levels.get(position).getNumber());

        TextView level = holder.mLevel;
        CardView levelCard = holder.mLevelCard;
        final View view = holder.mItemView;

        level.setText(string);
        int textSize = 24;

        level.setTextSize(textSize);
        if (position > MainActivity.maxAvailableLevel - 1) {
            levelCard.setAlpha(0.4f);//Color.parseColor("#ededed"));
            levelCard.setEnabled(false);
        }
        if (position == MainActivity.currentLevel - 1) {
            level.setBackgroundColor(Color.parseColor("#FFFF5656"));
        }

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), LevelHolderActivity.class);
                MainActivity.currentLevel = position + 1;
                view.getContext().startActivity(intent);
                /*s
                start activity with chosen level
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mLevel;
        private View mItemView;
        private CardView mLevelCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;

            mLevel = (TextView) itemView.findViewById(R.id.item_level);
            mLevelCard = (CardView) itemView.findViewById(R.id.level_card);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            mItemView.setOnClickListener(onClickListener);
        }
    }
}
