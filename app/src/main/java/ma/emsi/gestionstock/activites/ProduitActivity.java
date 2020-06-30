package ma.emsi.gestionstock.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ma.emsi.gestionstock.R;

public class ProduitActivity extends AppCompatActivity {

    private EditText id;
    private EditText Reference;
    private EditText Designation;
    private EditText quantite;
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
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            img.setImageResource(R.drawable.prod);
            id.setText(bundle.getString("id"));
            Reference.setText(bundle.getString("Reference"));
            Designation.setText(bundle.getString("Designation"));
            quantite.setText(bundle.getString("quantite"));
        }



    }
}
