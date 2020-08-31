package dental_v_2.db;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class DB_History_Orders_Update extends DBProcessor{
//    public void update_h(String[][] mas, int id_order){
//        try (
//                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//                Statement statement = connection.createStatement()) {
//            for (int i = 0; i < mas.length; i++) {
//                String sql_code = "update amount_pr_o\n" +
//                        "Set a_amount=?, a_price=? where amount_id = ?";
//                PreparedStatement preparedStmt = connection.prepareStatement(sql_code);
//                preparedStmt.setString(2, mas[i][2]);
//                preparedStmt.setString(1, mas[i][3]);
//
//                preparedStmt.setInt(3, Integer.parseInt(mas[i][0]));
//                preparedStmt.executeUpdate();
//            }
//
//            String sql_cod = " SELECT sum(a_amount*a_price) as ss from amount_pr_o where a_order_id = '"+id_order+"'";
//            PreparedStatement preparedStatement_sa = connection.prepareStatement(sql_cod, PreparedStatement.RETURN_GENERATED_KEYS);
//
//            ResultSet ResS = preparedStatement_sa.executeQuery();
//            int sum = 0;
//            while (ResS.next()){
//                sum = ResS.getInt("ss");
//            }
//
//
//
//            String sql_up = "Update orders set o_summa = ? where order_id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql_up);
//            preparedStatement.setString(1, String.valueOf(sum));
//            preparedStatement.setInt(2, id_order);
//
//            preparedStatement.executeUpdate();
//
//
//
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane op = new JOptionPane();
//            op.showMessageDialog(null, "Ошибка!");
//        }
//    }


    public void del_h(int id_del){
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement()) {
           // System.out.println(id_del);

            String sql_code_o = "update orders set o_show=?  where order_id = ?";


            PreparedStatement preparedStmt = connection.prepareStatement(sql_code_o);
            preparedStmt.setString(1, "false");
            preparedStmt.setInt(2, id_del);


            preparedStmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }
    }
    public void create_n(String[][] mas, int id_order, int id_uder, String vid_opl){
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement()) {
int nakl =0;
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            String sql_code_nakl = "INSERT INTO nakladnaya (n_user_id, n_data, n_time, n_vid_opl) values (?,?,?, ?)";
            PreparedStatement preparedStatement_n = connection.prepareStatement(sql_code_nakl, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement_n.setInt(1, id_uder);
            preparedStatement_n.setString(2, String.valueOf(date));
            preparedStatement_n.setString(3, String.valueOf(time));
            preparedStatement_n.setString(4, vid_opl);


            preparedStatement_n.executeUpdate();
            try (ResultSet i = preparedStatement_n.getGeneratedKeys()) {
                if (i.next()) {
                    nakl = (i.getInt(1));
                }
            }
            System.out.println(nakl + "");
            String sql_code_ord_n = "INSERT INTO ord_nak (on_order_id, on_nakladnaya_id) values (?,?)";
            PreparedStatement preparedStatement_o = connection.prepareStatement(sql_code_ord_n, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement_o.setInt(1, id_order);
            preparedStatement_o.setInt(2, nakl);
            preparedStatement_o.executeUpdate();


            for (int i = 0; i < mas.length; i++) {
                String sql_code_info = "INSERT INTO info_nakl (i_amount, i_price, i_nakladnaya, i_p_name) values (?, ?, ?, ?)";

                PreparedStatement preparedStmt = connection.prepareStatement(sql_code_info);
                preparedStmt.setString(2, mas[i][2]);
                preparedStmt.setString(1, mas[i][3]);

                preparedStmt.setInt(3, nakl);
                preparedStmt.setString(4, mas[i][1]);
                preparedStmt.executeUpdate();
            }
            String sql_code_3 = "Select sum(i_price*i_amount) as k, nakladnaya_id from info_nakl inner join nakladnaya on i_nakladnaya = nakladnaya.nakladnaya_id\n" +
                    "inner join ord_nak on ord_nak.on_nakladnaya_id = nakladnaya_id\n" +
                    "inner join orders on ord_nak.on_order_id = orders.order_id\n" +
                    "inner join clients on clients.client_id = orders.o_client_id\n" +
                    "where nakladnaya_id = '"+nakl+"' ";
            PreparedStatement preparedStatement_3 = connection.prepareStatement(sql_code_3);
            ResultSet resultSet = preparedStatement_3.executeQuery();

int am = 0;
while (resultSet.next()){
    am = resultSet.getInt("k");
}





            String sql_code_s = "update nakladnaya set n_summa=?  where nakladnaya_id = ?";
            PreparedStatement preparedStatement_s = connection.prepareStatement(sql_code_s, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement_s.setString(1, String.valueOf(am));
            preparedStatement_s.setInt(2, nakl);



            preparedStatement_s.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }
    }

}
