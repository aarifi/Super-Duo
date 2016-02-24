package it.jaschke.alexandria.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AdonisArifi on 15.12.2015 - 2015 . alexandria
 */
public class VolumeInfo implements Parcelable {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String title;
    @DatabaseField

    private List<String> authors = new ArrayList<String>();
    @DatabaseField

    private String publisher;
    @DatabaseField

    private String publishedDate;
    @DatabaseField

    private String description;
    @DatabaseField

    private Integer pageCount;
    @DatabaseField

    private String printType;
    @DatabaseField

    private List<String> categories = new ArrayList<String>();
    @DatabaseField

    private Double averageRating;
    @DatabaseField

    private Integer ratingsCount;
    @DatabaseField

    private String maturityRating;
    @DatabaseField

    private Boolean allowAnonLogging;
    @DatabaseField

    private String contentVersion;
    @DatabaseField

    private ImageLinks imageLinks;
    @DatabaseField

    private String language;
    @DatabaseField

    private String previewLink;
    @DatabaseField

    private String infoLink;
    @DatabaseField
    private String canonicalVolumeLink;

    @DatabaseField
    private List<IndustryIdentifiersModel> industryIdentifiers;

    public VolumeInfo() {

    }

    protected VolumeInfo(Parcel in) {
        id = in.readString();
        title = in.readString();
        authors = in.createStringArrayList();
        publisher = in.readString();
        publishedDate = in.readString();
        description = in.readString();
        printType = in.readString();
        categories = in.createStringArrayList();
        maturityRating = in.readString();
        contentVersion = in.readString();
        imageLinks = in.readParcelable(ImageLinks.class.getClassLoader());
        language = in.readString();
        previewLink = in.readString();
        infoLink = in.readString();
        canonicalVolumeLink = in.readString();
        industryIdentifiers = in.createTypedArrayList(IndustryIdentifiersModel.CREATOR);
    }

    public static final Creator<VolumeInfo> CREATOR = new Creator<VolumeInfo>() {
        @Override
        public VolumeInfo createFromParcel(Parcel in) {
            return new VolumeInfo(in);
        }

        @Override
        public VolumeInfo[] newArray(int size) {
            return new VolumeInfo[size];
        }
    };

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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public List<IndustryIdentifiersModel> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifiersModel> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeStringList(authors);
        dest.writeString(publisher);
        dest.writeString(publishedDate);
        dest.writeString(description);
        dest.writeString(printType);
        dest.writeStringList(categories);
        dest.writeString(maturityRating);
        dest.writeString(contentVersion);
        dest.writeParcelable(imageLinks, flags);
        dest.writeString(language);
        dest.writeString(previewLink);
        dest.writeString(infoLink);
        dest.writeString(canonicalVolumeLink);
        dest.writeTypedList(industryIdentifiers);
    }
}
