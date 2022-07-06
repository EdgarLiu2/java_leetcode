package edgar.try_new.nio.netty;

/**
 * Data Model: response data
 *
 * Created by liuzhao on 2022/7/4
 */
public class ResponseData {

    private int intValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "intValue=" + intValue +
                '}';
    }
}
