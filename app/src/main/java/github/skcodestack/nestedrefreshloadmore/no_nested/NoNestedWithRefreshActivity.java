package github.skcodestack.nestedrefreshloadmore.no_nested;

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
import github.skcodestack.nestedrefreshloadmore.nested.NestedWithRefreshActivity;
import github.skcodestack.nestedrefreshloadmore.nested.fragment.NestedRecyclerViewFragment;
import github.skcodestack.nestedrefreshloadmore.nested.fragment.NestedScrollViewFragment;
import github.skcodestack.nestedrefreshloadmore.no_nested.fragment.ListViewFragment;
import github.skcodestack.nestedrefreshloadmore.no_nested.fragment.ScrollViewFragment;

public class NoNestedWithRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_nested_with_refresh);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ListViewFragment.newInstance(true),"ListView");
        adapter.addFragment(ListViewFragment.newInstance(false),"ListView2");
        adapter.addFragment(ScrollViewFragment.newInstance(true),"ScrollView");
        adapter.addFragment(ScrollViewFragment.newInstance(false),"ScrollView2");

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
