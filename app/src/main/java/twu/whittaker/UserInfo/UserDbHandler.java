package twu.whittaker.UserInfo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class UserDbHandler extends SQLiteOpenHelper{
    //  Database Information
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_ID = "CustomerId";
    private static final String COLUMN_FIRSTNAME = "FirstName";
    private static final String COLUMN_LASTNAME = "LastName";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_USERNAME = "UserName";
    private static final String COLUMN_FAVORITELOCAL = "FavoriteLocation";
    private static final String COLUMN_PASSWORD = "Password";
    private SQLiteDatabase db;

    //  Public Selected User Object to carry over after login
    public UserInfo selectedUserInfo;

    //  Initialize the Database
    public UserDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE  = " CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT, " + COLUMN_LASTNAME + " TEXT, " + COLUMN_EMAIL + " TEXT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_FAVORITELOCAL + " TEXT, " + COLUMN_PASSWORD + " TEXT " + " )";
        db.execSQL( CREATE_TABLE );
    }

    public void onUpgrade(SQLiteDatabase db, int i, int il){
    }

    public String loadHandler(){
        StringBuilder result = new StringBuilder();
        String query = "Select * FROM " + TABLE_NAME;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( query, null );
        while (cursor.moveToNext()){
            int result_0 = cursor.getInt( 0 );
            String result_1 = cursor.getString( 1 );
            String result_2 = cursor.getString( 2 );
            String result_3 = cursor.getString( 3 );
            String result_4 = cursor.getString( 4 );
            String result_5 = cursor.getString( 5 );
            String result_6 = cursor.getString( 6 );
            result.append( String.valueOf( result_0 ) ).append( " " ).append( result_1 ).append( " " )
                    .append( result_2 ).append( " " ).append( result_3 ).append( " " ).append( result_4 )
                    .append( " " ).append( result_5 ).append( " " ).append( result_6 )
                    .append( System.getProperty( "line.separator" ) );

        }
        cursor.close();
        db.close();
        return result.toString();
    }

    //  Adds a new user
   public void addHandler(UserInfo selectedUserInfo){
        ContentValues values = new ContentValues(  );
        values.put( COLUMN_ID, selectedUserInfo.getCustomerId() );
        values.put( COLUMN_FIRSTNAME, selectedUserInfo.getFirstName() );
        values.put( COLUMN_LASTNAME, selectedUserInfo.getLastName() );
        values.put( COLUMN_EMAIL, selectedUserInfo.getEmail() );
        values.put( COLUMN_USERNAME, selectedUserInfo.getUserid() );
        values.put( COLUMN_PASSWORD, selectedUserInfo.getPassword() );
        //values.put( COLUMN_FAVORITELOCAL, selectedUserInfo.getFavLocation() );
        db = this.getWritableDatabase();
        db.insert( TABLE_NAME, null, values );
        db.close();
    }

    //  Register a new user with an id number (auto-increment)
    public void RegisterUserHandler(UserInfo selectedUserInfo){
        ContentValues values = new ContentValues(  );
        values.put( COLUMN_ID, selectedUserInfo.getCustomerId() );
        values.put( COLUMN_FIRSTNAME, selectedUserInfo.getFirstName() );
        values.put( COLUMN_LASTNAME, selectedUserInfo.getLastName() );
        values.put( COLUMN_EMAIL, selectedUserInfo.getEmail() );
        values.put( COLUMN_USERNAME, selectedUserInfo.getUserid() );
        values.put( COLUMN_PASSWORD, selectedUserInfo.getPassword() );
       // values.put( COLUMN_FAVORITELOCAL, selectedUserInfo.getFavLocation() );
        db = this.getWritableDatabase();
        db.insert( TABLE_NAME, null, values );
        db.close();
    }

    //  Find user by first and last name
    public UserInfo findHandler(String First, String Last){
        String query = "Select * From " + TABLE_NAME + " WHERE " + COLUMN_FIRSTNAME + " = " +
                "'" + First + "'" + " AND " + COLUMN_LASTNAME + " = " + "'" + Last + "'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( query, null );
        UserInfo userInfo = new UserInfo(  );
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            userInfo.setCustomerId( Integer.parseInt( cursor.getString( 0 ) ) );
            userInfo.setFirstName( cursor.getString( 1 ) );
            userInfo.setLastName( cursor.getString( 2 ) );
            userInfo.setEmail( cursor.getString( 3 ) );
            userInfo.setUserid( cursor.getString( 4 ) );
            userInfo.setPassword( cursor.getString( 5 ) );
            userInfo.setFirstName( cursor.getString( 6 ) );
            cursor.close();
        } else {
            userInfo = null;
        }
        db.close();
        return userInfo;
    }

    //  Find user by userName
    public UserInfo findByUserNameHandler(String userName){
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = " +
                "'" + userName + "'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( query, null );
        UserInfo userInfo = new UserInfo(  );
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            userInfo.setCustomerId( Integer.parseInt( cursor.getString( 0 ) ) );
            userInfo.setFirstName( cursor.getString( 1 ) );
            userInfo.setLastName( cursor.getString( 2 ) );
            userInfo.setEmail( cursor.getString( 3 ) );
            userInfo.setUserid( cursor.getString( 4 ) );
            userInfo.setPassword( cursor.getString( 5 ) );
            //userInfo.setFavLocation( cursor.getString( 6 ) );
            cursor.close();
        } else {
            userInfo = null;
        }
        db.close();
        return userInfo;
    }

    //  Find user in the database by userId and password.  For login activity
    public UserInfo loginHandler(String userName, String password){
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = " +
                "'" + userName + "'" + " AND " + COLUMN_PASSWORD + " = " + "'" + password + "'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( query, null );
        //  Public Selected User Object to carry over after login
        UserInfo selectedUserInfo = new UserInfo();
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            selectedUserInfo.setCustomerId( Integer.parseInt( cursor.getString( 0 ) ) );
            selectedUserInfo.setFirstName( cursor.getString( 1 ) );
            selectedUserInfo.setLastName( cursor.getString( 2 ) );
            selectedUserInfo.setEmail( cursor.getString( 3 ) );
            selectedUserInfo.setUserid( cursor.getString( 4 ) );
            selectedUserInfo.setPassword( cursor.getString( 5 ) );
           // selectedUserInfo.setFavLocation( cursor.getString( 6 ) );
            cursor.close();
        } else {
            selectedUserInfo = null;
        }
        db.close();
        return selectedUserInfo;
    }

    //  Checks if the user exists by userId for Register Activity
    public UserInfo checkExistsHandler(String userName){
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = " +
                "'" + userName + "'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( query, null );
        UserInfo userInfo = new UserInfo(  );
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            userInfo.setCustomerId( Integer.parseInt( cursor.getString( 0 ) ) );
            userInfo.setFirstName( cursor.getString( 1 ) );
            userInfo.setLastName( cursor.getString( 2 ) );
            userInfo.setEmail( cursor.getString( 3 ) );
            userInfo.setUserid( cursor.getString( 4 ) );
            userInfo.setPassword( cursor.getString( 5 ) );
           // userInfo.setFavLocation( cursor.getString( 6 ) );
            cursor.close();
        } else {
            userInfo = null;
        }
        db.close();
        return userInfo;
    }

    //  Delete by id
    public boolean deleteByHandler(int Id) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = "+
                String.valueOf( Id ) + "'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( query, null );
        UserInfo userInfo = new UserInfo(  );
        if (cursor.moveToFirst()){
            userInfo.setCustomerId( Integer.parseInt( cursor.getString( 0 ) ) );
            db.delete( TABLE_NAME, COLUMN_ID + " =?", new String[]{
                    String.valueOf( userInfo.getCustomerId() )
            } );
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    //  Upgrade by id
    public boolean updateHandler (int Id, String First, String Last){
        db = this.getWritableDatabase();
        ContentValues args = new ContentValues(  );
        args.put( COLUMN_ID, Id );
        args.put( COLUMN_FIRSTNAME, First );
        args.put( COLUMN_LASTNAME, Last );
        return db.update( TABLE_NAME, args, COLUMN_ID + " = " + Id, null ) > 0;
    }
}
