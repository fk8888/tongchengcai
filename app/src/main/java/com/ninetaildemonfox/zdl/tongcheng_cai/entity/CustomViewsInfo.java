package com.ninetaildemonfox.zdl.tongcheng_cai.entity;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/2
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class CustomViewsInfo extends SimpleBannerInfo {
    private String id;
    private String file_id;
    private String type;
    private String value;
    private String file_path;


    public CustomViewsInfo(String id, String file_id, String type, String value, String file_path) {
        this.id = id;
        this.file_id = file_id;
        this.type = type;
        this.value = value;
        this.file_path = file_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CustomViewsInfo(String file_path) {
        this.file_path = file_path;
    }
    @Override
    public String getXBannerUrl() {
        return file_path;
    }
}
