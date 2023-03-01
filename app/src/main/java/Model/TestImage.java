package Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TestImage {
    @PrimaryKey(autoGenerate = true)
    int imageId;

    @ColumnInfo(name = "imageList", typeAffinity = ColumnInfo.BLOB)
    byte [] image;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
