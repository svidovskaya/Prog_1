package dental_v_2.db;



import java.sql.*;

public class DB_All_Products extends DBProcessor {
    public int count_sales(){

        int r_c = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select count(prod_id) from amount_pr_s inner join products on products.prod_id = amount_pr_s.as_prod_id inner join manufacturer on manuf_id = products.p_manuf_id where p_show like 'try' ;";
            ResultSet resSet = statement.executeQuery(sql_code);
            while (resSet.next()) {
                r_c = resSet.getInt("count(prod_id)");
            }
        //   r_c += -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
           System.out.println(r_c + "orders");
        return r_c;
    }

    public String[][] gen_zhachenia_hist(int r_c){

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "Select prod_id, p_shtrih, p_code, p_articul, p_name, p_price, p_price_stom, sum(as_amount) as sss, manuf_name from amount_pr_s\n" +
                    "inner join products on products.prod_id = amount_pr_s.as_prod_id inner join manufacturer on manuf_id = products.p_manuf_id where p_show like 'try' group by as_prod_id ;";
            ResultSet resSet = statement.executeQuery(sql_code);

            String[][] result_h =new String[r_c][9];

            int i = 0;
            while (resSet.next()) {
                result_h[i][0] = String.valueOf(resSet.getInt("prod_id"));
                result_h[i][1] = resSet.getString("p_shtrih");
                result_h[i][2] = resSet.getString("p_code");
                result_h[i][3] = resSet.getString("p_articul");
                result_h[i][4] = resSet.getString("p_name");
                result_h[i][5] = resSet.getString("p_price");
                result_h[i][6] = resSet.getString("p_price_stom");

                result_h[i][8] = resSet.getString("sss");
                result_h[i][7] = resSet.getString("manuf_name");

                i+=1;
            }

            return result_h;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] rest_h = {{}};
        return rest_h;
    }


}
