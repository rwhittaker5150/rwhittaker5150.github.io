package twu.whittaker.locations.newzeal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import twu.whittaker.R;

import java.util.Objects;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    //  List of images
    private int[] lst_images = {
            R.drawable.milford_sound,
            R.drawable.bay_of_islands,
            R.drawable.tongariro_alpine_crossing,
            R.drawable.wai_o_tapu,
            R.drawable.franz_josef_glacier
    };

    //  List of Titles
    private int[] lst_titles = {
            R.string.mil,
            R.string.bay,
            R.string.ni,
            R.string.wai,
            R.string.par
    };

    //  List of Descriptions
    private int[] lst_description = {
            R.string.mil_description,
            R.string.bay_description,
            R.string.ni_description,
            R.string.wai_description,
            R.string.par_description
    };

    //  List of Prices
    private int[] lst_prices = {
            R.string.mil_price,
            R.string.bay_price,
            R.string.ni_price,
            R.string.wai_price,
            R.string.par_price
    };

    //  Price label
    private int priceLbl = R.string.price_lbl;
    //  Contact information.  Can be an array if more contacts are added.
    private int contact_info = R.string.contact_info;
    private int lst_contact = R.string.port_contact;
    private int title = R.string.port_label;


    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = Objects.requireNonNull(layoutInflater).inflate(R.layout.slide, container, false);
        TextView txtTop = view.findViewById(R.id.txtTop);
        ImageView imgSlide = view.findViewById(R.id.slideImage);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtDescription = view.findViewById(R.id.txtDescription);
        TextView txtPriceLbl = view.findViewById(R.id.txtPriceLbl);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        TextView txtContactLbl = view.findViewById(R.id.txtContactLbl);
        TextView txtContact = view.findViewById(R.id.txtContact);
        txtTop.setText(title);
        imgSlide.setImageResource(lst_images[position]);
        txtTitle.setText(lst_titles[position]);
        txtDescription.setText(lst_description[position]);
        txtPriceLbl.setText(priceLbl);
        txtPrice.setText(lst_prices[position]);
        txtContactLbl.setText(contact_info);
        txtContact.setText(lst_contact);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
