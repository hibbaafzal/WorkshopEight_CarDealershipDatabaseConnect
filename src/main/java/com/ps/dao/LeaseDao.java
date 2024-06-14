package com.ps.dao;

import com.ps.dao.interfaces.LeaseInt;
import com.ps.models.Lease;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseDao implements LeaseInt {

    private BasicDataSource dataSource;

    public LeaseDao (BasicDataSource basicDataSource) {
        this.dataSource = basicDataSource;

    }
@Override
    public List<Lease> allLeases() {
        List<Lease> leases = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM lease_contracts");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lesseeName = resultSet.getString("lessee_name");
                String vin = resultSet.getString("vin");

                Lease lease = new Lease(lesseeName, vin);
                leases.add(lease);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return leases;
        }
    }



@Override
    public int createLease(Lease lease) {
        int generateId = -1;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO lease_contracts(id, lessee_name, vin) VALUES(?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            preparedStatement.setInt(1, lease.getId());
            preparedStatement.setString(2, lease.getLesseeName());
            preparedStatement.setString(3,lease.getVin());
            preparedStatement.executeUpdate();

            try (
                    ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    generateId = keys.getInt(1);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generateId;
    }

@Override
    public void deleteLease(int id){

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM lease_contracts WHERE id = ?"
                );
        ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
