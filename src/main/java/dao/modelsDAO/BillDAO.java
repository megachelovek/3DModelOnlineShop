package dao.modelsDAO;

import dao.DAO;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    private DAO dao;
    BillDAO(DAO dao){this.dao = dao;}

    private final String BILL = "bill";
    private final String AUTHOR = "author_id";
    private final String BUYER = "buyer_id";
    private final String MODEL_ID = "model_id";
    private final String AMOUNT = "amount";


    public void addBill(Bill bill) {
        String query = "INSERT into " + BILL + " values(?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,bill.getAuthor().getId());
            preparedStatement.setLong(2,bill.getBuyer().getId());
            preparedStatement.setLong(3,bill.getModel().getIdModel());
            preparedStatement.setInt(4,bill.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            dao.sendMessage("Query updating error");
        }
    }


    public List<Bill> getBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM " + BILL + " ORDER BY " + AUTHOR;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            extractBillsFromQuery(bills, resultSet);
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
        return bills;
    }


    public List<Bill> getBillsByModel(long modelId) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM " + BILL + " WHERE " + MODEL_ID + " = ?";
        setParamsForSearch(modelId, bills, query);
        return bills;
    }

    public List<Bill> getBillsByAuthor(long authorId) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM " + BILL + " WHERE " + AUTHOR + " = ?";
        setParamsForSearch(authorId, bills, query);
        return bills;
    }

    public List<Bill> getBillsByBuyer(long buyerId) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM " + BILL + " WHERE " + AUTHOR + " = ?";
        setParamsForSearch(buyerId, bills, query);
        return bills;
    }

    private void setParamsForSearch(long authorId, List<Bill> bills, String query) {
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            extractBillsFromQuery(bills, resultSet);
        } catch (SQLException e) {
            dao.sendMessage("Query executing error");
        }
    }

    private void extractBillsFromQuery(List<Bill> bills, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            long modelId = resultSet.getLong(MODEL_ID);
            long authorId = resultSet.getLong(AUTHOR);
            long buyerId = resultSet.getLong(BUYER);
            int amount = resultSet.getInt(AMOUNT);
            UserDAO userDAO = new UserDAO(dao);
            User author = userDAO.getUser(authorId);
            User buyer = userDAO.getUser(buyerId);
            ModelDAO modelDAO = new ModelDAO(dao);
            Model model = modelDAO.getModel(modelId);
            bills.add(new Bill(author,buyer,model,amount));
        }
    }
}
