package hutool;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MyDb {
    @Test
    public void testInsert()throws Exception{
        Db.use().insert(new Entity().create("health").set("name","刘灿辉")
                .set("weight","").set("insertDate",new Date()));
    }
}
