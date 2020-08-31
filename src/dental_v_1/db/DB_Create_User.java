package dental_v_1.db;



import javax.swing.*;
import java.sql.*;

public class DB_Create_User extends DBProcessor{
    public String URL = "";

    public void save_user (String surname, String name, String middle, int phone, String mail, int id_positions, String login, String pass) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

             Statement statement = connection.createStatement()) {
            int user_id = 0;
            String sql_code_user = "INSERT INTO users (u_login, u_password, u_pos_id) values (?, ?, ?)";
            String sql_code_info = "INSERT INTO informations (i_name, i_surname, i_middlename, i_number, i_email, i_user_is) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement_user = connection.prepareStatement(sql_code_user, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement_info = connection.prepareStatement(sql_code_info, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement_user.setString(1, login);
            preparedStatement_user.setString(2, pass);
            preparedStatement_user.setInt(3, id_positions);

            preparedStatement_user.executeUpdate();
            try (ResultSet i = preparedStatement_user.getGeneratedKeys()) {
                if (i.next()) {
                    user_id = (i.getInt(1));
                }
            }


            preparedStatement_info.setString(1, name);
            preparedStatement_info.setString(2, surname);
            preparedStatement_info.setString(3, middle);
            preparedStatement_info.setInt(4, phone);
            preparedStatement_info.setString(5, mail);
            preparedStatement_info.setInt(6, user_id);


            preparedStatement_info.executeUpdate();

            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Это успех!");


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }

}
