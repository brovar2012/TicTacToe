import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dima on 24.02.2015.
 */
public class play implements ActionListener {
    int themeColor;
    public play ()
    {
    }

    public play (int themeColor)
    {
        this.themeColor = themeColor;
    }

    public void actionPerformed(ActionEvent e) {
        final JFrame frame = new JFrame("Выбор режима игры");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel background = new JLabel(new ImageIcon("src\\23.png"));
        frame.add(background);
        background.setLayout(new GridBagLayout());

        JButton buttonSinglePlay = new JButton(" Игра с компьютером  ");
        JButton buttonMultiplay =  new JButton("    Игра с другом    ");
        JButton buttonBack       = new JButton("        Назад        ");

        buttonSinglePlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonMultiplay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if ( themeColor == 1) {
            frame.getContentPane().setBackground(Color.WHITE);
        }
        else
        {
            buttonSinglePlay.setBackground(Color.BLACK);
            buttonMultiplay.setBackground(Color.BLACK);
            buttonBack.setBackground(Color.BLACK);
            buttonSinglePlay.setForeground(Color.WHITE);
            buttonMultiplay.setForeground(Color.WHITE);
            buttonBack.setForeground(Color.WHITE);
            frame.getContentPane().setBackground(Color.BLACK);
        }

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new mainClass();

            }
        });

        buttonMultiplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        }) ;
        buttonMultiplay.addActionListener(new friendGame(themeColor));

        buttonSinglePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        }) ;
        buttonSinglePlay.addActionListener(new singleGame(themeColor));

        background.add(buttonSinglePlay, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20, 50, 10, 50), 5, 5));
        background.add(buttonMultiplay, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(10, 50, 10, 50), 5, 5));
        background.add(buttonBack, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(10, 50, 10, 50), 5, 5));
        frame.setVisible(true);
    }
}