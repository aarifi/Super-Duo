package it.jaschke.alexandria.data;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import it.jaschke.alexandria.model.AuthorModel;
import it.jaschke.alexandria.model.BooksModel;
import it.jaschke.alexandria.model.CategoriesModel;

/**
 * Created by AdonisArifi on 3.11.2015 - 2015 . PopularMovies,Stage1
 */
public class DatabaseConfig extends OrmLiteConfigUtil {


    public static final Class<?>[] classes = new Class[]
            {
                    BooksModel.class,
                    CategoriesModel.class,
                    AuthorModel.class

            };


    public static void main(String[] args) {
        try {
            writeConfigFile("ormlite_config.txt", classes);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
