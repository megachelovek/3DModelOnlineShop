package dao.modelsDAO;

import dao.DAO;
import model.Model;
import model.ModelFormat;
import model.Render;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RenderDAO {
    private final String RENDER = "render";
    private final String RENDER_ID = "render_id";
    private final String SIZE = "size";
    private final String SOFTWARE = "render_software";

    private DAO dao;

    RenderDAO(DAO dao) {
        this.dao = dao;
    }


    public void addRender(Render render) {
        String query = "INSERT into " + RENDER + " values(?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, render.getIdRender());
            preparedStatement.setInt(2, render.getSize());
            preparedStatement.setString(3, render.getPoRender());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            dao.sendMessage("Query updating error");
        }
    }

    public List<Render> getRenders() {
        List<Render> renders = new ArrayList<>();
        String query = "SELECT * FROM " + RENDER + " ORDER BY " + RENDER_ID;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long renderId = resultSet.getLong(RENDER_ID);
                int size = resultSet.getInt(SIZE);
                String soft = resultSet.getString(SOFTWARE);
                renders.add(new Render(renderId, size, soft));
            }
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return renders;
    }


    public Render getRender(long id) {
        Render render = null;
        String query = "SELECT * FROM " + RENDER + " WHERE " + RENDER_ID + " = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            long renderId = resultSet.getLong(RENDER_ID);
            int size = resultSet.getInt(SIZE);
            String soft = resultSet.getString(SOFTWARE);
            render = new Render(renderId, size, soft);

        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return render;
    }
}

