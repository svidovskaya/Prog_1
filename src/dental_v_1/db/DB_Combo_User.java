package dental_v_1.db;


import java.sql.*;

public class DB_Combo_User extends DBProcessor{
public String URL = "";
    public String [] combo_position(){

System.out.println(URL + "db_combo_user");
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select position_id from positions ";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "select pos_name from positions";
            resSet = statement.executeQuery(sql_code);
            int i =0;
            while (resSet.next()) {
                result [i] = resSet.getString("pos_name");
                i+=1;
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] rest = {};
        return rest;
    }

    public int poisk_pos(String pos){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select position_id from positions where pos_name = '"+pos+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c = resSet.getInt("position_id") ;
            }


            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return k;
    }


}
