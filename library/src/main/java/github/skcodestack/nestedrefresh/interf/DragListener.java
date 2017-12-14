package github.skcodestack.nestedrefresh.interf;

import android.view.View;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/11
 * Version  1.0
 * Description: 拖动监听
 */

public interface DragListener {

    void onPrepare();

    /**
     *
     * @param dy    拖动距离
     * @param triggerOffset 触发回掉距离
     */
    void onDrag(int dy, int triggerOffset);

    void onRelease();

    void onComplete();

    void onReset();

    //拖动最大距离
    int getDragMaxOffset(View rootView);
    //拖动触发高度
    int getDragTriggerOffset(View rootView);
}
