package io.github.freeman0211.sample.apl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final AutoPhotoLayout apl = (AutoPhotoLayout) findViewById(R.id.apl_layout);
        apl.setItemSpace(10);*/

        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("http://ww2.sinaimg.cn/large/83dcc5dbjw1dtpbjzipdaj.jpg",
                "http://img2.3lian.com/2014/f5/158/d/86.jpg",
                "http://ww2.sinaimg.cn/large/83dcc5dbjw1dtpbjzipdaj.jpg",
                "http://ww2.sinaimg.cn/large/83dcc5dbjw1dtpbjzipdaj.jpg",
                "http://ww2.sinaimg.cn/large/83dcc5dbjw1dtpbjzipdaj.jpg"));
        lists.add(Arrays.asList("http://ww2.sinaimg.cn/large/83dcc5dbjw1dtpbjzipdaj.jpg",
                "http://ww2.sinaimg.cn/large/83dcc5dbjw1dtpbjzipdaj.jpg"));
        lists.add(Arrays.asList("http://ww2.sinaimg.cn/large/83dcc5dbjw1dtpbjzipdaj.jpg"
                ));
        lists.add(Arrays.asList("http://img.sc115.com/uploads/sc/jpgs/07/pic5093_sc115.com.jpg",
                "http://img2.3lian.com/2014/f5/158/d/86.jpg",
                "http://img.sc115.com/uploads/sc/jpgs/07/pic5093_sc115.com.jpg",
                "http://img.sc115.com/uploads/sc/jpgs/07/pic5093_sc115.com.jpg",
                "http://ww2.sinaimg.cn/large/83dcc5dbjw1dtpbjzipdaj.jpg"));
        lists.add(Arrays.asList("http://img10.3lian.com/sc6/show02/38/65/386515.jpg",
                "http://img10.3lian.com/sc6/show02/38/65/386515.jpg",
                "http://img10.3lian.com/sc6/show02/38/65/386515.jpg",
                "http://img2.3lian.com/2014/f5/158/d/88.jpg",
                "http://img10.3lian.com/sc6/show02/38/65/386515.jpg"));
        lists.add(Arrays.asList("http://pic2.ooopic.com/11/86/60/84bOOOPIC13.jpg",
                "http://pic2.ooopic.com/11/86/60/84bOOOPIC13.jpg",
                "http://pic2.ooopic.com/11/86/60/84bOOOPIC13.jpg"));
        lists.add(Arrays.asList("http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg",
                "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg",
                "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg",
                "http://img2.3lian.com/2014/f5/158/d/88.jpg",
                "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg",
                "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg",
                "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg",
                "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg",
                "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg"));

        /*List<ImageView> imageViewList = AutoPhotoHelper.initForImageView(apl, urlList.size());
        AutoPhotoHelper.addOnItemClickListener(apl, new AutoPhotoLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int childIndex) {
                Toast.makeText(MainActivity.this, "child: " + childIndex, Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < imageViewList.size(); i++) {
            Glide.with(this).load(urlList.get(i)).fitCenter().into(imageViewList.get(i));
        }*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(lists));

    }
}
