package barqsoft.footballscores.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AdonisArifi on 15.2.2016 - 2016 . Football_Scores-master
 */
public class Players implements Parcelable{


    private String dateOfBirth;

    private String position;

    private String jerseyNumber;

    private String nationality;

    private String contractUntil;

    private String name;

    private String marketValue;

    protected Players(Parcel in) {
        dateOfBirth = in.readString();
        position = in.readString();
        jerseyNumber = in.readString();
        nationality = in.readString();
        contractUntil = in.readString();
        name = in.readString();
        marketValue = in.readString();
    }

    public static final Creator<Players> CREATOR = new Creator<Players>() {
        @Override
        public Players createFromParcel(Parcel in) {
            return new Players(in);
        }

        @Override
        public Players[] newArray(int size) {
            return new Players[size];
        }
    };

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContractUntil() {
        return contractUntil;
    }

    public void setContractUntil(String contractUntil) {
        this.contractUntil = contractUntil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateOfBirth);
        dest.writeString(position);
        dest.writeString(jerseyNumber);
        dest.writeString(nationality);
        dest.writeString(contractUntil);
        dest.writeString(name);
        dest.writeString(marketValue);
    }
}
