package barqsoft.footballscores.utils;

import android.app.Application;
import android.content.Intent;

import barqsoft.footballscores.services.myFetchService;

/**
 * Created by AdonisArifi on 21.2.2016 - 2016 . Football_Scores-master
 */
public class FootballApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Intent service_start = new Intent(getApplicationContext(), myFetchService.class);
        getApplicationContext().startService(service_start);
    }

    public String getPackageApp() {
        return getPackageName().toString();
    }
}
