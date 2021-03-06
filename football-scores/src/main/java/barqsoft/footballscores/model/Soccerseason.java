package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public class Soccerseason implements Parcelable {

    private String href;

    protected Soccerseason(Parcel in) {
        href = in.readString();
    }

    public static final Creator<Soccerseason> CREATOR = new Creator<Soccerseason>() {
        @Override
        public Soccerseason createFromParcel(Parcel in) {
            return new Soccerseason(in);
        }

        @Override
        public Soccerseason[] newArray(int size) {
            return new Soccerseason[size];
        }
    };

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(href);
    }
}
