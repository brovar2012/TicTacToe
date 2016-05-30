import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dima on 10.03.2015.
 */
public class singleGame implements ActionListener {
    int themeColor;

    public singleGame() {

    }

    public singleGame(int themeColor) {
        this.themeColor = themeColor;
    }

    public void actionPerformed(ActionEvent e) {

        final JFrame frame = new JFrame("Выбор режима игры");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel labelSimpleGame = new JLabel("  Простая игра ");
        JLabel labelRecordGame = new JLabel(" Игра на рекорд");

        JRadioButton buttonSimpleGame = new JRadioButton();
        JRadioButton buttonRecordGame = new JRadioButton();

        JButton buttonBack = new JButton(" Назад ");

        JPanel simpleGame = new JPanel();
        JPanel recordGame = new JPanel();

        buttonSimpleGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonRecordGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        simpleGame.setLayout(new FlowLayout());
        recordGame.setLayout(new FlowLayout());

        if (themeColor == 1) {
            buttonSimpleGame.setBackground(Color.WHITE);
            buttonRecordGame.setBackground(Color.WHITE);
            simpleGame.setBackground(Color.WHITE);
            recordGame.setBackground(Color.WHITE);
            frame.getContentPane().setBackground(Color.WHITE);
        } else {
            simpleGame.setBackground(Color.BLACK);
            recordGame.setBackground(Color.BLACK);
            buttonSimpleGame.setBackground(Color.BLACK);
            buttonRecordGame.setBackground(Color.BLACK);
            labelSimpleGame.setForeground(Color.WHITE);
            labelRecordGame.setForeground(Color.WHITE);
            buttonBack.setBackground(Color.BLACK);
            buttonBack.setForeground(Color.WHITE);
            frame.getContentPane().setBackground(Color.BLACK);
        }

        simpleGame.add(buttonSimpleGame);
        simpleGame.add(labelSimpleGame);
        recordGame.add(buttonRecordGame);
        recordGame.add(labelRecordGame);

        buttonSimpleGame.addActionListener(new choiceOfDifficultyLevel(themeColor));
        buttonSimpleGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        buttonRecordGame.addActionListener(new simpleRecordGame(themeColor));
        buttonRecordGame.addActionListener(new ActionListener() {
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

        frame.add(simpleGame, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 10, 10), 10, 10));
        frame.add(recordGame, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 10, 10), 10, 10));
        frame.add(buttonBack, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 10, 10));
        frame.setVisible(true);
    }
}

