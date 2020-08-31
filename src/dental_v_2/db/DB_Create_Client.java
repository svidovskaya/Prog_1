package dental_v_2.db;



import javax.swing.*;
import java.sql.*;

public class DB_Create_Client extends DBProcessor{
    public int proverka(String surname, String name, String middle, String phone, String mail, String comment){
        int c =0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select client_id from clients where cl_number = '"+phone+"'";
            ResultSet resSet = statement.executeQuery(sql_code);

            while (resSet.next()) {
                c = 1;
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }


    public void save_client (String surname, String name, String middle, String phone, String mail, String comment, int id_clas){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            int id_cl =0;
            String sql_code_client = "INSERT INTO clients (cl_surname, cl_name, cl_middle_name, cl_number, cl_email,  cl_comment) values (?, ?, ?, ?, ?, ?)";
            String sql_code_cl_class = "INSERT INTO client_class (cc_client_id, cc_class_id) values (?, ?)";

            PreparedStatement preparedStatement_client = connection.prepareStatement(sql_code_client, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement_cl_class = connection.prepareStatement(sql_code_cl_class, PreparedStatement.RETURN_GENERATED_KEYS);



            preparedStatement_client.setString(1, surname);
            preparedStatement_client.setString(2, name);
            preparedStatement_client.setString(3, middle);
            preparedStatement_client.setString(4, phone);
            preparedStatement_client.setString(5, mail);
            preparedStatement_client.setString(6, comment);


            preparedStatement_client.executeUpdate();
            try (ResultSet i = preparedStatement_client.getGeneratedKeys()) {
                if (i.next()) {
                    id_cl = (i.getInt(1));
                }
            }
            preparedStatement_cl_class.setInt(1, id_cl);
            preparedStatement_cl_class.setInt(2, id_clas);

            preparedStatement_cl_class.executeUpdate();





            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Это успех!");


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }

}
