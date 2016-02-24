package barqsoft.footballscores.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import barqsoft.footballscores.R;
import barqsoft.footballscores.adapters.ViewPagerAdapter;
import barqsoft.footballscores.model.ListResponsFixturesMatch;
import barqsoft.footballscores.utils.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;


public class DeviceDetailsActivity extends Fragment {


    private Toolbar toolbar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;


    public static ListResponsFixturesMatch listResponsFixturesMatchPast;


    public boolean timeFrameStatusi = true;


    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    public static DeviceDetailsActivity newInstance(String s) {
        DeviceDetailsActivity fragmentPremierLeague = new DeviceDetailsActivity();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_FRAGMENT, s);
        fragmentPremierLeague.setArguments(bundle);
        return fragmentPremierLeague;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_device_details, container, false);
        ButterKnife.bind(this, rootView);
        addTab();

        return rootView;
    }


    public void addTab() {
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Fragment fragment = ((ViewPagerAdapter) viewPager.getAdapter()).getFragment(position);
                if (position == 0 && fragment != null) {
                    fragment.onResume();
                }
                if (position == 1 && fragment != null) {
                    fragment.onResume();
                }
                if (position == 2 && fragment != null) {
                    fragment.onResume();
                }

                if (position == 3 && fragment != null) {
                    fragment.onResume();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(Constants.TAB_POSITION_TODAY).select();


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


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();

    }


    public void getCurrentPage() {
        viewPager.getCurrentItem();
    }


}
