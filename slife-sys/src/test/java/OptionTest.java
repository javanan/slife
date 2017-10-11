import org.junit.Test;

import java.util.Optional;

/**
 * Created by chen on 2017/9/18.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class OptionTest {

    @Test
    public void test() {
        Integer i = new Integer(1);
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
      System.out.print(Optional.ofNullable(value2).orElseGet(()->12));
    }
}
