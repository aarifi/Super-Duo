package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by AdonisArifi on 17.2.2016 - 2016 . Football_Scores-master
 */
public class ListResponsTeams implements Parcelable{

    private List<TeamsModel> teams;

    private String count;

    protected ListResponsTeams(Parcel in) {
        teams = in.createTypedArrayList(TeamsModel.CREATOR);
        count = in.readString();
    }

    public static final Creator<ListResponsTeams> CREATOR = new Creator<ListResponsTeams>() {
        @Override
        public ListResponsTeams createFromParcel(Parcel in) {
            return new ListResponsTeams(in);
        }

        @Override
        public ListResponsTeams[] newArray(int size) {
            return new ListResponsTeams[size];
        }
    };

    public ListResponsTeams() {
    }

    public List<TeamsModel> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamsModel> teams) {
        this.teams = teams;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(teams);
        dest.writeString(count);
    }
}
