package edgar.try_new.jdk12;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edgar.Liu on 2022/12/4
 */
public class NewStringAPI {

    static void newTransform() {
        var result = "foo".transform(s -> s + " bar").transform(String::toUpperCase);
        System.out.println(result);

        var langs = List.of("python", "java", "c++", "c", "groovy");
        List<String> r = new ArrayList<>();
        langs.forEach(lang -> r.add(
                lang.transform(String::strip).transform(String::toUpperCase).transform(s -> "Hi " + s)
        ));
        System.out.println(r);
    }

    static void newIndent() {
        var langs = List.of("python", "java", "c++", "c", "groovy");
        langs.forEach(s -> System.out.println(s.indent(4)));
    }

    public static void main(String[] args) {
        newTransform();
        newIndent();
    }
}
