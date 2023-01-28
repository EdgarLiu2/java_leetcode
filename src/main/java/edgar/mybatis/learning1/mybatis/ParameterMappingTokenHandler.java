package edgar.mybatis.learning1.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edgar.Liu on 2023/1/25
 */
public class ParameterMappingTokenHandler implements TokenHandler {
    public final static String PARAM_PLACE_HOLDER = "?";
    private final List<ParameterMapping> parameterMapping = new ArrayList<>();

    public List<ParameterMapping> getParameterMappings() {
        return this.parameterMapping;
    }

    @Override
    public String handleToken(int index, String property, String fullGroup) {
        parameterMapping.add(new ParameterMapping(index, property, fullGroup));
        return PARAM_PLACE_HOLDER;
    }
}
