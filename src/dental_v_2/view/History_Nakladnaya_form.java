package dental_v_2.view;


import dental_v_2.controllers.History_Nakladnaya_Controler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class History_Nakladnaya_form {
    History_Nakladnaya_Controler controller = new History_Nakladnaya_Controler();

    public JButton b_back = new JButton("Назад");
  //  public JButton b_create_o = new JButton("Создать заказ");
 //   JButton b_del = new JButton("Удалить");
    String vid_o = "";
    public String[][] mas_action;
    public String[] head = {"№", "Дата", "Время",  "Тип цены", "Сумма", "Клиент","Ответственный", "Вид оплаты", "Коммент"};
    public String[] mass_vid_opl = {"Все","Наличные", "Безнал", "Безнал+"};
    public int[] mas_id;
    JPanel panel = new JPanel();
   public JTable table_action = new JTable();
   public JScrollPane scrollPane = new JScrollPane();
   public JComboBox c_vid_opl = new JComboBox(mass_vid_opl);
  // JButton r_b = new JButton("Обновить");

    public JPanel history_JPanel(){



        panel.setLayout(null);
        panel.setBounds(0, 120, 1300, 700);
        try {
            show("");
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

//        b_create_o.setBounds(810, 420, 150, 50);
//        panel.add(b_create_o);

        JButton b_show = new JButton("Инфо");
        b_show.setBounds(470, 420, 150, 50);
        panel.add(b_show);

        c_vid_opl.setBounds(0, 0, 100, 40);
        panel.add(c_vid_opl);

    /*    JButton b_show_del = new JButton("Доставка");
        b_show_del.setBounds(130, 420, 150, 50);
      //  b_show_del.setVisible(false);
        panel.add(b_show_del);
*/
//        r_b.setBounds(20,0,150,50);
//        r_b.setVisible(false);
//        panel.add(r_b);
/*
        b_del.setBounds(980, 420, 150, 50);
        panel.add(b_del);
*/
        JButton b_update = new JButton("Изменить");
        b_update.setBounds(640, 420, 150, 50);
        panel.add(b_update);

//        r_b.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panel.remove(scrollPane);
//                panel.revalidate();
//                panel.repaint();
//                try {
//                    show();
//                    table_action = new  JTable(mas_action,head);
//                    table_action.setBounds(0, 100, 1300, 250);
//                    panel.add(table_action);
//                    scrollPane = new JScrollPane(table_action);
//                    scrollPane.setBounds(150, 100, 1000, 300);
//                    panel.add(scrollPane);
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//
//                panel.repaint();
//            }
//        });

        b_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_inform = Integer.parseInt(String.valueOf(mas_id[table_action.getSelectedRow()]));

                try {
                    show_iformation(id_inform);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
/*
        b_show_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_inform = Integer.parseInt(String.valueOf(mas_id[table_action.getSelectedRow()]));

                try {
                    show_delivery(id_inform);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        b_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [][] mas_update = new String[mas_id.length][2];
                for (int i = 0; i < mas_id.length; i++){
                    mas_update[i][0] = String.valueOf(mas_id[i]);
                    mas_update[i][1] = (String) table_action.getValueAt(i,5);

                }
                try {
                    update_h(mas_update);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });


 */
//        b_del.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int id_del = Integer.parseInt(String.valueOf(mas_id[table_action.getSelectedRow()]));
//                try {
//                    del_h(id_del);
//
//
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//                panel.remove(scrollPane);
//                panel.revalidate();
//                panel.repaint();
//                try {
//                    show();
//                    table_action = new  JTable(mas_action,head);
//                    table_action.setBounds(0, 100, 1300, 250);
//                    panel.add(table_action);
//                    scrollPane = new JScrollPane(table_action);
//                    scrollPane.setBounds(150, 100, 1000, 300);
//                    panel.add(scrollPane);
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//
//                panel.repaint();
//
//            }
//        });
        b_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [][] mas_update = new String[mas_id.length][2];
                for (int i = 0; i < mas_id.length; i++){
                    mas_update[i][0] = String.valueOf(mas_id[i]);
                    mas_update[i][1] = (String) table_action.getValueAt(i,8);

                }
                try {
                    update_h(mas_update);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });

        c_vid_opl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vid_o = String.valueOf(c_vid_opl.getSelectedItem());
                if (vid_o.equals("Все")){
                    vid_o = "";
                }

                try {
                    show(vid_o);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                panel.remove(scrollPane);
                table_action = new  JTable(mas_action,head);
                table_action.setBounds(0, 100, 1300, 250);
                panel.add(table_action);
                scrollPane = new JScrollPane(table_action);
                scrollPane.setBounds(150, 100, 1000, 300);
                panel.add(scrollPane);
                panel.repaint();
            }
        });

        return panel;
    }

    public int count(String poisk){
        return controller.count_history(poisk);
    }

    public void show_iformation(int id)throws SQLException, ClassNotFoundException{
        controller.show_information(id);
    }
  /*
    public void show_delivery(int id)throws SQLException, ClassNotFoundException{
        controller.show_delivery(id);
    }
*/
    public void show(String poisk)throws SQLException, ClassNotFoundException{
        String[][] res = controller.history_action(poisk);
        int count = count(poisk);
        mas_id = new int[count];
        mas_action = new String[count][9];
        for (int i = 0; i < count; i++){
            mas_id[i] = Integer.parseInt(res[i][0]);
            for (int j = 0; j < 9; j++){
                mas_action[i][j] = res[i][j];
            }
        }
    }

    public void update_h(String[][] mass)throws SQLException, ClassNotFoundException{
        controller.update_h(mass);
    }
//
//    public void del_h (int id_del) throws SQLException, ClassNotFoundException{
//        controller.del_h(id_del);
//    }

}
