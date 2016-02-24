package it.jaschke.alexandria.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationLiveo;
import it.jaschke.alexandria.R;
import it.jaschke.alexandria.ui.fragment.FragmentAddBook;
import it.jaschke.alexandria.ui.fragment.FragmentListOfBooks;
import it.jaschke.alexandria.utils.AlexandriaSharedPref;

/**
 * Created by AdonisArifi on 11.1.2016 - 2016 . alexandria
 */
public class MainActivity2 extends NavigationLiveo implements OnItemClickListener {


    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle savedInstanceState) {

        // User Information
        this.userName.setText("Adonis Arifi");
        this.userEmail.setText("adonisarifi@gmail.com");
        this.userPhoto.setImageResource(R.drawable.profilphoto);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.list_of_books_navigation_item), R.drawable.ic_library_books_black_24dp);
        mHelpLiveo.add(getString(R.string.add_book_navigation_item), R.drawable.ic_add_black_24dp);


        mHelpLiveo.addSeparator(); // Item separator
        int startPosition = Integer.parseInt(AlexandriaSharedPref.getAlexandriaSharedPrefInstance(getApplicationContext()).getSelectStartScreen());
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

        switch (position) {
            case 0:
                fragment = new FragmentListOfBooks();
                break;
            case 1:
                fragment = new FragmentAddBook();
                break;
            default:
                fragment = FragmentListOfBooks.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (fragment != null) {
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
            Intent intent = new Intent(getApplicationContext(), SettingsActivity2.class);
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
