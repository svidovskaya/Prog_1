package dental_v_2.db;



import javax.swing.*;
import java.sql.*;

public class DB_History_Orders extends DBProcessor {
    public int count_sales(String poisk){
        poisk = "%" + poisk +  "%";

        int r_c = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "SELECT count(order_id) \n" +
                    "                    from orders inner join  users on o_user_id = user_id\n" +
                    "                    inner join informations on user_id = i_user_is\n" +
                    "                    inner join clients on client_id = o_client_id\n" +
                    "                    inner join client_class on client_id = client_class.cc_client_id\n" +
                    "                    inner join class on class.class_id = client_class.cc_class_id\n" +
                    "where o_show like 'try'";

            ResultSet resSet = statement.executeQuery(sql_code);
            while (resSet.next()) {
                r_c = resSet.getInt("count(order_id)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    //    System.out.println(r_c + "orders");
        return r_c;
    }

    public String[][] gen_zhachenia_hist(int r_c, String poisk){
        poisk = "%" + poisk +  "%";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "SELECT order_id, o_data, o_time, o_summa, i_surname, i_name, cl_surname, cl_name, c_info \n" +
                    "from orders inner join  users on o_user_id = user_id\n" +
                    "inner join informations on user_id = i_user_is\n" +

                    "inner join clients on client_id = o_client_id\n" +
                    "inner join client_class on client_id = client_class.cc_client_id\n" +
                    "inner join class on class.class_id = client_class.cc_class_id\n" +
                    "where o_show like 'try' \n" +
                    "order by order_id desc ";
            ResultSet resSet = statement.executeQuery(sql_code);

            String[][] result_h =new String[r_c][7];

            int i = 0;
            while (resSet.next()) {
                result_h[i][0] = String.valueOf(resSet.getInt("order_id"));
                result_h[i][1] = resSet.getString("o_data");
                result_h[i][2] = resSet.getString("o_time");
                result_h[i][3] = resSet.getString("c_info");
                result_h[i][5] = resSet.getString("cl_surname") + " " + resSet.getString("cl_name");
                result_h[i][4] = resSet.getString("o_summa");
                result_h[i][6] = resSet.getString("i_surname") + " " + resSet.getString("i_name");


                i+=1;
            }

            return result_h;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] rest_h = {{}};
        return rest_h;
    }


    public void show_inform1(int id_information){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select products.p_name, manufacturer.manuf_country, amount_pr_o.a_amount, purpose.purp_name\n" +
                    "from orders inner join amount_pr_o  on orders.order_id = amount_pr_o.a_order_id\n" +
                    "inner join products on amount_pr_o.a_prod_id = products.prod_id\n" +
                    "inner join manufacturer on products.p_manuf_id = manufacturer.manuf_id\n" +
                    "inner join characteristic on products.p_charact_id = characteristic.ch_id\n" +
                    "inner join ch_purp on ch_purp.ch_id = characteristic.ch_id\n" +
                    "inner join purpose on ch_purp.purpose_id = purpose.purpose_id\n" +
                    "where orders.order_id = '"+id_information+"'\n" +
                    "group by products.prod_id;"; //мега запрос
            ResultSet resSet = statement.executeQuery(sql_code);


            String result = "";

            int i = 0;
            while (resSet.next()) {
                result += resSet.getString("p_name");
                result += " ";
                result += resSet.getString("manuf_country");
                result += " ";
                result += resSet.getString("a_amount");
                result += " ";
                result += resSet.getString("purp_name");

                result += "\n";
                i+=1;


            }

            if (result.equals("")){
                result = "Товар не продавался";
            }
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, result );

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }


    public void show_inform(int id_information){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select p_name, sum(a_amount) as s, a_price\n" +
                    "from amount_pr_o inner join products on a_prod_id = prod_id \n" +

                    "where a_order_id = '"+id_information+"'\n" +
                    "group by a_prod_id;"; //мега запрос


            PreparedStatement preparedStatement_pr = connection.prepareStatement(sql_code);


            ResultSet result2 = preparedStatement_pr.executeQuery();


            String result = "";

            int i = 0;


            while (result2.next()) {

                result += result2.getString("p_name");
                result += "    ";
                result += result2.getString("s");
                result += " * ";
                result += result2.getString("a_price");
                result += " грн";


                result += "\n";
                i+=1;
            }

            if (result.equals("")){
                result = "Товар не продавался";
            }
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, result );

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }
/*
    public void show_delivery(int id_information){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "SELECT delivery_method.deliv_m_name, delivery.del_ttn, delivery.del_comment from\n" +
                    "orders inner join delivery on orders.o_deliv_id = delivery.deliv_id\n" +
                    "inner join delivery_method on delivery.del_id = delivery_method.delivery_m_id \n" +

                    "where orders.order_id = '"+id_information+"'" +
                    "group by order_id;";


            PreparedStatement preparedStatement_pr = connection.prepareStatement(sql_code);

            ResultSet result2 = preparedStatement_pr.executeQuery();


            String result = "";

            int i = 0;


            while (result2.next()) {
                result += " ";
                result += result2.getString("deliv_m_name");
                result += " ";
                result += result2.getString("del_ttn");
                result += " ";
                result += result2.getString("del_comment");


                result += "\n";
                i+=1;
            }

            if (result.equals("")){
                result = "Товар не продавался";
            }
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, result );

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

    }

 */
}
