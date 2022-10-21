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
    public UserRepository() {
        String url = "jdbc:h2:tcp://localhost/~/test";
        String username = "sa";
        String password = "";
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.print("Connected");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUserList() {
        userList = new ArrayList<>();
        query = "select * from userInfo";
        try {
            if (connection.isClosed()){
                System.out.println("Connection Closed!!");
            }
        else{
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setMessage(resultSet.getString("message"));
                    user.setCreated(resultSet.getString("dateOfCreation"));
                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null && resultSet != null && connection != null) {
                        preparedStatement.close();
                        resultSet.close();
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUser(int id) {
        query = "select * from userInfo where id = ?";
        User user = new User();
        try {
            if (connection.isClosed()) {
                System.out.println("Connection closed!!!");
            } else{
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {

                        user.setId(resultSet.getInt("id"));
                        user.setName(resultSet.getString("name"));
                        user.setMessage(resultSet.getString("message"));
                        user.setCreated(resultSet.getString("dateOfCreation"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (preparedStatement != null && resultSet != null) {
                            preparedStatement.close();
                            resultSet.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void update(User u1) {
        query = "update userInfo set name = ?, message = ?, dateOfCreation = ? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,u1.getName());
            preparedStatement.setString(2,u1.getMessage());
            preparedStatement.setString(3,u1.getCreated());
            preparedStatement.setInt(4,  u1.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void delete(int id) {
        query = "delete from userInfo where id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void tearDown(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
