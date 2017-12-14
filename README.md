# NestedRefreshLoadMore
下拉刷新，上拉加载，完美支持嵌套滑动，以及与AppBarLayout的嵌套使用

### 主要介绍我自己写的一个下拉刷新上拉加载控件NestedRefreshLoadMoreLayout，以及如何使用：

### NestedRefreshLoadMoreLayout的优势：
>### 1.灵活定制刷新，加载的样式，以及动画
>### 2.灵活切换刷新，加载的模式：跟随还是重叠
>### 3.灵活设置拉动的最大距离，触发偏移量等等
>### 4.完美兼容嵌套滑动，比如NestedScroll，AppBarLayout等控件

<br/>

### 效果：

<p>
<img src="screenshot/image1.gif" width="40%" />
<img src="screenshot/image2.gif" width="40%" />
</p>

<br/>


<p>
<img src="screenshot/image3.gif" width="40%" />
<img src="screenshot/image4.gif" width="40%" />
</p>

<br/>

### 使用：

### (1).项目引入

	compile 'github.skcodestack:nestedrefreshloadmore:1.1.0'

### (2).编写xml

	<github.skcodestack.nestedrefresh.NestedRefreshLoadMoreLayout
        android:id="@+id/lay_refresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:header="@layout/layout_default_header"
        app:footer="@layout/layout_default_footer"
        app:mode="follow"
        >
        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingBottom="5dp"
            android:scrollbars="vertical"
            android:background="#cccccc"/>

    </github.skcodestack.nestedrefresh.NestedRefreshLoadMoreLayout>


### app:header="@layout/layout_default_header"

### 头布局，layout_default_header是提供的默认头

### app:footer="@layout/layout_default_footer"

### 尾布局，layout_default_footer是提供的默认尾

### app:mode="follow"

### 模式，follow 跟随，overlap 重叠

### (3).编写代码

		refreshView.setOnRefreshListener(this);
        refreshView.setOnLoadMoreListener(this);




### 其他设置：

### 1.开关刷新和加载：
	
		refreshView.setRefreshEnabled(true);
        refreshView.setLoadMoreEnabled(true);

### 2.设置模式

>### (1).代码设置：
>

	refreshView.setMode(NestedRefreshLoadMoreLayout.Mode.FOLLOW);
  
>
>### (2).xml设置：
>

	app:mode="follow"

>


### 3.头布局和尾布局设置：

>### (1).代码设置：

>
		    refreshView.setRefreshHeaderView(view);
        refreshView.setLoadMoreFooterView(view);
>

>### (2).xml设置：
>

		app:header="@layout/layout_default_header"
        app:footer="@layout/layout_default_footer"

>

### 4.开关刷新与加载：

>### true:开启  flase:关闭

>

	refreshView.setRefreshing(true);
	refreshView.setLoadingMore(true);

>

### 5.设置刷新和加载监听

>

	 	   refreshView.setOnRefreshListener(this);
        refreshView.setOnLoadMoreListener(this);

>


### 6.定制刷新布局

>### 头布局需要继承BaseHeader,比如：DefaultOnRefreshHeaderView


>### 可以重写的方法：

>
 
		//开始滑动调用
		@Override
	    public void onPrepare() 
	    }
		//滑动过程中调用
		//dy ---滑动偏移量
		//offset---触发刷新的偏移量
	    @Override
	    public void onDrag(int dy,int offset) {
	    }
		//手释放时调用
	    @Override
	    public void onRelease() {
	    }
		//滑动结束调用
	    @Override
	    public void onComplete() 
	    }
		//重置时调用
	    @Override
	    public void onReset() {
	    }
		//返回最大滑动距离
	    @Override
	    public int getDragMaxOffset(View rootView) {
	        return 0;
	    }
		//返回触发刷新位移
	    @Override
	    public int getDragTriggerOffset(View rootView) {
	        return 0;
	    }
		//刷新时调用
	    @Override
	    public void onRefresh() {
	    }
>
>
>

### 6.定制加载布局

>### 头布局需要继承BaseFooter,比如：DefaultOnLoadMoreFooterView


>### 可以重写的方法：	

>
		//开始滑动调用
		@Override
	    public void onPrepare() {
	    }
		//滑动过程中调用
		//dy ---滑动偏移量
		//offset---触发加载的偏移量
	    @Override
	    public void onDrag(int dy,int offset) {
	    }
		//手释放时调用
	    @Override
	    public void onRelease() {
	    }
		//滑动结束调用
	    @Override
	    public void onComplete() {
	    }
		//重置时调用
	    @Override
	    public void onReset() {
	    }
		//返回最大滑动距离
	    @Override
	    public int getDragMaxOffset(View rootView) {
	        return 0;
	    }
		//返回触发加载位移
	    @Override
	    public int getDragTriggerOffset(View rootView) {
	        return 0;
	    }
		//加载时调用
	    @Override
	    public void onLoadMore() {
	    }
>


<br/>
