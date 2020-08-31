package dental_v_2.view;


import dental_v_2.controllers.All_Products_Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class All_products_form {
    All_Products_Controller controller = new All_Products_Controller();

    public JButton b_back = new JButton("Назад");
  //  public JButton b_create_o = new JButton("Создать продукт");
    JButton b_del = new JButton("Удалить");
    JTextField t_search = new JTextField("Поиск товара....");
    String poisk = "";
    public String[][] mas_action;
    public String[] head = {"Штрих-код","Код", "Артикул", "Название", "Цена","Цена стоматолога",  "Прооизводитель"};
    public int[] mas_id;
    JPanel panel = new JPanel();
    public JTable table_action = new JTable();
    public JScrollPane scrollPane = new JScrollPane();
    JButton b_seach = new JButton("Ok");


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
        t_search.setBounds(15, 40, 125, 30);
        t_search.setVisible(false);
        panel.add(t_search);
        b_seach.setBounds(155, 40, 35, 30);
        b_seach.setVisible(false);
        panel.add(b_seach);




        b_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [][] mas_update = new String[mas_id.length][7];
                int a = 0;
                for (int i = 0; i < mas_id.length; i++){
                    mas_update[i][0] = String.valueOf(mas_id[i]);
                    mas_update[i][1] = (String) table_action.getValueAt(i,0);
                    mas_update[i][2] = (String) table_action.getValueAt(i,1);
                    mas_update[i][3] = (String) table_action.getValueAt(i,2);
                    mas_update[i][4] = (String) table_action.getValueAt(i,3);
                    mas_update[i][5] = (String) table_action.getValueAt(i,4);
                    mas_update[i][6] = (String) table_action.getValueAt(i,5);
                  //  int sh = Integer.parseInt(mas_update[1][1]);
                    if (mas_update[i][2].length()<3 ||mas_update[i][4].length()<5){
                        a=1;
                    }
                 if ((mas_update[i][1].equals(""))){
                     a=0;
                    }

                    float pr = Float.parseFloat(mas_update[i][5]);
                    float pr_s = Float.parseFloat(mas_update[i][6]);

                    if (pr<0 || pr_s<0){
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "Введите корректную цену");
                        a=1;
                    }


                }


                try {
                    if (a==0){
                    update_h(mas_update);

                    } else {
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "Введите корректно данные");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
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

        t_search.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                t_search.setText(null);

            }
        });

        b_seach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poisk = t_search.getText();

                try {
                    show();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


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
        mas_action = new String[count][9];
        for (int i = 0; i < count; i++){
            mas_id[i] = Integer.parseInt(res[i][0]);
            for (int j = 0; j < 8; j++){
                mas_action[i][j] = res[i][j+1];
            }
        }
    }

    public void update_h(String[][] mass)throws SQLException, ClassNotFoundException{
        controller.update_h(mass);
    }
    public void del_h (int id_del) throws SQLException, ClassNotFoundException{
        controller.del_h(id_del);
    }


}
