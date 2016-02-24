package barqsoft.footballscores.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
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
import barqsoft.footballscores.api.ApiClient;
import barqsoft.footballscores.data.FixtureData;
import barqsoft.footballscores.model.Fixtures;
import barqsoft.footballscores.model.FixturesModelForLocal;
import barqsoft.footballscores.model.ListResponsFixturesMatch;
import barqsoft.footballscores.utils.Constants;
import barqsoft.footballscores.utils.FootballSharedPref;
import barqsoft.footballscores.utils.SupportMethod;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AdonisArifi on 16.2.2016 - 2016 . Football_Scores-master
 */
public class FragmentPasttDay1 extends Fragment implements LoaderManager.LoaderCallbacks<ListResponsFixturesMatch> {
    private static final String LOG = FragmentPasttDay1.class.getSimpleName();
    @Bind(R.id.recyclerView_pastday_1)
    RecyclerView recyclerView_today;

    public static List<FixturesModelForLocal> fixturesList = new ArrayList<>();

    private List<FixturesModelForLocal> fixturesListFilter = new ArrayList<>();
    private FixturesMatchAdapter fixturesMatchAdapter;


    public FragmentPasttDay1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pastday_1, container, false);
        ButterKnife.bind(this, view);
        if (SupportMethod.getSupportMethodInstance(getActivity()).IsConnectNetwork()) {
            getLoaderManager().restartLoader(1, null, FragmentPasttDay1.this).forceLoad();

        }

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
            DateTime dateTime2 = DateTime.now().minusDays(1);
            if (dateTime.getDayOfMonth() == dateTime2.getDayOfMonth()) {
                fixturesListFilter.add(list);
            }

        }

    }

    @Override
    public Loader<ListResponsFixturesMatch> onCreateLoader(int id, Bundle args) {
        Loader<ListResponsFixturesMatch> listResponsFixturesMatchLoader = new FixtureMatchLoader(getActivity(),
                Constants.FOOTBALL_API_TIMEFRAME_PARAMETER_PAST_VALUE);
        return listResponsFixturesMatchLoader;
    }

    @Override
    public void onLoadFinished(Loader<ListResponsFixturesMatch> loader, ListResponsFixturesMatch data) {

        if (data != null) {

            FixturesModelForLocal fixturesModelForLocal = new FixturesModelForLocal();
            for (Fixtures fixtures : data.getFixtures()) {

                fixturesModelForLocal.setId(fixtures.getId());
                fixturesModelForLocal.setAwayTeamId(fixtures.getAwayTeamId());
                fixturesModelForLocal.setStatus(fixtures.getStatus());
                fixturesModelForLocal.setSoccerseasonId(fixtures.getSoccerseasonId());
                fixturesModelForLocal.setHomeTeamId(fixtures.getHomeTeamId());
                fixturesModelForLocal.setMatchday(fixtures.getMatchday());
                fixturesModelForLocal.setAwayTeamName(fixtures.getAwayTeamName());
                fixturesModelForLocal.setDate(fixtures.getDate());
                fixturesModelForLocal.setHomeTeamName(fixtures.getHomeTeamName());
                fixturesModelForLocal.setGoalsHomeTeam(fixtures.getResult().getGoalsHomeTeam());
                fixturesModelForLocal.setGoalsAwayTeam(fixtures.getResult().getGoalsAwayTeam());
                FixtureData.getTeamsDataInstance(getActivity()).registerOrUpdate(fixturesModelForLocal);


            }
            addDataToRecyclerView();
        }
    }

    @Override
    public void onLoaderReset(Loader<ListResponsFixturesMatch> loader) {

    }

    public static class FixtureMatchLoader extends AsyncTaskLoader<ListResponsFixturesMatch> {

        String timeFrameV;

        public FixtureMatchLoader(Context context, String timeFrameValue) {
            super(context);
            timeFrameV = timeFrameValue;
        }

        @Override
        public ListResponsFixturesMatch loadInBackground() {
            String leagueId = null;
            ListResponsFixturesMatch list;
            if (Integer.parseInt(Constants.BUNDESLIGA_API_ID) == FootballSharedPref.getAlexandriaSharedPrefInstance(getContext()).getLeagueSelected()) {
                leagueId = Constants.BUNDESLIGA_API_ID;
            } else if (Integer.parseInt(Constants.PREMIER_LEAGUE_API_ID) == FootballSharedPref.getAlexandriaSharedPrefInstance(getContext()).getLeagueSelected()) {
                leagueId = Constants.PREMIER_LEAGUE_API_ID;

            } else if (Integer.parseInt(Constants.PRIMERA_DIVISION_API_ID) == FootballSharedPref.getAlexandriaSharedPrefInstance(getContext()).getLeagueSelected()) {
                leagueId = Constants.PRIMERA_DIVISION_API_ID;

            } else if (Integer.parseInt(Constants.SERIE_A_API_ID) == FootballSharedPref.getAlexandriaSharedPrefInstance(getContext()).getLeagueSelected()) {
                leagueId = Constants.SERIE_A_API_ID;

            } else if (Integer.parseInt(Constants.CHAMPIONS_LEAGUE_API_ID) == FootballSharedPref.getAlexandriaSharedPrefInstance(getContext()).getLeagueSelected()) {
                leagueId = Constants.CHAMPIONS_LEAGUE_API_ID;

            }

            //Home tab selected
            if (FootballSharedPref.getAlexandriaSharedPrefInstance(getContext()).getLeagueSelected() == 0) {
                list = ApiClient.getApiClientInstance(getContext())
                        .geListResponsFootballHome(Constants.BASE_URL_FOOTBALL_DATA_API,
                                Constants.FOOTBALL_API_TIMEFRAME_PARAMETER_PAST_VALUE);

            } else {
                list = ApiClient.getApiClientInstance(getContext())
                        .getListResponsFootballForLeague(Constants.BASE_URL_FOOTBALL_DATA_API,
                                leagueId,
                                Constants.FOOTBALL_API_TIMEFRAME_PARAMETER_PAST_VALUE);
            }

            return list;
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
