package com.example.starsgallery.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.service.StarService;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private List<Star> stars;        // liste originale — ne jamais modifier
    private List<Star> starsFilter;  // liste affichée
    private Context context;
    private NewFilter mfilter;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = new ArrayList<>(stars);      // copie indépendante
        this.starsFilter = new ArrayList<>(stars); // copie indépendante
        this.mfilter = new NewFilter(this);
    }

    @Override
    public StarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        final StarViewHolder holder = new StarViewHolder(v);

        holder.itemView.setOnClickListener(view -> {
            View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);
            ImageView img = popup.findViewById(R.id.img);
            RatingBar bar = popup.findViewById(R.id.ratingBar);
            TextView idss = popup.findViewById(R.id.idss);

            Bitmap bitmap = ((BitmapDrawable) ((ImageView) view.findViewById(R.id.img)).getDrawable()).getBitmap();
            img.setImageBitmap(bitmap);
            bar.setRating(((RatingBar) view.findViewById(R.id.stars)).getRating());
            idss.setText(((TextView) view.findViewById(R.id.ids)).getText().toString());

            new AlertDialog.Builder(context)
                    .setTitle("Notez :")
                    .setMessage("Donner une note entre 1 et 5 :")
                    .setView(popup)
                    .setPositiveButton("Valider", (dialog, which) -> {
                        float s = bar.getRating();
                        int ids = Integer.parseInt(idss.getText().toString());
                        Star star = StarService.getInstance().findById(ids);
                        star.setStar(s);
                        StarService.getInstance().update(star);
                        notifyItemChanged(holder.getAdapterPosition());
                    })
                    .setNegativeButton("Annuler", null)
                    .create()
                    .show();
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(StarViewHolder holder, int position) {
        String imgName = starsFilter.get(position).getImg();
        int resId = context.getResources().getIdentifier(
                imgName, "drawable", context.getPackageName()
        );

        Glide.with(context)
                .load(resId)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .circleCrop()
                .into(holder.img);

        holder.name.setText(starsFilter.get(position).getName().toUpperCase());
        holder.stars.setRating(starsFilter.get(position).getStar());
        holder.ids.setText(String.valueOf(starsFilter.get(position).getId()));
    }

    @Override
    public int getItemCount() { return starsFilter.size(); }

    @Override
    public Filter getFilter() { return mfilter; }

    public class StarViewHolder extends RecyclerView.ViewHolder {
        TextView ids, name;
        CircleImageView img;
        RatingBar stars;

        StarViewHolder(View itemView) {
            super(itemView);
            ids = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
        }
    }

    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;

        public NewFilter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Star> filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(stars);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Star p : stars) {
                    if (p.getName().toLowerCase().contains(filterPattern)) {
                        filtered.add(p);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            results.count = filtered.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter.clear();
            starsFilter.addAll((List<Star>) filterResults.values);
            mAdapter.notifyDataSetChanged();
        }
    }
}