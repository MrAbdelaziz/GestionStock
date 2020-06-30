package ma.emsi.gestionstock.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ma.emsi.gestionstock.R;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import ma.emsi.gestionstock.model.Produit;
import java.util.List;

public class ProduitsAdapter extends RecyclerView.Adapter<ProduitsAdapter.ProduitsViewHolder> {

    @NonNull
    @Override
    public ProduitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProduitsViewHolder extends RecyclerView.ViewHolder {
        public ProduitsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
