package io.github.freeman0211.sample.apl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.freeman0211.library.apl.AutoPhotoHelper;
import io.github.freeman0211.library.apl.AutoPhotoLayout;

/**
 * Created by freeman on 16/2/17.
 */
public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ItemViewHolder> {


    private List<List<String>> mItemList = null;

    public SimpleRecyclerViewAdapter (List<List<String>> itemList) {
        this.mItemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        List<ImageView> imageViewList = AutoPhotoHelper.initForImageView(holder.mLayout, mItemList.get(position).size());
        List<String> stringList = mItemList.get(position);
        AutoPhotoHelper.addOnItemClickListener(holder.mLayout, new AutoPhotoLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int childIndex) {
                Toast.makeText(holder.itemView.getContext(), "Child "+ childIndex, Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < stringList.size(); i++) {
            Glide.with(holder.itemView.getContext()).load(stringList.get(i)).fitCenter().into(imageViewList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        AutoPhotoLayout mLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mLayout = (AutoPhotoLayout) itemView.findViewById(R.id.apl_layout);
        }
    }
}
