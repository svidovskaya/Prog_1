package dental_v_2.view;


import dental_v_2.controllers.All_Clients_Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class All_clients_form {
    All_Clients_Controller controller = new All_Clients_Controller();

    public JButton b_back = new JButton("Назад");
  //  public JButton b_create_o = new JButton("Создать продукт");
    JButton b_del = new JButton("Удалить");
    public String[][] mas_action;
    public String[] head = {"Тип цен", "Фамилия", "Имя","Отчество","Телефон","e-mail", "Комментарий"};
    public int[] mas_id;
    JPanel panel = new JPanel();
    public JTable table_action = new JTable();
    public JScrollPane scrollPane = new JScrollPane();
    int mail = 0;


//    public String[] cbd = {"Тип цен", "Фамилия", "Имя","Отчество","Телефон","e-mail", "Комментарий"};
//
//    JComboBox cb = new JComboBox(cbd);

    public JPanel all_product_JPanel(){


        panel.setLayout(null);
        panel.setBounds(0, 120, 1300, 700);
        try {
            show();
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

        JButton b_update = new JButton("Изменить");
        b_update.setBounds(640, 420, 150, 50);
        panel.add(b_update);

        b_del.setBounds(980, 420, 150, 50);
        panel.add(b_del);


        b_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [][] mas_update = new String[mas_id.length][8];
                for (int i = 0; i < mas_id.length; i++) {
                    mas_update[i][0] = String.valueOf(mas_id[i]);
                    mas_update[i][1] = (String) table_action.getValueAt(i, 0);
                    mas_update[i][2] = (String) table_action.getValueAt(i, 1);
                    mas_update[i][3] = (String) table_action.getValueAt(i, 2);
                    mas_update[i][4] = (String) table_action.getValueAt(i, 3);
                    mas_update[i][5] = (String) table_action.getValueAt(i, 4);
                    mas_update[i][6] = (String) table_action.getValueAt(i, 5);
                    mas_update[i][7] = (String) table_action.getValueAt(i, 6);


                    if (mas_update[i][5].length() != 11) {

                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "Номер телефона состоит из 11 цифр!");
                    }
                    if (mas_update[i][1].equals("р") || mas_update[i][1].equals("розница")) {
                        mas_update[i][1] = "розница";
                    } else if (mas_update[i][1].equals("о") || mas_update[i][1].equals("опт")) {
                        mas_update[i][1] = "опт";
                    } else {
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "Такого класса покупателя не существует!");
                    }
                    Pattern pattern = Pattern.compile("^((\\w|[-+])+(\\.[\\w-]+)*@[\\w-]+((\\.[\\d\\p{Alpha}]+)*(\\.\\p{Alpha}{2,})*)*)$");
                    Matcher matcher = pattern.matcher(mas_update[i][6]);


                    while (matcher.find()) {
                        mail += 1;

                    }

                }





                if (mail != mas_id.length) {

                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Введите верный email");
                    mail = 0;
                } else {

                    try {
                        update_h(mas_update);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

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
                    show();
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

                panel.repaint();

            }
        });


        return panel;
    }

    public int count(){
        return controller.count_history();
    }


    public void show()throws SQLException, ClassNotFoundException{
        String[][] res = controller.history_action();
        int count = count();
        mas_id = new int[count];
        mas_action = new String[count][8];



        //  System.out.println(table_action.getColumnName(0));
//         Object a = table_action.getColumnName(0);
//           table_action.getColumn(a).setCellEditor(new DefaultCellEditor(cb));

            for (int i = 0; i < count; i++){

                mas_id[i] = Integer.parseInt(res[i][0]);
              //  mas_action[i][0] = String.valueOf(cb);
                for (int j = 0; j < 6; j++){

                    mas_action[i][j] = res[i][j+1];
                }
            }
            //table_action.getColumn(0).setCellEditor(new DefaultCellEditor(cb));

        }


    public void update_h(String[][] mass)throws SQLException, ClassNotFoundException{
        controller.update_h(mass);
    }

    public void del_h (int id_del) throws SQLException, ClassNotFoundException{
        controller.del_h(id_del);
    }

}
