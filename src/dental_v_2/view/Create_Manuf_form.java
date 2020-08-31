package dental_v_2.view;


import dental_v_2.controllers.Create_Manuf_Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Create_Manuf_form {
    JButton b_back = new JButton("Назад");
    String mass_class[];
    Create_Manuf_Controller controller = new Create_Manuf_Controller();
    int p = 0;

    public JPanel newJPanel(){


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);

        JButton b_create = new JButton("Создать");
        b_create.setBounds(700, 300, 150, 50);
        b_create.setVisible(true);
        panel.add(b_create);
        b_back.setBounds(480, 300, 150, 50);
        panel.add(b_back);


        JTextField text_name = new JTextField("Название*");
        text_name.setBounds(515, 45, 300, 30);
        panel.add(text_name);
        JTextField text_country = new JTextField("Страна");
        text_country.setBounds(515, 80 , 300, 30);
        panel.add(text_country);





        text_name.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_name.setText(null);

            }
        });

        text_country.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_country.setText(null);

            }
        });

        b_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = text_name.getText();
                String country = text_country.getText();

                try {
                    proverka(name);
                    if (p==1){
                        create_manuf(name, country);
                    } else {
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "Такой производитель существует!");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return panel;
    }


    public void create_manuf ( String name, String country) throws SQLException, ClassNotFoundException{
        controller.create_manuf(name, country);
    }

    public int proverka ( String name) throws SQLException, ClassNotFoundException{
      p =  controller.proverka(name);
      return p;
    }
}
