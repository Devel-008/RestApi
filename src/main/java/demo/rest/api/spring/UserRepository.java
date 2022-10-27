package demo.rest.api.spring;

import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    String query;
    List<User> userList;
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(User u1, Logger logger) {
        logger.info("Logger intialized to create!!");
        query = "insert into userInfo values(?,?,?,?)";
        int i = this.jdbcTemplate.update(query,u1.getId(),u1.getName(),u1.getMessage(),u1.getCreated());
        if(i > 0){
            logger.info("Inserted ID {} Data",u1.getId());
        }else {
            logger.info("Failed to insert ID {} Data",u1.getId());
        }
    }
    public List<User> getUserList(Logger logger) {
        userList = new ArrayList<>();
        query = "select * from userInfo";
        return jdbcTemplate.query(query, rs -> {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setMessage(rs.getString("message"));
                user.setCreated(rs.getString("dateOfCreation"));
                userList.add(user);
            }
            return userList;
        });
    }
    public User getUser(int id,Logger logger) {
        query = "select * from userInfo where id = ?";
        RowMapper<User> rowMapper = new RowMapperImplementation();
        User user = this.jdbcTemplate.queryForObject(query,rowMapper,id);
        return user;
    }
    public User delete(int id, Logger logger)
    {
        query = "delete from userInfo where id = ?";
        int i = this.jdbcTemplate.update(query, id);
        if(i > 0){
            logger.info("Deleted ID {} with data!!!",id);
        }else {
            logger.warn("Not Deleted ID {} with data!!!",id);
        }
        return null;
    }
    public User update(Logger logger,User user){
        query = "update userInfo set name = ?, message = ?, dateOfCreation = ? where id = ?";
        int i = this.jdbcTemplate.update(query, user.getName(), user.getMessage(), user.getCreated(), user.getId());
        System.out.println(user.getId());
        if(i > 0){
            logger.info("Updated ID {} with data!!!", user.getId());
        }else {
            logger.warn("Not Updated ID {} with data!!!",user.getId());
        }
        return user;
    }
}
