
package com.murad.roya_news.models;

import com.google.gson.annotations.SerializedName;

public class Section {

    @SerializedName("ads_code")
    private String mAdsCode;
    @SerializedName("alias_ar")
    private String mAliasAr;
    @SerializedName("alias_en")
    private String mAliasEn;
    @SerializedName("deleted_at")
    private Object mDeletedAt;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private Long mId;
    @SerializedName("iframe")
    private Object mIframe;
    @SerializedName("name")
    private String mName;
    @SerializedName("order")
    private Long mOrder;
    @SerializedName("show_in_app")
    private Long mShowInApp;
    @SerializedName("show_in_homepage")
    private Long mShowInHomepage;
    @SerializedName("thumbs_images")
    private Long mThumbsImages;

    public String getAdsCode() {
        return mAdsCode;
    }

    public void setAdsCode(String adsCode) {
        mAdsCode = adsCode;
    }

    public String getAliasAr() {
        return mAliasAr;
    }

    public void setAliasAr(String aliasAr) {
        mAliasAr = aliasAr;
    }

    public String getAliasEn() {
        return mAliasEn;
    }

    public void setAliasEn(String aliasEn) {
        mAliasEn = aliasEn;
    }

    public Object getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        mDeletedAt = deletedAt;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Object getIframe() {
        return mIframe;
    }

    public void setIframe(Object iframe) {
        mIframe = iframe;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getOrder() {
        return mOrder;
    }

    public void setOrder(Long order) {
        mOrder = order;
    }

    public Long getShowInApp() {
        return mShowInApp;
    }

    public void setShowInApp(Long showInApp) {
        mShowInApp = showInApp;
    }

    public Long getShowInHomepage() {
        return mShowInHomepage;
    }

    public void setShowInHomepage(Long showInHomepage) {
        mShowInHomepage = showInHomepage;
    }

    public Long getThumbsImages() {
        return mThumbsImages;
    }

    public void setThumbsImages(Long thumbsImages) {
        mThumbsImages = thumbsImages;
    }

}
