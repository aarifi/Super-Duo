package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public class ListResponsFixturesMatch implements Parcelable {

    private List<Fixtures> fixtures;

    private String count;

    private String timeFrameStart;

    private String timeFrameEnd;

    protected ListResponsFixturesMatch(Parcel in) {
        fixtures = in.createTypedArrayList(Fixtures.CREATOR);
        count = in.readString();
        timeFrameStart = in.readString();
        timeFrameEnd = in.readString();
    }

    public static final Creator<ListResponsFixturesMatch> CREATOR = new Creator<ListResponsFixturesMatch>() {
        @Override
        public ListResponsFixturesMatch createFromParcel(Parcel in) {
            return new ListResponsFixturesMatch(in);
        }

        @Override
        public ListResponsFixturesMatch[] newArray(int size) {
            return new ListResponsFixturesMatch[size];
        }
    };

    public List<Fixtures> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixtures> fixtures) {
        this.fixtures = fixtures;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTimeFrameStart() {
        return timeFrameStart;
    }

    public void setTimeFrameStart(String timeFrameStart) {
        this.timeFrameStart = timeFrameStart;
    }

    public String getTimeFrameEnd() {
        return timeFrameEnd;
    }

    public void setTimeFrameEnd(String timeFrameEnd) {
        this.timeFrameEnd = timeFrameEnd;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(fixtures);
        dest.writeString(count);
        dest.writeString(timeFrameStart);
        dest.writeString(timeFrameEnd);
    }
}
