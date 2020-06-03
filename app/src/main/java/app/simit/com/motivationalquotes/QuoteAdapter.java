package app.simit.com.motivationalquotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {
    private ArrayList<Quote> quotes = new ArrayList<>();
    private Context context;

    public QuoteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(quotes.get(position).getImageURL()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView parent;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.img);
        }
    }
    public void setQuotes(ArrayList<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }
}
