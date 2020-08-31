package dental_v_2.db;



import java.sql.*;

public class DB_Combo_Prod_in_Stock extends DBProcessor {

    public String [] combo_prod(String poisk){

        poisk = "%" + poisk +  "%";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "SELECT products.prod_id\n" +
                    "from products inner join manufacturer on manufacturer.manuf_id = products.p_manuf_id\n" +
                                    "\n" +
                    "where products.p_name like '"+poisk+"' or manufacturer.manuf_name like '"+poisk+"' ";

            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "SELECT products.prod_id, products.p_name\n" +
                    "from products inner join manufacturer on manufacturer.manuf_id = products.p_manuf_id\n" +

                    "where products.p_name like '"+poisk+"' or manufacturer.manuf_name like '"+poisk+"' ";
            resSet = statement.executeQuery(sql_code);
            int i =0;
            while (resSet.next()) {
                result [i] = resSet.getString("p_name");



                i+=1;
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] rest = {};
        return rest;
    }

    public String [] combo_prod_1(String poisk){

        poisk = "%" + poisk +  "%";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "SELECT products.prod_id\n" +
                    "from products \n" +
                    "\n" +
                    "where products.p_shtrih like '"+poisk+"' or p_code like '"+poisk+"' or p_articul like '"+poisk+"' ";

            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "SELECT products.prod_id, products.p_name\n" +
                    "from products \n" +

                    "where products.p_shtrih like '"+poisk+"' or p_code like '"+poisk+"' or p_articul like '"+poisk+"' ";
            resSet = statement.executeQuery(sql_code);
            int i =0;
            while (resSet.next()) {
                result [i] = resSet.getString("p_name");



                i+=1;
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] rest = {};
        return rest;
    }

    public int poisk_prod(String product){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select prod_id from products where p_name = '"+product+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c = resSet.getInt("prod_id") ;
            }


            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return k;
    }
}
