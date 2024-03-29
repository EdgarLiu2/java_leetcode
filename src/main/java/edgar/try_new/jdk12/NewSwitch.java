package edgar.try_new.jdk12;

import static java.util.Calendar.*;

/**
 * Created by Edgar.Liu on 2022/12/3
 */
public class NewSwitch {

    static void test1() {
        int day = FRIDAY;

        // 默认 break
        switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
            case TUESDAY                -> System.out.println(7);
            case THURSDAY, SATURDAY     -> System.out.println(8);
            case WEDNESDAY              -> System.out.println(9);
        }
    }

    static void test2() {

        int day = FRIDAY;

        int numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY                -> 7;
            case THURSDAY, SATURDAY     -> 8;
            case WEDNESDAY              -> 9;
            default -> 0;
        };

        System.out.println(numLetters);
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
