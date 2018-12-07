package twu.whittaker.userInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import twu.whittaker.R;
import twu.whittaker.register.RegistrationActivity;

public class UserDbActions extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_db_actions );

    }

    // Handles the load button click and returns the menu items
    public void onLoadClick(View view) {
        TextView newText = findViewById( R.id.menuTextView );
        UserDbHandler userDbHandler = new UserDbHandler(this, null, null, 1);
        newText.setText(userDbHandler.loadHandler());
    }
}