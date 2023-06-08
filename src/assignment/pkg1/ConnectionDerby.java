package assignment.pkg1;

import javax.swing.*;
import java.sql.*;

public class ConnectionDerby {
    private static Connection connection = null;

    public static void initDB() {
        try {
            String query_create_table = "CREATE TABLE SCORE(" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(25), " +
                    "score INT) ";

            String query_init_table = "INSERT INTO SCORE (id, name, score) values (?, ?, ?)";


            //initialize connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:blackjackdb; create=true");
            ResultSet rs = connection.getMetaData().getTables(null, null, "SCORE", null);
            if(rs.next())
            {
                System.out.println("Table SCORE already exists !!");
            }else {
                PreparedStatement preparedStatement = connection.prepareStatement(query_create_table);
                preparedStatement.executeUpdate();
                System.out.println("Tabe SCORE created successfully!!");

                //-------------------------------------------------------------//
                //                  initialize player info
                //-------------------------------------------------------------//
                preparedStatement = connection.prepareStatement(query_init_table);
                preparedStatement.setLong(1, 1);
                preparedStatement.setString(2, "Player");
                preparedStatement.setInt(3, 500);

                preparedStatement.executeUpdate();
                System.out.println("Tabe SCORE initialized successfully!!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void saveScore(int score) {
        String query_load_table = "update score set score = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query_load_table);
            preparedStatement.setInt(1, score);
            preparedStatement.setLong(2, 1);
            preparedStatement.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getScore() {
        String query_load_table = "select * from SCORE";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query_load_table);
            if(rs.next()){
                Integer score = rs.getInt("score");
                return  score;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
