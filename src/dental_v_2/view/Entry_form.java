package dental_v_2.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Entry_form {
    public JButton e_entry = new JButton("Войти");
    public JButton e_drop = new JButton("Сброс");
    public JButton e_registration = new JButton("Восстановить");
    public JTextField text_login = new JTextField("Логин");
    public JTextField text_passw = new JTextField("Пароль");

//    Entry_Controller controller = new Entry_Controller();
//    public int id_user =0;
//    public int id_pos =0;

    public JPanel newJPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);

        e_entry.setBounds(500, 150, 150, 50);
        e_entry.setVisible(true);
        panel.add(e_entry);
        e_drop.setBounds(700, 150, 150, 50);
        panel.add(e_drop);
        e_registration.setBounds(600, 230, 150, 50);
        e_registration.setVisible(false);
        panel.add(e_registration);


        text_login.setBounds(515, 45, 300, 30);
        panel.add(text_login);

        text_passw.setBounds(515, 80 , 300, 30);
        panel.add(text_passw);



        text_login.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_login.setText(null);

            }
        });
        text_passw.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_passw.setText(null);

            }
        });

        e_drop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text_login.setText("");
                text_passw.setText("");
            }
        });

        e_entry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = text_login.getText();
                String pass = text_passw.getText();

//                try {
//                    entry_user(login, pass);
//                    System.out.println(id_user + "user");
//                    poisk_position(id_user);
//                    System.out.println(id_pos + "pos");
//
//
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
            }
        });










        return panel;
    }



}
