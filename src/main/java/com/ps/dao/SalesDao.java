package com.ps.dao;

import com.ps.dao.interfaces.SalesInt;
import com.ps.models.Sales;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDao implements SalesInt {
    private BasicDataSource dataSource;

    public SalesDao(BasicDataSource basicDataSource) {
        this.dataSource = basicDataSource;
    }

    @Override
    public List<Sales> getAllSales() {
        List<Sales> sales = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("SELECT * FROM sales_contracts");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String buyerName = resultSet.getString("buyer_name");
                String vin = resultSet.getString("vin");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return sales;
        }
    }

    @Override
    public int createSale(Sales sale) {
        int generatedId = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO sales_contracts(id, buyer_name, contract_date, vin) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, sale.getId());
            preparedStatement.setString(2, sale.getBuyerName());
            preparedStatement.setString(3, sale.getVin());
            preparedStatement.setString(4, sale.getContractDate());
            preparedStatement.executeUpdate();


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return generatedId;
    }

    @Override
    public void deleteSale(int id) {

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM sales_contracts WHERE id = ?"
                );
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


