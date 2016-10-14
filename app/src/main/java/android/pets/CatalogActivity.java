package android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.data.PetContract.PetEntry;
import android.data.PetsDbHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    /**
     * The Pets database helper instance that will be used throughout this activity
     */
    private PetsDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new PetsDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Display an updated table rows
     */
    @SuppressWarnings("TryFinallyCanBeTryWithResources")
    private void displayDatabaseInfo() {
        // Create a new/ open an existing database
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = new String[]{
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
                PetEntry.COLUMN_PET_GENDER,
                PetEntry.COLUMN_PET_WEIGHT
        };

        // Get the cursor that represents rows of data returned by the query
        Cursor cursor = db.query(PetEntry.TABLE_NAME, projection, null, null, null, null, null);

        try {
            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
            displayView.setText(String.format(getString(R.string.counts_in_db), cursor.getCount()));

            // Create a header in the text view

            displayView.append(PetEntry._ID + " - " +
                    PetEntry.COLUMN_PET_NAME + " - " +
                    PetEntry.COLUMN_PET_BREED + " - " +
                    PetEntry.COLUMN_PET_GENDER + " - " +
                    PetEntry.COLUMN_PET_WEIGHT + "\n"
            );

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(PetEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
            int breedColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
            int genderColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                double currentWeight = cursor.getDouble(weightColumnIndex);

                // Display the values from each column of the current row in the TextView
                displayView.append("\n" + currentId + " - " +
                        currentName + " - " +
                        currentBreed + " - " +
                        String.valueOf(currentGender) + " - " +
                        String.valueOf(currentWeight)
                );
            }
        } finally {
            // always close the cursor to avoid memory leak
            cursor.close();
        }
    }

    /**
     * Insert a new pet record into the database
     */
    private void insertPet() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetEntry.COLUMN_PET_NAME, "Toto");
        contentValues.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        contentValues.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        contentValues.put(PetEntry.COLUMN_PET_WEIGHT, 7.0);

        long newRowId = dbHelper.getReadableDatabase().insert(PetEntry.TABLE_NAME, null, contentValues);
        Log.v("CatalogActivity", "New row ID " + newRowId);
    }
}
