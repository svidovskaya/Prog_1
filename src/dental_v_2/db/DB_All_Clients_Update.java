package dental_v_2.db;

import javax.swing.*;
import java.sql.*;



public class DB_All_Clients_Update extends DBProcessor{
    public void update_h(String[][] mas){
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement()) {
            for (int i = 0; i < mas.length; i++) {
                int type_cl = 0;
                String sql_code = "update clients\n" +
                        "Set cl_surname=?, cl_name=?, cl_middle_name=?, cl_number=?, cl_email=?, cl_comment=? where clients.client_id = ?;";


                PreparedStatement preparedStmt = connection.prepareStatement(sql_code);
                preparedStmt.setString(1, mas[i][2]);
                preparedStmt.setString(2, mas[i][3]);
                preparedStmt.setString(3, mas[i][4]);
                preparedStmt.setString(4, mas[i][5]);
                preparedStmt.setString(5, mas[i][6]);
                preparedStmt.setString(6, mas[i][7]);

                preparedStmt.setInt(7, Integer.parseInt(mas[i][0]));
                preparedStmt.executeUpdate();

                String sql_code1 = "update client_class\n" +
                        "Set cc_class_id=? where cc_client_id = ?;";
                PreparedStatement preparedStm = connection.prepareStatement(sql_code1);


                if (mas[i][1].equals("розница")){

                    preparedStm.setInt(2, Integer.parseInt(mas[i][0]));
                    preparedStm.setInt(1, 1);

                } else if (mas[i][1].equals("опт")){
                    preparedStm.setInt(2, Integer.parseInt(mas[i][0]));
                    preparedStm.setInt(1, 2);
                }
                preparedStm.executeUpdate();
            }










        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }
    }

    public void del_h(int id_del){
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement()) {

            String sql_code_o = "UPDATE clients " +
                    "Set cl_show=? where client_id=?";


            PreparedStatement preparedStmt = connection.prepareStatement(sql_code_o);


            preparedStmt.setString(1, "false");
            preparedStmt.setInt(2, id_del);

            preparedStmt.executeUpdate();

            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Удалено! Это успех!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }
    }
}
