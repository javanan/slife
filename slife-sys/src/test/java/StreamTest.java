import org.apache.el.stream.Optional;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/9/14.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class StreamTest {

    List<String> strings = new ArrayList() {{
        add("1");
        add("2");
        add("3");
        add("4");
        add("5");
        add("6");
        add("7");
    }};

    @Test
    public void page() {
        strings.stream().skip(1).limit(2).forEach(System.out::println);
    }
}
