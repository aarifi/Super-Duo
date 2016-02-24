package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AdonisArifi on 15.2.2016 - 2016 . Football_Scores-master
 */
public class TeamsLinks implements Parcelable{

    private Fixtures fixtures;

    private Players players;

    private Self self;

    protected TeamsLinks(Parcel in) {
        fixtures = in.readParcelable(Fixtures.class.getClassLoader());
        players = in.readParcelable(Players.class.getClassLoader());
        self = in.readParcelable(Self.class.getClassLoader());
    }

    public static final Creator<TeamsLinks> CREATOR = new Creator<TeamsLinks>() {
        @Override
        public TeamsLinks createFromParcel(Parcel in) {
            return new TeamsLinks(in);
        }

        @Override
        public TeamsLinks[] newArray(int size) {
            return new TeamsLinks[size];
        }
    };

    public Fixtures getFixtures() {
        return fixtures;
    }

    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(fixtures, flags);
        dest.writeParcelable(players, flags);
        dest.writeParcelable(self, flags);
    }
}
