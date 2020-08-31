package dental_v_2.db;



import javax.swing.*;
import java.sql.*;

public class DB_Create_Manuf extends DBProcessor{
    public void save_manuf (String name, String country){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql_code_client = "INSERT INTO manufacturer (manuf_name, manuf_country) values (?, ?)";

            PreparedStatement preparedStatement_client = connection.prepareStatement(sql_code_client, PreparedStatement.RETURN_GENERATED_KEYS);



            preparedStatement_client.setString(2, country);
            preparedStatement_client.setString(1, name);



            preparedStatement_client.executeUpdate();
            preparedStatement_client.closeOnCompletion();




            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Это успех!");


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }
    public int proverka_manuf (String name){
        int p=0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code_client = "Select manuf_id from manufacturer where manuf_name = '"+name+"'";
            ResultSet resultSet = statement.executeQuery(sql_code_client);
            int v = 0;
            while (resultSet.next()){
                v = resultSet.getInt("manuf_id");
            }
            if (v == 0){
                p = 1;
            } else {
                p = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }


}
