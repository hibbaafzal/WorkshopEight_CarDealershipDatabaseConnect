package com.ps.dao.interfaces;

import com.ps.models.Lease;

import java.util.List;



public interface LeaseInt {




    List<Lease> allLeases();

    int createLease(Lease lease);


    void deleteLease(int id);
}
