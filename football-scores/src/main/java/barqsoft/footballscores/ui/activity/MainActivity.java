package barqsoft.footballscores.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import barqsoft.footballscores.R;
import barqsoft.footballscores.data.FixtureData;
import barqsoft.footballscores.utils.Constants;
import barqsoft.footballscores.utils.FootballSharedPref;
import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationLiveo;


/**
 * Created by AdonisArifi on 11.1.2016 - 2016 . alexandria
 */
public class MainActivity extends NavigationLiveo implements OnItemClickListener {


    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle savedInstanceState) {

        // User Information
        this.userName.setText("Adonis Arifi");
        this.userEmail.setText("adonisarifi@gmail.com");
        this.userPhoto.setImageResource(R.drawable.profilphoto);
        this.userBackground.setImageResource(R.drawable.background);
        // Creating items navigation
        mHelpLiveo = new HelpLiveo();

        mHelpLiveo.add(getString(R.string.home), R.drawable.ic_soccer_black_24dp);
        mHelpLiveo.addSubHeader(getString(R.string.top_league));
        mHelpLiveo.add(getString(R.string.bundesliga),R.drawable.ic_soccer_black_24dp);
        mHelpLiveo.add(getString(R.string.premier_league),R.drawable.ic_soccer_black_24dp);
        mHelpLiveo.add(getString(R.string.primeradivison),R.drawable.ic_soccer_black_24dp);

        mHelpLiveo.add(getString(R.string.seriaa), R.drawable.ic_soccer_black_24dp);
        // mHelpLiveo.add(getString(R.string.c), R.drawable.ic_soccer_black_24dp);

        mHelpLiveo.addSeparator(); // Item separator
        mHelpLiveo.add(getString(R.string.champions_league), R.drawable.ic_soccer_black_24dp);
        int startPosition = 0;
        with(this).startingPosition(startPosition) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())

                        //{optional} - List Customization "If you remove these methods and the list will take his white standard color"
                        //.selectorCheck(R.drawable.selector_check) //Inform the background of the selected item color
                        //.colorItemDefault(R.color.nliveo_blue_colorPrimary) //Inform the standard color name, icon and counter
                        //.colorItemSelected(R.color.nliveo_purple_colorPrimary) //State the name of the color, icon and meter when it is selected
                        //.backgroundList(R.color.nliveo_black_light) //Inform the list of background color
                        //.colorLineSeparator(R.color.nliveo_transparent) //Inform the color of the subheader line

                        //{optional} - SubHeader Customization
                .colorItemSelected(R.color.primary)
                .colorNameSubHeader(R.color.primary)
                        //.colorLineSeparator(R.color.nliveo_blue_colorPrimary)

                .footerItem(R.string.action_settings, R.drawable.ic_settings_black_24dp)

                        //{optional} - Header Customization
                        //.customHeader(mCustomHeader)

                        //{optional} - Footer Customization
                        //.footerNameColor(R.color.nliveo_blue_colorPrimary)
                        //.footerIconColor(R.color.nliveo_blue_colorPrimary)
                        //.footerBackground(R.color.nliveo_white)


                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                        //.setOnClickFooterSecond(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);
    }

    @Override
    public void onItemClick(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = null;
        FixtureData.getTeamsDataInstance(getApplicationContext()).deleteAllRows();


        switch (position) {
            case 0:
                FootballSharedPref.getAlexandriaSharedPrefInstance(getApplicationContext()).saveLeagueSelected("0");
                fragment = new DeviceDetailsActivity();
                break;
            case 2:
                FootballSharedPref.getAlexandriaSharedPrefInstance(getApplicationContext()).saveLeagueSelected(Constants.BUNDESLIGA_API_ID);
                fragment = new DeviceDetailsActivity();

                break;
            case 3:
                FootballSharedPref.getAlexandriaSharedPrefInstance(getApplicationContext()).saveLeagueSelected(Constants.PREMIER_LEAGUE_API_ID);
                fragment = new DeviceDetailsActivity();
                break;
            case 4:
                FootballSharedPref.getAlexandriaSharedPrefInstance(getApplicationContext()).saveLeagueSelected(Constants.PRIMERA_DIVISION_API_ID);
                fragment = new DeviceDetailsActivity();
                break;
            case 5:
                FootballSharedPref.getAlexandriaSharedPrefInstance(getApplicationContext()).saveLeagueSelected(Constants.SERIE_A_API_ID);
                fragment = new DeviceDetailsActivity();
                break;

            case 7:
                FootballSharedPref.getAlexandriaSharedPrefInstance(getApplicationContext()).saveLeagueSelected(Constants.CHAMPIONS_LEAGUE_API_ID);
                fragment = new DeviceDetailsActivity();
                break;
           /* case 1:
                fragment = new FragmentAddBook();
                break;*/
            default:
                fragment = DeviceDetailsActivity.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (fragment != null) {
        /*    Intent service_start = new Intent(getApplicationContext(), myFetchService.class);
            startService(service_start);*/
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            closeDrawer();
        }
    };

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };
}
