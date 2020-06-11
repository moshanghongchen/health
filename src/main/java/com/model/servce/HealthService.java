package com.model.servce;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.model.echars.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class HealthService {
    Logger logger = LoggerFactory.getLogger(getClass());


    private final String TABLE_NAME="health";
    private final String NAME="name";
    private final String WEIGHT="weight";
    private final String INSERT_DATE="insertDate";
    //全部
    private final String ALL="0";
    //一周
    private final String WEEK="1";
    //半个月
    private final String HALF_MONTH="2";
    //一个月
    private final String MONTH="3";
    //这个月
    private final String CURRENT_MONTH="4";

    public int insert(Map<String , Object> req){
        //获取用户
        Object name = req.get("name");
        //获取数据
        Object weight = req.get("weight");
        //数据校验
        if (StringUtils.isEmpty(name.toString())||StringUtils.isEmpty(weight.toString())){
            return 0;
        }
        //获取时间
        Date now=new Date();

        Entity entity = Entity.create(TABLE_NAME).set(NAME, name).set(WEIGHT, weight).set(INSERT_DATE, now);
        int insert=0;
        try {
            insert=Db.use().insert(entity);
        }catch (SQLException e){
            logger.error("save data error e={}",e);
        }
        return insert;
    }

    public List<Entity> show(Map<String,String > req){
        List<Entity> res=null;
        //获取时间范围
        String queryType = req.get("queryType");
        Date startTime = getStartTime(queryType);
        try {
            if (startTime==null){
                res=Db.use().query("select * from ? where name= ? orderBy insertDate",TABLE_NAME);
            }else {
                res=Db.use().query("select * from ? where insertDate between ? and ?",TABLE_NAME,startTime,new Date());
            }
        }catch (SQLException e){
            logger.error("查询出错 e={}",e);
        }

        return res;
    }
    private Date getStartTime(String queryType){
        if (queryType==null){
            return null;
        }
        switch (queryType){
            case WEEK :
                return DateUtil.lastWeek();
            case HALF_MONTH :
                return DateUtil.offsetDay(new Date(),-15);
            case MONTH :
                return DateUtil.lastMonth();
            default:
                return null;
        }
    }



//    public Options getOption(List<Entity> list){
//
//    }







}
