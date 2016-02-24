package it.jaschke.alexandria.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by AdonisArifi on 14.12.2015 - 2015 . alexandria
 */
public class BooksModel implements Parcelable {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String imgurl;
    @DatabaseField
    private String subtitle;
    @DatabaseField
    private String description;
    @DatabaseField
    private String imageLinks;
    @DatabaseField
    private String author;
    @DatabaseField
    private String publishedDate;
    @DatabaseField
    private String publisher;
    @DatabaseField
    private String categories;

    protected BooksModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        imgurl = in.readString();
        subtitle = in.readString();
        description = in.readString();
        imageLinks = in.readString();
        author = in.readString();
        publishedDate = in.readString();
        publisher = in.readString();
        categories = in.readString();
    }

    public static final Creator<BooksModel> CREATOR = new Creator<BooksModel>() {
        @Override
        public BooksModel createFromParcel(Parcel in) {
            return new BooksModel(in);
        }

        @Override
        public BooksModel[] newArray(int size) {
            return new BooksModel[size];
        }
    };

    public BooksModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(imgurl);
        dest.writeString(subtitle);
        dest.writeString(description);
        dest.writeString(imageLinks);
        dest.writeString(author);
        dest.writeString(publishedDate);
        dest.writeString(publisher);
        dest.writeString(categories);
    }
}
