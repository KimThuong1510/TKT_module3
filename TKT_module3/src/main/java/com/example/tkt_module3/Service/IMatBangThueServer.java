package com.example.tkt_module3.Service;

import com.example.tkt_module3.Model.MatBangThue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IMatBangThueServer{
    List<MatBangThue> findAll();
    void deleteMatBang(String maMb);


}

