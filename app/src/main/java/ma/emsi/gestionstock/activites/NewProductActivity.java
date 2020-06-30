package ma.emsi.gestionstock.activites;

import androidx.appcompat.app.AppCompatActivity;

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

public class NewProductActivity extends AppCompatActivity {

    private EditText Reference;
    private EditText Designation;
    private EditText quantite;
    private EditText prix;
    ImageView img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        img = findViewById(R.id.imageViewadd);
        img.setImageResource(R.drawable.prod);

        Button btngoback = findViewById(R.id.gobackbtn);
        Button btnadd = findViewById(R.id.addbtnn);

        Reference = findViewById(R.id.Referenceadd);
        Designation = findViewById(R.id.Designationadd);
        quantite = findViewById(R.id.quantiteadd);
        prix = findViewById(R.id.prixadd);

        btngoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().createProd(Ref,Des,pri,qua,1);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        Intent intent = new Intent(NewProductActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        Toast.makeText(NewProductActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
