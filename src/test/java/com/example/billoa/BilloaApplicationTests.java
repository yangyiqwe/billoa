package com.example.billoa;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.example.billoa.common.utils.DemoDataListener;
import com.example.billoa.common.utils.DemoDataListenerOut;
import com.example.billoa.common.utils.Jwtutil;
import com.example.billoa.common.utils.TestFileUtil;
import com.example.billoa.sys.entity.*;
import com.example.billoa.sys.mapper.*;
import com.example.billoa.sys.service.IMoneyflowService;
import com.example.billoa.sys.service.impl.InbillServiceImpl;
import com.example.billoa.sys.service.impl.OutbillServiceImpl;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class BilloaApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IMoneyflowService moneyflowService;
    @Autowired
    private YearbillMapper yearbillMapper;
    @Autowired
    private OutbillMapper outbillMapper;
    @Autowired
    private ConnectionMapper connectionMapper;
    @Autowired
    private InbillMapper inbillMapper;

    @Autowired
    private Jwtutil jwtutil;

    private static final int BATCH_COUNT= 5;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }

    @Test
    void testCreateJwt(){
        User user =new User();
        user.setUsername("zhanshan");
        String token = jwtutil.createToken(user);
        System.out.println(token);
    }

    @Test
    void  testParseJwt(){
       String token= "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxYjlmNTM0YS0zNTZlLTQyODUtOWRkMC0xOGVlMjkwMmMzNTQiLCJzdWIiOiJ7XCJ1c2VybmFtZVwiOlwiemhhbnNoYW5cIn0iLCJpc3MiOiJzeXN0ZW0iLCJpYXQiOjE2Nzg3ODE0MTgsImV4cCI6MTY3ODc4MzIxOH0.ON72vlvv767qk3cpupF5Bpz2GNLLIDQzC1l8J3afkv0";
        Claims claims = jwtutil.parseToken(token);
        System.out.println(claims);
    }

    @Test
    void testjiami(){
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
        //$2a$10$c5uR2ZtyQH/AbFUTMJ1s6.s3FNe3RodhAx0aUX1o/wGjXAAQC4Zae
        boolean matches = passwordEncoder.matches("$2a$10$ia1jtIlSPKk6muycliuum.4YMN374df0oP2TjALOG1D49NxTehZay", "1234");
        System.out.println(matches);
    }

    @Test
    void excelExport(){
        List<Moneyflow> moneyflowList = moneyflowService.list();
        String sheetName="测试";
        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";

        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("moneyflowId");
        excludeColumnFiledNames.add("deleted");

        EasyExcel.write(fileName, Moneyflow.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板")
                .doWrite(moneyflowList);

        //EasyExcel.write(fileName, Moneyflow.class).sheet("模板").doWrite(moneyflowList);
    }

    @Autowired
    private OutbillServiceImpl outbillService;
    @Autowired
    private InbillServiceImpl inbillService;

    @Test
    void excelImport(){
        String url="C:\\Users\\86155\\Desktop\\数据库入库.xlsx";
        File file=new File(url);

        //写法1
        /*EasyExcel.read(file, Outbill.class, new AnalysisEventListener<Outbill>() {
            @Override
            public void invoke(Outbill outbill, AnalysisContext analysisContext) {
                outbillMapper.insert(outbill);
                System.out.println("解析数据为:" + outbill.toString());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("解析完成...");
            }
        }).sheet().doRead();*/

        //写法2
        // 一个文件一个reader
        try (ExcelReader excelReader = EasyExcel.read(file, Inbill.class, new DemoDataListener(inbillService)).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }
    }

    @Test
    void excelImportOut(){

        String url="C:\\Users\\86155\\Desktop\\数据库出库 - 副本.xlsx";
        File file=new File(url);

        try (ExcelReader excelReader = EasyExcel.read(file, Outbill.class, new DemoDataListenerOut(outbillService)).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
            excelReader.finish();
        }
    }

    @Test
    void excelImportConnection(){

        String url="C:\\Users\\86155\\Desktop\\pythonexcel\\website.xlsx";
        File file=new File(url);

        //写法1
        EasyExcel.read(file, Connection.class, new AnalysisEventListener<Connection>() {
            @Override
            public void invoke(Connection connection, AnalysisContext analysisContext) {
                connectionMapper.insert(connection);
                System.out.println("解析数据为:" + connection.toString());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("解析完成...");
            }
        }).sheet().doRead();
    }

    @Test
    void test2(){
        List<Map<String, Object>> checkOut = inbillMapper.getCheckOut();
        List<List<Object>> dataList=new ArrayList<>();
        for (Map<String, Object> map : checkOut) {
            List<Object> data=new ArrayList<>();
            String date=new SimpleDateFormat("MM月dd日").format(map.get("inbill_date"));
            data.add(date);
            data.add(map.get("inbill_connection"));
            data.add(map.get("inbill_inrent"));
            data.add(map.get("year"));
            data.add(map.get("inbill_cotent"));
            dataList.add(data);
        }
        // 写法1
        String fileName = TestFileUtil.getPath() + "已结账" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(dataList);
    }

    private List<List<String>> head() {
        List<List<String>> list = ListUtils.newArrayList();
        List<String> head0 = ListUtils.newArrayList();
        head0.add("日期");
        List<String> head1 = ListUtils.newArrayList();
        head1.add("名字");
        List<String> head2 = ListUtils.newArrayList();
        head2.add("租金");
        List<String> head3 = ListUtils.newArrayList();
        head3.add("年份");
        List<String> head4 = ListUtils.newArrayList();
        head4.add("备注");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        return list;
    }

}
