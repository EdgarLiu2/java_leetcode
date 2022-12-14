package edgar.try_new.jdk14;

/**
 * Created by Edgar.Liu on 2022/12/13
 */
public class NewTextBlocks {

    public final static String query1 = """
               SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB`
               WHERE `CITY` = 'INDIANAPOLIS'
               ORDER BY `EMP_ID`, `LAST_NAME`;
               """;

    // 去掉行尾空格
    public final static String query2 = """
               SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB` \
               WHERE `CITY` = 'INDIANAPOLIS' \
               ORDER BY `EMP_ID`, `LAST_NAME`; \
               """;

    public static void main(String[] args) {
        System.out.println(query1);
        System.out.println(query2);
    }
}
