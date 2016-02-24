package it.jaschke.alexandria.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import it.jaschke.alexandria.R;
import it.jaschke.alexandria.model.AuthorModel;
import it.jaschke.alexandria.model.BooksModel;
import it.jaschke.alexandria.model.CategoriesModel;
import it.jaschke.alexandria.utils.Constants;

/**
 * Created by AdonisArifi on 3.11.2015 - 2015 . PopularMovies,Stage1
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = Constants.DATABASE_NAME_ORMLITE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BooksModel.class);
            TableUtils.createTable(connectionSource, CategoriesModel.class);
            TableUtils.createTable(connectionSource, AuthorModel.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, BooksModel.class, true);
            TableUtils.dropTable(connectionSource, CategoriesModel.class, true);
            TableUtils.dropTable(connectionSource, AuthorModel.class, true);



            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }



    private RuntimeExceptionDao<CategoriesModel, Integer> categoriesModelsDao = null;

    public RuntimeExceptionDao<CategoriesModel, Integer> getCategoriesModelDao() {
        if (categoriesModelsDao == null) {
            categoriesModelsDao = getRuntimeExceptionDao(CategoriesModel.class);
        }
        return categoriesModelsDao;
    }

    private RuntimeExceptionDao<AuthorModel, Integer> authorModelsDao = null;

    public RuntimeExceptionDao<AuthorModel, Integer> getAuthorModelDao() {
        if (authorModelsDao == null) {
            authorModelsDao = getRuntimeExceptionDao(AuthorModel.class);
        }
        return authorModelsDao;
    }


    private RuntimeExceptionDao<BooksModel, Integer> booksModelsDao = null;

    public RuntimeExceptionDao<BooksModel, Integer> getBooksModelDao() {
        if (booksModelsDao == null) {
            booksModelsDao = getRuntimeExceptionDao(BooksModel.class);
        }
        return booksModelsDao;
    }

}
