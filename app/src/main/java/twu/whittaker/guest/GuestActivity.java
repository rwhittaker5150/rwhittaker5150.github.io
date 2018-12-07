package twu.whittaker.guest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import twu.whittaker.R;
import twu.whittaker.login.LoginActivity;
import twu.whittaker.register.RegistrationActivity;

public class GuestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_guest );

        Button loginBtn = findViewById(R.id.loginBTN);
        loginBtn.setOnClickListener((v) -> openLoginActivity());

        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener((v -> openRegistrationActivity()));
    }

    private void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void openRegistrationActivity(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
