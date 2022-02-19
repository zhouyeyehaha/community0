package com.jc.community.actuator;

import com.jc.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Endpoint(id = "database")
public class DatabaseEndPoint {

    private Logger logger = LoggerFactory.getLogger(DatabaseEndPoint.class);

    @Autowired
    private DataSource dataSource;

    // get请求来访问
    @ReadOperation
    public String checkConnection() {
        try (
                Connection connection = dataSource.getConnection()
        ) {
            return CommunityUtil.getJSONString(0, "获取连接成功");
        } catch (SQLException e) {
            logger.error("获取连接失败" + e.getMessage());
            return CommunityUtil.getJSONString(1, "获取连接失败");
        }
    }


}
