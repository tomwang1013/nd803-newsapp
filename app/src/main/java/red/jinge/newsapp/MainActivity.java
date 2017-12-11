package red.jinge.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity 
        implements LoaderManager.LoaderCallbacks<List<News>> {
    private static final String NBA_SPURS_URL =
            "http://content.guardianapis.com/search?section=sport&q=spurs&api-key=test";

    private ListView mListViewNews;
    private TextView mTextViewEmpty;
    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewNews = findViewById(R.id.list_view_news);
        mTextViewEmpty = findViewById(R.id.text_view_empty);

        mNewsAdapter = new NewsAdapter(this, new ArrayList<News>());
        mListViewNews.setAdapter(mNewsAdapter);
        mListViewNews.setEmptyView(mTextViewEmpty);

        mListViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = (News) parent.getItemAtPosition(position);
                Uri webpage = Uri.parse(news.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // check internet connectivity
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            mTextViewEmpty.setText(R.string.no_network_connection);
        } else {
            getLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsAsyncTaskLoader(this, NBA_SPURS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        updateUI(data);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        updateUI(null);
    }

    /**
     * 更新UI
     * @param news earthquake list from network
     */
    private void updateUI(List<News> news) {
        mTextViewEmpty.setText(R.string.no_result);
        mNewsAdapter.clear();

        if (news != null && news.size() != 0) {
            mNewsAdapter.addAll(news);
        }
    }
}
