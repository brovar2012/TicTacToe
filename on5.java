import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dima on 01.03.2015.
 */
public class on5 extends JFrame implements ActionListener {
    int themeColor;
    int winnerOrNo =0;
    static int throughCourse=0;
    public on5 ()
    {

    }
    public on5 (int themeColor)
    {
        this.themeColor = themeColor;
    }
        XOButton buttons[] = new XOButton[25];
        final  JFrame frame = new JFrame("5 на 5");
        public void actionPerformed(ActionEvent e) {
            frame.setSize(665, 665);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(5, 5));
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            for (int j = 0; j < 25; j++) {
                buttons[j] = new XOButton(0);
                if (themeColor == 0)
                    buttons[j].setBackground(Color.BLACK);
                frame.add(buttons[j]);
            }

            buttons[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(0);
                    buttons[0].removeActionListener(this);
                }
            });

            buttons[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(1);
                    buttons[1].removeActionListener(this);
                }
            });

            buttons[2].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(2);
                    buttons[2].removeActionListener(this);
                }
            });

            buttons[3].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(3);
                    buttons[3].removeActionListener(this);
                }
            });

            buttons[4].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(4);
                    buttons[4].removeActionListener(this);
                }
            });

            buttons[5].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(5);
                    buttons[5].removeActionListener(this);
                }
            });

            buttons[6].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(6);
                    buttons[6].removeActionListener(this);
                }
            });

            buttons[7].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(7);
                    buttons[7].removeActionListener(this);
                }
            });

            buttons[8].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(8);
                    buttons[8].removeActionListener(this);
                }
            });

            buttons[9].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(9);
                    buttons[9].removeActionListener(this);
                }
            });
            buttons[10].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(10);
                    buttons[10].removeActionListener(this);
                }
            });
            buttons[11].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(11);
                    buttons[11].removeActionListener(this);
                }
            });
            buttons[12].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(12);
                    buttons[12].removeActionListener(this);
                }
            });
            buttons[13].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(13);
                    buttons[13].removeActionListener(this);
                }
            });

            buttons[14].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(14);
                    buttons[14].removeActionListener(this);
                }
            });

            buttons[15].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(15);
                    buttons[15].removeActionListener(this);
                }
            });

            buttons[16].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(16);
                    buttons[16].removeActionListener(this);
                }
            });

            buttons[17].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(17);
                    buttons[17].removeActionListener(this);
                }
            });

            buttons[18].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(18);
                    buttons[18].removeActionListener(this);
                }
            });

            buttons[19].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(19);
                    buttons[19].removeActionListener(this);
                }
            });

            buttons[20].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(20);
                    buttons[20].removeActionListener(this);
                }
            });

            buttons[21].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(21);
                    buttons[21].removeActionListener(this);
                }
            });

            buttons[22].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(22);
                    buttons[22].removeActionListener(this);
                }
            });

            buttons[23].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(23);
                    buttons[23].removeActionListener(this);
                }
            });

            buttons[24].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsAction(24);
                    buttons[24].removeActionListener(this);
                }
            });

            frame.setVisible(true);
        }

    public void buttonsAction(int i)
    {
        if (throughCourse % 2 == 0) {
            buttons[i].value = 1;
            buttons[i].setX();
        } else
        {
            buttons[i].value = 2;
            buttons[i].setO();
        }
        throughCourse++;
        checkWin();
        checkOnDraw ();
    }

    public void checkOnDraw ()
    {
        if (winnerOrNo ==0) {
            int j = 0;
            for (int i = 0; i < 25; i++)
                if (buttons[i].value != 0)
                    j++;
            if (j == 25) {
                new finishWindow(0, 5, themeColor,1,0);
                waiter();
            }
        }
    }

    private void waiter() {
        frame.setEnabled(false);
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                    frame.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        };
        thread1.start();
    }

    public void checkWin() {
        int j = 0;
        for (j = 1; j < 3; j++) {
            //Проверка рядов
            if ((buttons[0].value == j) && (buttons[1].value == j) && (buttons[2].value == j) && (buttons[3].value == j) && (buttons[4].value == j)) {
                buttons[0].setEnabled(false);
                buttons[1].setEnabled(false);
                buttons[2].setEnabled(false);
                buttons[3].setEnabled(false);
                buttons[4].setEnabled(false);
                winnerOrNo = j;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[5].value == j) && (buttons[6].value == j) && (buttons[7].value == j) && (buttons[8].value == j) && (buttons[9].value == j)) {
                buttons[5].setEnabled(false);
                buttons[6].setEnabled(false);
                buttons[7].setEnabled(false);
                buttons[8].setEnabled(false);
                buttons[9].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[10].value == j) && (buttons[11].value == j) && (buttons[12].value == j) && (buttons[13].value == j) && (buttons[14].value == j)) {
                buttons[10].setEnabled(false);
                buttons[11].setEnabled(false);
                buttons[12].setEnabled(false);
                buttons[13].setEnabled(false);
                buttons[14].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[15].value == j) && (buttons[16].value == j) && (buttons[17].value == j) && (buttons[18].value == j) && (buttons[19].value == j)) {
                buttons[15].setEnabled(false);
                buttons[16].setEnabled(false);
                buttons[17].setEnabled(false);
                buttons[18].setEnabled(false);
                buttons[19].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[20].value == j) && (buttons[21].value == j) && (buttons[22].value == j) && (buttons[23].value == j) && (buttons[24].value == j)) {
                buttons[20].setEnabled(false);
                buttons[21].setEnabled(false);
                buttons[22].setEnabled(false);
                buttons[23].setEnabled(false);
                buttons[24].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            //Проверка столбцов
            if ((buttons[0].value == j) && (buttons[5].value == j) && (buttons[10].value == j) && (buttons[15].value == j) && (buttons[20].value == j)) {
                buttons[0].setEnabled(false);
                buttons[5].setEnabled(false);
                buttons[10].setEnabled(false);
                buttons[15].setEnabled(false);
                buttons[20].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[1].value == j) && (buttons[6].value == j) && (buttons[11].value == j) && (buttons[16].value == j) && (buttons[21].value == j)) {
                buttons[1].setEnabled(false);
                buttons[6].setEnabled(false);
                buttons[11].setEnabled(false);
                buttons[16].setEnabled(false);
                buttons[21].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[2].value == j) && (buttons[7].value == j) && (buttons[12].value == j) && (buttons[17].value == j) && (buttons[22].value == j)) {
                buttons[2].setEnabled(false);
                buttons[7].setEnabled(false);
                buttons[12].setEnabled(false);
                buttons[17].setEnabled(false);
                buttons[22].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[3].value == j) && (buttons[8].value == j) && (buttons[13].value == j) && (buttons[18].value == j) && (buttons[23].value == j)) {
                buttons[3].setEnabled(false);
                buttons[8].setEnabled(false);
                buttons[13].setEnabled(false);
                buttons[18].setEnabled(false);
                buttons[23].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[4].value == j) && (buttons[9].value == j) && (buttons[14].value == j) && (buttons[19].value == j) && (buttons[24].value == j)) {
                buttons[4].setEnabled(false);
                buttons[9].setEnabled(false);
                buttons[14].setEnabled(false);
                buttons[19].setEnabled(false);
                buttons[24].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            //Проверка диагоналей
            if ((buttons[0].value == j) && (buttons[6].value == j) && (buttons[12].value == j) && (buttons[18].value == j) && (buttons[24].value == j)) {
                buttons[0].setEnabled(false);
                buttons[6].setEnabled(false);
                buttons[12].setEnabled(false);
                buttons[18].setEnabled(false);
                buttons[24].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
            if ((buttons[4].value == j) && (buttons[8].value == j) && (buttons[12].value == j) && (buttons[16].value == j) && (buttons[20].value == j)) {
                buttons[4].setEnabled(false);
                buttons[8].setEnabled(false);
                buttons[12].setEnabled(false);
                buttons[16].setEnabled(false);
                buttons[20].setEnabled(false);
                winnerOrNo = 1;
                new finishWindow(j, 5, themeColor,1,0);
                waiter();
            }
        }
    }
}

