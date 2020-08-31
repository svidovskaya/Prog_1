package dental_v_1.view;

import javax.swing.*;

public class Menu_form {
    public JButton b_generation = new JButton("Генерировать");
    public JButton b_history = new JButton("История акций");
    public JButton b_history_order = new JButton("История заказов");
    public JButton b_create_order = new JButton("Создать заказ");
    public JButton b_create_product = new JButton("Создать продукт");
    public JButton b_add_prod_in_st = new JButton("Добавить на склад");
    public JButton b_create_client = new JButton("Создать клиента");
    public JButton b_rest_prod = new JButton("Остатки");
    public JButton b_show_prod = new JButton("Товар");
    public JButton b_create_manuf = new JButton("+ производителя");




    public JPanel newJPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);

        b_generation.setBounds(750, 80, 150, 50);
        b_generation.setVisible(true);
        panel.add(b_generation);
        b_history.setBounds(750, 10, 150, 50);
        panel.add(b_history);
        b_history_order.setBounds(450, 10, 150, 50);
        panel.add(b_history_order);
        b_create_order.setBounds(450, 80, 150, 50);
        panel.add(b_create_order);
        b_create_product.setBounds(750, 150, 150, 50);
        panel.add(b_create_product);
        b_add_prod_in_st.setBounds(450, 150, 150, 50);
        panel.add(b_add_prod_in_st);
        b_create_client.setBounds(750, 220, 150, 50 );
        panel.add(b_create_client);
        b_rest_prod.setBounds(450, 220, 150, 50 );
        panel.add(b_rest_prod);
        b_show_prod.setBounds(450, 290, 150, 50 );
        panel.add(b_show_prod);
        b_create_manuf.setBounds(750, 290, 150, 50 );
        panel.add(b_create_manuf);






        return panel;
    }


}
