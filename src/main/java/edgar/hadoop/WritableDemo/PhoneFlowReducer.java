package edgar.hadoop.WritableDemo;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by liuzhao on 2022/6/26
 */
public class PhoneFlowReducer extends Reducer<Text, PhoneFlowBean, Text, PhoneFlowBean> {
    private PhoneFlowBean outValue = new PhoneFlowBean();

    @Override
    protected void reduce(Text key, Iterable<PhoneFlowBean> values, Reducer<Text, PhoneFlowBean, Text, PhoneFlowBean>.Context context) throws IOException, InterruptedException {
        long totalUpFlow = 0;
        long totalDownFlow = 0;

        // 遍历集合values
        for (PhoneFlowBean v : values) {
            totalUpFlow += v.getUpFlowBytes();
            totalDownFlow += v.getDownFlowBytes();
        }

        // 封装
        outValue.setUpFlowBytes(totalUpFlow);
        outValue.setDownFlowBytes(totalDownFlow);
        outValue.setTotalFlowBytes();

        // 写出
        context.write(key, outValue);
    }
}
