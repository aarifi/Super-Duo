package barqsoft.footballscores.data;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import barqsoft.footballscores.model.LeagueSeasonsModel;

/**
 * Created by AdonisArifi on 18.2.2016 - 2016 . Football_Scores-master
 */
public class LeagueSeasonsData {

    private static final String LOG = LeagueSeasonsData.class.getSimpleName();
    static RuntimeExceptionDao<LeagueSeasonsModel, Integer> runtimeExceptionDao;
    DatabaseHelper databaseHelper;
    public static LeagueSeasonsData leagueSeasonsDataInstance;

    public static LeagueSeasonsData getLeagueSeasonsDataInstance(Context context) {
        if (leagueSeasonsDataInstance == null) {
            leagueSeasonsDataInstance = new LeagueSeasonsData(context);
        }
        return leagueSeasonsDataInstance;
    }

    public LeagueSeasonsData(Context context) {

        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        runtimeExceptionDao = databaseHelper.getLeagueSeasonsModelDao();
    }

    public String registerOrUpdate(LeagueSeasonsModel leagueSeasonsModel) {
        try {
            Dao.CreateOrUpdateStatus status = runtimeExceptionDao.createOrUpdate(leagueSeasonsModel);
            Log.d(LOG, status.toString());
            return status.toString();
        } catch (SQLiteException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public List<LeagueSeasonsModel> getAll() {
        List<LeagueSeasonsModel> list = null;
        QueryBuilder<LeagueSeasonsModel, Integer> queryBuilder = runtimeExceptionDao.queryBuilder();

        try {
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LeagueSeasonsModel getByTeamId(int teamId) {
        return runtimeExceptionDao.queryForId(teamId);
    }
}
