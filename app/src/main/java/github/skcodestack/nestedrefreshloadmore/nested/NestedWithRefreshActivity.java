package github.skcodestack.nestedrefreshloadmore.nested;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.LinkedList;
import java.util.List;

import github.skcodestack.nestedrefreshloadmore.R;
import github.skcodestack.nestedrefreshloadmore.nested.fragment.AliNestedRecyclerViewFragment;
import github.skcodestack.nestedrefreshloadmore.nested.fragment.NestedRecyclerViewFragment;
import github.skcodestack.nestedrefreshloadmore.nested.fragment.NestedScrollViewFragment;

public class NestedWithRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_with_refresh);

        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(NestedRecyclerViewFragment.newInstance(true),"RecyclerView");
        adapter.addFragment(NestedRecyclerViewFragment.newInstance(false),"RecyclerView2");
        adapter.addFragment(NestedScrollViewFragment.newInstance(true),"NestedScrollView");
        adapter.addFragment(NestedScrollViewFragment.newInstance(false),"NestedScrollView2");
        adapter.addFragment(AliNestedRecyclerViewFragment.newInstance(),"ALI");

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments = new LinkedList<>();
        private List<String> mTitles = new LinkedList<>();
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
