package dental_v_2.view;


import dental_v_2.controllers.File_Nakladnaya_Controller;
import dental_v_2.controllers.Order_Product_Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Order_product_form {
    Order_Product_Controller controller = new Order_Product_Controller();
    File_Nakladnaya_Controller controller_f = new File_Nakladnaya_Controller();
public int id_user = 0;
    public JButton b_back = new JButton("Назад");
    public JButton b_create_o = new JButton("Утв");
    public JButton b_file = new JButton("file");
    public JButton b_mail = new JButton("Отправить");

    JButton b_del = new JButton("Удалить");
    public JButton b_update = new JButton("Изменить");
  //  JTextField t_search = new JTextField("Поиск товара....");
  //  String poisk = "";
    public String[][] mas_action;
    public String[] head = {"Название", "Цена", "Кол-во"};
    public int[] mas_id;
    JPanel panel = new JPanel();
    public JTable table_action = new JTable();
    public JScrollPane scrollPane = new JScrollPane();
    public int id_order = 0;
    int sum = 0;
    public JLabel l_sum = new JLabel("Сумма заказа: " + sum + " грн");
    public String[] mass_vid_o = {"Наличные", "Безнал", "Безнал+"};

    //  JButton b_seach = new JButton("Ok");


    public JPanel o_p_JPanel(){


        panel.setLayout(null);
        panel.setBounds(0, 120, 1300, 700);
        try {
            show(id_order);
            table_action = new  JTable(mas_action,head);
            table_action.setBounds(0, 100, 1300, 250);
            panel.add(table_action);
            scrollPane = new JScrollPane(table_action);
            scrollPane.setBounds(150, 100, 1000, 300);
            panel.add(scrollPane);



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        b_back.setBounds(300, 420, 150, 50);
        panel.add(b_back);
        b_create_o.setBounds(780, 420, 150, 50);
        b_create_o.setVisible(false);
        panel.add(b_create_o);
        b_mail.setBounds(1120, 420, 150, 50);
        b_mail.setVisible(false);
        panel.add(b_mail);
        b_file.setBounds(940, 420, 150, 50);
        b_file.setVisible(false);
        panel.add(b_file);
        l_sum.setBounds(10, 30, 300, 40);

//        panel.remove(l_sum);


        b_update.setBounds(460, 420, 150, 50);
        panel.add(b_update);

        b_del.setBounds(620, 420, 150, 50);
        panel.add(b_del);
//        t_search.setBounds(15, 40, 125, 30);
//        t_search.setVisible(false);
//        panel.add(t_search);
//        b_seach.setBounds(155, 40, 35, 30);
//        b_seach.setVisible(false);
//        panel.add(b_seach);
        JComboBox c_opl = new JComboBox(mass_vid_o);
        c_opl.setBounds(560, 35, 200, 30);
        panel.add(c_opl);

        c_opl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_create_o.setVisible(true);

            }
        });



        b_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [][] mas_update = new String[mas_id.length][4];
                for (int i = 0; i < mas_id.length; i++){
                    mas_update[i][0] = String.valueOf(mas_id[i]);
                    mas_update[i][1] = (String) table_action.getValueAt(i,0);
                    mas_update[i][2] = (String) table_action.getValueAt(i,1);
                    mas_update[i][3] = (String) table_action.getValueAt(i,2);




                }
//                System.out.println(mas_update[0][0]);
//                System.out.println(mas_update[0][1]);
//                System.out.println(mas_update[0][2]);
//                System.out.println(mas_update[0][3]);
//
                try {
                    update_h(mas_update, id_order);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.remove(scrollPane);
                panel.remove(l_sum);
                panel.revalidate();
                panel.repaint();
                try {
                    show(id_order);
                    table_action = new  JTable(mas_action,head);
                    table_action.setBounds(0, 100, 1300, 250);
                    panel.add(table_action);
                    scrollPane = new JScrollPane(table_action);
                    scrollPane.setBounds(150, 100, 1000, 300);
                    panel.add(scrollPane);
                    sum = poisk_summa(id_order);
                    l_sum = new JLabel("Сумма заказа: "+sum + " грн" );
                    l_sum.setBounds(10, 30, 300, 40);
                    panel.add(l_sum);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
//                try {
//
//                    order_create_form.poisk_summa(id_order);
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }


                panel.repaint();


            }
        });




        b_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_del = Integer.parseInt(String.valueOf(mas_id[table_action.getSelectedRow()]));
                try {
                    del_h(id_del);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.remove(scrollPane);
                panel.revalidate();
                panel.repaint();
                try {
                    show(id_order);
                    table_action = new  JTable(mas_action,head);
                    table_action.setBounds(0, 100, 1300, 250);
                    panel.add(table_action);
                    scrollPane = new JScrollPane(table_action);
                    scrollPane.setBounds(150, 100, 1000, 300);
                    panel.add(scrollPane);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
//                try {
//
//                    order_create_form.poisk_summa(id_order);
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }


                panel.repaint();



            }
        });

        b_create_o.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [][] mas_create = new String[mas_id.length][4];
                for (int i = 0; i < mas_id.length; i++){
                    mas_create[i][0] = String.valueOf(mas_id[i]);
                    mas_create[i][1] = (String) table_action.getValueAt(i,0);
                    mas_create[i][2] = (String) table_action.getValueAt(i,1);
                    mas_create[i][3] = (String) table_action.getValueAt(i,2);




                }
