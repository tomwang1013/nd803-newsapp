package red.jinge.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Seeyon on 2017-12-11.
 */

public class NewsAsyncTaskLoader extends AsyncTaskLoader<List<News>> {
    String mRequestUrl;

    public NewsAsyncTaskLoader(Context context, String requestUrl) {
        super(context);
        mRequestUrl = requestUrl;
    }

    @Override
    public List<News> loadInBackground() {
        return QueryUtil.fetchNewsList(mRequestUrl);
    }
}
