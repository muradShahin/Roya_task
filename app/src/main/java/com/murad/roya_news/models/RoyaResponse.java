package com.murad.roya_news.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RoyaResponse {

    @SerializedName("breaking_news")
    private List<Object> mBreakingNews;
    @SerializedName("now_showing")
    private Object mNowShowing;
    @SerializedName("section_info")
    private List<SectionInfo> mSectionInfo;
    @SerializedName("special_events")
    private List<SpecialEvent> mSpecialEvents;
    @SerializedName("surveys")
    private List<Object> mSurveys;

    public List<Object> getBreakingNews() {
        return mBreakingNews;
    }

    public void setBreakingNews(List<Object> breakingNews) {
        mBreakingNews = breakingNews;
    }

    public Object getNowShowing() {
        return mNowShowing;
    }

    public void setNowShowing(Object nowShowing) {
        mNowShowing = nowShowing;
    }

    public List<SectionInfo> getSectionInfo() {
        return mSectionInfo;
    }

    public void setSectionInfo(List<SectionInfo> sectionInfo) {
        mSectionInfo = sectionInfo;
    }

    public List<SpecialEvent> getSpecialEvents() {
        return mSpecialEvents;
    }

    public void setSpecialEvents(List<SpecialEvent> specialEvents) {
        mSpecialEvents = specialEvents;
    }

    public List<Object> getSurveys() {
        return mSurveys;
    }

    public void setSurveys(List<Object> surveys) {
        mSurveys = surveys;
    }

}
