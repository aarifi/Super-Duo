package barqsoft.footballscores.utils;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public class Constants {


    public static final String BASE_URL_FOOTBALL_DATA_API = "http://api.football-data.org/v1";
    public static final String LEAGUE_SEASONS_URL_FOOTBALL_DATA_API = "http://api.football-data.org/v1/soccerseasons/?season=2015";
    public static final String FOOTBALL_DATA_API_KEY = "061580a71dca4a06929bf8e01751ef58";
    public static final String FOOTBALL_API_KEY_PARAMETER = "X-Auth-Token";
    public static final String FOOTBALL_API_TIMEFRAME_PARAMETER = "timeFrame";
    public static final String FOOTBALL_API_TIMEFRAME_PARAMETER_PAST_VALUE = "p2";
    public static final String FOOTBALL_API_TIMEFRAME_PARAMETER_NEXT_VALUE = "n3";
    public static final String FOOTBALL_API_TIMEFRAME_PARAMETER_PAST_FOR_SPECIFIC_LEAGUE_VALUE = "p7";
    public static final String FOOTBALL_API_TIMEFRAME_PARAMETER_NEXT_FOR_SPECIFIC_LEAGUE_VALUE = "n7";
    public static final String RESPONSE_FORMAT = "X-Response-Control";
    public static final String RESPONSE_FORMAT_VALUE = "minified";


    public static final String BUNDESLIGA_API_ID = "394";
    public static final String PREMIER_LEAGUE_API_ID = "398";
    public static final String SERIE_A_API_ID = "401";
    public static final String PRIMERA_DIVISION_API_ID = "399";
    public static final String CHAMPIONS_LEAGUE_API_ID = "405";
    public static final String LEAGUE_SELECTED = SupportMethod.getPackageApp() + "LEAGUE_SELECTED";
    public static final String MATCH_DETAILS_INTENT_KEY = SupportMethod.getPackageApp() + "MATCH_DETAILS_INTENT_KEY";


    public static String DATABASE_NAME_ORMLITE = "footballScores.db";
    public static String DEFULT_IMAGE_URL_FOOTBALL = "http://icons.iconarchive.com/icons/blackvariant/button-ui-requests-9/512/Soccer-Football-icon.png";


    //Tab
    public static final int TAB_POSITION_PAST_DAY_2 = 0;
    public static final int TAB_POSITION_PAST_DAY_1 = 1;
    public static final int TAB_POSITION_TODAY = 2;
    public static final int TAB_POSITION_NEXT_DAY_1 = 3;
    public static final int TAB_POSITION_NEXT_DAY_2 = 4;

    public static final String ACTION_DATA_UPDATE = SupportMethod.getPackageApp() + ".ACTION_DATA_UPDATE";
    public static final String WIDGET_BUTTON  = SupportMethod.getPackageApp() + ".WIDGET_BUTTON ";

}
