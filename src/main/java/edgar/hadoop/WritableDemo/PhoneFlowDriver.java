package edgar.hadoop.WritableDemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * mvn package -DskipTests
 * hadoop jar ~/workspace/GitHub/java_leetcode/target/leetcode-1.0-SNAPSHOT.jar edgar.hadoop.WritableDemo.PhoneFlowDriver /Users/liuzhao/Desktop/Bytedance/workspace/hadoop/hadoop_test/phone_data/input /Users/liuzhao/Desktop/Bytedance/workspace/hadoop/hadoop_test/phone_data/output
 *
 * Created by liuzhao on 2022/6/26
 */
public class PhoneFlowDriver {
    public static void main(String[] args) throws Exception {
        // 1. 获取配置信息，获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "PhoneFlow");

        // 2. 指定本程序的jar包所再的本地路径
        job.setJarByClass(PhoneFlowDriver.class);

        // 3. 关联Mapper/Reducer业务类
        job.setMapperClass(PhoneFlowMapper.class);
        job.setCombinerClass(PhoneFlowReducer.class);
        job.setReducerClass(PhoneFlowReducer.class);

        // 4. 指定Mapper输出的<K, V>类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PhoneFlowBean.class);

        // 5. 指定最终输出的<K, V>类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PhoneFlowBean.class);

        // 6. 指定Job的输入文件目录
        FileInputFormat.addInputPath(job, new Path(args[0]));

        // 7. 指定Job的输出结果目录
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 8. 提交Job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
