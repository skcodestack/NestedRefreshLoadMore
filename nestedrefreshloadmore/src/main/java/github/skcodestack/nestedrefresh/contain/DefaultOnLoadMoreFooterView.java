package github.skcodestack.nestedrefresh.contain;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import github.skcodestack.nestedrefresh.R;
import github.skcodestack.nestedrefresh.base.BaseFooter;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/12
 * Version  1.0
 * Description:
 */

public class DefaultOnLoadMoreFooterView extends BaseFooter {

    private TextView tvLoadMore;
    private ImageView ivSuccess;
    private ProgressBar progressBar;


    public DefaultOnLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public DefaultOnLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultOnLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoadMore = (TextView) findViewById(R.id.tvLoadMore);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onPrepare() {
        ivSuccess.setVisibility(GONE);
    }

    @Override
    public void onDrag(int y, int offset) {

        if(y < 0){
            ivSuccess.setVisibility(GONE);
            progressBar.setVisibility(GONE);
            int deY = Math.abs(y);
            if(deY >= offset){
                tvLoadMore.setText("RELEASE TO LOAD MORE");
            }else {
                tvLoadMore.setText("SWIPE TO LOAD MORE");
            }
        }


    }

    @Override
    public void onLoadMore() {
        tvLoadMore.setText("LOADING MORE");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        ivSuccess.setVisibility(GONE);
    }
}
