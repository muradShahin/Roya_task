package com.murad.roya_news.helpers;

/**
 * this class is used to store a value for the progress bar 0 for visible 8 = invisible
 * fo data binding use
 */
public class ProgressBar {

    private int progressStatus;

    public ProgressBar(int progressStatus){
        this.progressStatus = progressStatus;
    }

    public int getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(int progressStatus) {
        this.progressStatus = progressStatus;
    }
}
