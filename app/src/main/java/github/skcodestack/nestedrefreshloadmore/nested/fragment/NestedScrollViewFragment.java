package github.skcodestack.nestedrefreshloadmore.nested.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import github.skcodestack.nestedrefresh.NestedRefreshLoadMoreLayout;
import github.skcodestack.nestedrefresh.interf.onLoadMoreListener;
import github.skcodestack.nestedrefresh.interf.onRefreshListener;
import github.skcodestack.nestedrefreshloadmore.DataUtil;
import github.skcodestack.nestedrefreshloadmore.R;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class NestedScrollViewFragment extends Fragment implements onLoadMoreListener, onRefreshListener {

    private View rootView;

    private Handler mHandler = new Handler();
    private NestedRefreshLoadMoreLayout refreshView;
    private LinearLayout content;
    private LayoutInflater layoutInflater;

    public boolean isFollow = true;
    public static NestedScrollViewFragment newInstance(boolean isFollow) {
        NestedScrollViewFragment f = new NestedScrollViewFragment();
        f.isFollow = isFollow;
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_nested_scrollview, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {

        refreshView = (NestedRefreshLoadMoreLayout) rootView.findViewById(R.id.lay_refresh);
        content = (LinearLayout) rootView.findViewById(R.id.content);

        layoutInflater = LayoutInflater.from(refreshView.getContext());
        View headerView = null;
        View footerView = null;


        if(isFollow){
            refreshView.setMode(NestedRefreshLoadMoreLayout.Mode.FOLLOW);
            headerView = layoutInflater.inflate(R.layout.layout_default_header, null);
            footerView = layoutInflater.inflate(R.layout.layout_default_footer, null);
        }else {
            refreshView.setMode(NestedRefreshLoadMoreLayout.Mode.OVERLAP);
            headerView = layoutInflater.inflate(R.layout.layout_jd_header, null);
            footerView = layoutInflater.inflate(R.layout.layout_default_footer, null);
        }
        refreshView.setRefreshHeaderView(headerView);
        refreshView.setLoadMoreFooterView(footerView);

        refreshView.setOnRefreshListener(this);
        refreshView.setOnLoadMoreListener(this);

    }

    @Override
    public void onLoadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            content.addView(createView("loadMore"));
                            refreshView.setLoadingMore(false);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onRefresh() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            content.addView(createView("refresh"),0);
                            refreshView.setRefreshing(false);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    private View  createView(String message){
        View view = layoutInflater.inflate(R.layout.layout_item, null);
        TextView item
                = (TextView) view.findViewById(R.id.tv_item);
        item.setText(message);
        return view;
    }


}
