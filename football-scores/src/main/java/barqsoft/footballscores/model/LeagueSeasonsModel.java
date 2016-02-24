package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by AdonisArifi on 18.2.2016 - 2016 . Football_Scores-master
 */
public class LeagueSeasonsModel implements Parcelable{

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String numberOfGames;
    @DatabaseField
    private String numberOfTeams;
    @DatabaseField
    private String lastUpdated;
    @DatabaseField
    private String currentMatchday;
    @DatabaseField
    private String year;
    @DatabaseField
    private String caption;
    @DatabaseField
    private String league;
    @DatabaseField
    private String numberOfMatchdays;

    protected LeagueSeasonsModel(Parcel in) {
        id = in.readString();
        numberOfGames = in.readString();
        numberOfTeams = in.readString();
        lastUpdated = in.readString();
        currentMatchday = in.readString();
        year = in.readString();
        caption = in.readString();
        league = in.readString();
        numberOfMatchdays = in.readString();
    }

    public static final Creator<LeagueSeasonsModel> CREATOR = new Creator<LeagueSeasonsModel>() {
        @Override
        public LeagueSeasonsModel createFromParcel(Parcel in) {
            return new LeagueSeasonsModel(in);
        }

        @Override
        public LeagueSeasonsModel[] newArray(int size) {
            return new LeagueSeasonsModel[size];
        }
    };

    public LeagueSeasonsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(String numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(String numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(String currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public void setNumberOfMatchdays(String numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(numberOfGames);
        dest.writeString(numberOfTeams);
        dest.writeString(lastUpdated);
        dest.writeString(currentMatchday);
        dest.writeString(year);
        dest.writeString(caption);
        dest.writeString(league);
        dest.writeString(numberOfMatchdays);
    }
}
