package edgar.mybatis.learning1.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Edgar.Liu on 2023/1/26
 */
public class GenericTokenParser {

    public final static String DEFAULT_OPEN_TOKEN = "[#|\\$]\\{";
    public final static String DEFAULT_CLOSE_TOKEN = "}";

    private final String openToken;
    private final String closeToken;
    private final TokenHandler tokenHandler;

    public GenericTokenParser(TokenHandler tokenHandler) {
        this(DEFAULT_OPEN_TOKEN, DEFAULT_CLOSE_TOKEN, tokenHandler);
    }

    public GenericTokenParser(String openToken, String closeToken, TokenHandler tokenHandler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.tokenHandler = tokenHandler;
    }

    public String parse(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }

        // regex = "#\\{(.+?)}";
        String regex =
                this.openToken +
                "(.+?)" +
                this.closeToken;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);

        int idx = 1;
        List<String> fullGroups = new ArrayList<>();
        while (m.find()) {
            String fullGroup = m.group(0);
            String property = m.group(1);

            fullGroups.add(fullGroup);
            tokenHandler.handleToken(idx++, property, fullGroup);
        }

        // 没有匹配正则，返回原始String
        if (fullGroups.size() == 0) {
            return text;
        }

        // 使用 ？ 替换所有的 #{username}
        String parsedText = text;
        for (String fullGroup : fullGroups) {
            parsedText = parsedText.replace(fullGroup, ParameterMappingTokenHandler.PARAM_PLACE_HOLDER);
        }

        return parsedText;
    }
}
