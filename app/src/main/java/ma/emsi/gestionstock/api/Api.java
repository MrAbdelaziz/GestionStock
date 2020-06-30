package ma.emsi.gestionstock.api;

import androidx.annotation.Nullable;

import java.util.List;

import ma.emsi.gestionstock.model.DefaultResponse;
import ma.emsi.gestionstock.model.LoginResponse;
import ma.emsi.gestionstock.model.ProduitResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @GET("users/?format=json")
    Call<LoginResponse[]> userLogin();


    @GET("produits/?format=json")
    Call<ProduitResponse[]> produits();

    @FormUrlEncoded
    @PUT("produits/{id}/")
    Call<ProduitResponse> updateprod(
            @Path("id") String id,
            @Field("reference") String reference,
            @Field("designation") String designation,
            @Field("prixU") String prixU,
            @Field("quantite") String quantite,
            @Field("fournisseur") int fournisseur
    );

    @DELETE("produits/{id}/")
    Call<DefaultResponse> deleteprod(
            @Path("id") String id
    );


    @FormUrlEncoded
    @POST("produits/")
    Call<DefaultResponse> createProd(
            @Field("reference") String reference,
            @Field("designation") String designation,
            @Field("prixU") String prixU,
            @Field("quantite") String quantite,
            @Field("fournisseur") int fournisseur
    );


}
