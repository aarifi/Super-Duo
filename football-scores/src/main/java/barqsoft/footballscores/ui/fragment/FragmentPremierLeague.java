package barqsoft.footballscores.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import barqsoft.footballscores.R;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public class FragmentPremierLeague extends Fragment {

    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    public static FragmentPremierLeague newInstance(String s) {
        FragmentPremierLeague fragmentPremierLeague = new FragmentPremierLeague();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_FRAGMENT, s);
        fragmentPremierLeague.setArguments(bundle);
        return fragmentPremierLeague;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_premierleague, container, false);
        return rootView;
    }
}
