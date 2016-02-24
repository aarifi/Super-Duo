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

import barqsoft.footballscores.model.TeamsModel;
import barqsoft.footballscores.utils.Constants;

/**
 * Created by AdonisArifi on 17.2.2016 - 2016 . Football_Scores-master
 */
public class TeamsData {

    private static final String LOG = TeamsData.class.getSimpleName();
    static RuntimeExceptionDao<TeamsModel, Integer> runtimeExceptionDao;
    DatabaseHelper databaseHelper;
    public static TeamsData teamsDataInstance;

    public static TeamsData getTeamsDataInstance(Context context) {
        if (teamsDataInstance == null) {
            teamsDataInstance = new TeamsData(context);
        }
        return teamsDataInstance;
    }

    public TeamsData(Context context) {

        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        runtimeExceptionDao = databaseHelper.getTeamsModelsDao();
    }

    public String registerOrUpdate(TeamsModel teamsModel) {
        try {
            Dao.CreateOrUpdateStatus status = runtimeExceptionDao.createOrUpdate(teamsModel);
            Log.d(LOG, status.toString());
            return status.toString();
        } catch (SQLiteException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public List<TeamsModel> getAll() {
        List<TeamsModel> list = null;
        QueryBuilder<TeamsModel, Integer> queryBuilder = runtimeExceptionDao.queryBuilder();

        try {
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public TeamsModel getByTeamId(int teamId) {
        return runtimeExceptionDao.queryForId(teamId);
    }

    public String getCrestUrlById(int teamId) {
        TeamsModel teamsModel = runtimeExceptionDao.queryForId(teamId);
        if (teamsModel == null) {
            return Constants.DEFULT_IMAGE_URL_FOOTBALL;
        } else {

            if (teamsModel.getCrestUrl() !=null && teamsModel.getCrestUrl().substring(teamsModel.getCrestUrl().length()-4).contains("svg")) {

                return Constants.DEFULT_IMAGE_URL_FOOTBALL;

            } else {
                return teamsModel.getCrestUrl();
            }


        }

    }
}
