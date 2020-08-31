package dental_v_2.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class DB_File extends DBProcessor {

    public String[][] poisk_danni() {
int r_c =0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code_1 = "Select count(info_nakl.i_nakladnaya) as sss from info_nakl inner join nakladnaya on i_nakladnaya = nakladnaya.nakladnaya_id\n" +
                    "inner join ord_nak on ord_nak.on_nakladnaya_id = nakladnaya_id\n" +
                    "inner join orders on ord_nak.on_order_id = orders.order_id\n" +
                    "inner join clients on clients.client_id = orders.o_client_id\n" +
                    "where n_data = date(now());";
            ResultSet resSet1 = statement.executeQuery(sql_code_1);
            int i = 0;

            while (resSet1.next()) {
                r_c = resSet1.getInt("sss");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        String[][] result_h = new String[r_c][6];

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {


            String sql_code2 = "Select sum(i_price*i_amount) as ssss from info_nakl inner join nakladnaya on i_nakladnaya = nakladnaya.nakladnaya_id\n" +
                    "inner join ord_nak on ord_nak.on_nakladnaya_id = nakladnaya_id\n" +
                    "inner join orders on ord_nak.on_order_id = orders.order_id\n" +
                    "inner join clients on clients.client_id = orders.o_client_id\n" +
                    "where n_data = date(now()) group by nakladnaya.nakladnaya_id;";
            ResultSet resSet2 = statement.executeQuery(sql_code2);

            int i=0;

            LocalDate dt = LocalDate.now();

//            String x = "C:/192.168.1.4/";

            String name = "C:/" +dt+".txt";
          //  String name = x +dt+".txt";
            File file = new File(name);
       //    System.out.println(file.getAbsolutePath());
            FileWriter fileWriter = new FileWriter(file);


            String sql_code = "Select info_nakl.i_nakladnaya, cl_surname, cl_number, i_p_name, i_price, n_summa from info_nakl inner join nakladnaya on i_nakladnaya = nakladnaya.nakladnaya_id\n" +
                    "inner join ord_nak on ord_nak.on_nakladnaya_id = nakladnaya_id\n" +
                    "inner join orders on ord_nak.on_order_id = orders.order_id\n" +
                    "inner join clients on clients.client_id = orders.o_client_id\n" +
                    "where n_data = date(now());";
            ResultSet resSet = statement.executeQuery(sql_code);
//            int zn_n = 0;
//            Float s = Float.valueOf(0);
            while (resSet.next()) {



                result_h[i][0] = String.valueOf(resSet.getInt("i_nakladnaya"));
                result_h[i][1] = resSet.getString("cl_surname");
                result_h[i][2] = resSet.getString("cl_number");
                result_h[i][3] = resSet.getString("i_p_name");
                result_h[i][4] = resSet.getString("i_price");
                result_h[i][5] = resSet.getString("n_summa");

                fileWriter.write(i+1 + " nn: " + (result_h[i][0]) + " s: " + (result_h[i][1]) + " t: " + (result_h[i][2]) + " p: "
                  + (result_h[i][3]) + " " + (result_h[i][4]) + " грн \n");

                   fileWriter.write("" + (result_h[i][5]) + " грн \n" );

//                if (result_h[i][0].equals(String.valueOf(zn_n))){
//
//                    String k = result_h[i][4];
//                    float kk = Float.valueOf(k);
//                    System.out.println(kk);
//
//                    s+= kk;
//
//                    System.out.println(s);
//                    String ss = String.valueOf(s);
//                  //  fileWriter.write("Сумма накладной " + ss + " грн");
//                    fileWriter.write(i+1 + " nn: " + (result_h[i][0]) + " s: " + (result_h[i][1]) + " t: " + (result_h[i][2]) + " p: "
//                            + (result_h[i][3]) + " " + (result_h[i][4]) + " грн \n");
//
//
//                } else {
//                    fileWriter.write("\n");
//                    fileWriter.write("Сумма накладной " + s + " грн \n");
//
//                    s= Float.valueOf(0);
////                    fileWriter.write(i+1 + " nn: " + (result_h[i][0]) + " s: " + (result_h[i][1]) + " t: " + (result_h[i][2]) + " p: "
////                            + (result_h[i][3]) + " " + (result_h[i][4]) + " грн \n");
//
//
//                    s= s;
//                }
//                String r = "null";
//                if ((result_h[i][5]).equals(null)){
//                    fileWriter.write("" + (result_h[i][5]) + " грн" );
//                    fileWriter.flush();
//                } else {
//                    fileWriter.write("\n");
//                }
               // fileWriter.write("" + (result_h[i][5]) + " грн" );




                fileWriter.flush();

             //   zn_n= Integer.parseInt(result_h[i][0]);

                i += 1;



            }
           // fileWriter.write("Сумма накладной " + s + " грн \n");


            fileWriter.close();



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result_h;
    }
}
