package softradix.discover.com.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import softradix.discover.com.R;
import softradix.discover.com.view.activities.ActivityMain;

/**
 * Created by softradix on 06/11/17.
 */

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    int[] myImageList = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7,
            R.drawable.image8, R.drawable.image9, R.drawable.image10
            , R.drawable.image11, R.drawable.image12, R.drawable.image13};

    public AdapterMain(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        displayMetrics = new DisplayMetrics();
        ((ActivityMain) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    DisplayMetrics displayMetrics;

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.adapter_main_list, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        Glide.clear(holder.imageView);

        Glide.with(context).load("").placeholder(myImageList[position])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return myImageList.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imageView)
        ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
