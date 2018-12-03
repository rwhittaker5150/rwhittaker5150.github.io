package twu.whittaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import twu.whittaker.UserInfo.UserDbHandler;
import twu.whittaker.UserInfo.UserInfo;


public class RegistrationActivity extends Activity {

    private EditText editTxtUsr;
    private EditText editTxtPwd;
    private EditText editTxtCPwd;
    private UserInfo newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );
    }

    //  Handles the Registration Button Click Event
    public void onRegisterClick(View view){
        //  Get the user's input
        EditText editTxtFn = findViewById( R.id.editTxtFN );
        EditText editTxtLn = findViewById( R.id.editTxtLN );
        EditText editTxtEmail = findViewById( R.id.editTxtEmail );
        editTxtUsr = findViewById( R.id.editTxtUsr);
        editTxtPwd = findViewById( R.id.editTxtPwd);
        editTxtCPwd = findViewById( R.id.editTxtCPwd );


        String firstName = editTxtFn.getText().toString();
        String lastName = editTxtLn.getText().toString();
        String email = editTxtEmail.getText().toString();
        String userid = editTxtUsr.getText().toString();
        String pwd = editTxtPwd.getText().toString();
        String comPwd = editTxtCPwd.getText().toString();

        //  Validate all fields have content
        if( (firstName.matches( "" )) || (lastName.matches( "" )) ||
                (email.matches( "" )) || (userid.matches( "" )) ||
                (pwd.matches( "" )) || (comPwd.matches( "" ))) {
            Toast.makeText( this, "One or more fields are empty. " +
                    "Please fill in all the fields and try again.", Toast.LENGTH_LONG ).show();
        } else if (!pwd.matches( comPwd )){
            //  If passwords do not match display an error
            Toast.makeText( this, "Passwords do not match. Please try again.", Toast.LENGTH_LONG ).show();
        } else {

            //  Checks to see if the user already exist
            UserDbHandler userDbHandler = new UserDbHandler(this, null, null, 1);

            //  Calls the method to search for the user by userName
            newUser = userDbHandler.checkExistsHandler( userid );

            // If user exist in the database return an error
            if(newUser != null){
                Toast.makeText( this, "An account already exist with that username.", Toast.LENGTH_LONG ).show();
            } else {
                //  Set the id 0 - this is not used due to autoincrement function
                int id = 0;
                // Create the user
                UserInfo userInfo = new UserInfo( id, firstName, lastName, email, userid, pwd );
                userDbHandler.RegisterUserHandler(userInfo);

                //  Reset the text fields
                editTxtFn.setText( "" );
                editTxtLn.setText( "" );
                editTxtEmail.setText( "" );
                editTxtUsr.setText( "" );
                editTxtPwd.setText( "" );
                editTxtCPwd.setText( "" );

                //  Display a message that the user was registered
                Toast.makeText( this, "Registration was successful!  Please login now with your new account", Toast.LENGTH_LONG ).show();
                //  Go to the login page
                Intent intent = new Intent( this, LoginActivity.class );
                startActivity( intent );
            }
        }

    }

    public void onCancelClick(View view){
        Intent intent = new Intent( this, GuestActivity.class );
        startActivity( intent );
    }
}
