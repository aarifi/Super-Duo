package barqsoft.footballscores.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by AdonisArifi on 19.2.2016 - 2016 . Football_Scores-master
 */
public class SupportMethod {

    private Context context;
    public DateTimeFormatter dateTimeFormatter_for_api = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    public static SupportMethod supportMethodInstance;

    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SUNDAY = 7;

    public SupportMethod(Context context) {
        this.context = context;
    }

    public static SupportMethod getSupportMethodInstance(Context context) {
        if (supportMethodInstance == null) {
            supportMethodInstance = new SupportMethod(context);
        }
        return supportMethodInstance;
    }

    public String getTimeFromDate(String date) {
        DateTime dateTime = dateTimeFormatter_for_api.parseDateTime(date);
        return dateTime.toLocalTime().toString();
    }

    public DateTime getDateFromString(String date) {
        DateTime dateTime = dateTimeFormatter_for_api.parseDateTime(date);
        return dateTime.toDateTime();
    }

    public String getNameOfDayForTab(DateTime dateTime) {
        DateTime now = dateTime;
        String dayOfWeek = null;
        switch (now.getDayOfWeek()) {
            case DateTimeConstants.MONDAY:
                dayOfWeek = "Monday";
                break;
            case DateTimeConstants.TUESDAY:
                dayOfWeek = "Tuesday";
                break;
            case DateTimeConstants.WEDNESDAY:
                dayOfWeek = "Wednesday";
                break;
            case DateTimeConstants.THURSDAY:
                dayOfWeek = "Thursday";
                break;
            case DateTimeConstants.FRIDAY:
                dayOfWeek = "Friday";
                break;
            case DateTimeConstants.SATURDAY:
                dayOfWeek = "Saturday";
                break;
            case DateTimeConstants.SUNDAY:
                dayOfWeek = "Sunday";
                break;
        }

        return dayOfWeek;
    }


    public boolean IsConnectNetwork() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    public static String getPackageApp() {
        return "barqsoft.footballscores";
    }
}
