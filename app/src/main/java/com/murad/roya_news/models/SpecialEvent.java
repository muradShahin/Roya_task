
package com.murad.roya_news.models;

import com.google.gson.annotations.SerializedName;

public class SpecialEvent {

    @SerializedName("created_age")
    private String mCreatedAge;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("event_id")
    private Long mEventId;
    @SerializedName("event_title")
    private String mEventTitle;
    @SerializedName("event_topbar_text")
    private String mEventTopbarText;
    @SerializedName("event_topbar_text1")
    private String mEventTopbarText1;

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

    public Long getEventId() {
        return mEventId;
    }

    public void setEventId(Long eventId) {
        mEventId = eventId;
    }

    public String getEventTitle() {
        return mEventTitle;
    }

    public void setEventTitle(String eventTitle) {
        mEventTitle = eventTitle;
    }

    public String getEventTopbarText() {
        return mEventTopbarText;
    }

    public void setEventTopbarText(String eventTopbarText) {
        mEventTopbarText = eventTopbarText;
    }

    public String getEventTopbarText1() {
        return mEventTopbarText1;
    }

    public void setEventTopbarText1(String eventTopbarText1) {
        mEventTopbarText1 = eventTopbarText1;
    }

}
