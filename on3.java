import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

/*Created by Dima on 26.02.2015.
*/

public class on3 extends JFrame implements ActionListener
{
    int themeColor;
    int i;
    int winnerOrNo=0;
    static int throughCourse=0;
    XOButton buttons[] = new XOButton[9];
    public on3() { }

    public on3(int themeColor) {
        this.themeColor = themeColor;
    }

    final JFrame frame = new JFrame("3 на 3");
    public void actionPerformed(ActionEvent e) {
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        for (i = 0; i < 9; i++) {
            buttons[i] = new XOButton(0);
            if (themeColor == 0)
                buttons[i].setBackground(Color.BLACK);
            frame.add(buttons[i]);
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
            for (int i = 0; i < 9; i++)
                if (buttons[i].value != 0)
                    j++;
            if (j == 9)
            {
                new finishWindow(0, 3, themeColor,1,0);
                waiter();
            }
        }
    }

    private void waiter() {
        frame.setEnabled(false);
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(10000);
                    frame.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        };
        thread1.start();
    }

    public void checkWin() {
        int count = 0;
        for (count = 1; count < 3; count++) {
            //Проверка рядов
            if ((buttons[0].value == count) && (buttons[1].value == count) && (buttons[2].value == count)) {
                buttons[0].setEnabled(false);
                buttons[1].setEnabled(false);
                buttons[2].setEnabled(false);
                winnerOrNo = count;
                new finishWindow(count, 3, themeColor,1,0);
                waiter();
            }
            if (buttons[3].value == buttons[4].value && buttons[4].value == buttons[5].value &&
                    ((buttons[3].value == count) || (buttons[4].value == count) || (buttons[5].value == count))) {
                buttons[3].setEnabled(false);
                buttons[4].setEnabled(false);
                buttons[5].setEnabled(false);
                winnerOrNo = count;
                new finishWindow(count, 3, themeColor,1,0);
                waiter();
            }
            if (buttons[6].value == buttons[7].value && buttons[7].value == buttons[8].value &&
                    ((buttons[6].value == count) || (buttons[7].value == count) || (buttons[8].value == count))) {
                buttons[6].setEnabled(false);
                buttons[7].setEnabled(false);
                buttons[8].setEnabled(false);
                winnerOrNo = count;
                new finishWindow(count, 3, themeColor,1,0);
                waiter();
            }
            //Проверка столбцов
            if (buttons[0].value == buttons[3].value && buttons[3].value == buttons[6].value &&
                    ((buttons[0].value == count) || (buttons[3].value == count) || (buttons[6].value == count))) {
                buttons[0].setEnabled(false);
                buttons[3].setEnabled(false);
                buttons[6].setEnabled(false);
                winnerOrNo = count;
                new finishWindow(count, 3, themeColor,1,0);
                waiter();
            }
            if (buttons[1].value == buttons[4].value && buttons[4].value == buttons[7].value &&
                    ((buttons[1].value == count) || (buttons[4].value == count) || (buttons[7].value == count))) {
                buttons[1].setEnabled(false);
                buttons[4].setEnabled(false);
                buttons[7].setEnabled(false);
                winnerOrNo = count;
                new finishWindow(count, 3, themeColor,1,0);
                waiter();
            }
            if (buttons[2].value == buttons[5].value && buttons[5].value == buttons[8].value &&
                    ((buttons[2].value == count) || (buttons[5].value == count) || (buttons[8].value == count))) {
                buttons[2].setEnabled(false);
                buttons[5].setEnabled(false);
                buttons[8].setEnabled(false);
                winnerOrNo = count;
                new finishWindow(count, 3, themeColor,1,0);
                waiter();
            }
            //Проверка диагоналей
            if (buttons[0].value == buttons[4].value && buttons[4].value == buttons[8].value &&
                    ((buttons[0].value == count) || (buttons[4].value == count) || (buttons[8].value == count))) {
                buttons[0].setEnabled(false);
                buttons[4].setEnabled(false);
                buttons[8].setEnabled(false);
                winnerOrNo = count;
                new finishWindow(count, 3, themeColor,1,0);
                waiter();
            }
            if (buttons[2].value == buttons[4].value && buttons[4].value == buttons[6].value &&
                    ((buttons[2].value == count) || (buttons[4].value == count) || (buttons[6].value == count))) {
                buttons[2].setEnabled(false);
                buttons[4].setEnabled(false);
                buttons[6].setEnabled(false);
                winnerOrNo = count;
                new finishWindow(count, 3, themeColor,1,0);
                waiter();
            }
        }
    }
}
