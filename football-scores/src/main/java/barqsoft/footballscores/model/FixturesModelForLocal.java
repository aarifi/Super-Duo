package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by AdonisArifi on 20.2.2016 - 2016 . Football_Scores-master
 */
public class FixturesModelForLocal implements Parcelable{

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String awayTeamId;
    @DatabaseField
    private String status;
    @DatabaseField
    private String soccerseasonId;
    @DatabaseField
    private String homeTeamId;
    @DatabaseField
    private String matchday;
    @DatabaseField
    private String awayTeamName;
    @DatabaseField
    private String date;
    @DatabaseField
    private String homeTeamName;
    @DatabaseField
    private String goalsHomeTeam;
    @DatabaseField
    private String goalsAwayTeam;

    public  FixturesModelForLocal()
    {

    }
    public FixturesModelForLocal(Parcel in) {
        id = in.readString();

        awayTeamId = in.readString();
        status = in.readString();
        soccerseasonId = in.readString();
        homeTeamId = in.readString();
        matchday = in.readString();
        awayTeamName = in.readString();
        date = in.readString();
        homeTeamName = in.readString();
        goalsHomeTeam = in.readString();
        goalsAwayTeam = in.readString();
    }

    public static final Creator<FixturesModelForLocal> CREATOR = new Creator<FixturesModelForLocal>() {
        @Override
        public FixturesModelForLocal createFromParcel(Parcel in) {
            return new FixturesModelForLocal(in);
        }

        @Override
        public FixturesModelForLocal[] newArray(int size) {
            return new FixturesModelForLocal[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSoccerseasonId() {
        return soccerseasonId;
    }

    public void setSoccerseasonId(String soccerseasonId) {
        this.soccerseasonId = soccerseasonId;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getMatchday() {
        return matchday;
    }

    public void setMatchday(String matchday) {
        this.matchday = matchday;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(String goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public String getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(String goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);

        dest.writeString(awayTeamId);
        dest.writeString(status);
        dest.writeString(soccerseasonId);
        dest.writeString(homeTeamId);
        dest.writeString(matchday);
        dest.writeString(awayTeamName);
        dest.writeString(date);
        dest.writeString(homeTeamName);
        dest.writeString(goalsHomeTeam);
        dest.writeString(goalsAwayTeam);
    }
}
