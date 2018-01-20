package text.reflection.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import text.reflection.com.gallery.ImageAdapter;
import text.reflection.com.gallery.MyGallery;

/**
 * Created by Mr_ZY on 1/20/18.
 */

public class GalleryActivity extends Activity {

    private TextView tvTitle;
    private MyGallery gallery;
    private ImageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.gallery);

        initRes();
    }

    private void initRes() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        gallery = (MyGallery) findViewById(R.id.mygallery);     // 获取自定义的MyGallery控件

        adapter = new ImageAdapter(this);
        adapter.createReflectedImages();    // 创建倒影效果        
        gallery.setAdapter(adapter);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {    // 设置选择事件监听
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvTitle.setText(adapter.titles[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {          // 设置点击事件监听
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GalleryActivity.this, "img " + (position + 1) + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}  