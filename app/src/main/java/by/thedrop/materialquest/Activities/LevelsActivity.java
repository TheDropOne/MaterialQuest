package by.thedrop.materialquest.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import by.thedrop.materialquest.Constants.Constants;
import by.thedrop.materialquest.R;
import by.thedrop.materialquest.Templates.LevelsRVAdapter;

public class LevelsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        mRecyclerView = (RecyclerView) findViewById(R.id.levelsRV);

        new LoadLevels().execute();
    }

    class LoadLevels extends AsyncTask<Void, Void, Void> {

        LevelsRVAdapter mAdapter;

        @Override
        protected Void doInBackground(Void... voids) {
            mAdapter = new LevelsRVAdapter(Constants.levels);
            mGridLayoutManager = new GridLayoutManager(LevelsActivity.this, 5);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                assert mRecyclerView != null;
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(mGridLayoutManager);
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(LevelsActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
