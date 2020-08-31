package dental_v_2.db;



import javax.swing.*;
import java.sql.*;

public class DB_Prod_in_Stock extends DBProcessor{

    public void save_prod(int id_prod, int count){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql_code_sale = "INSERT INTO amount_pr_s (as_prod_id, as_stock_id, as_amount) values (?, ?, ?)";

            PreparedStatement preparedStatement_sale = connection.prepareStatement(sql_code_sale, PreparedStatement.RETURN_GENERATED_KEYS);



            preparedStatement_sale.setInt(1, id_prod);
            preparedStatement_sale.setInt(2, 1);
            preparedStatement_sale.setInt(3, count);


            preparedStatement_sale.executeUpdate();




            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Это успех!");


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }

    public void save_prod_minus(int id_prod, int count){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql_code_sale = "INSERT INTO amount_pr_s (as_prod_id, as_stock_id, as_amount) values (?, ?, ?)";

            PreparedStatement preparedStatement_sale = connection.prepareStatement(sql_code_sale, PreparedStatement.RETURN_GENERATED_KEYS);



            preparedStatement_sale.setInt(1, id_prod);
            preparedStatement_sale.setInt(2, 1);
            preparedStatement_sale.setInt(3, -count);


            preparedStatement_sale.executeUpdate();



            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Это успех!");


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }
}