//                System.out.println(mas_update[0][0]);
//                System.out.println(mas_update[0][1]);
//                System.out.println(mas_update[0][2]);
//                System.out.println(mas_update[0][3]);

                String vid_opl = (String) c_opl.getSelectedItem();
//
                try {
                    create_n(mas_create, id_order, id_user, vid_opl);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                b_create_o.setVisible(false);
                b_mail.setVisible(true);
                b_file.setVisible(true);


            }
        });

        b_mail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n_id_m(id_order);
            }
        });
        b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_mail.setVisible(false);
                b_file.setVisible(false);
            }
        });


//        b_file.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String[][]danni;
//                LocalDate dt = LocalDate.now();
//
//                String name = "C:/" +dt+".txt";
////                File file = new File(name);
////                if (!file.exists()){
////                    try {
////                        file.createNewFile();
////                    } catch (IOException ex) {
////                        ex.printStackTrace();
////                    }
////                }
//
//                try {
////                    FileWriter fileWriter = new FileWriter(file);
//                    danni = poisk_danni();
////                    int i=0;
////                    fileWriter.write("nksjndksjandk");
////                    for (i=0; i<2; i++){
////                        fileWriter.write(i + " nn " + (danni[i][0]) + " s " + (danni[i][1]) + " t " + (danni[i][2]) + " p "
////                        + (danni[i][3]) + " " + (danni[i][4]) + " грн \n");
////                        System.out.println(danni[i][0]);
////                        System.out.println(danni[i][1]);
////                        System.out.println(danni[i][2]);
////                        System.out.println(danni[i][3]);
////                        System.out.println(danni[i][4]);
//
//                 //   }
////                    fileWriter.flush();
////                    fileWriter.close();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//
//
//            }
//        });
//
//        t_search.addMouseListener(new MouseAdapter(){
//            public void mouseClicked (MouseEvent event){
//                t_search.setText(null);
//
//            }
//        });
//
//        b_seach.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                poisk = t_search.getText();
//
//                try {
//                    show();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//
//
//            }
//        });
b_file.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        n_id(id_order);


    }
});


        return panel;
    }

    public int count(int id_order){
        return controller.count_history(id_order);
    }
//    public String[][] poisk_danni(){
//        return controller.poisk_danni();
//    }

    public void show(int id_order)throws SQLException, ClassNotFoundException{
        String[][] res = controller.history_action(id_order);
        int count = count(id_order);
        mas_id = new int[count];
        mas_action = new String[count][4];
        for (int i = 0; i < count; i++){
            mas_id[i] = Integer.parseInt(res[i][0]);
            for (int j = 0; j < 3; j++){
                mas_action[i][j] = res[i][j+1];
            }
        }
    }

    public int poisk_summa (int id_order){
        return sum = controller.poisk_summa(id_order);
    }



    //
    public void update_h(String[][] mass, int id_order)throws SQLException, ClassNotFoundException{
        controller.update_h(mass, id_order);
    }
    public void del_h (int id_del) throws SQLException, ClassNotFoundException{
        controller.del_h(id_del);
    }
    public void create_n(String[][] mass, int id_order, int id_user, String vid_opl)throws SQLException, ClassNotFoundException{
        controller.create_n(mass, id_order, id_user, vid_opl);
    }
    public void n_id (int id_order){
        controller_f.file_db(id_order);
    }
    public void n_id_m (int id_order){
        controller_f.file_db_mail(id_order);
    }



}
