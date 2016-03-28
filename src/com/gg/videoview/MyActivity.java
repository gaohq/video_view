package com.gg.videoview;

import android.app.Activity;
import android.os.Bundle;
import com.gg.videoview.view.GGVideoView;

public class MyActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GGVideoView ggVideoView = (GGVideoView) findViewById(R.id.vv_main);
        ggVideoView.setURL("http://resource.heartonline.cn/20160314/1_jbundefinedFE6H.mp4");
    }
}
