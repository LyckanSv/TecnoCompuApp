package sv.com.tecnocompu.tecnocompuapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("picture_url")
    @Expose
    private String pictureUrl;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("searches")
    @Expose
    private Integer searches;
    @SerializedName("category_id")
    @Expose
    private String categoryId;

    protected Product(Parcel in) {
        name = in.readString();
        description = in.readString();
        pictureUrl = in.readString();
        manufacturer = in.readString();
        model = in.readString();
        if (in.readByte() == 0) {
            likes = null;
        } else {
            likes = in.readInt();
        }
        if (in.readByte() == 0) {
            searches = null;
        } else {
            searches = in.readInt();
        }
        categoryId = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getSearches() {
        return searches;
    }

    public void setSearches(Integer searches) {
        this.searches = searches;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(pictureUrl);
        parcel.writeString(manufacturer);
        parcel.writeString(model);
        if (likes == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(likes);
        }
        if (searches == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(searches);
        }
        parcel.writeString(categoryId);
    }
}