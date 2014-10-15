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

    //TODO: how to store images? Is it Bitmap?, how to store geo points

    @ServerProperty("Name")
    private String name;

    @ServerProperty("Description")
    private String description;

    @ServerProperty("Picture")
    private UUID pictureId;

    @ServerIgnore
    private String uri;

//    public Place(String name, String description, UUID pictureId){
//        this.name = name;
//        this.description = description;
//        this.pictureId = pictureId;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public UUID getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(UUID pictureId) {
        this.pictureId = pictureId;
    }

    public void setUri(String uri){
        this.uri = uri;
    }

    public String getUri(){
        return this.uri;
    }

}
