package com.gg.videoview.enums;

/**
 * Created by gaohaoqing
 * Date : 16/3/24
 * Time : 15:51
 */
public enum VideoType {

    /**
     * 播放
     */
    Play(0, "播放"),
    /**
     * 暂停
     */
    Pause(1, "暂停");


    private int value;
    private String name;

    private VideoType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * 通过数值获取枚举对象
     *
     * @param value
     * @return
     */
    public static VideoType parse(int value) {
        VideoType type = VideoType.Play;
        for (VideoType t : VideoType.values()) {
            if (value == t.getValue()) {
                type = t;
                break;
            }
        }
        return type;
    }
}
