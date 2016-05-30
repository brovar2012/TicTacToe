import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dima on 16.03.2015.
 */
public class simpleGame extends JFrame implements ActionListener {
    int themeColor;
    int difficulty;
    int j;
    int attack;
    int winnerOrNo = 0;
    int repeatOrNo;
    static int throughCourse = 0;
    final JFrame frame = new JFrame("Простая игра");
    final XOButton buttons[] = new XOButton[9];
    String fileName;
    String  fileForOpen;
    Thread thread;

    public simpleGame() {
    }

    public simpleGame(int themeColor,int difficulty, int repeatOrNo, String fileForOpen) {
        fileName ="c:\\Users\\Dima\\IdeaProjects\\untitled1\\repeatGame\\"+ getTimeAndDate()+".txt";
        this.themeColor = themeColor;
        this.difficulty = difficulty;
        this.repeatOrNo = repeatOrNo;
        this.fileForOpen = fileForOpen;
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    String getTimeAndDate(){
        long curTime = System.currentTimeMillis();
        String curStringDate = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(curTime);
        return curStringDate;
    }

    public void actionPerformed(ActionEvent e) {
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
            if (repeatOrNo==1) {
                throughCourse--;
                frame.setVisible(true);
                thread = new Thread() {
                    public void run() {
                        repeatGame();
                        try {
                            checkWin();
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            checkOnDraw();
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
            else {
                fileWorker.write(fileName, "");
                if (throughCourse % 2 != 0) {
                    final int protectOrAttack;
                    if (attack == 1)
                        protectOrAttack = 2;
                    else
                        protectOrAttack = 1;
                    thread = new Thread() {
                        public void run() {
                            setFirstCourse(protectOrAttack);
                        }
                    };
                    thread.start();
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
    }

    public void setFirstCourse (int protectOrAttack)
    {
        int i = 0 + (int) (Math.random() * ((4 - 0) + 1)); //Min + (int)(Math.random() * ((Max - Min) + 1))

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
            try {
                fileWorker.update(fileName, ("comp " + i + " " + protectOrAttack));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        else
        {
            buttons[i].setAutoO();
            try {
                fileWorker.update(fileName, ("comp " + i + " " + 2));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
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
                            thread.sleep(200);
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
                            thread.sleep(200);
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
                //fileWorker.update(fileName,"end end end");
               // fileWorker.update(fileName, "DRAW");
                new finishWindow(0, 6, themeColor,difficulty,fileName);
                waiter();
                throughCourse++;
            }
        }
    }

    public void allButtonsBlocked() {
        for (int i =0;i<9;i++) {
            buttons[i].remove();
            for (ActionListener al : buttons[i].getActionListeners()) {
                buttons[i].removeActionListener(al);
            }
        }
    }

    // ожидание
    public void waiter() {
        frame.setEnabled(false);
        //allButtonsBlocked();
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(10000);
                    frame.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        };
        thread.start();
    }

    public void checkWinForEachButton(int i,int j,int k,int count) throws FileNotFoundException {
        buttons[i].setEnabled(false);
        buttons[j].setEnabled(false);
        buttons[k].setEnabled(false);
        winnerOrNo = count;
        throughCourse++;
        //fileWorker.update(fileName,"end end end");
        //fileWorker.update(fileName, "victory " + count ); //"victory " + count + " "  +i  + j + k);
        new finishWindow(count, 6, themeColor,difficulty,fileName);
        waiter();
    }

    public void checkWin() throws FileNotFoundException {
        int count = 0;
        for (count = 1; count < 3; count++) {
            //Проверка рядов
            if ((buttons[0].value == count) && (buttons[1].value == count) && (buttons[2].value == count)) {
                checkWinForEachButton(0,1,2,count);
                return;
            }
            if (buttons[3].value == buttons[4].value && buttons[4].value == buttons[5].value &&
                    ((buttons[3].value == count) || (buttons[4].value == count) || (buttons[5].value == count))) {
                checkWinForEachButton(3,4,5,count);
                return;
            }
            if (buttons[6].value == buttons[7].value && buttons[7].value == buttons[8].value &&
                    ((buttons[6].value == count) || (buttons[7].value == count) || (buttons[8].value == count))) {
                checkWinForEachButton(6,7,8,count);
                return;
            }
            //Проверка столбцов
            if (buttons[0].value == buttons[3].value && buttons[3].value == buttons[6].value &&
                    ((buttons[0].value == count) || (buttons[3].value == count) || (buttons[6].value == count))) {
                checkWinForEachButton(0,3,6,count);
                return;
            }
            if (buttons[1].value == buttons[4].value && buttons[4].value == buttons[7].value &&
                    ((buttons[1].value == count) || (buttons[4].value == count) || (buttons[7].value == count))) {
                checkWinForEachButton(1,4,7,count);
                return;
            }
            if (buttons[2].value == buttons[5].value && buttons[5].value == buttons[8].value &&
                    ((buttons[2].value == count) || (buttons[5].value == count) || (buttons[8].value == count))) {
                checkWinForEachButton(2,5,8,count);
                return;
            }
            //Проверка диагоналей
            if (buttons[0].value == buttons[4].value && buttons[4].value == buttons[8].value &&
                    ((buttons[0].value == count) || (buttons[4].value == count) || (buttons[8].value == count))) {
                checkWinForEachButton(0,4,8,count);
                return;
            }
            if (buttons[2].value == buttons[4].value && buttons[4].value == buttons[6].value &&
                    ((buttons[2].value == count) || (buttons[4].value == count) || (buttons[6].value == count))) {
                checkWinForEachButton(2,4,6,count);
                return;
            }
        }
    }

    public void buttonBlocked(int number) throws FileNotFoundException {
        checkWin();
        buttons[number].remove();
        for (ActionListener al : buttons[number].getActionListeners()) {
            buttons[number].removeActionListener(al);
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

    public int attack(int attack) throws FileNotFoundException {
        int i = 0;
        int protection = 0;
        if (attack == 1)
            protection = 2;
        else
            protection = 1;
        if (difficulty == 2) {
            if (buttons[0].value == protection && buttons[8].value == protection && buttons[4].value == 0) {
                attackForEachButton(protection, 4);
                return 1;
            }
            if (buttons[0].value == protection && buttons[4].value == protection && buttons[8].value == 0) {
                attackForEachButton(protection, 8);
                return 1;
            }
            if (buttons[4].value == protection && buttons[8].value == protection && buttons[0].value == 0) {
                attackForEachButton(protection, 0);
                return 1;
            }
            // проверка побочной диагонали
            if (buttons[2].value == protection && buttons[6].value == protection && buttons[4].value == 0) {
                attackForEachButton(protection, 4);
                return 1;
            }
            if (buttons[2].value == protection && buttons[4].value == protection && buttons[6].value == 0) {
                attackForEachButton(protection, 6);
                return 1;
            }
            if (buttons[4].value == protection && buttons[6].value == protection && buttons[2].value == 0) {
                attackForEachButton(protection, 2);
                return 1;
            }
            // проверка по 1-ому и 2-ому столбцу
            for (i = 0; i < 9; i += 3) {
                if (buttons[i].value == protection && buttons[i + 1].value == protection && buttons[i + 2].value == 0) {
                    attackForEachButton(protection, i + 2);
                    return 1;
                }
            }
            //проверка по 1-ому и 3-ему столбцу
            for (i = 0; i < 9; i += 3) {
                if (buttons[i].value == protection && buttons[i + 2].value == protection && buttons[i + 1].value == 0) {
                    attackForEachButton(protection, i + 1);
                    return 1;
                }
            }
            // проверка по 2-ому и 3-ему столбцу
            for (i = 1; i < 9; i += 3) {
                if (buttons[i].value == protection && buttons[i + 1].value == protection && buttons[i - 1].value == 0) {
                    attackForEachButton(protection, i - 1);
                    return 1;
                }
            }
            // проверка по 1-ой и 3-ей строке
            for (i = 0; i < 3; i++) {
                if (buttons[i].value == protection && buttons[i + 6].value == protection && buttons[i + 3].value == 0) {
                    attackForEachButton(protection, i + 3);
                    return 1;

                }
            }
            //проверка по 2-ой и 3-ей строке
            for (i = 3; i < 6; i++) {
                if (buttons[i].value == protection && buttons[i + 3].value == protection && buttons[i - 3].value == 0) {
                    attackForEachButton(protection, i - 3);
                    return 1;
                }
            }
            //проверка по 1-ой и 2-ой строке
            for (i = 0; i < 3; i++) {
                if (buttons[i].value == protection && buttons[i + 3].value == protection && buttons[i + 6].value == 0) {
                    attackForEachButton(protection, i + 6);
                    return 1;
                }
            }
            return 0;
        }
        return 0;
    }


    public void protect(int attack) throws FileNotFoundException {
        int i = 0;
        int ifNotAttack = 0;
        int protection = 0;
        if (attack == 1)
            protection = 2;
        else
            protection = 1;
        if (winnerOrNo == 0)
        {
            ifNotAttack = attack(attack);
            if (ifNotAttack == 0)
            {
                if (difficulty==2)
                {
                    //проверка главной диагонали
                    if (buttons[0].value == attack && buttons[8].value == attack && buttons[4].value == 0) {
                        attackForEachButton(protection, 4);
                        return;
                    }
                    if (buttons[0].value == attack && buttons[4].value == attack && buttons[8].value == 0) {
                        attackForEachButton(protection, 8);
                        return;
                    }
                    if (buttons[4].value == attack && buttons[8].value == attack && buttons[0].value == 0) {
                        attackForEachButton(protection, 0);
                        return;
                    }
                    // проверка побочной диагонали
                    if (buttons[2].value == attack && buttons[6].value == attack && buttons[4].value == 0) {
                        attackForEachButton(protection, 4);
                        return;
                    }
                    if (buttons[2].value == attack && buttons[4].value == attack && buttons[6].value == 0) {
                        attackForEachButton(protection, 6);
                        return;
                    }
                    if (buttons[4].value == attack && buttons[6].value == attack && buttons[2].value == 0) {
                        attackForEachButton(protection, 2);
                        return;
                    }
                    // проверка по 1-ому и 2-ому столбцу
                    for (i = 0; i < 9; i += 3) {
                        if (buttons[i].value == attack && buttons[i + 1].value == attack && buttons[i + 2].value == 0) {
                            attackForEachButton(protection, i + 2);
                            return;
                        }
                    }
                    //проверка по 1-ому и 3-ему столбцу
                    for (i = 0; i < 9; i += 3) {
                        if (buttons[i].value == attack && buttons[i + 2].value == attack && buttons[i + 1].value == 0) {
                            attackForEachButton(protection, i + 1);
                            return;
                        }
                    }
                    // проверка по 2-ому и 3-ему столбцу
                    for (i = 1; i < 9; i += 3) {
                        if (buttons[i].value == attack && buttons[i + 1].value == attack && buttons[i - 1].value == 0) {
                            attackForEachButton(protection, i - 1);
                            return;
                        }
                    }
                    // проверка по 1-ой и 3-ей строке
                    for (i = 0; i < 3; i++) {
                        if (buttons[i].value == attack && buttons[i + 6].value == attack && buttons[i + 3].value == 0) {
                            attackForEachButton(protection, i + 3);
                            return;

                        }
                    }
                    //проверка по 2-ой и 3-ей строке
                    for (i = 3; i < 6; i++) {
                        if (buttons[i].value == attack && buttons[i + 3].value == attack && buttons[i - 3].value == 0) {
                            attackForEachButton(protection, i - 3);
                            return;
                        }
                    }
                    //проверка по 1-ой и 2-ой строке
                    for (i = 0; i < 3; i++) {
                        if (buttons[i].value == attack && buttons[i + 3].value == attack && buttons[i + 6].value == 0) {
                            attackForEachButton(protection, i + 6);
                            return;
                        }
                    }
                }
                //проверка центра
                if (buttons[4].value == 0) {
                    attackForEachButton(protection,4);
                    return;
                }
                // расставление ,,засады ,, по углам
                if (buttons[0].value == 0) {
                    attackForEachButton(protection,0);
                    return;
                }
                if (buttons[2].value == 0) {
                    attackForEachButton(protection,2);
                    return;
                }
                if (buttons[6].value == 0) {
                    attackForEachButton(protection,6);
                    return;
                }
                if (buttons[8].value == 0) {
                    attackForEachButton(protection,8);
                    return;
                }
                //если ничего не угрожает
                for (i = 0; i < 9; i++) {
                    if (buttons[i].value == 0) {
                        attackForEachButton(protection,i);
                        return;
                    }
                }
            }
        }
        return;
    }

    public void repeatGame ()
    {
        ArrayList<String> list = new ArrayList<String>();
        try {
            fileWorker.update(fileForOpen,"end end end");
            list = fileWorker.readFile(fileForOpen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 2; i < list.size()-2; i+=3){
            if(list.get(i) == "end")
                break;
            if(Integer.parseInt(list.get(i)) == 1){
                buttons[Integer.parseInt(list.get(i-1))].setAutoX();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                buttons[Integer.parseInt(list.get(i-1))].setAutoO();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
}