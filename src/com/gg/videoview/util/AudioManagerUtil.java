package com.gg.videoview.util;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by gaohaoqing
 * Date : 16/3/24
 * Time : 18:30
 */
public class AudioManagerUtil {

    private static AudioManager mAudioManager; // Audio管理器，用了控制音量

    //增加音量
    public static void addVolume(Context context){
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.adjustStreamVolume (AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_SHOW_UI);
    }
    //减小音量
    public static void reduceVolume(Context context){
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.adjustStreamVolume (AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_SHOW_UI);
    }

}
