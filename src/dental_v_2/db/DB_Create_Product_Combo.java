package dental_v_2.db;

import java.sql.*;

public class DB_Create_Product_Combo extends DBProcessor{
    public String [] combo_manuf(){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select manuf_id from manufacturer";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "select manuf_name from manufacturer";
            resSet = statement.executeQuery(sql_code);
            int i = 0;
            while (resSet.next()) {
                result [i] = resSet.getString("manuf_name");
                i+=1;
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] rest = {};
        return rest;
    }


    public int poisk_manuf(String manuf){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select manuf_id from manufacturer where manuf_name = '"+manuf+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c = resSet.getInt("manuf_id") ;
            }



            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return k;
    }

}
