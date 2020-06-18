package com.health;

import cn.hutool.core.io.FileUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.health.health.AAA;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JSONTest {
    @Test
    public void test1(){
        AAA aaa = new AAA();
        aaa.setName("123");

        String s = JSON.toJSONString(aaa);
        System.out.println(s);
    }

    @Test
    public void test123(){
        AAA aaa = new AAA();
        aaa.setName("ss");
        testaaa(aaa);
        System.out.println(aaa);
    }

    public void testaaa(AAA aaa){
        aaa.setName("1233333");
    }


    @Test
    public void testAAA(){
        String str="{\"respCode\":\"0\",\"respMsg\":\"succ\",\"result\":{\"firstLoan\":true}}";
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject);
        JSONObject result = jsonObject.getJSONObject("result");
        System.out.println(result);


    }
    @Test
    public void testErWeiMaREad(){
        String decode = QrCodeUtil.decode(FileUtil.file("/Users/dxm/Desktop/aaa.jpg"));
        System.out.println(decode);
    }
    @Test
    public void testErWeiMaWite(){
//        QrCodeUtil.generate(" (*^__^*)", 300, 300, FileUtil.file("/Users/dxm/Desktop/ht.jpg"));
    }
    @Test
    public void test11()throws Exception{
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("~/Desktop/aaa.xlsx"));
        List<List<Object>> read = reader.read(0);
        for (List<Object> doubles:read){
            insertData(doubles);
        }

    }

    private void insertData(List<Object> doubles)throws Exception{
        Db.use().insert(new Entity().create("my_salary").set("id",doubles.get(0))
                .set("real_salary",doubles.get(1))
                .set("total_salary",doubles.get(2))
                .set("pre_tax_add",doubles.get(3))
                .set("pre_tax_reduce",doubles.get(4))
                .set("kaoqin_reduce",doubles.get(5))
                .set("shebao_reduce",doubles.get(6))
                .set("pre_reduce",doubles.get(7))
                .set("after_tax_add",doubles.get(8))
                .set("after_tax_reduce",doubles.get(9))
        );
    }






    public List<Double> parseDoublle(List<Object> list){
        ArrayList<Double> doubles = new ArrayList<>();
        for (Object o:list){
            doubles.add(Double.parseDouble(o.toString()));
        }
        return doubles;
    }

    @Test
    public void testSQl()throws Exception{
        String sql="select COLUMN_NAME as field ,COLUMN_COMMENT as title from information_schema.COLUMNS" +
                " where table_name = 'my_salary' and table_schema = 'lch' " +
                "order by ordinal_position ";
        List<Entity> query = Db.use().query(sql);
        JSONArray jsonArray = (JSONArray)JSON.toJSON(query);
        StringBuffer stringBuffer = new StringBuffer();
        for (Object jsonObject:jsonArray){
            JSONObject jsonObject2=(JSONObject) jsonObject;
            String field=jsonObject2.getString("field");
            stringBuffer.append("<td th:if=\"${list2.");
            stringBuffer.append(field);
            stringBuffer.append("}!=null\"\t");
            stringBuffer.append("th:text=\"${list2.");
            stringBuffer.append(field);
            stringBuffer.append("}\"");
            //th:if="${list2.id}!=null"

            stringBuffer.append("/>\n");
        }
        System.out.println(stringBuffer);
    }



}
