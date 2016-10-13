package android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.data.PetContract.PetEntry;

public class PetsDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pets.db";
    private static final String TEXT_TYPE = " integer";
    private static final String INT_TYPE = " text";
    private static final String DECIMAL_TYPE = " real";
    private static final String NOT_NULL = " not null";
    private static final String DEFAULT = " default";
    private static final String COMMA_SEP = ",";

    // The SQL script
    private static final String SQL_CREATE_ENTRIES =
            "create table " + PetEntry.TABLE_NAME + " (" + PetEntry._ID + INT_TYPE + NOT_NULL + " primary key autoincrement" + COMMA_SEP
            + PetEntry.COLUMN_PET_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP
            + PetEntry.COLUMN_PET_BREED + TEXT_TYPE + COMMA_SEP
            + PetEntry.COLUMN_PET_GENDER + INT_TYPE + NOT_NULL + DEFAULT + " 0" + COMMA_SEP
            + PetEntry.COLUMN_PET_WEIGHT + DECIMAL_TYPE + ")";

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
