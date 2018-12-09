package twu.whittaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import twu.whittaker.SQLite.SQLiteHelper;
import twu.whittaker.login.LoginActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Image and Text
        ImageView imageView = findViewById(R.id.imgWorld);
        imageView.setOnClickListener(this::onClickImage);
        TextView textView = findViewById(R.id.txtWelcome);

        //  Animation of the world image
        Animation animation = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.rotate_anim );
        imageView.startAnimation( animation );

        //  Animation of the text
        Animation animation1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.down_anim );
        textView.startAnimation( animation1 );

        //  Initialize the button
        Button startTravelBTN = findViewById(R.id.btnTravel);
        startTravelBTN.setOnClickListener((v) -> openRegisterActivity());
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void onClickImage(View view) {
        Intent intent = new Intent(this, SQLiteHelper.class);
        startActivity(intent);

    }
}
