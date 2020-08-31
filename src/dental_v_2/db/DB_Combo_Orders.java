package dental_v_2.db;



import java.sql.*;

public class DB_Combo_Orders extends DBProcessor {
    public String [] combo_platforms(){

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select platform_id from platforms";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "select p_name from platforms order by p_name desc ";
            resSet = statement.executeQuery(sql_code);
            int i = 0;
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

            sql_code = "select c_name from class";
            resSet = statement.executeQuery(sql_code);
            int i =0;
            while (resSet.next()) {
                result [i] = resSet.getString("c_name");
                i+=1;
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] rest = {};
        return rest;
    }

    public String [] combo_deliv(){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select delivery_m_id from delivery_method";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "select deliv_m_name from delivery_method";
            resSet = statement.executeQuery(sql_code);
            int i =0;
            while (resSet.next()) {
                result [i] = resSet.getString("deliv_m_name");
                i+=1;
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] rest = {};
        return rest;
    }

    public String [] combo_clients(String clas){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select client_id, cl_surname, cl_name from clients inner join client_class  on client_id = cc_client_id\n" +
                    "inner join class on cc_class_id = class_id \n" +
                    "where class.c_name like '"+clas+"' and cl_show like 'try' "  +
                    "group by clients.client_id";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "select client_id, cl_surname, cl_name from clients inner join client_class  on client_id = cc_client_id\n" +
                    "inner join class on cc_class_id = class_id \n" +
                    "where class.c_name like '"+clas+"' and cl_show like 'try' "  +
                    "group by clients.client_id";
            resSet = statement.executeQuery(sql_code);
            int i =0;
            while (resSet.next()) {
                result [i] = resSet.getString("cl_surname") + " "+  resSet.getString("cl_name");
                i+=1;
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] rest = {};
        return rest;
    }
    public String [] combo_prod(String poisk){

        poisk = "%" + poisk +  "%";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "SELECT products.prod_id\n" +
                    "from products inner join manufacturer on manufacturer.manuf_id = products.p_manuf_id\n" +

                    "where products.p_name like '"+poisk+"' or manufacturer.manuf_name like '"+poisk+"' and p_show like 'try'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c += 1;
            }
            String[] result =new String[c];

            sql_code = "SELECT products.p_name\n" +
                    "from products inner join manufacturer on manufacturer.manuf_id = products.p_manuf_id\n" +

                    "where products.p_name like '"+poisk+"' or manufacturer.manuf_name like '"+poisk+"' and p_show like 'try'";
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


    public int poisk_platforms(String platforms){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select platform_id from platforms where p_name = '"+platforms+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c = resSet.getInt("platform_id") ;
            }


            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return k;
    }
    public int poisk_delivery(String delivery){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select delivery_m_id from delivery_method where deliv_m_name = '"+delivery+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c = resSet.getInt("delivery_m_id") ;
            }


            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return k;
    }

    public int poisk_client(String client_surname, String client_name){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select client_id from clients where cl_surname = '"+client_surname+"' and cl_name = '"+client_name+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            while (resSet.next()) {
                c = resSet.getInt("client_id") ;
            }


            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return k;
    }
    public String[] poisk_prod(String product){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select prod_id, p_price, p_price_stom from products where p_name = '"+product+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;
            String[] inf = new String[3];

            while (resSet.next()) {
             //   c = resSet.getInt("prod_id");
                inf[0] = String.valueOf(resSet.getInt("prod_id"));
                inf[1] = String.valueOf(resSet.getInt("p_price"));
                inf[2] = String.valueOf(resSet.getInt("p_price_stom"));
            }

            return inf;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new String[0];
    }

    public int poisk_clas(String clas){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select class_id from class where c_name = '"+clas+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c =0;

            while (resSet.next()) {
                c = resSet.getInt("class_id");
            }

            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return k;
    }

    public String poisk_price(String id_prod, int clas){


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql_code = "select p_price from products where p_name = '"+id_prod+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
            String  c = "";

            while (resSet.next()) {
                c = resSet.getString("p_price");
            }

            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        return String.valueOf(k);
    }

}
