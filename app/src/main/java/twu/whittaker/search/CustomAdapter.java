package twu.whittaker.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import twu.whittaker.R;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private final String[] countryList;
    private final int[] flags;
    private final LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, String[] countryList, int[] flags){
        this.countryList = countryList;
        this.flags = flags;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount(){
        return countryList.length;
    }
    @Override
    public Object getItem(int i){
        return null;
    }

    @Override
    public long getItemId(int i){
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        view = inflater.inflate(R.layout.activity_listview, null);
        TextView country = view.findViewById(R.id.textView);
        ImageView icon = view.findViewById(R.id.icon);
        country.setText(countryList[i]);
        icon.setImageResource(flags[i]);
        return view;
    }

}
