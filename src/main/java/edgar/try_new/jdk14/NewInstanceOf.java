package edgar.try_new.jdk14;

/**
 * Created by Edgar.Liu on 2022/12/9
 */
public class NewInstanceOf {
    static void test1() {
        Object obj = "Hello InstanceOf";
        if (obj instanceof String str) {
            // can use str here
            System.out.println("str = " + str);
        } else {
            // can't use str here
        }
    }

    static boolean test2(Object obj) {
        return obj instanceof String str && str.toLowerCase().startsWith("hello");
    }

    public static void main(String[] args) {
        test1();
        assert test2("Hello World");
    }
}
