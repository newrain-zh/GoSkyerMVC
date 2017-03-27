import com.controller.TestController;
import org.goskyer.bean.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zzqno on 2017-3-26.
 */
public class Test {

    @org.junit.Test
    public void test() {
        Class cls = TestController.class;
        try {
            Method method = cls.getMethod("get", String.class);
            View view = (View) method.invoke(cls.newInstance(), "jay");
            System.out.println("========");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
