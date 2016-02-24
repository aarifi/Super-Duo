package it.jaschke.alexandria.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by AdonisArifi on 15.12.2015 - 2015 . alexandria
 */
public class ListResponseBooks implements Parcelable {


    private String kind;
    private int totalItems;
    private List<ResponsItemsBook> items;

    protected ListResponseBooks(Parcel in) {
        kind = in.readString();
        totalItems = in.readInt();
        items = in.createTypedArrayList(ResponsItemsBook.CREATOR);
    }

    public static final Creator<ListResponseBooks> CREATOR = new Creator<ListResponseBooks>() {
        @Override
        public ListResponseBooks createFromParcel(Parcel in) {
            return new ListResponseBooks(in);
        }

        @Override
        public ListResponseBooks[] newArray(int size) {
            return new ListResponseBooks[size];
        }
    };

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<ResponsItemsBook> getItems() {
        return items;
    }

    public void setItems(List<ResponsItemsBook> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        dest.writeInt(totalItems);
        dest.writeTypedList(items);
    }
}
