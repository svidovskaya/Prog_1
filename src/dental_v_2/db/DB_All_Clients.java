package dental_v_2.db;



import java.sql.*;

public class DB_All_Clients extends DBProcessor {
    public int count_sales(){

        int r_c = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select count(client_id) \n" +
                    "from clients inner join client_class on client_id = cc_client_id \n" +
                    "inner join class on class.class_id = client_class.cc_class_id \n" +
                    "where cl_show like 'try'";
            ResultSet resSet = statement.executeQuery(sql_code);
            while (resSet.next()) {
                r_c = resSet.getInt("count(client_id)");
            }
          // r_c += -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
          //  System.out.println(r_c + "orders");
        return r_c;
    }

    public String[][] gen_zhachenia_hist(int r_c){

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select client_id, c_info, cl_surname, cl_name, clients.cl_middle_name, cl_number, cl_email, cl_comment\n" +
                    "from clients inner join client_class on client_id = cc_client_id \n" +
                    "inner join class on class.class_id = client_class.cc_class_id \n" +
                    "where cl_show like 'try'";
            ResultSet resSet = statement.executeQuery(sql_code);

            String[][] result_h =new String[r_c][8];

            int i = 0;
            while (resSet.next()) {
                result_h[i][0] = String.valueOf(resSet.getInt("client_id"));
                result_h[i][1] = resSet.getString("c_info");
                result_h[i][2] = resSet.getString("cl_surname");
                result_h[i][3] = resSet.getString("cl_name");
                result_h[i][4] = resSet.getString("cl_middle_name");
                result_h[i][5] = resSet.getString("cl_number");
                result_h[i][6] = resSet.getString("cl_email");
                result_h[i][7] = resSet.getString("cl_comment");


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
