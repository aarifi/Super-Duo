package it.jaschke.alexandria.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AdonisArifi on 15.1.2016 - 2016 . alexandria
 */
public class IndustryIdentifiersModel implements Parcelable {

    private String type;
    private String identifier;

    protected IndustryIdentifiersModel(Parcel in) {
        type = in.readString();
        identifier = in.readString();
    }

    public static final Creator<IndustryIdentifiersModel> CREATOR = new Creator<IndustryIdentifiersModel>() {
        @Override
        public IndustryIdentifiersModel createFromParcel(Parcel in) {
            return new IndustryIdentifiersModel(in);
        }

        @Override
        public IndustryIdentifiersModel[] newArray(int size) {
            return new IndustryIdentifiersModel[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(identifier);
    }
}
