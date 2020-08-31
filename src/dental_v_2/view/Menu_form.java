package dental_v_2.view;

import javax.swing.*;

public class Menu_form {


    public JButton b_history_nakl = new JButton("Накладные");
    public JButton b_create_order = new JButton("Создать заказ");
    public JButton b_create_product = new JButton("Создать продукт");
    public JButton b_add_prod_in_st = new JButton("Добавить на склад");
    public JButton b_create_client = new JButton("Создать клиента");
    public JButton b_rest_prod = new JButton("Остатки");
    public JButton b_show_prod = new JButton("Товары");
    public JButton b_create_manuf = new JButton("+ производителя");
    public JButton b_create_class = new JButton("+ класс");
    public JButton b_all_clients = new JButton("Клиенты");
    public JButton b_create_user = new JButton("+ user");
    public JButton b_create_file = new JButton("+ file");
    public JButton b_history_order = new JButton("История заказов");






    public JPanel newJPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);


        b_history_nakl.setBounds(400, 80, 150, 50);
        panel.add(b_history_nakl);
        b_history_order.setBounds(400, 150, 150, 50);
        panel.add(b_history_order);
        b_create_order.setBounds(600, 80, 150, 50);
        panel.add(b_create_order);
        b_create_product.setBounds(200, 80, 150, 50);
        panel.add(b_create_product);
        b_add_prod_in_st.setBounds(450, 220, 150, 50);
        b_add_prod_in_st.setVisible(false);
        panel.add(b_add_prod_in_st);
        b_create_client.setBounds(800, 80, 150, 50 );
        panel.add(b_create_client);
        b_rest_prod.setBounds(450, 220, 150, 50 );
        b_rest_prod.setVisible(false);
        panel.add(b_rest_prod);
        b_show_prod.setBounds(200, 150, 150, 50 );
        panel.add(b_show_prod);
        b_create_manuf.setBounds(1000, 150, 150, 50 );
        panel.add(b_create_manuf);
        b_create_class.setBounds(1000, 80, 150, 50 );
        panel.add(b_create_class);
        b_all_clients.setBounds(800, 150, 150, 50 );
        panel.add(b_all_clients);
        b_create_user.setBounds(1000, 220, 150, 50);
        panel.add(b_create_user);
        b_create_file.setBounds(1000, 10, 150, 50);
        panel.add(b_create_file);










        return panel;
    }


}
