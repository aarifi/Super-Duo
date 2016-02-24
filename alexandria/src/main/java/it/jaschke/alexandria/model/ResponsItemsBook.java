package it.jaschke.alexandria.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AdonisArifi on 15.12.2015 - 2015 . alexandria
 */
public class ResponsItemsBook implements Parcelable {

    private String kind;
    private String id;
    private String etag;
    private VolumeInfo volumeInfo;


    protected ResponsItemsBook(Parcel in) {
        kind = in.readString();
        id = in.readString();
        etag = in.readString();
        volumeInfo = in.readParcelable(VolumeInfo.class.getClassLoader());
    }

    public static final Creator<ResponsItemsBook> CREATOR = new Creator<ResponsItemsBook>() {
        @Override
        public ResponsItemsBook createFromParcel(Parcel in) {
            return new ResponsItemsBook(in);
        }

        @Override
        public ResponsItemsBook[] newArray(int size) {
            return new ResponsItemsBook[size];
        }
    };

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(kind);
        dest.writeString(id);
        dest.writeString(etag);
        dest.writeParcelable(volumeInfo, flags);
    }


}
