
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class mainClass {
    private static ArrayList<File> files;
    private static FileInputStream fis = null;
    private static DataInputStream dis = null;
    private static List<Integer> scores;
    private static String fileName;
    private static Map<Integer, String> replays;
    private static int size = 0;

    int themeColor = 2;

    public mainClass() {
        final JFrame frame = new JFrame("Крестики-нолики");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.WHITE);

        JLabel background = new JLabel(new ImageIcon("src\\23.png"));
        frame.add(background);
        background.setLayout(new GridBagLayout());

        final JButton buttonPlay = new JButton(" Играть  ");
        final JButton buttonRecord = new JButton(" Рекорды ");
        final JButton buttonExit = new JButton("  Выход  ");
        JLabel white = new JLabel("Светлый фон");
        JLabel black = new JLabel("Темный фон");
        final JRadioButton whiteButton = new JRadioButton();
        final JRadioButton blackButton = new JRadioButton();
        whiteButton.setBackground(Color.WHITE);
        blackButton.setBackground(Color.BLACK);

        final JPanel whitePanel = new JPanel();
        final JPanel blackPanel = new JPanel();
        whitePanel.setLayout(new FlowLayout());
        blackPanel.setLayout(new FlowLayout());
        buttonPlay.setEnabled(false);
        // buttonRecord.setEnabled(false);
        whitePanel.add(whiteButton);
        whitePanel.add(white);
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setForeground(Color.BLACK);
        blackPanel.add(blackButton);
        blackPanel.add(black);
        blackPanel.setBackground(Color.BLACK);
        whitePanel.setForeground(Color.WHITE);
        white.setForeground(Color.BLACK);
        black.setForeground(Color.WHITE);

        buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonRecord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });
        buttonRecord.addActionListener(new tableOfRecords(themeColor, 0));


        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        whiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.WHITE);
                whitePanel.setVisible(false);
                blackPanel.setVisible(false);
                themeColor = 1;
                buttonPlay.setEnabled(true);
                buttonRecord.setEnabled(true);
                buttonPlay.addActionListener(new play(themeColor));
                // buttonRecord.addActionListener(new tableOfRecords(themeColor,0));
            }
        });

        /*buttonRecord.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });*/

        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.BLACK);
                whitePanel.setVisible(false);
                blackPanel.setVisible(false);
                buttonExit.setBackground(Color.BLACK);
                buttonPlay.setBackground(Color.BLACK);
                buttonRecord.setBackground(Color.BLACK);
                buttonExit.setForeground(Color.WHITE);
                buttonPlay.setForeground(Color.WHITE);
                buttonRecord.setForeground(Color.WHITE);
                themeColor = 0;
                buttonPlay.setEnabled(true);
                // buttonRecord.setEnabled(true);
                buttonPlay.addActionListener(new play(themeColor));
                //buttonRecord.addActionListener(new tableOfRecords(themeColor,0));
            }
        });


        buttonExit.setBackground(Color.LIGHT_GRAY);
        buttonPlay.setBackground(Color.LIGHT_GRAY);
        buttonRecord.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints buttonPlayLocation = new GridBagConstraints();
        buttonPlayLocation.gridx = 1;//- номер ячейки в строке
        buttonPlayLocation.gridy = 1;//- номер ячейки в столбце
        buttonPlayLocation.gridwidth = 1;//- количество ячеек в строке занимаемое компонентов
        buttonPlayLocation.gridheight = 1;//- количество ячеек в столбце занимаемое компонентом

        buttonPlayLocation.anchor = GridBagConstraints.CENTER;//- растяжение для заполнения ячейки
        buttonPlayLocation.ipadx = 10; //- горизонтальные и вертикальные поля вокруг контейнера по x
        buttonPlayLocation.ipady = 10; //- по y
        buttonPlayLocation.weightx = 1;//; - растяжение компонентов при изменении размеров контейнера по x
        buttonPlayLocation.weighty = 1;//; - по y
        buttonPlayLocation.fill = GridBagConstraints.HORIZONTAL;
        buttonPlayLocation.insets = new Insets(80, 70, 10, 70);

        background.add(buttonPlay, buttonPlayLocation);
        background.add(buttonRecord, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(1, 70, 10, 70), 10, 10));
        background.add(buttonExit, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(1, 70, 60, 70), 10, 10));
        background.add(whitePanel, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 1, 1));
        background.add(blackPanel, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 1, 1));
        frame.setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        new mainClass();
        try {
            fillList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printScoresList();
        sortScoresScala();
        //sortScoresJava();
        writeToFile();
        Statistics();
        createNewNotation();

    }

    public static void writeToFile() {
        fileWorker.write("c:\\Users\\Dima\\IdeaProjects\\untitled1\\sortingNotation.txt", "");
        String string;
        for (int i = 0; i < size; i++) {
            string = (files.get(i).getName()) + " " + scores.get(i);
            try {
                fileWorker.update("c:\\Users\\Dima\\IdeaProjects\\untitled1\\sortingNotation.txt", string);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }
    }

    public static void sortScoresJava() throws IOException {
        System.out.println("\n");
        System.out.println("SORTING IN JAVA");
        long timeout = System.currentTimeMillis();
        for (int i =0;i<1000;i++)
            Collections.sort(scores);
        timeout = System.currentTimeMillis() - timeout;
        System.out.println("\n");
        System.out.println(timeout + " msec");
        printScoresList();
        System.out.println("\n");
    }

    public static void sortScoresScala() {
        size = files.size();
        List list = new ArrayList<Integer>();
        System.out.println("\n");
        System.out.println("SORTING IN SCALA");
        long timeout = System.currentTimeMillis();
        for (int i =0;i<1000;i++) {
            list = Sorter.sortScores(scores);
        }
        timeout = System.currentTimeMillis() - timeout;
        System.out.println(timeout + " msec");
        System.out.println("\n");
        scores = list;
        printScoresList();
        System.out.println("\n");
    }

    public static void setFileList() {
        files = new ArrayList<File>();
        File[] fList;
        File replaysDir = new File("c:\\Users\\Dima\\IdeaProjects\\untitled1\\repeatGame\\");
        fList = replaysDir.listFiles();
        for (int i = 0; i < fList.length; i++) {
            if (fList[i].isFile()) {
                files.add(fList[i]);
                size++;
            }
        }
    }

    public static void fillList() throws IOException {
        int score = 0;
        setFileList();
        scores = new ArrayList<Integer>();
        for (int i = 0; i < files.size(); i++) {
            fis = new FileInputStream(files.get(i).getPath());
            dis = new DataInputStream(fis);
            int j = 0;
            ArrayList<String> list = new ArrayList<String>();
            try {
                list = fileWorker.readFile(files.get(i).getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int k = 2; k < list.size() - 2; k += 3) {
                if (list.get(k) == "end")
                    break;
                j++;
            }
            score = j;
            scores.add(score);
            dis.close();
            fis.close();
        }
    }

    public static void printScoresList() {
        for (Integer s : scores) {
            System.out.print(s + " ");
        }
    }

    public static void printFileList() {
        for (File f : files) {
            System.out.println(f.getName());
        }
    }

    public static void Statistics() throws FileNotFoundException {
        int numberOfX=0;
        int numberOfO=0;
        int kX =0;
        int kO=0;
        int tempX [] = new int [size*5];
        int tempO [] = new int [size*5];
        int arrOfCourseForX [] = null;
        int arrOfCourseForO []= null;
        ArrayList<String> list = new ArrayList<String>();
        for (int j = 0; j < size; j++) {
            list = fileWorker.readFile(files.get(j).getPath());
            for (int i = 2; i < list.size() - 2; i += 3) {
                if (list.get(i) == "end")
                    continue;
                if (Integer.parseInt(list.get(i)) == 1)
                {
                    tempX[kX]= Integer.parseInt(list.get(i-1));
                    numberOfX++;
                    kX++;
                }
                else
                {
                    tempO[kO]= Integer.parseInt(list.get(i-1));
                    numberOfO++;
                    kO++;
                }
            }
        }
        arrOfCourseForO = new int[kO];
        arrOfCourseForX = new int[kX];
        for (int i =0;i<arrOfCourseForX.length;i++)
            arrOfCourseForX[i] = tempX[i];
        for (int i =0;i<arrOfCourseForO.length;i++)
            arrOfCourseForO[i] = tempO[i];

        System.out.println("Ходов всего крестиков и ноликов: "+ (numberOfX + numberOfO) );
        System.out.println("Ходов крестиков: "+ numberOfX);
        System.out.println("Ходов ноликов: " + numberOfO);

        System.out.println("");
        System.out.println("Для крестиков");
        Sorter.numberOfCourseInCage(arrOfCourseForX);
        System.out.println("Для ноликов");
        Sorter.numberOfCourseInCage(arrOfCourseForO);
    }
    public static void createNewNotation()
    {
        ArrayList<String> list = new ArrayList<String>();
        String tempPath;
        String tempFileName;
        for (int j = 0; j < size; j++) {
            tempPath = files.get(j).getPath();
            tempFileName = files.get(j).getName();
            Sorter.translateNotation(tempPath,tempFileName);
        }
    }
}

