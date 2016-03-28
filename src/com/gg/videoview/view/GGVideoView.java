package com.gg.videoview.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import com.gg.videoview.R;
import com.gg.videoview.enums.VideoType;
import com.gg.videoview.util.AudioManagerUtil;
import com.gg.videoview.util.URLUtil;

/**
 * Created by gaohaoqing
 * Date : 16/3/24
 * Time : 16:36
 */
public class GGVideoView extends LinearLayout implements View.OnClickListener {
    //后退
    private ImageView iv_vv_back_up;
    //开始
    private ImageView iv_vv_play;
    //暂停
    private ImageView iv_vv_pause;
    //停止
    private ImageView iv_vv_stop;
    //快进
    private ImageView iv_vv_fast_forward;
    //音量减少
    private ImageView iv_vv_volume_reduce;
    //音量增加
    private ImageView iv_vv_volume_add;
    //全屏
    private ImageView iv_vv_full_screen;
    //进度条
    private SeekBar seek_vv_progress_bar;
    //音量条
    private SeekBar seek_vv_volume;
    //视频步长
    private int mVideoStep = 5000;
    //音量步长
    private int mVolumeStep = 1;
    //当前播放时间
    private int mCurrentPlayTime = 0;
    //总共时间
    private int mMaxTime = 0;

    private android.widget.VideoView vv_vv_video_view;

    private Context mContext;

    public GGVideoView(Context context) {
        super(context);
        initView(context);
    }

    public GGVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GGVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.video_view, this);

        iv_vv_back_up = (ImageView) findViewById(R.id.iv_vv_back_up);
        iv_vv_back_up.setOnClickListener(this);
        iv_vv_play = (ImageView) findViewById(R.id.iv_vv_play);
        iv_vv_play.setOnClickListener(this);
        iv_vv_pause = (ImageView) findViewById(R.id.iv_vv_pause);
        iv_vv_pause.setOnClickListener(this);
        iv_vv_stop = (ImageView) findViewById(R.id.iv_vv_stop);
        iv_vv_stop.setOnClickListener(this);
        iv_vv_fast_forward = (ImageView) findViewById(R.id.iv_vv_fast_forward);
        iv_vv_fast_forward.setOnClickListener(this);
        iv_vv_volume_reduce = (ImageView) findViewById(R.id.iv_vv_volume_reduce);
        iv_vv_volume_reduce.setOnClickListener(this);
        iv_vv_volume_add = (ImageView) findViewById(R.id.iv_vv_volume_add);
        iv_vv_volume_add.setOnClickListener(this);
        iv_vv_full_screen = (ImageView) findViewById(R.id.iv_vv_full_screen);
        iv_vv_full_screen.setOnClickListener(this);
        seek_vv_progress_bar = (SeekBar) findViewById(R.id.seek_vv_progress_bar);
        seek_vv_volume = (SeekBar) findViewById(R.id.seek_vv_volume);
        vv_vv_video_view = (android.widget.VideoView) findViewById(R.id.vv_vv_video_view);
    }

    /**
     * 设置URL
     *
     * @param url
     */
    public void setURL(String url) {
        String safeURL = URLUtil.returnSafeURL(url);
        vv_vv_video_view.setVideoURI(Uri.parse(safeURL));
    }

    /**
     * 根据Type显示button
     *
     * @param videoType
     */
    public void showBtnByType(VideoType videoType) {
        if (videoType == VideoType.Play) {
            iv_vv_play.setVisibility(View.GONE);
            iv_vv_pause.setVisibility(View.VISIBLE);
        } else if (videoType == VideoType.Pause) {
            iv_vv_pause.setVisibility(View.GONE);
            iv_vv_play.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_vv_play:
                playVideo();
                break;
            case R.id.iv_vv_pause:
                pauseVideo();
                break;
            case R.id.iv_vv_stop:
                stopVideo();
                break;
            case R.id.iv_vv_back_up:
                backUp();
                break;
            case R.id.iv_vv_fast_forward:
                fastForward();
                break;
            case R.id.iv_vv_volume_add:
                volumeAdd();
                break;
            case R.id.iv_vv_volume_reduce:
                volumeReduce();
                break;
        }
    }

    /**
     * 播放视频
     */
    private void playVideo() {
        if (!vv_vv_video_view.isPlaying()) {
            vv_vv_video_view.start();
            showBtnByType(VideoType.Play);
        }
    }

    /**
     * 暂停视频
     */
    private void pauseVideo() {
        if (vv_vv_video_view.isPlaying()) {
            vv_vv_video_view.pause();
            showBtnByType(VideoType.Pause);
        }
    }

    /**
     * 停止播放视频
     */
    private void stopVideo() {
        if (vv_vv_video_view.isPlaying()) {
            vv_vv_video_view.stopPlayback();
            showBtnByType(VideoType.Pause);
            vv_vv_video_view.seekTo(0);
        }
    }

    /**
     * 后退
     */
    private void backUp() {
        mCurrentPlayTime = vv_vv_video_view.getCurrentPosition();
        Log.e("video-view", "当前视频播放时长" + mCurrentPlayTime);
        if (mCurrentPlayTime < 5) mCurrentPlayTime = 0;
        mCurrentPlayTime = mCurrentPlayTime - mVideoStep;
        vv_vv_video_view.seekTo(mCurrentPlayTime);
    }

    /**
     * 快进
     */
    private void fastForward() {
        mCurrentPlayTime = vv_vv_video_view.getCurrentPosition();
        Log.e("video-view", "当前视频播放时长" + mCurrentPlayTime);
        mMaxTime = vv_vv_video_view.getDuration();
        Log.e("video-view", "当前视频总长度" + mMaxTime);
        if (mCurrentPlayTime + mVideoStep > mMaxTime) mCurrentPlayTime = mMaxTime;
        mCurrentPlayTime = mCurrentPlayTime + mVideoStep;
        vv_vv_video_view.seekTo(mCurrentPlayTime);
    }

    /**
     * 增加音量
     */
    private void volumeAdd() {
        AudioManagerUtil.addVolume(getContext());
    }

    /**
     * 减少音量
     */
    private void volumeReduce() {
        AudioManagerUtil.reduceVolume(getContext());
    }


}
