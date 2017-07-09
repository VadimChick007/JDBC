package com.jdbc;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;


public class Main {
    private static final String USERNAME = "root";

    private static final String PASSWORD = "vadymSQL";

    private static final String URL = "jdbc:mysql://localhost:3306/business?useSSL=false";

    public Main() throws SQLException {
    }


    public static void main(String[] args) throws SQLException {

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        }catch (SQLException e){
            System.out.println("Exception during connection to database");
            return;
        }

        try(Connection connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
            Statement statement = connection.createStatement()){

            statement.execute("INSERT INTO business.products (product_name, price, shop_id) VALUES (\"IMAC\", 1500,1)");
            statement.executeUpdate("UPDATE business.products SET  product_name = \"Mars\" WHERE product_id = 6");
            statement.addBatch("INSERT INTO business.shops (shop_name, address) VALUES (\"Coca Cola\",\"DC\")");
            statement.addBatch("INSERT INTO business.shops (shop_name, address) VALUES (\"Pepci\",\"DC\")");
            statement.executeBatch();
            statement.clearBatch();
        }catch (SQLException e){
            e.printStackTrace();
        }

        DBProcessor dbProcessor = new DBProcessor();

        Connection connection2 = dbProcessor.getConnection(URL, USERNAME,PASSWORD);

        String query = "SELECT * FROM business.products";

        Statement statement1 = connection2.createStatement();

        ResultSet resultSet = statement1.executeQuery(query);

        while(resultSet.next()){
            int id;
            String name;
            double price;
            int shop_id;
            id = resultSet.getInt("product_id");
            name = resultSet.getString("product_name");
            price = resultSet.getDouble("price");
            shop_id = resultSet.getInt("shop_id");
            Product product = new Product(id, name, price, shop_id);
            System.out.println(product);
        }

        statement1.close();
        connection2.close();


        DBProcessor dbProcessor1 = new DBProcessor();

        Connection connection1 = dbProcessor.getConnection(URL,USERNAME,PASSWORD);

        String query1 = "SELECT * FROM business.products";

        String INSERT = "INSERT INTO business.products (product_name, price , shop_id) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection1.prepareStatement(INSERT);

        preparedStatement.setString(1,"yep");
        preparedStatement.setDouble(2,23.1);
        preparedStatement.setInt(3,2);
        preparedStatement.execute();

        PreparedStatement preparedStatement1 = connection1.prepareStatement(query);

            ResultSet resultSet1 = preparedStatement1.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getInt("product_id" + " " + resultSet.getString("product_name")));
        }

        preparedStatement.close();
        connection1.close();


    }



}
