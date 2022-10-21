package demo.rest.api.practice;

import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    List<User> userList;
    Connection connection = null;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String query;
    private JdbcTemplate jdbcTemplate;
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(User u1, Logger logger) {
        query = "insert into userInfo values(?,?,?,?)";
        int i = this.jdbcTemplate.update(query,u1.getId(),u1.getName(),u1.getMessage(),u1.getCreated());
        if(i > 0){
            logger.info("Inserted ID {} Data",u1.getId());
        }
    }


}
