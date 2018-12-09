package twu.whittaker.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import twu.whittaker.R;
import twu.whittaker.SQLite.SQLiteHelper;
import twu.whittaker.SQLite.UserInfo;
import twu.whittaker.login.LoginActivity;


public class RegisterActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextConPwd;
    private Button Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //  Add onClickListner to the cancel button
        Cancel = findViewById(R.id.btnCancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //  Handles the Register Button Click Event
    public void onRegisterClick(View view){
        //  Get user entered input
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConPwd = findViewById(R.id.editTextConPwd);

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String conPwd = editTextConPwd.getText().toString();

        //  Validates all fields have content and password entered match
        if((userName.matches("")) || (firstName.matches("")) || (lastName.matches("")) || (email.matches(""))
        || (password.matches("")) || (conPwd.matches(""))){
            Toast.makeText(this, "One or more fields are empty.  Please fill in all fields.", Toast.LENGTH_LONG).show();

        } else if(!password.matches(conPwd)){
            //  If the passwords do not match display error
            Toast.makeText(this,"The passwords entered do not match.  Please re-enter the passwords and try again.", Toast.LENGTH_LONG).show();

        } else {
            //  Check to see if username already exists in database
            SQLiteHelper sqLiteHelper = new SQLiteHelper(this, null, null, 1);
            //  Calls the method to search for the user by userName
            UserInfo newUser = sqLiteHelper.checkExistsHandler(userName);
            //  If the user exists in the database return an error
            if (newUser != null) {
                Toast.makeText(this, "An account with that Username already exists.  Please try again.", Toast.LENGTH_LONG).show();

            } else {
                //  Set userid 0 - this is not used due to autoincrement function.
                int userid = 0;
                //  Otherwise create the user
                UserInfo userInfo = new UserInfo(userid, firstName, lastName, email, userName, password);
                sqLiteHelper.RegisterUserHandler(userInfo);

                //  Reset all the fields
                EmptyEditText();

                //  Go to the login page
                Intent intent = new Intent( this, LoginActivity.class);
                startActivity(intent);

                //  Display a message that the user was registered
                Toast.makeText(this, "Registration was successful!  Please login.", Toast.LENGTH_LONG).show();
            }
        }

    }

    //  Clear all the EditText Fields
    private void EmptyEditText() {
        editTextFirstName.getText().clear();
        editTextLastName.getText().clear();
        editTextUserName.getText().clear();
        editTextEmail.getText().clear();
        editTextUserName.getText().clear();
        editTextPassword.getText().clear();
        editTextConPwd.getText().clear();
    }
}
