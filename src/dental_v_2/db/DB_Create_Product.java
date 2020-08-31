package dental_v_2.db;



import javax.swing.*;
import java.sql.*;

public class DB_Create_Product extends DBProcessor{
    public void save_prod(int id_manuf, String name, float price, float price_stom, String shtrih, String code, String articul){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){


            String sql_code_prod = "INSERT INTO products (p_name, p_price, p_manuf_id, p_price_stom, p_shtrih, p_code, p_articul) values (?, ?, ?, ?, ?,?,?)";
            String sql_code_st = "INSERT INTO amount_pr_s (as_prod_id, as_stock_id, as_amount) values (?, ?, ?)";

            PreparedStatement preparedStatement_prod = connection.prepareStatement(sql_code_prod, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement_st = connection.prepareStatement(sql_code_st, PreparedStatement.RETURN_GENERATED_KEYS);


            preparedStatement_prod.setString(1, name);
            preparedStatement_prod.setFloat(2, price);
            preparedStatement_prod.setInt(3, id_manuf);
            preparedStatement_prod.setFloat(4, price_stom);
            preparedStatement_prod.setString(5, shtrih);
            preparedStatement_prod.setString(6, code);
            preparedStatement_prod.setString(7, articul);


            preparedStatement_prod.executeUpdate();
int id_prod = 0;
            try (ResultSet i = preparedStatement_prod.getGeneratedKeys()) {
                if (i.next()) {
                    id_prod = (i.getInt(1));
                }
            }
            preparedStatement_st.setInt(1, id_prod);
            preparedStatement_st.setInt(2, 1);
            preparedStatement_st.setInt(3, 0);
            

            preparedStatement_st.executeUpdate();






            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Это успех!");


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Код не уникальный!");
        }

    }

}
