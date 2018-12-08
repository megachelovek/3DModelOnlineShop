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

public class ModelDAO {
    private final String MODEL = "model";
    private final String AUTHOR = "author_id";
    private final String MODEL_ID = "model_id";
    private final String MODEL_NAME = "model_name";
    private final String POLYGONS_COUNT = "poly_count";
    private final String POLYGONS_VERTEX = "poly_vertex";
    private final String RENDER = "render_id";
    private final String FORMAT = "format_id";
    private DAO dao;
    ModelDAO(DAO dao){this.dao = dao;}


    public void addModel(Model model) {
        String query = "INSERT into " + MODEL + " values(?,?,?,?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,model.getAuthor().getId());
            preparedStatement.setLong(2,model.getIdModel());
            preparedStatement.setString(3,model.getName());
            preparedStatement.setInt(4,model.getPoly());
            preparedStatement.setInt(5,model.getVertex());
            preparedStatement.setLong(6,model.getRender().getIdRender());
            preparedStatement.setLong(7,model.getFormat().getIdFormat());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            dao.sendMessage("Query updating error");
        }
    }


    public List<Model> getModels() {
        List<Model> models = new ArrayList<>();
        String query = "SELECT * FROM " + MODEL + " ORDER BY " + MODEL_ID;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long modelId = resultSet.getLong(MODEL_ID);
                long authorId = resultSet.getLong(AUTHOR);
                String name = resultSet.getString(MODEL_NAME);
                int poly = resultSet.getInt(POLYGONS_COUNT);
                int vertex = resultSet.getInt(POLYGONS_VERTEX);
                long renderId = resultSet.getInt(RENDER);
                long formatId = resultSet.getInt(FORMAT);
                UserDAO authorDAO = new UserDAO(dao);
                User author = authorDAO.getUser(authorId);
                ModelFormatDAO formatDAO = new ModelFormatDAO(dao);
                ModelFormat format = formatDAO.getModelFormat(formatId);
                RenderDAO renderDAO = new RenderDAO(dao);
                Render render = renderDAO.getRender(renderId);
                models.add(new Model(author,modelId,name,poly,vertex,format,render));
            }
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return models;
    }


    public Model getModel(long model_id) {
        Model model = null;
        String query = "SELECT * FROM " + MODEL + " WHERE " + MODEL_ID + " = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,model_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            long modelId = resultSet.getLong(MODEL_ID);
            long authorId = resultSet.getLong(AUTHOR);
            String name = resultSet.getString(MODEL_NAME);
            int poly = resultSet.getInt(POLYGONS_COUNT);
            int vertex = resultSet.getInt(POLYGONS_VERTEX);
            long renderId = resultSet.getInt(RENDER);
            long formatId = resultSet.getInt(FORMAT);
            UserDAO authorDAO = new UserDAO(dao);
            User author = authorDAO.getUser(authorId);
            ModelFormatDAO formatDAO = new ModelFormatDAO(dao);
            ModelFormat format = formatDAO.getModelFormat(formatId);
            RenderDAO renderDAO = new RenderDAO(dao);
            Render render = renderDAO.getRender(renderId);
            model = new Model(author, modelId, name, poly, vertex, format, render);

        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return model;
    }
}
