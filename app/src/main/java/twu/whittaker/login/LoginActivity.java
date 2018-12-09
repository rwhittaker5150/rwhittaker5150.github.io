package twu.whittaker.login;

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
import twu.whittaker.search.SearchActivity;
import twu.whittaker.register.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    public UserInfo selectUser;
    private String userLogInName;

    Button SignUpBtn;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //  Add a click listener to the signup button
        SignUpBtn = findViewById(R.id.btnSignUp);
        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Opens the Registration Page
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //  Add a click listener to the login button;
        Login = findViewById(R.id.btnLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteHelper sqLiteHelper = new SQLiteHelper(LoginActivity.this, null, null, 1);
                EditText login = findViewById(R.id.edTxtUser);
                EditText password = findViewById(R.id.edTxtPwd);
                String loginName = login.getText().toString();
                String loginPwd = password.getText().toString();
                //  Calls the method to search for the user by userid and password
                selectUser = sqLiteHelper.loginHandler(loginName, loginPwd);
                //  If the selected user is not null then display an error message
                if (selectUser != null){
                    sqLiteHelper.selectedUserInfo = selectUser;
                    String userName = selectUser.getFirstName();
                    userLogInName = selectUser.getUserName();
                    login.setVisibility(View.INVISIBLE);

                    //  Open the search page to begin your searches
                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(intent);

                    Toast.makeText(LoginActivity.this, "You are now logged into Travel With Us! Welcome, " + userName + "!", Toast.LENGTH_LONG).show();
                } else {
                    //  If the user cannot be found display an error message
                    Toast.makeText(LoginActivity.this, "The login information cannot be found.  Please try again or register. ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
