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

import barqsoft.footballscores.model.FixturesModelForLocal;

/**
 * Created by AdonisArifi on 17.2.2016 - 2016 . Football_Scores-master
 */
public class FixtureData {

    private static final String LOG = FixtureData.class.getSimpleName();
    static RuntimeExceptionDao<FixturesModelForLocal, Integer> runtimeExceptionDao;
    DatabaseHelper databaseHelper;
    public static FixtureData teamsDataInstance;

    public static FixtureData getTeamsDataInstance(Context context) {
        if (teamsDataInstance == null) {
            teamsDataInstance = new FixtureData(context);
        }
        return teamsDataInstance;
    }

    public FixtureData(Context context) {

        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        runtimeExceptionDao = databaseHelper.getFixturesModelForLocals();
    }

    public void registerOrUpdate(FixturesModelForLocal fixturesModelForLocal) {
        try {
            Dao.CreateOrUpdateStatus status = runtimeExceptionDao.createOrUpdate(fixturesModelForLocal);
            if (status.isCreated()) {
                Log.d(LOG, "isCreated");

            }
            if (status.isUpdated()) {
                Log.d(LOG, "isUpdated");

            }

        } catch (SQLiteException ex) {
            ex.printStackTrace();

        }
    }

    public List<FixturesModelForLocal> getAll() {
        List<FixturesModelForLocal> list = null;
        QueryBuilder<FixturesModelForLocal, Integer> queryBuilder = runtimeExceptionDao.queryBuilder();

        try {
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public FixturesModelForLocal getByTeamId(int teamId) {
        return runtimeExceptionDao.queryForId(teamId);
    }

    public void deleteAllRows() {
        List<FixturesModelForLocal> modelForLocal = getAll();
        runtimeExceptionDao.delete(modelForLocal);
    }


}
