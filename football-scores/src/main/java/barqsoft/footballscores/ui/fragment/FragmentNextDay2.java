package barqsoft.footballscores.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import barqsoft.footballscores.R;
import barqsoft.footballscores.adapters.FixturesMatchAdapter;
import barqsoft.footballscores.data.FixtureData;
import barqsoft.footballscores.model.FixturesModelForLocal;
import barqsoft.footballscores.model.ListResponsFixturesMatch;
import barqsoft.footballscores.utils.SupportMethod;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AdonisArifi on 16.2.2016 - 2016 . Football_Scores-master
 */
public class FragmentNextDay2 extends Fragment {

    private static final String LOG = FragmentNextDay2.class.getSimpleName();
    @Bind(R.id.recyclerView_nextday_2)
    RecyclerView recyclerView_today;

    private ListResponsFixturesMatch listResponsFixturesMatch;
    private List<FixturesModelForLocal> fixturesList;
    private List<FixturesModelForLocal> fixturesListFilter = new ArrayList<>();
    private FixturesMatchAdapter fixturesMatchAdapter;

    public FragmentNextDay2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nextday_2, container, false);
        ButterKnife.bind(this, view);
        addDataToRecyclerView();
        return view;
    }

    public void addDataToRecyclerView() {
        filterData();
        fixturesMatchAdapter = new FixturesMatchAdapter(fixturesListFilter, getActivity());
        recyclerView_today.setAdapter(fixturesMatchAdapter);
        recyclerView_today.setLayoutManager(new LinearLayoutManager(getActivity()));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView_today.setLayoutManager(staggeredGridLayoutManager);
    }

    //get match only for the specific day
    public void filterData() {
        fixturesList = FixtureData.getTeamsDataInstance(getActivity()).getAll();
        fixturesListFilter.clear();
        for (FixturesModelForLocal list : fixturesList) {

            DateTime dateTime = SupportMethod.getSupportMethodInstance(getActivity()).getDateFromString(list.getDate());
            DateTime dateTime2 = DateTime.now().plusDays(2);
            if (dateTime.getDayOfMonth() == dateTime2.getDayOfMonth()) {
                fixturesListFilter.add(list);
            }

        }
    }
    @Override
    public void onStart() {
        super.onStart();

        Log.d(LOG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        addDataToRecyclerView();
        Log.d(LOG, "onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG, "onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG, "onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "onDestroy");

    }
}
