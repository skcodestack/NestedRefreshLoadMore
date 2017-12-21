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

public class AliNestedRecyclerViewFragment extends Fragment implements onLoadMoreListener, onRefreshListener {

    private View rootView;
    private RecyclerView recyclerView;

    private Handler mHandler = new Handler();
    private List<String> list;
    private CustomAdapter adapter;
    private NestedRefreshLoadMoreLayout refreshView;

    public static AliNestedRecyclerViewFragment newInstance() {
        AliNestedRecyclerViewFragment f = new AliNestedRecyclerViewFragment();
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_nested_recyclerview, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {
        list = DataUtil.obtainRandomData(100);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        refreshView = (NestedRefreshLoadMoreLayout) rootView.findViewById(R.id.lay_refresh);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.VERTICAL, false));

        adapter = new CustomAdapter(recyclerView.getContext(), list);
        recyclerView.setAdapter(adapter);

        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View headerView = null;
        View footerView = null;



        refreshView.setMode(NestedRefreshLoadMoreLayout.Mode.FOLLOW);
        headerView = layoutInflater.inflate(R.layout.layout_ali_header, null);
        footerView = layoutInflater.inflate(R.layout.layout_default_footer, null);

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


    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

        Context mContext;
        List<String> list;
        LayoutInflater layoutInflater;

        public CustomAdapter(Context context , List<String> list){
            this.mContext=context;
            this.list = list;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.layout_item, parent, false);
            return new CustomAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
            TextView item
                    = (TextView) holder.itemView.findViewById(R.id.tv_item);
            item.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
