import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;


/**
 * Created by Dima on 01.03.2015.
 */
public class XOButton extends JButton implements ActionListener{
    ImageIcon X, O;
    int value = 0;
    int XorO =0 ;
    public static int throughCourse=0;

    public XOButton(int XorO) {
        this.XorO=XorO;
        X = new ImageIcon(this.getClass().getResource("X.png"));
        O = new ImageIcon(this.getClass().getResource("O.png"));
        this.addActionListener(this);
    }

    public void setX()
    {
        X = new ImageIcon(this.getClass().getResource("X.png"));
    }
    public void setO()
    {
        O = new ImageIcon(this.getClass().getResource("O.png"));

    }
    public void setAutoX()
    {
        setIcon(X);
        value =1;
        removeActionListener(this);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setAutoO()
    {
        setIcon(O);
        value =2;
        removeActionListener(this);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (XorO == 1) {
            value = 1;
            setIcon(X);
        }
        if (XorO == 2) {
            value = 2;
            setIcon(O);
        }
        if (XorO == 0) {
            if (throughCourse % 2 == 0) {
                value = 1;
                setIcon(X);
            } else {
                value = 2;
                setIcon(O);
            }
            throughCourse++;
        }
        removeActionListener(this);
    }
    public void remove()
    {
        removeActionListener(this);
    }
}