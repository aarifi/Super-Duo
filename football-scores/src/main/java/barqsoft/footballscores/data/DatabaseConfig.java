package barqsoft.footballscores.data;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import barqsoft.footballscores.model.FixturesModelForLocal;
import barqsoft.footballscores.model.LeagueSeasonsModel;
import barqsoft.footballscores.model.TeamsModel;

/**
 * Created by AdonisArifi on 3.11.2015 - 2015 . PopularMovies,Stage1
 */
public class DatabaseConfig extends OrmLiteConfigUtil {


    public static final Class<?>[] classes = new Class[]
            {
                    TeamsModel.class,
                    LeagueSeasonsModel.class,
                    FixturesModelForLocal.class

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
