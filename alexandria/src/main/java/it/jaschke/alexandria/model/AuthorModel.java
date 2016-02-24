package it.jaschke.alexandria.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by AdonisArifi on 14.12.2015 - 2015 . alexandria
 */
public class AuthorModel implements Parcelable {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String author;

    protected AuthorModel(Parcel in) {
        id = in.readString();
        author = in.readString();
    }

    public static final Creator<AuthorModel> CREATOR = new Creator<AuthorModel>() {
        @Override
        public AuthorModel createFromParcel(Parcel in) {
            return new AuthorModel(in);
        }

        @Override
        public AuthorModel[] newArray(int size) {
            return new AuthorModel[size];
        }
    };

    public AuthorModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
    }
}
