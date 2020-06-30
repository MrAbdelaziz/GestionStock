package ma.emsi.gestionstock.api;

import java.util.List;

import ma.emsi.gestionstock.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Field;

public interface Api {

    @GET("users/?format=json")
    Call<LoginResponse[]> userLogin();
}
