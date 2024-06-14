package com.ps.dao.interfaces;

import com.ps.models.Sales;

import java.util.List;

public interface SalesInt {


    List<Sales> getAllSales();

    int createSale(Sales sale);

    void deleteSale(int id);
}
