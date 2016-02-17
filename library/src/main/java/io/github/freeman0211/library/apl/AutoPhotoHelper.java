package io.github.freeman0211.library.apl;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by freeman on 16/2/17.
 */
public class AutoPhotoHelper {

    public static List<ImageView> initForImageView (AutoPhotoLayout layout, int count) {
        layout.removeAllViews();
        List<ImageView> imageViewList = new ArrayList<>();
        AutoPhotoLayout.LayoutParams childLp =
                new AutoPhotoLayout.LayoutParams(AutoPhotoLayout.LayoutParams.WRAP_CONTENT,
                        AutoPhotoLayout.LayoutParams.WRAP_CONTENT);
//                new AutoPhotoLayout.LayoutParams(200, 400);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(layout.getContext());
            layout.addView(imageView, childLp);
            imageViewList.add(imageView);
        }
        return imageViewList;
    }

    public static void addOnItemClickListener (AutoPhotoLayout layout, final AutoPhotoLayout.OnItemClickListener listener) {
        if (layout != null) {
            for (int i = 0; i < layout.getChildCount(); i++) {
                final View child = layout.getChildAt(i);
                child.setTag(R.id.apl_child_tag_key, i);
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            Object tag = v.getTag(R.id.apl_child_tag_key);
                            int childIndex = 0;
                            if (tag != null && tag instanceof Integer) {
                                childIndex = (int) tag;
                            }
                            listener.onItemClick(v, childIndex);
                        }
                    }
                });
            }
        }
    }


}
