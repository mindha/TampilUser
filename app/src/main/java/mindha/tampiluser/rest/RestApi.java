package mindha.tampiluser.rest;

import mindha.tampiluser.models.Models;
import mindha.tampiluser.models.User;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mindha on 15/01/2018.
 */

public interface RestApi {

    @GET("users/mindha")
    Call<User> getDataUser();
}
