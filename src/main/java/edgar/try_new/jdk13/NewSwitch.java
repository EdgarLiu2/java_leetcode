package edgar.try_new.jdk13;

import static java.util.Calendar.*;

/**
 * Created by Edgar.Liu on 2022/12/4
 */
public class NewSwitch {

    static void test2() {

        int day = FRIDAY;

        int numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY                -> 7;
            case THURSDAY, SATURDAY     -> 8;
            case WEDNESDAY              -> 9;
            default -> {
                yield 0;
            }
        };

        System.out.println(numLetters);
    }

    public static void main(String[] args) {
        test2();
    }
}
