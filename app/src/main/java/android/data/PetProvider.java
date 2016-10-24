package android.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

public class PetProvider extends ContentProvider {

    // URI matcher code for the content URI
    private static final int PETS = 100;

    // URI matcher code for the content URI for a single pet
    private static final int PET_ID = 101;

    // UriMatcher object to match a content URI to a corresponding code
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // An instance of the PetsDbHelper class to access database
    private PetsDbHelper petsDbHelper;

    // static initializer. This is run the first time anything is called from this class
    static {
        // This content URI of the form "content://com.android.pets/pets" will map to the integer code PETS
        uriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS, PETS);

        // This content URI of the form "content://com.android.pets/pets/pets/#" will map to the integer code PET_ID
        uriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS + "/#", PET_ID);
    }

    @Override
    public boolean onCreate() {
        petsDbHelper = new PetsDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase db = petsDbHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);

        return null;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
