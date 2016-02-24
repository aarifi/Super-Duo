package barqsoft.footballscores.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.HashMap;

import barqsoft.footballscores.model.ListResponsFixturesMatch;
import barqsoft.footballscores.ui.fragment.FragmentNextDay1;
import barqsoft.footballscores.ui.fragment.FragmentNextDay2;
import barqsoft.footballscores.ui.fragment.FragmentPasttDay1;
import barqsoft.footballscores.ui.fragment.FragmentPasttDay2;
import barqsoft.footballscores.ui.fragment.FragmentToday;
import barqsoft.footballscores.utils.Constants;
import barqsoft.footballscores.utils.SupportMethod;

/**
 * Created by Priyabrat on 21-08-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private ListResponsFixturesMatch listFixtureMatch;
    private Context context;
    private HashMap<Integer, String> mFragmentTags;
    private FragmentManager fragmentManager;


    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        fragmentManager = fm;
        mFragmentTags = new HashMap<Integer, String>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == Constants.TAB_POSITION_PAST_DAY_2) {
            fragment = new FragmentPasttDay2();
        }
        if (position == Constants.TAB_POSITION_PAST_DAY_1) {
            fragment = new FragmentPasttDay1();
        }
        if (position == Constants.TAB_POSITION_TODAY) {
            fragment = new FragmentToday();
        }
        if (position == Constants.TAB_POSITION_NEXT_DAY_1) {
            fragment = new FragmentNextDay1();
        }
        if (position == Constants.TAB_POSITION_NEXT_DAY_2) {
            fragment = new FragmentNextDay2();
        }

        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            // record the fragment tag here.
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return fragmentManager.findFragmentByTag(tag);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }


    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);

    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if (position == Constants.TAB_POSITION_PAST_DAY_2) {
            DateTime dateTime = DateTime.now();
            title = SupportMethod.getSupportMethodInstance(context).getNameOfDayForTab(dateTime.minusDays(2));
        } else if (position == Constants.TAB_POSITION_PAST_DAY_1) {
            DateTime dateTime = DateTime.now();
            title = SupportMethod.getSupportMethodInstance(context).getNameOfDayForTab(dateTime.minusDays(1));

        } else if (position == Constants.TAB_POSITION_TODAY) {
            title = "TODAY";
        } else if (position == Constants.TAB_POSITION_NEXT_DAY_1) {
            DateTime dateTime = DateTime.now();
            title = SupportMethod.getSupportMethodInstance(context).getNameOfDayForTab(dateTime.plusDays(1));

        } else if (position == Constants.TAB_POSITION_NEXT_DAY_2) {
            DateTime dateTime = DateTime.now();
            title = SupportMethod.getSupportMethodInstance(context).getNameOfDayForTab(dateTime.plusDays(2));

        }
        return title;
    }

    public interface CallBacksItemCurrentPage {
        void onItemSelected(int position);
    }
}
