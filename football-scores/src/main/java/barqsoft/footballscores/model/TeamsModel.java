package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by AdonisArifi on 14.2.2016 - 2016 . Football_Scores-master
 */
public class TeamsModel implements Parcelable{

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String squadMarketValue;
    @DatabaseField
    private String crestUrl;
    @DatabaseField
    private String name;
    @DatabaseField
    private String shortName;

    protected TeamsModel(Parcel in) {
        id = in.readString();
        squadMarketValue = in.readString();
        crestUrl = in.readString();
        name = in.readString();
        shortName = in.readString();
    }

    public static final Creator<TeamsModel> CREATOR = new Creator<TeamsModel>() {
        @Override
        public TeamsModel createFromParcel(Parcel in) {
            return new TeamsModel(in);
        }

        @Override
        public TeamsModel[] newArray(int size) {
            return new TeamsModel[size];
        }
    };

    public TeamsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(squadMarketValue);
        dest.writeString(crestUrl);
        dest.writeString(name);
        dest.writeString(shortName);
    }
}
