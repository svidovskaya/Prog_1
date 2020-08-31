package dental_v_2.db;

import javax.swing.*;
import java.sql.*;



public class DB_All_Products_Update extends DBProcessor{
    public void update_h(String[][] mas){
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement()) {
            for (int i = 0; i < mas.length; i++) {
                String sql_code = "update products\n" +
                        "Set p_shtrih=?, p_code=?, p_articul=?, products.p_name=?, products.p_price = ?, products.p_price_stom = ? where products.prod_id = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(sql_code);
                preparedStmt.setString(1, mas[i][1]);
                preparedStmt.setString(2, mas[i][2]);
                preparedStmt.setString(3, mas[i][3]);
                preparedStmt.setString(4, mas[i][4]);
                preparedStmt.setString(5, mas[i][5]);
                preparedStmt.setString(6, mas[i][6]);



                preparedStmt.setInt(7, Integer.parseInt(mas[i][0]));
                preparedStmt.executeUpdate();
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

            String sql_code_o = "UPDATE products " +
                    "Set p_show=? where prod_id=?";


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
