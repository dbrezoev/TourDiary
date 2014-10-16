package models;

import android.graphics.Picture;

import com.telerik.everlive.sdk.core.model.base.DataItem;
import com.telerik.everlive.sdk.core.serialization.ServerIgnore;
import com.telerik.everlive.sdk.core.serialization.ServerProperty;
import com.telerik.everlive.sdk.core.serialization.ServerType;

import java.util.UUID;

/**
 * Created by Dobromir on 11.10.2014 г..
 */

@ServerType("Place")
public class Place extends DataItem {

    private static final String WRONG_DESCRIPTION_MESSAGE = "Description of the Place object cannot be null or empty.";
    private static final String WRONG_NAME_MESSAGE = "Name of the Place object cannot be null or empty.";
    private static final String INVALID_PICTUREID_MESSAGE = "PictureId cannot be null.";
    private static final String INVALID_URI_MESSAGE = "Picture URI cannot be null or empty.";

    @ServerProperty("Name")
    private String name;

    @ServerProperty("Description")
    private String description;


    @ServerProperty("Picture")
    private UUID pictureId;

    @ServerProperty("Id")
    private UUID id;

    @ServerIgnore
    private String uri;

    public void setName(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException(WRONG_NAME_MESSAGE);
        }

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        if(description == null || description.isEmpty()){
            throw new IllegalArgumentException(WRONG_DESCRIPTION_MESSAGE);
        }

        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public UUID getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(UUID pictureId) {
        if(pictureId == null){
            throw new IllegalArgumentException(INVALID_PICTUREID_MESSAGE);
        }

        this.pictureId = pictureId;
    }

    public void setUri(String uri){
        if(uri == null || uri.isEmpty()){
            throw new IllegalArgumentException(INVALID_URI_MESSAGE);
        }

        this.uri = uri;
    }

    public String getUri(){
        return this.uri;
    }

    public void setUId(UUID id){
        this.id = id;
    }

    public UUID getId(){
        return this.id;
    }
}
