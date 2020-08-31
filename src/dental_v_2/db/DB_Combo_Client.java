package dental_v_2.db;



import java.sql.*;

public class DB_Combo_Client extends DBProcessor {

    public String [] combo_class(){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select class_id from class";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "select c_name from class order by c_name desc ";
            resSet = statement.executeQuery(sql_code);
            int i =0;
            while (resSet.next()) {
                result [i] = resSet.getString("c_name");
                i+=1;
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        String[] rest = {};
        return rest;
    }


    public int poisk_class(String clas){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select class_id from class where c_name = '"+clas+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c = resSet.getInt("class_id") ;
            }


            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return k;
    }


}
