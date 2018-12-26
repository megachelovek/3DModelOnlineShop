package dao.modelsDAO;

import dao.DAO;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final String USER = "threed_user";
    private final String USER_ID = "user_id";
    private final String USER_NAME = "user_name";
    private final String FOLLOWERS_COUNT = "followers_count";
    private final String MODELS_COUNT = "models_count";
    private final String RATING = "rating";
    private final String ACCOUNT = "account";
    private DAO dao;
    public UserDAO(DAO dao) {this.dao = dao;}

    public void addUser(User user) {
        String query = "INSERT into " + USER + " values(?,?,?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,user.getId());
            preparedStatement.setString(2,user.getUserName());
            preparedStatement.setInt(3,user.getFollowersCount());
            preparedStatement.setInt(4,user.getModelCount());
            preparedStatement.setInt(5,user.getRating());
            preparedStatement.setLong(6,user.getAccount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            dao.sendMessage("Query updating error");
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM " + USER + " ORDER BY " + USER_ID;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong(USER_ID);
                String name = resultSet.getString(USER_NAME);
                int followers = resultSet.getInt(FOLLOWERS_COUNT);
                int models = resultSet.getInt(MODELS_COUNT);
                int rating = resultSet.getInt(RATING);
                long account = resultSet.getInt(ACCOUNT);
                User user = new User(userId, name, followers, models, rating, account);
                users.add(user);
            }
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return users;
    }

    public User getUser(long id) {
        User user = null;
        String query = "SELECT * FROM " + USER +" WHERE " + USER_ID + " = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(USER_NAME);
                int followers = resultSet.getInt(FOLLOWERS_COUNT);
                int models = resultSet.getInt(MODELS_COUNT);
                int rating = resultSet.getInt(RATING);
                long account = resultSet.getInt(ACCOUNT);
                user = new User(id, name, followers, models, rating, account);
            }
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return user;
    }
}
