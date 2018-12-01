package twu.whittaker;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        //  Image and Text
        ImageView imageView = findViewById(R.id.world);
        TextView textView = findViewById(R.id.welcome);

        //  Animation of the world image
        Animation animation = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.rotate_anim );
        imageView.startAnimation( animation );

        //  Animation of the text
        Animation animation1 = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.down_anim );
        textView.startAnimation( animation1 );

        //  Initialize the button
        Button startTravelBTN = findViewById(R.id.startTravel);
        startTravelBTN.setOnClickListener((v) -> openGuestActivity());

    }

    //  Opens the guest activity page
    private void openGuestActivity(){
        Intent intent = new Intent(this, GuestActivity.class);
        startActivity(intent);

    }
}
