package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AdonisArifi on 15.2.2016 - 2016 . Football_Scores-master
 */
public class FixturesLinks implements Parcelable{

    private TeamsModel awayTeam;

    private Soccerseason soccerseason;

    private Self self;

    private TeamsModel homeTeam;

    protected FixturesLinks(Parcel in) {
        awayTeam = in.readParcelable(TeamsModel.class.getClassLoader());
        soccerseason = in.readParcelable(Soccerseason.class.getClassLoader());
        self = in.readParcelable(Self.class.getClassLoader());
        homeTeam = in.readParcelable(TeamsModel.class.getClassLoader());
    }

    public static final Creator<FixturesLinks> CREATOR = new Creator<FixturesLinks>() {
        @Override
        public FixturesLinks createFromParcel(Parcel in) {
            return new FixturesLinks(in);
        }

        @Override
        public FixturesLinks[] newArray(int size) {
            return new FixturesLinks[size];
        }
    };

    public TeamsModel getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamsModel awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Soccerseason getSoccerseason() {
        return soccerseason;
    }

    public void setSoccerseason(Soccerseason soccerseason) {
        this.soccerseason = soccerseason;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public TeamsModel getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamsModel homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(awayTeam, flags);
        dest.writeParcelable(soccerseason, flags);
        dest.writeParcelable(self, flags);
        dest.writeParcelable(homeTeam, flags);
    }
}
