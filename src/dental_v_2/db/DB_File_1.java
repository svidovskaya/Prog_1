package dental_v_2.db;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class DB_File_1 extends DBProcessor {

    public String[][] poisk_danni() throws IOException {
int r_c =0;
int[] mass_id ;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code_1 = "Select count(nakladnaya_id) as sss from nakladnaya\n" +
                    "                    where n_data = date(now())";
            ResultSet resSet1 = statement.executeQuery(sql_code_1);
            int i = 0;

            while (resSet1.next()) {
                r_c = resSet1.getInt("sss");
            }


            if (r_c != 0){
                String sql_code = "Select nakladnaya_id from nakladnaya\n" +
                        "                    where n_data = date(now())";
                ResultSet resSet = statement.executeQuery(sql_code);
             int   a = 0;
                mass_id = new int [r_c];
                while (resSet.next()) {
                    mass_id[a]=resSet.getInt("nakladnaya_id");

                    a++;
                }
                LocalDate dt = LocalDate.now();
                String name = "C:/" +dt+".txt";
                File file = new File(name);
                FileWriter fileWriter = new FileWriter(file);

                fileWriter.write("Отчёт за " + dt);
                fileWriter.write("\n \n");
               // String danni = "";
                for (int b=0; b<r_c; b++){

                        int pr = 0;

                   int m = mass_id[b];
                    String sql_code_2 = "Select nakladnaya_id, n_vid_opl, n_summa, o_comment, cl_surname, cl_name from nakladnaya \n" +
                            "inner join ord_nak on nakladnaya_id = on_nakladnaya_id\n" +
                            "inner join orders on order_id = on_order_id\n" +
                            "inner join clients on orders.o_client_id = clients.client_id \n" +
                            "where nakladnaya_id = '"+m+"'";

                    ResultSet resSet_2 = statement.executeQuery(sql_code_2);
                    while (resSet_2.next()){
                        fileWriter.write("№ "+resSet_2.getInt("nakladnaya_id")+"   Клиент: "+ resSet_2.getString("cl_surname")+" "+resSet_2.getString("cl_name"));
                        fileWriter.write("   Cумма: "+ resSet_2.getInt("n_summa")+" грн    \n Вид оплаты: " + resSet_2.getString("n_vid_opl") + " Ответств.: " +resSet_2.getString("o_comment") );
                        fileWriter.write("\n \n");
                        pr=1;
                    }
                    if(pr==1){
                        String sql_code_3 = "Select i_amount, i_p_name, i_price from info_nakl \n" +

                                "where i_nakladnaya = '"+m+"'";

                        ResultSet resSet_3 = statement.executeQuery(sql_code_3);
int k = 0;
                        while (resSet_3.next()){
                            k+=1;
                            float am = resSet_3.getInt("i_amount");
                            float cen = resSet_3.getInt("i_price");
                            float su = am*cen;
                            fileWriter.write(k + " "+(resSet_3.getString("i_p_name")) + " " + am +" шт. " + cen + " сумма:" + su + " грн \n");
                            fileWriter.flush();

                        }
                        fileWriter.write("\n \n ______________________________________________________________________ \n");
                    }
                    fileWriter.flush();
                }
                fileWriter.flush();
                fileWriter.close();

                Desktop.getDesktop().edit(file);
            } else {System.out.println("Нет продаж");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
String[][]r = {{}};


        return r;
    }
}
