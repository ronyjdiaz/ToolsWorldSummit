package social.rony.com.firebaseauthdemo;

import android.app.Activity;
import android.os.Bundle;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import social.rony.com.firebaseauthdemo.adapter.ProfileListViewAdapter;
import social.rony.com.firebaseauthdemo.interfaces.apiService;
import social.rony.com.firebaseauthdemo.interfaces.FloresClient;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class listado extends Activity {
    public List<Profile>  lp;
  //  @BindView(R.id.list_pesan) ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        final ListView lv = (ListView) findViewById(R.id.list_pesan);






        apiService apiService = FloresClient.getClient().create(social.rony.com.firebaseauthdemo.interfaces.apiService.class);
        Call<List<Profile>> call = apiService.listadoFlores();


        call.enqueue(new Callback<List<Profile>>() {

            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                lp = response.body();
                ProfileListViewAdapter adapt = new ProfileListViewAdapter(listado.this, R.layout.pesan_list_row, lp);
                lv.setAdapter(adapt);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(listado.this, "Ha seleccionado la flor es " + lp.get(position).getName(),Toast.LENGTH_SHORT  ).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {

            }
        });




    }

}
