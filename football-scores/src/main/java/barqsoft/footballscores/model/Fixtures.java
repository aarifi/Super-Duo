package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public class Fixtures implements Parcelable{

    private String id;

    private Result result;

    private String awayTeamId;

    private String status;

    private String soccerseasonId;

    private String homeTeamId;

    private String matchday;

    private String awayTeamName;

    private String date;

    private String homeTeamName;

    private List<TeamsModel> teamsModels;


    protected Fixtures(Parcel in) {
        id = in.readString();
        result = in.readParcelable(Result.class.getClassLoader());
        awayTeamId = in.readString();
        status = in.readString();
        soccerseasonId = in.readString();
        homeTeamId = in.readString();
        matchday = in.readString();
        awayTeamName = in.readString();
        date = in.readString();
        homeTeamName = in.readString();
        teamsModels = in.createTypedArrayList(TeamsModel.CREATOR);
    }

    public static final Creator<Fixtures> CREATOR = new Creator<Fixtures>() {
        @Override
        public Fixtures createFromParcel(Parcel in) {
            return new Fixtures(in);
        }

        @Override
        public Fixtures[] newArray(int size) {
            return new Fixtures[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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

    public List<TeamsModel> getTeamsModels() {
        return teamsModels;
    }

    public void setTeamsModels(List<TeamsModel> teamsModels) {
        this.teamsModels = teamsModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(result, flags);
        dest.writeString(awayTeamId);
        dest.writeString(status);
        dest.writeString(soccerseasonId);
        dest.writeString(homeTeamId);
        dest.writeString(matchday);
        dest.writeString(awayTeamName);
        dest.writeString(date);
        dest.writeString(homeTeamName);
        dest.writeTypedList(teamsModels);
    }
}
