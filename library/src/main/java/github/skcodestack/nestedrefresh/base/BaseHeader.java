package github.skcodestack.nestedrefresh.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import github.skcodestack.nestedrefresh.interf.DragListener;
import github.skcodestack.nestedrefresh.interf.onRefreshListener;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/11
 * Version  1.0
 * Description:
 */

public class BaseHeader  extends FrameLayout implements onRefreshListener, DragListener {
    public BaseHeader(Context context) {
        super(context);
    }

    public BaseHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onPrepare() {

    }

    @Override
    public void onDrag(int dy,int offset) {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {

    }

    @Override
    public int getDragMaxOffset(View rootView) {
        return 0;
    }

    @Override
    public int getDragTriggerOffset(View rootView) {
        return 0;
    }

    @Override
    public void onRefresh() {

    }
}
