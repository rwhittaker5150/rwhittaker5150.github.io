package twu.whittaker.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import twu.whittaker.R;
import twu.whittaker.locations.*;

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    String[] countryList = {"India", "China", "Australia", "Portugal", "America", "New Zealand"};
    int[] flags = {R.drawable.india, R.drawable.china, R.drawable.australia, R.drawable.portugal, R.drawable.america, R.drawable.newzealand};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setContentView(R.layout.activity_search);
        listView = findViewById(R.id.destListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplication(), countryList, flags);
        listView.setAdapter(customAdapter);

        //  Make the list view clickable
        //  Clickable method
        listView.setOnItemClickListener((parent, view, position, id) -> {

            if(position == 0){
                Intent india = new Intent(SearchActivity.this, IndiaActivity.class);
                startActivity(india);
            } else if (position == 1){
                Intent china = new Intent(SearchActivity.this, ChinaActivity.class);
                startActivity(china);
            }else if (position == 2){
                Intent aussie = new Intent(SearchActivity.this, AustraliaActivity.class);
                startActivity(aussie);
            } else if (position == 3){
                Intent port = new Intent(SearchActivity.this, PortugalActivity.class);
                startActivity(port);
            } else if (position == 4){
                Intent usa = new Intent(SearchActivity.this, AmericaActivity.class);
                startActivity(usa);
            } else if (position == 5){
                Intent newz = new Intent(SearchActivity.this, NewZealandActivity.class);
                startActivity(newz);
            }
        });
    }
}
