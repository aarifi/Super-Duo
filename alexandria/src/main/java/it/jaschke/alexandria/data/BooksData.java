package it.jaschke.alexandria.data;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import it.jaschke.alexandria.model.BooksModel;

/**
 * Created by AdonisArifi on 15.1.2016 - 2016 . alexandria
 */
public class BooksData {

    private static final String LOG = BooksData.class.getSimpleName();
    static RuntimeExceptionDao<BooksModel, Integer> runtimeExceptionDao;

    DatabaseHelper databaseHelper;

    public static BooksData booksDataInstance;

    public static BooksData getBooksDataInstance(Context context) {
        if (booksDataInstance == null) {
            booksDataInstance = new BooksData(context);
        }
        return booksDataInstance;
    }

    public BooksData(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        runtimeExceptionDao = databaseHelper.getBooksModelDao();
    }

    public String registerOrUpdate(BooksModel booksModel) {
        try {
            Dao.CreateOrUpdateStatus status = runtimeExceptionDao.createOrUpdate(booksModel);
            if (status.isCreated()) {
                return "isCreated";

            }
            if (status.isUpdated()) {
                return "isUpdate";
            }

            return "";
        } catch (SQLiteException ex) {
            ex.printStackTrace();
            return "";
        }

    }

    public List<BooksModel> getAll() {
        List<BooksModel> list = null;
        QueryBuilder<BooksModel, Integer> queryBuilder = runtimeExceptionDao.queryBuilder();
        try {
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public BooksModel getbyIsbn(int isbn) {
        return runtimeExceptionDao.queryForId(isbn);
    }

    public void deleteBook(int bookId) {
        runtimeExceptionDao.deleteById(bookId);
        Log.d(LOG, "deleteBook");
    }


}
