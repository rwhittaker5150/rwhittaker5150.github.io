package twu.whittaker.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    //  Database Information
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_NAME = "User";
    public static final String COLUMN_ID = "CustomerId";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_USERNAME = "UserName";
    public static final String COLUMN_FAVORITELOCAL = "FavoriteLocation";
    public static final String COLUMN_PASSWORD = "Password";
    public SQLiteDatabase db;

    public SQLiteHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE  = " CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " VARCHAR, " + COLUMN_LASTNAME + " VARCHAR, " + COLUMN_EMAIL + " VARCHAR, " +
                COLUMN_USERNAME + " VARCHAR UNIQUE, " +
                COLUMN_FAVORITELOCAL + " VARCHAR, " + COLUMN_PASSWORD + " VARCHAR )";
        db.execSQL( CREATE_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
}
