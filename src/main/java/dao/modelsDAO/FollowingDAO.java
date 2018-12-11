package dao.modelsDAO;

import dao.DAO;
import model.Following;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowingDAO{
    private final String FOLLOWING = "following";
    private final String USER_ID = "user_id";
    private final String FOLLOWING_USER_ID = "following_user_id";
    private DAO dao;
    public FollowingDAO(DAO dao) {this.dao = dao;}

    public void addFollowing(Following following) {
        String query = "INSERT into " + FOLLOWING + " values(?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,following.getIdol().getId());
            preparedStatement.setLong(2,following.getFollowing().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            dao.sendMessage("Query updating error");
        }
    }


    public List<Following> getFollowings() {
        List<Following> followings = new ArrayList<>();
        String query = "SELECT * FROM " + FOLLOWING + " ORDER BY " + USER_ID;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong(USER_ID);
                UserDAO idolDAO = new UserDAO(dao);
                User idol = idolDAO.getUser(userId);
                long following_user_id = resultSet.getLong(FOLLOWING_USER_ID);
                UserDAO followerDAO = new UserDAO(dao);
                User follower  = followerDAO.getUser(userId);
                Following following = new Following(follower, idol);
                followings.add(following);
            }
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return followings;
    }


    public boolean isFollowing(User idol, User follower) {
        boolean bool = false;
        String query = "SELECT COUNT(*) FROM " + FOLLOWING + " WHERE " + USER_ID + " = ? AND " + FOLLOWING_USER_ID + " = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,idol.getId());
            preparedStatement.setLong(2,follower.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            bool = resultSet.getInt("COUNT(*)")!=0;
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return bool;
    }
}
