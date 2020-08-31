package dental_v_2.db;



import javax.swing.*;
import java.sql.*;

public class DB_Create_Class extends DBProcessor{
    public void save_class (String name, String info){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql_code_client = "INSERT INTO class (c_name, c_info) values (?, ?)";

            PreparedStatement preparedStatement_client = connection.prepareStatement(sql_code_client, PreparedStatement.RETURN_GENERATED_KEYS);



            preparedStatement_client.setString(2, info);
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


}
