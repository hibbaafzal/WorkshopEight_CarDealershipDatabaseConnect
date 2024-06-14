package com.ps.dao.interfaces;

import com.ps.models.Lease;

import java.util.List;



public interface LeaseInt {


    List<Lease> getallLease();

    int createLease(Lease lease);


    void deleteLease(int id);
}
