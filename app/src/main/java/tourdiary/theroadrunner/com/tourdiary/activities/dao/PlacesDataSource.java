package tourdiary.theroadrunner.com.tourdiary.activities.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlacesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_NAME,
            SQLiteHelper.COLUMN_LATITUDE,
            SQLiteHelper.COLUMN_LONGITUDE,
            SQLiteHelper.COLUMN_DATE};

    public PlacesDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Place createPlace(String name, String latitude, String longitude, String date) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NAME, name);
        values.put(SQLiteHelper.COLUMN_LATITUDE, latitude);
        values.put(SQLiteHelper.COLUMN_LONGITUDE, longitude);
        values.put(SQLiteHelper.COLUMN_DATE, date);
        long insertId = database.insert(SQLiteHelper.TABLE_PLACES, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_PLACES,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Place newPlace = cursorToPlace(cursor);
        cursor.close();
        return newPlace;
    }

    public void deletePlace(Place place) {
        long id = place.getId();
        System.out.println("Place deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_PLACES, SQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Place> getAllPlaces() {
        List<Place> places = new ArrayList<Place>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_PLACES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Place place = cursorToPlace(cursor);
            places.add(place);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return places;
    }

    private Place cursorToPlace(Cursor cursor) {
        Place place = new Place();
        place.setId(cursor.getLong(0));
        place.setName(cursor.getString(1));
        place.setLatitude(cursor.getString(2));
        place.setLongitude(cursor.getString(3));
        place.setDate(cursor.getString(4));
        return place;
    }
}

