package com.example.glideapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import static com.example.glideapp.MainActivity.i;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private ArrayList<RecyclerModel> mItems;
    private Context context;


    public RecyclerAdapter(ArrayList<RecyclerModel> mItems,Context context){
        this.mItems = mItems;
        this.context = context;

    }



    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new ItemViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        int[] idArray = {R.drawable.image0,R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8
                ,R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.image12,R.drawable.image13,R.drawable.image14,R.drawable.image15,R.drawable.image16,R.drawable.image17
                ,R.drawable.image18,R.drawable.image19,R.drawable.image20,R.drawable.image21};

        Target<GlideDrawable> id = Glide.with(context).load(idArray[i]).into(holder.imageview);
        i++;
        holder.imageview.setImageResource(mItems.get(position).id);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageview;
        public Context context;
        public ItemViewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.imageview1);




        }
    }
}



