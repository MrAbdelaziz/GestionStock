package ma.emsi.gestionstock.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ma.emsi.gestionstock.R;
import ma.emsi.gestionstock.api.RetrofitClient;
import ma.emsi.gestionstock.model.DefaultResponse;
import ma.emsi.gestionstock.model.ProduitResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProduitActivity extends AppCompatActivity{

    private EditText id;
    private EditText Reference;
    private EditText Designation;
    private EditText quantite;
    private EditText prix;

    ImageView img ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit);

        id = findViewById(R.id.id);
        Reference = findViewById(R.id.Reference);
        Designation = findViewById(R.id.Designation);
        quantite = findViewById(R.id.quantite);
        img = findViewById(R.id.imageView);
        prix = findViewById(R.id.prix);

        Button btnedit = findViewById(R.id.editbtn);
        Button btngoback = findViewById(R.id.gobackbtn);
        Button btndelete = findViewById(R.id.deletebtn);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            img.setImageResource(R.drawable.prod);
            id.setText(bundle.getString("id"));
            Reference.setText(bundle.getString("Reference"));
            Designation.setText(bundle.getString("Designation"));
            quantite.setText(bundle.getString("quantite"));
            prix.setText(bundle.getString("prix"));
        }

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateproduit();
            }
        });




        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deleteproduit();

                AlertDialog.Builder builder = new AlertDialog.Builder( ProduitActivity.this );
                builder.setTitle("Are you sure ?");
                builder.setMessage("This action is irreversible !");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteproduit();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });


        btngoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProduitActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateproduit() {
        final String idd = id.getText().toString().trim();
        final String Ref = Reference.getText().toString().trim();
        final String Des = Designation.getText().toString().trim();
        final String qua = quantite.getText().toString().trim();
        final String pri = prix.getText().toString().trim();

        if (Ref.isEmpty()) {
            Reference.setError("Reference is required");
            Reference.requestFocus();
            return;
        }


        if (Des.isEmpty()) {
            Designation.setError("Designation required");
            Designation.requestFocus();
            return;
        }

        if (qua.isEmpty()) {
            quantite.setError("quantite required");
            quantite.requestFocus();
            return;
        }

        if (pri.isEmpty()) {
            prix.setError("prix required");
            prix.requestFocus();
            return;
        }


        Call<ProduitResponse> call = RetrofitClient.getInstance().getApi().updateprod(idd,Ref,Des,pri,qua,1);
        call.enqueue(new Callback<ProduitResponse>() {
            @Override
            public void onResponse(Call<ProduitResponse> call, Response<ProduitResponse> response) {
                Intent intent = new Intent(ProduitActivity.this,HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ProduitResponse> call, Throwable t) {
                Toast.makeText(ProduitActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    private void deleteproduit() {
        final String idd = id.getText().toString().trim();

        Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().deleteprod(idd);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if(!response.body().isErr()){
                    Intent intent = new Intent(ProduitActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
/*
        Call call = RetrofitClient.getInstance().getApi().deleteprod(idd);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Intent intent = new Intent(ProduitActivity.this,HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(ProduitActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }


        });*/

    }


}
