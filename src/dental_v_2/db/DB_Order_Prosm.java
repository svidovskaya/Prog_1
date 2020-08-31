package dental_v_2.db;



import java.sql.*;

public class DB_Order_Prosm extends DBProcessor {
    public int count_sales(int id_order){

        int r_c = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select count(amount_id) from amount_pr_o inner join products on products.prod_id = amount_pr_o.a_prod_id where a_order_id = '"+id_order+"';";
            ResultSet resSet = statement.executeQuery(sql_code);
            while (resSet.next()) {
                r_c = resSet.getInt("count(amount_id)");
            }
        //   r_c += -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
           System.out.println(r_c + "orders");
        return r_c;
    }

    public String[][] gen_zhachenia_hist(int id_order, int r_c){

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "Select amount_id, p_name, a_price, a_amount from amount_pr_o\n" +
                    "inner join products on products.prod_id = amount_pr_o.a_prod_id where a_order_id = '"+id_order+"';";
            ResultSet resSet = statement.executeQuery(sql_code);

            String[][] result_h =new String[r_c][4];

            int i = 0;
            while (resSet.next()) {
                result_h[i][0] = String.valueOf(resSet.getInt("amount_id"));
                result_h[i][1] = resSet.getString("p_name");
                result_h[i][2] = resSet.getString("a_price");
                result_h[i][3] = resSet.getString("a_amount");



                i+=1;
            }

            return result_h;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] rest_h = {{}};
        return rest_h;
    }

    public int poisk_summa (int id_order){
        int s = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql_code = "SELECT o_summa from orders WHERE order_id = '"+id_order+"'";
            ResultSet resSet = statement.executeQuery(sql_code);

            while (resSet.next()){
             s = resSet.getInt("o_summa");
            }
            System.out.println(s + "summ ord");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;

    }


}
