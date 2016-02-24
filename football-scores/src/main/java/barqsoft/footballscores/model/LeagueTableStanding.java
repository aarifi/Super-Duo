package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AdonisArifi on 15.2.2016 - 2016 . Football_Scores-master
 */
public class LeagueTableStanding implements Parcelable{

    private String position;

    private String teamName;

    private String crestURI;

    private String playedGames;

    private _links _links;

    private String goals;

    private String goalDifference;

    private String losses;

    private String points;

    private String goalsAgainst;

    private String draws;

    private String wins;

    protected LeagueTableStanding(Parcel in) {
        position = in.readString();
        teamName = in.readString();
        crestURI = in.readString();
        playedGames = in.readString();
        _links = in.readParcelable(barqsoft.footballscores.model._links.class.getClassLoader());
        goals = in.readString();
        goalDifference = in.readString();
        losses = in.readString();
        points = in.readString();
        goalsAgainst = in.readString();
        draws = in.readString();
        wins = in.readString();
    }

    public static final Creator<LeagueTableStanding> CREATOR = new Creator<LeagueTableStanding>() {
        @Override
        public LeagueTableStanding createFromParcel(Parcel in) {
            return new LeagueTableStanding(in);
        }

        @Override
        public LeagueTableStanding[] newArray(int size) {
            return new LeagueTableStanding[size];
        }
    };

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public void setCrestURI(String crestURI) {
        this.crestURI = crestURI;
    }

    public String getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(String playedGames) {
        this.playedGames = playedGames;
    }

    public barqsoft.footballscores.model._links get_links() {
        return _links;
    }

    public void set_links(barqsoft.footballscores.model._links _links) {
        this._links = _links;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(String goalDifference) {
        this.goalDifference = goalDifference;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(position);
        dest.writeString(teamName);
        dest.writeString(crestURI);
        dest.writeString(playedGames);
        dest.writeParcelable(_links, flags);
        dest.writeString(goals);
        dest.writeString(goalDifference);
        dest.writeString(losses);
        dest.writeString(points);
        dest.writeString(goalsAgainst);
        dest.writeString(draws);
        dest.writeString(wins);
    }
}
