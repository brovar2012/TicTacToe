import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Created by Dima on 30.03.2015.
 */
public class simpleRecordGame extends simpleGame implements ActionListener {
    static int numberOfLevel = 0;
    int difficulty = 2;
    String fileName;
    String  fileForOpen;

    public simpleRecordGame(int themeColor)
    {
        fileName ="c:\\Users\\Dima\\IdeaProjects\\untitled1\\repeatGame\\"+ getTimeAndDate()+".txt";
        this.themeColor = themeColor;
        frame.setSize(400, 535);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(4, 3));
        Font font = new Font("Verdana", Font.BOLD , 20);
        JLabel counter = new JLabel(Integer.toString(numberOfLevel));
        JLabel counterLine = new JLabel("Уровень:");
        JLabel c = new JLabel();
        counterLine.setFont(font);
        counter.setFont(font);
        counterLine.setHorizontalAlignment (JLabel.CENTER);
        counter.setHorizontalAlignment (JLabel.CENTER);
        frame.add(counterLine);
        frame.add(c);
        frame.add(counter);
    }

    public void actionPerformed(ActionEvent e)
    {
        fileWorker.write(fileName, "");
        if (throughCourse % 2 == 0)
            attack = 1;
        else
            attack = 2;
        if (attack == 1) {
            for (j = 0; j < 9; j++) {
                buttons[j] = new XOButton(1);
                if (themeColor == 0)
                    buttons[j].setBackground(Color.BLACK);
                frame.add(buttons[j]);
            }
        } else {
            for (j = 0; j < 9; j++) {
                buttons[j] = new XOButton(2);
                if (themeColor == 0)
                    buttons[j].setBackground(Color.BLACK);
                frame.add(buttons[j]);

            }
        }

        if (throughCourse % 2 != 0) {
            int protectOrAttack = 0;
            if (attack == 1)
                protectOrAttack = 2;
            else
                protectOrAttack = 1;

            int i = (int) (Math.random() * ((4 - 0) + 1));

            if (i == 1)
                i = 2;
            if (i == 2)
                i = 4;
            if (i == 3)
                i = 6;
            if (i == 4)
                i = 8;

            buttons[i].value = protectOrAttack;
            if (protectOrAttack == 1) {
                buttons[i].setAutoX();
                buttons[i].removeActionListener(this);
            }
            else
            {
                buttons[i].setAutoO();
                buttons[i].removeActionListener(this);
            }
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

    public void buttonsAction(final int i) {
        thread = new Thread() {
            public void run() {
                if (winnerOrNo == 0) {
                    if (attack == 1) {
                        buttons[i].value = 1;
                        buttons[i].setX();
                        try {
                            fileWorker.update(fileName, ("user " + i + " " + buttons[i].value));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            checkWin();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            protect(1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        buttons[i].value = 2;
                        buttons[i].setO();
                        try {
                            fileWorker.update(fileName, ("user " + i + " " + buttons[i].value));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            checkWin();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            protect(2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        checkOnDraw();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
        };
        thread.start();
    }

    public void checkOnDraw() throws FileNotFoundException {
        if (winnerOrNo == 0) {
            int j = 0;
            for (int i = 0; i < 9; i++)
                if (buttons[i].value != 0)
                    j++;
            if (j == 9) {
                fileWorker.update(fileName, "end end end");
                new finishWindow(0, 7, themeColor,numberOfLevel,attack);
                waiter();
                throughCourse++;
            }
        }
    }

    public void attackForEachButton(int protection,int numberOfButton) throws FileNotFoundException {
        buttons[numberOfButton].value = protection;
        if (attack == 1) {
            buttons[numberOfButton].setAutoO();
            buttonBlocked(numberOfButton);
            try {
                fileWorker.update(fileName, ("comp "+numberOfButton+" "+protection));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            buttons[numberOfButton].setAutoX();
            buttonBlocked(numberOfButton);
            try {
                fileWorker.update(fileName, ("comp "+numberOfButton+" "+protection));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkWin() throws FileNotFoundException {
        int count = 0;
        int t;
        for (count = 1; count < 3; count++) {
            //Проверка рядов
            if ((buttons[0].value == count) && (buttons[1].value == count) && (buttons[2].value == count)) {
                buttons[0].setEnabled(false);
                buttons[1].setEnabled(false);
                buttons[2].setEnabled(false);
                winnerOrNo=count;
                if (attack == count) {
                    numberOfLevel++;
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                }
                else
                {
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                    numberOfLevel = 0;
                }
                return;
            }
            if (buttons[3].value == buttons[4].value && buttons[4].value == buttons[5].value &&
                    ((buttons[3].value == count) || (buttons[4].value == count) || (buttons[5].value == count))) {
                buttons[3].setEnabled(false);
                buttons[4].setEnabled(false);
                buttons[5].setEnabled(false);
                winnerOrNo = count;
                if (attack == count) {
                    numberOfLevel++;
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                }
                else
                {
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                    numberOfLevel = 0;
                }
                return;
            }
            if (buttons[6].value == buttons[7].value && buttons[7].value == buttons[8].value &&
                    ((buttons[6].value == count) || (buttons[7].value == count) || (buttons[8].value == count))) {
                buttons[6].setEnabled(false);
                buttons[7].setEnabled(false);
                buttons[8].setEnabled(false);
                winnerOrNo = count;
                if (attack == count) {
                    numberOfLevel++;
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                }
                else
                {
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                    numberOfLevel = 0;
                }
                return;
            }
            //Проверка столбцов
            if (buttons[0].value == buttons[3].value && buttons[3].value == buttons[6].value &&
                    ((buttons[0].value == count) || (buttons[3].value == count) || (buttons[6].value == count))) {
                buttons[0].setEnabled(false);
                buttons[3].setEnabled(false);
                buttons[6].setEnabled(false);
                winnerOrNo = count;
                if (attack == count) {
                    fileWorker.update(fileName, "end end end");
                    numberOfLevel++;
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                }
                else
                {
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                    numberOfLevel = 0;
                }
                return;
            }
            if (buttons[1].value == buttons[4].value && buttons[4].value == buttons[7].value &&
                    ((buttons[1].value == count) || (buttons[4].value == count) || (buttons[7].value == count))) {
                buttons[1].setEnabled(false);
                buttons[4].setEnabled(false);
                buttons[7].setEnabled(false);
                winnerOrNo = count;
                if (attack == count) {
                    fileWorker.update(fileName, "end end end");
                    numberOfLevel++;
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                }
                else
                {
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                    numberOfLevel = 0;
                }
                return;
            }
            if (buttons[2].value == buttons[5].value && buttons[5].value == buttons[8].value &&
                    ((buttons[2].value == count) || (buttons[5].value == count) || (buttons[8].value == count))) {
                buttons[2].setEnabled(false);
                buttons[5].setEnabled(false);
                buttons[8].setEnabled(false);
                winnerOrNo = count;
                if (attack == count) {
                    fileWorker.update(fileName, "end end end");
                    numberOfLevel++;
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                }
                else
                {
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                    numberOfLevel = 0;
                }
                return;
            }
            //Проверка диагоналей
            if (buttons[0].value == buttons[4].value && buttons[4].value == buttons[8].value &&
                    ((buttons[0].value == count) || (buttons[4].value == count) || (buttons[8].value == count))) {
                buttons[0].setEnabled(false);
                buttons[4].setEnabled(false);
                buttons[8].setEnabled(false);
                winnerOrNo = count;
                if (attack == count) {
                    fileWorker.update(fileName, "end end end");
                    numberOfLevel++;
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                }
                else
                {
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                    numberOfLevel = 0;
                }
                return;
            }
            if (buttons[2].value == buttons[4].value && buttons[4].value == buttons[6].value &&
                    ((buttons[2].value == count) || (buttons[4].value == count) || (buttons[6].value == count))) {
                buttons[2].setEnabled(false);
                buttons[4].setEnabled(false);
                buttons[6].setEnabled(false);
                winnerOrNo = count;
                if (attack == count) {
                    fileWorker.update(fileName, "end end end");
                    numberOfLevel++;
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                }
                else
                {
                    fileWorker.update(fileName, "end end end");
                    new finishWindow(count, 7, themeColor, numberOfLevel, attack);
                    waiter();
                    throughCourse++;
                    numberOfLevel = 0;
                }
                return;
            }
        }
    }

}
