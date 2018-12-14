package twu.whittaker.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import twu.whittaker.R;

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    String countryList[] = {"India", "China", "Australia", "Portugle", "America", "New Zealand"};
    int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia, R.drawable.portugle, R.drawable.america, R.drawable.newzealand};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setContentView(R.layout.activity_search);
        listView = findViewById(R.id.destListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplication(), countryList, flags);
        listView.setAdapter(customAdapter);
    }
}
