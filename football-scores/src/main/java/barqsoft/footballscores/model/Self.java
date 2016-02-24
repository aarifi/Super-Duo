package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public class Self implements Parcelable{

    private String href;

    protected Self(Parcel in) {
        href = in.readString();
    }

    public static final Creator<Self> CREATOR = new Creator<Self>() {
        @Override
        public Self createFromParcel(Parcel in) {
            return new Self(in);
        }

        @Override
        public Self[] newArray(int size) {
            return new Self[size];
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
