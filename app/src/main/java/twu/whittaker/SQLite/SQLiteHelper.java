package twu.whittaker.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserDataBase";
    public static final String TABLE_NAME = "User";
    public static final String TABLE_COLUMN_ID = "id";
    public static final String TABLE_COLUMN_FRISTNAME = "firstname";
    public static final String TABLE_COLUMN_LASTNAME = "lastname";
    public static final String TABLE_COLUMN_EMAIL = "email";
    public static final String TABLE_COLUMN_USERNAME = "username";
    public static final String TABLE_COLUMN_PASSWORD = "password";

    //  Public Selected User Object to carry over after login
    public UserInfo selectedUserInfo;

    //  Initialize the Database
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //  Create the user database
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+TABLE_COLUMN_ID+" INTEGER PRIMARY KEY, "+
                TABLE_COLUMN_FRISTNAME + " TEXT, " + TABLE_COLUMN_LASTNAME + " TEXT, " + TABLE_COLUMN_EMAIL + " TEXT, " +
                TABLE_COLUMN_USERNAME + " TEXT, " + TABLE_COLUMN_PASSWORD + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
    }

    //  Load handler to show everything in the database
    public String loadHandler(){
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while(cursor.moveToNext()){
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            String result_4 = cursor.getString(4);
            String result_5 = cursor.getString(5);
            result += String.valueOf(result_0) + " " + result_1 + " " + result_2 + " " + result_3 + " " +
                    result_4 + " " + result_5 + " " + System.getProperty("line.separator");
        }
        //  Close cursor
        cursor.close();
        //  Close Database
        sqLiteDatabase.close();
        //  Return the results
        return result;
    }

    //  Register a new user without an id number (auto-increment)
    public void RegisterUserHandler(UserInfo userInfo){
        ContentValues values = new ContentValues();
        values.put(TABLE_COLUMN_FRISTNAME, userInfo.getFirstName());
        values.put(TABLE_COLUMN_LASTNAME, userInfo.getLastName());
        values.put(TABLE_COLUMN_EMAIL, userInfo.getEmail());
        values.put(TABLE_COLUMN_USERNAME, userInfo.getUserName());
        values.put(TABLE_COLUMN_PASSWORD, userInfo.getPassword());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    //  Find a user in the database by user id and password for login activity
    public UserInfo loginHandler(String userName, String password){
        String query = "Select * FROM " + TABLE_NAME + " WHERE " +  TABLE_COLUMN_USERNAME + " = " +
                "'" + userName + "'" + " AND " + TABLE_COLUMN_PASSWORD + " = " + "'" + password + "'";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        selectedUserInfo = new UserInfo();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            selectedUserInfo.setCustomerId(Integer.parseInt(cursor.getString(0)));
            selectedUserInfo.setFirstName(cursor.getString(1));
            selectedUserInfo.setLastName(cursor.getString(2));
            selectedUserInfo.setEmail(cursor.getString(3));
            selectedUserInfo.setUserName(cursor.getString(4));
            selectedUserInfo.setPassword((cursor.getString(5)));
            cursor.close();
        } else {
            selectedUserInfo = null;
        }
        sqLiteDatabase.close();
        return selectedUserInfo;
    }

    //  Checks if user exists by userid for Register Activity
    public UserInfo checkExistsHandler(String userName){
        String query = "Select * FROM " + TABLE_NAME + " WHERE " +  TABLE_COLUMN_USERNAME + " = " +
                "'" + userName + "'";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        UserInfo userInfo = new UserInfo();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            userInfo.setCustomerId(Integer.parseInt(cursor.getString(0)));
            userInfo.setFirstName(cursor.getString(1));
            userInfo.setLastName(cursor.getString(2));
            userInfo.setEmail(cursor.getString(3));
            userInfo.setUserName(cursor.getString(4));
            userInfo.setPassword((cursor.getString(5)));
            cursor.close();
        } else {
            userInfo = null;
        }
        sqLiteDatabase.close();
        return userInfo;
    }

    public boolean updateHandler(int ID, String first, String last){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(TABLE_COLUMN_ID, ID);
        args.put(TABLE_COLUMN_FRISTNAME, first);
        args.put(TABLE_COLUMN_LASTNAME, last);
        return sqLiteDatabase.update(TABLE_NAME, args, TABLE_COLUMN_ID + " = " + ID, null) > 0;
    }

}
