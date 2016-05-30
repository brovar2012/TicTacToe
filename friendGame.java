import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class friendGame implements ActionListener {
    int themeColor;
    public friendGame ()
    {

    }
    public friendGame (int themeColor)
    {
        this.themeColor = themeColor;
    }
    public void actionPerformed(ActionEvent e) {

        final JFrame frame = new JFrame("Выбор размера поля");
        frame.setSize(200,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel label3 = new JLabel ("Поле 3 на 3");
        JLabel label4 = new JLabel ("Поле 4 на 4");
        JLabel label5 = new JLabel ("Поле 5 на 5");

        JRadioButton checkBox3 = new JRadioButton();
        JRadioButton checkBox4 = new JRadioButton();
        JRadioButton checkBox5 = new JRadioButton();

        JButton buttonBack  = new JButton(" Назад ");

        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        checkBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());
        if (themeColor == 1) {
            checkBox3.setBackground(Color.WHITE);
            checkBox4.setBackground(Color.WHITE);
            checkBox5.setBackground(Color.WHITE);
            panel3.setBackground(Color.WHITE);
            panel4.setBackground(Color.WHITE);
            panel5.setBackground(Color.WHITE);
            frame.getContentPane().setBackground(Color.WHITE);
        }
        else
        {
            panel3.setBackground(Color.BLACK);
            panel4.setBackground(Color.BLACK);
            panel5.setBackground(Color.BLACK);
            checkBox3.setBackground(Color.BLACK);
            checkBox4.setBackground(Color.BLACK);
            checkBox5.setBackground(Color.BLACK);
            label3.setForeground(Color.WHITE);
            label4.setForeground(Color.WHITE);
            label5.setForeground(Color.WHITE);
            buttonBack.setBackground(Color.BLACK);
            buttonBack.setForeground(Color.WHITE);
            frame.getContentPane().setBackground(Color.BLACK);
        }

        panel3.add(checkBox3);
        panel3.add(label3);
        panel4.add(checkBox4);
        panel4.add(label4);
        panel5.add(checkBox5);
        panel5.add(label5);

        checkBox3.addActionListener(new on3(themeColor));
        checkBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        checkBox4.addActionListener(new on4(themeColor));
        checkBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        checkBox5.addActionListener(new on5(themeColor));
        checkBox5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });


        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        buttonBack.addActionListener(new play(themeColor));

        frame.add(panel3,new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1),10,10) );
        frame.add(panel4,new GridBagConstraints(1,2,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1),10,10) );
        frame.add(panel5,new GridBagConstraints(1,3,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1),10,10) );
        frame.add(buttonBack,new GridBagConstraints(1,4,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1),10,10) );



        frame.setVisible(true);
    }
}
