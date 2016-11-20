package social.rony.com.firebaseauthdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import social.rony.com.firebaseauthdemo.R;
import java.util.List;

import social.rony.com.firebaseauthdemo.Profile;

/**
 * Created by rony_2 on 20/11/2016.
 */

public class ProfileListViewAdapter extends ArrayAdapter {

    String url = "http://services.hanselandpetal.com/photos/";
    private Context context;
    private List<Profile> profileList;
    public ProfileListViewAdapter(Context context, int resource, List<Profile> object){
        super(context, resource, object);
        this.context = context;
        this.profileList = object;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pesan_list_row,parent,false);
        Profile flower = profileList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.isi_pesan);
        tv.setText(flower.getCategory());
        TextView tv1 = (TextView) view.findViewById(R.id.title1);
        tv1.setText(flower.getName());
        TextView tv2 = (TextView) view.findViewById(R.id.waktu);
        tv2.setText(flower.getPhoto());
        ImageView img = (ImageView) view.findViewById(R.id.thumbnail);
        Picasso.with(getContext()).load(url+flower.getPhoto()).resize(100,100).into(img);
        return view;
    }
}
