package red.jinge.newsapp;

/**
 * Created by Seeyon on 2017-12-11.
 */

public class News {
    private String mDate;
    private String mTitle;
    private String mUrl;

    public News(String mDate, String mTitle, String mUrl) {
        this.mDate = mDate;
        this.mTitle = mTitle;
        this.mUrl = mUrl;
    }

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
