package com.model.servce;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SalaryService {
    private final String TABLE_NAME="my_salary" ;
    private static List<String> zeroList=null;
    Logger logger = LoggerFactory.getLogger(getClass());

    public List<Entity> QueryAll(){
        List<Entity> all = null;
        try {
            all = Db.use().findAll(TABLE_NAME);
        } catch (SQLException e) {
            logger.error("查询出错 e={}",e);
        }
        dealZeor(all);

        return all;
    }


    private List<String> dealZeor(List<Entity> list){
        zeroList=new ArrayList<>(list.get(0).getFieldNames());
        for (Entity entity:list){
            Set<String> fieldNames = entity.getFieldNames();
            for (String fieldName:fieldNames){
                if (entity.getDouble(fieldName)!=0){
                    zeroList.remove(fieldName);
                }
            }

        }
        if (CollectionUtils.isNotEmpty(zeroList)){
            for (Entity entity:list){
                for (String key:zeroList){
                    entity.set(key,"-1");
                }
            }

        }
        return zeroList;

    }


    public String getHead(){
        String sql="select COLUMN_NAME as field ,COLUMN_COMMENT as title from information_schema.COLUMNS" +
                " where table_name = 'my_salary' and table_schema = 'lch' " +
                "order by ordinal_position ";
        List<Entity> query = null;
        try {
            query = Db.use().query(sql);
        } catch (SQLException e) {
            logger.error("查询出错 e={}",e);
        }
        JSONArray jsonArray = (JSONArray) JSON.toJSON(query);
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String title=jsonObject.getString("title");
            String field=jsonObject.getString("field");
            if (zeroList.contains(field)){
                continue;
            }
            buffer.append("<td>");
            buffer.append(title);
            buffer.append("</td>\n");
        }
        return buffer.toString();
    }


}
