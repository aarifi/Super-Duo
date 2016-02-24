package barqsoft.footballscores.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import barqsoft.footballscores.R;
import barqsoft.footballscores.data.TeamsData;
import barqsoft.footballscores.model.FixturesModelForLocal;
import barqsoft.footballscores.utils.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsMatchActivity extends AppCompatActivity {

    @Bind(R.id.txt_details_homeTeam_name)
    TextView txt_details_homeTeam_name;
    @Bind(R.id.imageview_details_photo_homeTeam)
    ImageView imageview_details_photo_homeTeam;
    @Bind(R.id.txt_details_result)
    TextView txt_details_result;
    @Bind(R.id.txt_details_awayTeam_name)
    TextView txt_details_awayTeam_name;
    @Bind(R.id.imageview_details_photo_awayTeam)
    ImageView imageview_details_photo_awayTeam;


    private Bundle bundle;
    private FixturesModelForLocal fixturesModelForLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        bundle = this.getIntent().getExtras();
        fixturesModelForLocal = (FixturesModelForLocal) bundle.getParcelable(Constants.MATCH_DETAILS_INTENT_KEY);
        addDetails();
    }

    public void addDetails() {
        Glide.with(this).load(TeamsData.getTeamsDataInstance(this)
                .getCrestUrlById(Integer.parseInt(fixturesModelForLocal.getHomeTeamId())))
                .asBitmap()
                .error(R.drawable.no_icon)
                .skipMemoryCache(false)
                .into(imageview_details_photo_homeTeam);
        txt_details_homeTeam_name.setText(fixturesModelForLocal.getHomeTeamName());

        Glide.with(this).load(TeamsData.getTeamsDataInstance(this)
                .getCrestUrlById(Integer.parseInt(fixturesModelForLocal.getAwayTeamId())))
                .asBitmap()
                .error(R.drawable.no_icon)
                .into(imageview_details_photo_awayTeam);
        txt_details_awayTeam_name.setText(fixturesModelForLocal.getAwayTeamName());

        if (fixturesModelForLocal.getGoalsHomeTeam() == null && fixturesModelForLocal.getGoalsAwayTeam() == null) {
            txt_details_result.setText("-:-");
        } else {
            txt_details_result.setText(fixturesModelForLocal.getGoalsHomeTeam() + " : " + fixturesModelForLocal.getGoalsAwayTeam());

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details_match, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            shareMatchDetails();
        }

        return super.onOptionsItemSelected(item);
    }


    private void shareMatchDetails() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");

        // Add data to the intent, the receiving app will decide
        // what to do with it.

        String result;
        if (fixturesModelForLocal.getGoalsHomeTeam() == null && fixturesModelForLocal.getGoalsAwayTeam() == null) {
            result = ("-:-");
        } else {
            result = (fixturesModelForLocal.getGoalsHomeTeam() + ":" + fixturesModelForLocal.getGoalsAwayTeam());

        }
        String finaly = fixturesModelForLocal.getHomeTeamName() + " " + result + " " + fixturesModelForLocal.getAwayTeamName();
        share.putExtra(Intent.EXTRA_TEXT, finaly);

        startActivity(Intent.createChooser(share, "Share Match details!"));
    }
}
