
package com.murad.roya_news.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;
import com.murad.roya_news.R;

public class SectionInfo {

    @SerializedName("created_age")
    private String mCreatedAge;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("createdstamp")
    private Long mCreatedstamp;
    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("imageLink")
    private String mImageLink;
    @SerializedName("main_image_path")
    private String mMainImagePath;
    @SerializedName("news_id")
    private Long mNewsId;
    @SerializedName("news_link")
    private String mNewsLink;
    @SerializedName("news_section")
    private String mNewsSection;
    @SerializedName("news_title")
    private String mNewsTitle;
    @SerializedName("section")
    private Section mSection;
    @SerializedName("section_id")
    private Long mSectionId;
    @SerializedName("section_name")
    private String mSectionName;
    @SerializedName("updatedstamp")
    private Boolean mUpdatedstamp;

    public String getCreatedAge() {
        return mCreatedAge;
    }

    public void setCreatedAge(String createdAge) {
        mCreatedAge = createdAge;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public Long getCreatedstamp() {
        return mCreatedstamp;
    }

    public void setCreatedstamp(Long createdstamp) {
        mCreatedstamp = createdstamp;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public void setImageLink(String imageLink) {
        mImageLink = imageLink;
    }

    public String getMainImagePath() {
        return mMainImagePath;
    }

    public void setMainImagePath(String mainImagePath) {
        mMainImagePath = mainImagePath;
    }

    public Long getNewsId() {
        return mNewsId;
    }

    public void setNewsId(Long newsId) {
        mNewsId = newsId;
    }

    public String getNewsLink() {
        return mNewsLink;
    }

    public void setNewsLink(String newsLink) {
        mNewsLink = newsLink;
    }

    public String getNewsSection() {
        return mNewsSection;
    }

    public void setNewsSection(String newsSection) {
        mNewsSection = newsSection;
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        mNewsTitle = newsTitle;
    }

    public Section getSection() {
        return mSection;
    }

    public void setSection(Section section) {
        mSection = section;
    }

    public Long getSectionId() {
        return mSectionId;
    }

    public void setSectionId(Long sectionId) {
        mSectionId = sectionId;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public void setSectionName(String sectionName) {
        mSectionName = sectionName;
    }

    public Boolean getUpdatedstamp() {
        return mUpdatedstamp;
    }

    public void setUpdatedstamp(Boolean updatedstamp) {
        mUpdatedstamp = updatedstamp;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView,String imageUrl){

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_loading);
        Glide.with(imageView)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView);

    }
}
