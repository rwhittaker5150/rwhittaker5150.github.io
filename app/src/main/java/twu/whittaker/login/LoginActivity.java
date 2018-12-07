package twu.whittaker.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import twu.whittaker.R;
import twu.whittaker.guest.GuestActivity;
import twu.whittaker.userInfo.UserDbHandler;
import twu.whittaker.userInfo.UserInfo;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

    }

    //  Login button click event
    public void onLoginClick(View view){
        UserDbHandler userDbHandler = new UserDbHandler(this, null, null, 1);
        EditText loginText = findViewById( R.id.userName);
        EditText pwdText = findViewById( R.id.login_password );
        String userName = loginText.getText().toString();
        String login_password = pwdText.getText().toString();

        //  Calls the method to search for by userid and password
        UserInfo selectUser = userDbHandler.loginHandler(userName, login_password);

        //  If the selected user is not null then display log in message otherwise display an error
        if(selectUser != null){
            userDbHandler.selectedUserInfo = selectUser;
            userName = selectUser.getFirstName();
            String userLoginName = selectUser.getUserid();
            loginText.setVisibility( View.INVISIBLE );
            Intent intent = new Intent( this, LoginActivity.class );
            intent.putExtra( "User Name", userLoginName );
            startActivity( intent );
            Toast.makeText( this, "You are now logged into Travel With Us, " + userName + "!", Toast.LENGTH_LONG ).show();
        } else {
            //  If the user cannont be found display an error
            Toast.makeText( this, "The login name and/or password are incorrect!  Please try again. ", Toast.LENGTH_LONG ).show();
        }

    }

    //  Cancel onClick vent
    public void onCancelClick(View view){
        Intent intent = new Intent( this, GuestActivity.class );
        startActivity( intent );
    }

}
