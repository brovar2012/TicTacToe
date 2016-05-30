import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by User on 08.03.2015.
 */
public class finishWindow {
    int winPlayer;
    int sizeOfField;
    int themeColor;
    int difficulty;

    public finishWindow() {
    }

    public finishWindow(int winPlayer, int sizeOfField, int themeColor, int difficulty, String fileName) {
        this.winPlayer = winPlayer;
        this.sizeOfField = sizeOfField;
        this.themeColor = themeColor;
        this.difficulty = difficulty;
        final JFrame frame = new JFrame("Игра окончена");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(100, 200);

        JButton ok = new JButton("Заново?");
        JButton exit = new JButton("Выход в главное меню");
        JButton repeat = new JButton ("Повторить игру");
        JLabel labelX = new JLabel("Победили крестики!");
        JLabel labelO = new JLabel("Победили нолики!");
        JLabel labelNo = new JLabel("Ничья!");
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(ok);
        p.add(exit);
        p.add (repeat);

        if (sizeOfField == 3) {
            ok.addActionListener(new on3(themeColor));
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });
        }

        if (sizeOfField == 4) {
            ok.addActionListener(new on4(themeColor));
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });

        }

        if (sizeOfField == 5) {
            ok.addActionListener(new on5(themeColor));
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });
        }

        if (sizeOfField == 6) {
            ok.addActionListener(new simpleGame(themeColor,difficulty,0,fileName));
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });

        }

        repeat.addActionListener(new simpleGame(themeColor, difficulty,1,fileName ));
        repeat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainClass();

            }
        });

        if (winPlayer == 2) {
            frame.add(labelO, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(p, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
        }
        if (winPlayer == 1) {
            frame.add(labelX, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(p, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
        }
        if (winPlayer == 0) {
            frame.add(labelNo, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(p, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));

        }
        frame.pack();
        frame.setVisible(true);
    }

    public finishWindow(int winPlayer, int sizeOfField, final int themeColor, int numberOfLevel,int go) {
        this.winPlayer = winPlayer;
        this.sizeOfField = sizeOfField;
        this.themeColor = themeColor;
        final JFrame frame = new JFrame("Игра на рекорд");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(100, 200);

        final tableOfRecords tableOfRecords = new tableOfRecords(themeColor,numberOfLevel);

        JButton ok = new JButton("Продолжить?");
        JButton saveResult = new JButton("Сохранить результат?");
        JButton exit = new JButton("Выход в главное меню");

        JLabel labelWin = new JLabel("Вы выйграли!");
        JLabel labelLose = new JLabel("Вы проиграли!");
        JLabel labelDraw = new JLabel("Ничья! Уровень не пройден");
        JLabel labelResult = new JLabel("Вы достигли уровня:" + Integer.toString(numberOfLevel));
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        saveResult.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableOfRecords.enterPlayerName();
            }
        });

        saveResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        ok.addActionListener(new simpleRecordGame(themeColor));


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainClass();

            }
        });

        if (winPlayer == go) {
            p.add(ok);
            p.add(saveResult);
            p.add(exit);

            frame.add(labelWin, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(labelResult, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(p, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
        }
        if (winPlayer != go && winPlayer!= 0) {
            p.add (saveResult);
            p.add(exit);

            frame.add(labelLose, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(labelResult, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(p, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
        }
        if ( winPlayer==0) {
            p.add(ok);
            p.add(saveResult);
            p.add(exit);

            frame.add(labelDraw, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(labelResult, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));
            frame.add(p, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(1, 1, 1, 1), 10, 10));

        }
        frame.pack();
        frame.setVisible(true);
    }
}
