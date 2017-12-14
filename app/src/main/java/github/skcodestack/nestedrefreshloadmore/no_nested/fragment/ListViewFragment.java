package github.skcodestack.nestedrefreshloadmore.no_nested.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
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

public class ListViewFragment extends Fragment implements onLoadMoreListener, onRefreshListener {

    private View rootView;
    private ListView listView;

    private Handler mHandler = new Handler();
    private List<String> list;
    private CustomAdapter adapter;
    private NestedRefreshLoadMoreLayout refreshView;

    public boolean isFollow = true;

    public static ListViewFragment newInstance(boolean isFollow) {
        ListViewFragment f = new ListViewFragment();
        f.isFollow = isFollow;
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_listview_layout, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {
        list = DataUtil.obtainRandomData(100);
        listView = (ListView) rootView.findViewById(R.id.listview);
        refreshView = (NestedRefreshLoadMoreLayout) rootView.findViewById(R.id.lay_refresh);

        adapter = new CustomAdapter(listView.getContext(), list);
        listView.setAdapter(adapter);

        LayoutInflater layoutInflater = LayoutInflater.from(listView.getContext());
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
                            list.add("loadmore");
                            adapter.notifyDataSetChanged();
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
                            list.add(0,"refresh");
                            adapter.notifyDataSetChanged();
                            refreshView.setRefreshing(false);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public class  CustomAdapter extends BaseAdapter {

        private List<String> mList;
        private Context mContent;
        private LayoutInflater layoutInflater;

        public CustomAdapter(Context context , List<String> list){
            this.mList = list;
            this.mContent = context;
            layoutInflater = LayoutInflater.from(mContent);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = layoutInflater.inflate(R.layout.layout_item, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_item);
            textView.setText(mList.get(position));
            return view;
        }
    }
}
