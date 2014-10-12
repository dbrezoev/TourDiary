package models;

import android.graphics.Picture;

import com.telerik.everlive.sdk.core.model.base.DataItem;
import com.telerik.everlive.sdk.core.serialization.ServerIgnore;
import com.telerik.everlive.sdk.core.serialization.ServerProperty;

import java.util.UUID;

/**
 * Created by Dobromir on 11.10.2014 г..
 */
public class Place extends DataItem {

    private static final String WrongDistanceMessage = "Distance cannot be less than zero.";

    //TODO: how to store images? Is it Bitmap?, how to store geo points
    private String name;

    @ServerProperty("Picture")
    private UUID pictureId;

    @ServerIgnore
    private int distanceToClient;

    public Place(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public UUID getPictureId(){
        return this.pictureId;
    }

    public void setPictureId(UUID id){
        this.pictureId = id;
    }

    public void setDistanceToClient(int distance){

        if(distance < 0){
            throw new IllegalArgumentException(WrongDistanceMessage);
        }

        this.distanceToClient = distance;
    }

    public int getDistanceToClient(){
        return this.distanceToClient;
    }

}
