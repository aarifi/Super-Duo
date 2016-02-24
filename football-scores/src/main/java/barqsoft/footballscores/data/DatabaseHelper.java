package barqsoft.footballscores.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import barqsoft.footballscores.R;
import barqsoft.footballscores.model.FixturesModelForLocal;
import barqsoft.footballscores.model.LeagueSeasonsModel;
import barqsoft.footballscores.model.TeamsModel;
import barqsoft.footballscores.utils.Constants;


/**
 * Created by AdonisArifi on 3.11.2015 - 2015 . PopularMovies,Stage1
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = Constants.DATABASE_NAME_ORMLITE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, TeamsModel.class);
            TableUtils.createTable(connectionSource, LeagueSeasonsModel.class);
            TableUtils.createTable(connectionSource, FixturesModelForLocal.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, TeamsModel.class, true);
            TableUtils.dropTable(connectionSource, LeagueSeasonsModel.class, true);
            TableUtils.dropTable(connectionSource, FixturesModelForLocal.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }


    private RuntimeExceptionDao<TeamsModel, Integer> categoriesModelsDao = null;

    public RuntimeExceptionDao<TeamsModel, Integer> getTeamsModelsDao() {
        if (categoriesModelsDao == null) {
            categoriesModelsDao = getRuntimeExceptionDao(TeamsModel.class);
        }
        return categoriesModelsDao;
    }


    private RuntimeExceptionDao<LeagueSeasonsModel, Integer> leagueSeasonsModelsDao = null;

    public RuntimeExceptionDao<LeagueSeasonsModel, Integer> getLeagueSeasonsModelDao() {
        if (leagueSeasonsModelsDao == null) {
            leagueSeasonsModelsDao = getRuntimeExceptionDao(LeagueSeasonsModel.class);
        }
        return leagueSeasonsModelsDao;
    }


    private RuntimeExceptionDao<FixturesModelForLocal, Integer> fixturesModelForLocals = null;

    public RuntimeExceptionDao<FixturesModelForLocal, Integer> getFixturesModelForLocals() {
        if (fixturesModelForLocals == null) {
            fixturesModelForLocals = getRuntimeExceptionDao(FixturesModelForLocal.class);
        }
        return fixturesModelForLocals;
    }


}
