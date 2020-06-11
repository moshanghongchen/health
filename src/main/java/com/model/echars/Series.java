package com.model.echars;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Data
public class Series implements Serializable {
    List<Map<String,Object>> serie= new ArrayList<>();
//    ArrayList{new Serie("销量","line",new Integer[]{5, 20, 36, 10, 10, 20})}

    public Series(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","销量");
        map.put("type","bar");
        map.put("data",new Integer[]{5, 20, 36, 10, 10, 20});
        serie.add(map);
    }




}
