package dental_v_2.db;



import javax.swing.*;
import java.sql.*;

public class DB_History_Nakladnaya extends DBProcessor {
    public int count_sales(String poisk){
        poisk = "%" + poisk +  "%";

        int r_c = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "SELECT count(nakladnaya_id) \n" +
                    "                    from nakladnaya inner join  users on n_user_id = user_id\n" +
                    "                    inner join informations on user_id = i_user_is\n" +
                    "                    inner join ord_nak on nakladnaya_id = on_nakladnaya_id\n" +
                    "                    inner join orders on order_id = on_order_id\n" +
                    "                    inner join clients on client_id = o_client_id\n" +
                    "                    inner join client_class on client_id = client_class.cc_client_id\n" +
                    "                    inner join class on class.class_id = client_class.cc_class_id\n" +
                    "                    where n_show = 'try' and n_vid_opl like '"+poisk+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            while (resSet.next()) {
                r_c = resSet.getInt("count(nakladnaya_id)");
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
            String sql_code = "SELECT nakladnaya_id, n_data, n_time, n_summa, i_surname, i_name, cl_surname, cl_name, c_info, n_vid_opl, n_comment \n" +
                    "from nakladnaya inner join  users on n_user_id = user_id\n" +
                    "inner join informations on user_id = i_user_is\n" +
                    "inner join ord_nak on nakladnaya_id = on_nakladnaya_id\n" +
                    "inner join orders on order_id = on_order_id\n" +
                    "inner join clients on client_id = o_client_id\n" +
                    "inner join client_class on client_id = client_class.cc_client_id\n" +
                    "inner join class on class.class_id = client_class.cc_class_id\n" +
                    "where n_show = 'try' and n_vid_opl like '"+poisk+"' " +
                    "order by nakladnaya_id desc ";
            ResultSet resSet = statement.executeQuery(sql_code);

            String[][] result_h =new String[r_c][9];

            int i = 0;
            while (resSet.next()) {
                result_h[i][0] = String.valueOf(resSet.getInt("nakladnaya_id"));
                result_h[i][1] = resSet.getString("n_data");
                result_h[i][2] = resSet.getString("n_time");
                result_h[i][3] = resSet.getString("c_info");
                result_h[i][5] = resSet.getString("cl_surname") + " " + resSet.getString("cl_name");
                result_h[i][4] = resSet.getString("n_summa");
                result_h[i][6] = resSet.getString("i_surname") + " " + resSet.getString("i_name");
                result_h[i][7] = resSet.getString("n_vid_opl");
                result_h[i][8] = resSet.getString("n_comment");

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
            String sql_code = "select i_p_name, sum(i_amount) as s, i_price\n" +
                    "from info_nakl \n" +

                    "where i_nakladnaya = '"+id_information+"'\n" +
                    "group by i_p_name;"; //мега запрос


            PreparedStatement preparedStatement_pr = connection.prepareStatement(sql_code);


            ResultSet result2 = preparedStatement_pr.executeQuery();


            String result = "";

            int i = 0;


            while (result2.next()) {

                result += result2.getString("i_p_name");
                result += "    ";
                result += result2.getString("s");
                result += " * ";
                result += result2.getString("i_price");
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
