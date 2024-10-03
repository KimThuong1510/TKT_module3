package com.example.tkt_module3.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MatBangThueService implements IMatBangThueServer {
        private final BaseRepository baseRepository = new BaseRepository();

        public MatBangThueService() throws SQLException, ClassNotFoundException {
        }

        private static final String FIND_ALL = "SELECT * FROM MatBangThue;";
        private static final String DELETE_MB = "DELETE FROM MatBangThue WHERE maMb = ?";
        private static final String INSERT_MB = "INSERT INTO MatBangThue (maMb, dienTich, trThai, tang, loaiVp, giaThue, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        @Override
        public List<com.example.tkt_module3.Model.MatBangThue> findAll() {
            List<com.example.tkt_module3.Model.MatBangThue> list = new ArrayList<>();
            try (Connection connection = baseRepository.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String maMb = resultSet.getString("maMb");
                    double dienTich = resultSet.getDouble("dienTich");
                    int trThai = resultSet.getInt("trThai");
                    int tang = resultSet.getInt("tang");
                    int loaiVp = resultSet.getInt("loaiVp");
                    double giaThue = resultSet.getDouble("giaThue");
                    Date startDate = resultSet.getDate("startDate");
                    Date endDate = resultSet.getDate("endDate");
                    list.add(new com.example.tkt_module3.Model.MatBangThue(maMb, dienTich, trThai, tang, loaiVp, giaThue, (java.sql.Date) startDate, (java.sql.Date) endDate));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error fetching MatBangThue data", e);
            }
            return list;
        }

        @Override
        public void deleteMatBang(String maMb) {
            try (Connection connection = baseRepository.getConnection();
                 PreparedStatement ps = connection.prepareStatement(DELETE_MB)) {
                ps.setString(1, maMb);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error deleting MatBangThue: " + e.getMessage());
            }
        }
    }


