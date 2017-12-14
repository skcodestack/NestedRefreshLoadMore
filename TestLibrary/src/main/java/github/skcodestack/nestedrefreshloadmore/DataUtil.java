package github.skcodestack.nestedrefreshloadmore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class DataUtil {

   static String[] urls = new String[]{
        "http://www.7lili.com/uploads/allimg/140915/0114512925-0.jpg",

        "http://e.hiphotos.baidu.com/image/pic/item/279759ee3d6d55fb165ccc266f224f4a20a4dd1c.jpg",
        "http://img5.178.com/mm/201205/130357597977/130357656690.jpg"
    };

    public static List<String > obtainRandomData(int count){
        List<String > lis = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            lis.add("My Item is "+i);

        }
        return lis;
    }

    public static String getImageUrl(){
        Random random = new Random();
        int i = random.nextInt(3);
        return urls[i%3];
    }

}
