package dental_v_2.db;



import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class DB_Create_Order extends DBProcessor{



    public int save_action(int id_client, int id_delivery, int id_platform ,  String fio, int user_id){
        int order_id = 0;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql_code_sale = "INSERT INTO orders (o_client_id, o_platform_id, o_data,  o_comment, o_time, o_user_id) values (?, ?, ?, ?, ?, ?)";
            String sql_code_d = "INSERT INTO delivery (del_id, order_id) values (?, ?)";
            PreparedStatement preparedStatement_sale = connection.prepareStatement(sql_code_sale, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement_d = connection.prepareStatement(sql_code_d, PreparedStatement.RETURN_GENERATED_KEYS);

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            preparedStatement_sale.setInt(1, id_client);
            preparedStatement_sale.setInt(2, id_platform);
            preparedStatement_sale.setString(3, String.valueOf(date));
            preparedStatement_sale.setString(4, fio);
            preparedStatement_sale.setString(5, String.valueOf(time));
            preparedStatement_sale.setInt(6, user_id);


            preparedStatement_sale.executeUpdate();

            try (ResultSet i = preparedStatement_sale.getGeneratedKeys()) {
                if (i.next()) {
                    order_id = (i.getInt(1));
                }
            }


            preparedStatement_d.setInt(1, id_delivery);
            preparedStatement_d.setInt(2, order_id);
            preparedStatement_d.executeUpdate();



            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Это успех!");

            return order_id;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }
        return order_id;
    }

    public void save_prod(int id_order, int id_prod, String pr, int count){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql_code_sale = "INSERT INTO amount_pr_o (a_prod_id, a_order_id, a_amount, a_price) values (?, ?, ?, ?)";

            PreparedStatement preparedStatement_sale = connection.prepareStatement(sql_code_sale, PreparedStatement.RETURN_GENERATED_KEYS);



            preparedStatement_sale.setInt(1, id_prod);
            preparedStatement_sale.setInt(2, id_order);
            preparedStatement_sale.setInt(3, count);
            preparedStatement_sale.setString(4, pr);


            preparedStatement_sale.executeUpdate();


            String sql_code = " SELECT sum(a_amount*a_price) as ss from amount_pr_o where a_order_id = '"+id_order+"'";
            PreparedStatement preparedStatement_sa = connection.prepareStatement(sql_code, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet ResS = preparedStatement_sa.executeQuery();
            int sum = 0;
            while (ResS.next()){
                sum = ResS.getInt("ss");
            }



            String sql_up = "Update orders set o_summa = ? where order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql_up);
            preparedStatement.setString(1, String.valueOf(sum));
            preparedStatement.setInt(2, id_order);

            preparedStatement.executeUpdate();



            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Это успех!");


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }




    public String poisk_s(int id_order){
        String count_new = "";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "Select sum(a_amount * a_price) as sss from amount_pr_o where a_order_id = '"+id_order+"'";

            ResultSet resSet = statement.executeQuery(sql_code);



            resSet.next();
            count_new = String.valueOf(resSet.getInt("sss"));

            return count_new;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //  System.out.println(count_new);

        return count_new;
    }
    public int poisk_colva(String product){
        int count_new = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "SELECT sum(as_amount) as sss FROM amount_pr_s inner join products on products.prod_id = amount_pr_s.as_prod_id\n" +
                    "where p_name = '"+product+"'\n" +
                    "group by products.p_name";
            ResultSet resSet = statement.executeQuery(sql_code);



           if (resSet.next()){
               count_new = resSet.getInt("sss");
           };


            return count_new;

        } catch (SQLException e) {
            e.printStackTrace();
        }
      //  System.out.println(count_new);

        return count_new;
    }
    }






