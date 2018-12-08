package dao.modelsDAO;

import dao.DAO;
import model.ModelFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelFormatDAO {
    private final String MODEL_FORMAT = "model_format";
    private final String FORMAT_ID = "format_id";
    private final String FORMAT_NAME = "format_name";
    private DAO dao;
    ModelFormatDAO(DAO dao){this.dao = dao;}


    public void addModelFormat(ModelFormat modelFormat) {
        String query = "INSERT into " + MODEL_FORMAT + " values(?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,modelFormat.getIdFormat());
            preparedStatement.setString(2,modelFormat.getFormatName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            dao.sendMessage("Query updating error");
        }
    }


    public List<ModelFormat> getModelFormats() {
        List<ModelFormat> modelFormats = new ArrayList<>();
        String query = "SELECT * FROM " + MODEL_FORMAT + " ORDER BY " + FORMAT_ID;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long formatId = resultSet.getLong(FORMAT_ID);
                String formatName = resultSet.getString(FORMAT_NAME);
                modelFormats.add(new ModelFormat(formatId,formatName));
            }
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return modelFormats;
    }


    public ModelFormat getModelFormat(long modelFormat_id) {
        ModelFormat modelFormat = null;
        String query = "SELECT * FROM " + MODEL_FORMAT + " ORDER BY " + FORMAT_ID + " WHERE " + MODEL_FORMAT + " = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, modelFormat_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long formatId = resultSet.getLong(FORMAT_ID);
                String formatName = resultSet.getString(FORMAT_NAME);
                modelFormat = new ModelFormat(formatId,formatName);
            }
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return modelFormat;
    }
}
