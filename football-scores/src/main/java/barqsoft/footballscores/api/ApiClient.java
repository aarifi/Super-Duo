package barqsoft.footballscores.api;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import barqsoft.footballscores.model.LeagueSeasonsModel;
import barqsoft.footballscores.model.ListResponsFixturesMatch;
import barqsoft.footballscores.model.ListResponsTeams;
import barqsoft.footballscores.utils.Constants;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public class ApiClient implements RequestInterceptor {
    private static final String LOG = ApiClient.class.getSimpleName();
    private static ApiClient apiClientInstance;
    private static FootballApi footballApiInterfaceMethod;

    public static ApiClient getApiClientInstance(Context context) {
        if (apiClientInstance == null) {
            apiClientInstance = new ApiClient();
        }

        return apiClientInstance;
    }

    public FootballApi getFootballApiInterfaceMethod(String url) {
        try {
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(100, TimeUnit.SECONDS);
            okHttpClient.setConnectTimeout(100, TimeUnit.SECONDS);
            if (footballApiInterfaceMethod == null) {
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setRequestInterceptor(this)
                        .setEndpoint(url)
                        .setErrorHandler(ErrorHandler.DEFAULT)
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setClient(new OkClient(okHttpClient))
                        .build();
                footballApiInterfaceMethod = restAdapter.create(FootballApi.class);
            }
        } catch (RetrofitError error) {
            error.getMessage();
        }


        return footballApiInterfaceMethod;
    }

    @Override
    public void intercept(RequestFacade request) {

        request.addHeader(Constants.FOOTBALL_API_KEY_PARAMETER, Constants.FOOTBALL_DATA_API_KEY);
        //  request.addHeader(Constants.RESPONSE_FORMAT, Constants.RESPONSE_FORMAT_VALUE);
    }

    public ListResponsFixturesMatch geListResponsFootballHome(String url, String timeFrameValeu) {
        ListResponsFixturesMatch listResponsFootball = null;
        try
        {
             listResponsFootball = getFootballApiInterfaceMethod(url).getListResponsFootballHome(timeFrameValeu);

        }catch (RetrofitError error)
        {
            error.getMessage();
        }
        return listResponsFootball;
    }

    public ListResponsTeams getListResponsTeams(String url, String seasonsId) {
        ListResponsTeams listResponsTeams = getFootballApiInterfaceMethod(url).getListResponsTeams(seasonsId);
        return listResponsTeams;
    }

    public List<LeagueSeasonsModel> getListResponsLeagueSeasons(String url) {
        List<LeagueSeasonsModel> leagueSeasonsModel = null;
        try {
            leagueSeasonsModel = getFootballApiInterfaceMethod(url).getLeagueSeasonsModel();

        } catch (RetrofitError e) {
            e.getMessage();
        }
        return leagueSeasonsModel;
    }

    public ListResponsFixturesMatch getListResponsFootballForLeague(String url, String leagueId, String timeFrameValue) {
        ListResponsFixturesMatch listResponsFixturesMatch = null;
        try{
             listResponsFixturesMatch = getFootballApiInterfaceMethod(url).getListResponsFootballForLeague(leagueId, timeFrameValue);

        }catch (RetrofitError error)
        {
            error.getMessage();
        }
        return listResponsFixturesMatch;
    }
}
