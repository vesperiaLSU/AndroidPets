package android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.data.PetContract.PetEntry;

public class PetsDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shelter.db";

    // The SQL script
    private static final String SQL_CREATE_ENTRIES =
            "create table " + PetEntry.TABLE_NAME + " ("
            + PetEntry._ID + " integer primary key autoincrement,"
            + PetEntry.COLUMN_PET_NAME + " text not null,"
            + PetEntry.COLUMN_PET_BREED + " text,"
            + PetEntry.COLUMN_PET_GENDER + " integer not null default 0,"
            + PetEntry.COLUMN_PET_WEIGHT + " real not null default 0)";

    private static final String SQL_DELETE_ENTRIES = "drop table if exists " + PetEntry.TABLE_NAME;


    /**
     * Constructor for creating a instance of {@link PetsDbHelper}
     * @param context of the using class
     */
    public PetsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy
        // is to simply discard the ata and start a new database
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
