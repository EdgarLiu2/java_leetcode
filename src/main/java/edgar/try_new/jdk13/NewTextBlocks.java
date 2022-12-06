package edgar.try_new.jdk13;

/**
 * Created by Edgar.Liu on 2022/12/5
 */
public class NewTextBlocks {
    public final static String html = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;

    public final static String query = """
               SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB`
               WHERE `CITY` = 'INDIANAPOLIS'
               ORDER BY `EMP_ID`, `LAST_NAME`;
               """;

    public final static  String story = """
    "When I use a word," Humpty Dumpty said,
    in rather a scornful tone, "it means just what I
    choose it to mean - neither more nor less."
    "The question is," said Alice, "whether you
    can make words mean so many different things."
    "The question is," said Humpty Dumpty,
    "which is to be master - that's all."
    """;

    public static void main(String[] args) {
        System.out.println(html);
        System.out.println(query);
        System.out.println(story);

        String source = """
                public void print(%s object) {
                    System.out.println(Objects.toString(object));
                }
                """.formatted("Object");
        System.out.println(source);
    }
}
