package android.data;

import android.provider.BaseColumns;

/**
 * The contract class for shelter database, defined final so that
 * it cannot be extended
 */
public final class PetContract {
    /**
     * Private constructor so that this class cannot be instantiated
     */
    private PetContract() {}

    /**
     * Inner class that define the schema for pets table
     */
    public static final class PetEntry implements BaseColumns {

        // table name
        public final static String TABLE_NAME = "pets";

        // column names
        final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PET_NAME = "name";
        public final static String COLUMN_PET_BREED = "breed";
        public final static String COLUMN_PET_GENDER = "gender";
        public final static String COLUMN_PET_WEIGHT = "weight";

        // constants for gender
        public final static int GENDER_UNKNOWN = 0;
        public final static int GENDER_MALE = 1;
        public final static int GENDER_FEMALE = 2;

    }
}
