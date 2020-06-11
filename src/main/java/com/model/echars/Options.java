package com.model.echars;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Options implements Serializable {
    /**
     * 标题
     * */
    private Title title=new Title();
    /**
     * 工具
     * */
    private Tooltip tooltip=new Tooltip();
    /**
     * 指数（或者是曲线）
     * */
    private Legend legend=new Legend();
    /**
     * X轴
     * */
    @JSONField(name="xAxis")
    private XAxis xAxis=new XAxis();
    /**
     * Y轴
     * */
    @JsonAlias("yAxis")
    private YAxis yAxis=new YAxis();
    private List<Map<String,Object>> series=new ArrayList<>();



    public Options(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","销量");
        map.put("type","bar");
        map.put("data",new Integer[]{5, 20, 36, 10, 10, 20});
        series.add(map);
        series.add(map);
    }







}
