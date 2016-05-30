import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dima on 22.04.2015.
 */
public class choiceOfDifficultyLevel implements ActionListener {
        int themeColor;

        public choiceOfDifficultyLevel() {

        }

        public choiceOfDifficultyLevel(int themeColor) {
            this.themeColor = themeColor;
        }

        public void actionPerformed(ActionEvent e) {

            final JFrame frame = new JFrame("Выбор сложности игры");
            frame.setSize(200, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridBagLayout());
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JLabel labelSimpleGame = new JLabel("Легкий ");
            JLabel labelRecordGame = new JLabel("Сложный");

            JRadioButton buttonEasyGame = new JRadioButton();
            JRadioButton buttonHardGame = new JRadioButton();

            JButton buttonBack = new JButton(" Назад ");

            JPanel easyGame = new JPanel();
            JPanel hardGame = new JPanel();

            buttonEasyGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonHardGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            easyGame.setLayout(new FlowLayout());
            hardGame.setLayout(new FlowLayout());

            if (themeColor == 1) {
                buttonEasyGame.setBackground(Color.WHITE);
                buttonHardGame.setBackground(Color.WHITE);
                easyGame.setBackground(Color.WHITE);
                hardGame.setBackground(Color.WHITE);
                frame.getContentPane().setBackground(Color.WHITE);
            } else {
                easyGame.setBackground(Color.BLACK);
                hardGame.setBackground(Color.BLACK);
                buttonEasyGame.setBackground(Color.BLACK);
                buttonHardGame.setBackground(Color.BLACK);
                labelSimpleGame.setForeground(Color.WHITE);
                labelRecordGame.setForeground(Color.WHITE);
                buttonBack.setBackground(Color.BLACK);
                buttonBack.setForeground(Color.WHITE);
                frame.getContentPane().setBackground(Color.BLACK);
            }

            easyGame.add(buttonEasyGame);
            easyGame.add(labelSimpleGame);
            hardGame.add(buttonHardGame);
            hardGame.add(labelRecordGame);

            buttonEasyGame.addActionListener(new simpleGame(themeColor,1,0, ""));
            buttonEasyGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });

            buttonHardGame.addActionListener(new simpleGame(themeColor,2,0,""));
            buttonHardGame.addActionListener(new ActionListener() {
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

            frame.add(easyGame, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 10, 10), 10, 10));
            frame.add(hardGame, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 10, 10), 10, 10));
            frame.add(buttonBack, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.setVisible(true);
        }
    }
