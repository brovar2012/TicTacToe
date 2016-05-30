import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by User on 11.04.2015.
 */
public class tableOfRecords implements ActionListener{
    int themeColor;
    int numberOfLevel;
    static int i =0;
    String recordsFile = "c:\\Users\\Dima\\IdeaProjects\\untitled1\\records.txt";
    public tableOfRecords ()
    {

    }

    public tableOfRecords (int themeColor ,int numberOfLevel)
    {
        this.themeColor = themeColor;
        this.numberOfLevel = numberOfLevel;

    }

    public void enterPlayerName() {
        final JFrame frame = new JFrame("Сохранение результата");
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);

        JButton button1 = new JButton("Сохранить");
        JLabel label1 = new JLabel("Имя игрока:");
        final JTextField text1 = new JTextField(15);
        final Date currentDate = new Date();


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fileWorker.update(recordsFile, text1.getText() + "; " + Integer.toString(numberOfLevel) + "; " + currentDate + ";");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                new mainClass();
                frame.setVisible(false);
            }
        });

        frame.add(label1, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
            new Insets(2, 2, 2, 2), 0, 0));
        frame.add(text1, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        frame.add(button1, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
            new Insets(2, 2, 2, 2), 0, 0));
        frame.setVisible(true);
        //i++;
        //System.out.println(i);
        frame.pack();
    }
    public void actionPerformed(ActionEvent e) {
        final JFrame frame = new JFrame("Рекорды");
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.pack();

        RecordConnect con = new RecordConnect();
        RecordTable dTable = new RecordTable(con.getList(), con.getCount());
        JTable table = new JTable(dTable);

        table.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(table);

        frame.add(scroll, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

class RecordTable extends AbstractTableModel {
    private ArrayList<String[]> list;
    private int Count;
    private String[] ColumnName = {"Name","Level","Date"};

    RecordTable(ArrayList<String[]> list, int Count){
        this.list = list;
        this.Count = Count;

    }

    @Override
    public int getColumnCount() {
        return Count;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return list.get(rowIndex)[columnIndex];
    }

    public String getColumnName(int col) {

        return ColumnName[col];
    }

}
class RecordConnect {
    Scanner sc = null;
    private ArrayList<String[]> list;
    private int Count;

    RecordConnect(){
        list = new ArrayList<String[]>();

        try{
            sc = new Scanner(new File("c:\\Users\\Dima\\IdeaProjects\\untitled1\\records.txt"));
            while(sc.hasNext()){
                String[] str = sc.nextLine().split(";");
                Count = str.length;
                list.add(str);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        finally{
            sc.close();
        }
    }

    public ArrayList<String[]> getList(){
        return list;
    }

    public int getCount(){
        return Count;
    }
}
