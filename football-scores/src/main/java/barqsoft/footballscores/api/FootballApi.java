package barqsoft.footballscores.api;

import java.util.List;

import barqsoft.footballscores.model.LeagueSeasonsModel;
import barqsoft.footballscores.model.ListResponsFixturesMatch;
import barqsoft.footballscores.model.ListResponsLeagueTable;
import barqsoft.footballscores.model.ListResponsTeams;
import barqsoft.footballscores.utils.Constants;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public interface FootballApi {
    @Headers("X-Response-Control:minified")
    @GET("/fixtures")
    ListResponsFixturesMatch getListResponsFootballHome(
            @Query(Constants.FOOTBALL_API_TIMEFRAME_PARAMETER) String value);


    @GET("/")
    ListResponsLeagueTable getListResponsLeagueTable(@Query("") String leagueID);

    @Headers("X-Response-Control:minified")
    @GET("/soccerseasons/{seasonId}/teams")
    ListResponsTeams getListResponsTeams(@Path("seasonId") String seasonId);

    @Headers("X-Response-Control:minified")
    @GET("/soccerseasons/?season=2015")
    List<LeagueSeasonsModel> getLeagueSeasonsModel();


    @Headers("X-Response-Control:minified")
    @GET("/soccerseasons/{soccerSeasonsId}/fixtures")
    ListResponsFixturesMatch getListResponsFootballForLeague(
            @Path("soccerSeasonsId")String soccerSeasonsId,
            @Query(Constants.FOOTBALL_API_TIMEFRAME_PARAMETER) String timeFrame);

}
