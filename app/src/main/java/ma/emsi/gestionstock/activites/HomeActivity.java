package ma.emsi.gestionstock.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ma.emsi.gestionstock.R;
import ma.emsi.gestionstock.api.RetrofitClient;
import ma.emsi.gestionstock.model.LoginResponse;
import ma.emsi.gestionstock.model.Produit;
import ma.emsi.gestionstock.model.ProduitResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ListView list;
    private List<ProduitResponse> prodresp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list = findViewById(R.id.list);
        final ArrayList<Produit> produits = new ArrayList<Produit>();


        Call<ProduitResponse[]> call = RetrofitClient.getInstance().getApi().produits();
        call.enqueue(new Callback<ProduitResponse[]>() {
            @Override
            public void onResponse(Call<ProduitResponse[]> call, Response<ProduitResponse[]> response) {
                prodresp = Arrays.asList(response.body());
                class customAdapter extends BaseAdapter {
                    Context context;

                    public customAdapter(Context context) {
                        this.context = context;
                    }

                    @Override
                    public int getCount() {
                        return prodresp.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View row, ViewGroup parent) {

                        LayoutInflater inflater=getLayoutInflater();
                        row = inflater.inflate(R.layout.row,null,true);
                        ImageView img = row.findViewById(R.id.imgprod);
                        TextView reference = row.findViewById(R.id.reference);
                        TextView designation = row.findViewById(R.id.designation);
                        TextView quantite = row.findViewById(R.id.quantite);


                         img.setImageResource(R.drawable.prod);
                         reference.setText("Reference :"+prodresp.get(position).getReference());
                         designation.setText("Designation :"+prodresp.get(position).getDesignation());
                         quantite.setText("quantite :"+Integer.toString(prodresp.get(position).getQuantite()));

                        return row;
                    }
                }
                customAdapter Adapter = new customAdapter(getApplicationContext());
                list.setAdapter(Adapter);

            }

            @Override
            public void onFailure(Call<ProduitResponse[]> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"server 404",Toast.LENGTH_LONG).show();

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this,ProduitActivity.class);

                intent.putExtra("id",Integer.toString(prodresp.get(position).getId()));
                intent.putExtra("Reference",prodresp.get(position).getReference());
                intent.putExtra("Designation",prodresp.get(position).getDesignation());
                intent.putExtra("quantite",Integer.toString(prodresp.get(position).getQuantite()));
                startActivity(intent);
            }
        });
    }
}
