package social.rony.com.firebaseauthdemo.interfaces;

/**
 * Created by rony_2 on 20/11/2016.
 */


import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import social.rony.com.firebaseauthdemo.Profile;
import social.rony.com.firebaseauthdemo.adapter.FloresAdapterRetofit;

public interface apiService {

    @GET("feeds/flowers.json")
    Call <List<Profile>> listadoFlores();
}
