
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MyClient implements ActionListener {

    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    static JTextArea ta;
    static JTextField tf;

    MyClient() {
        JFrame f = new JFrame("Client");
        f.setSize(500, 600);
        f.getContentPane().setBackground(Color.GREEN);
        tf = new JTextField();
        tf.setBounds(10, 480, 380, 50);
        tf.setBackground(new Color(240, 180, 150));
        ta = new JTextArea();
        ta.setBackground(new Color(200, 240, 213));
        ta.setBounds(0, 0, 480, 450);
        ta.setEditable(false);

        JButton bt = new JButton("Send");
        bt.addActionListener(this);
        bt.setBounds(400, 480, 80, 50);
        bt.setBackground(new Color(40, 180, 150));
        f.add(ta);
        f.add(tf);
        f.add(bt);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        MyClient myClient = new MyClient();

        try {
            String magin = "";
            s = new Socket("localhost", 6666);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while (!magin.equals("bye")) {
                magin = din.readUTF();
                ta.setText(ta.getText().trim() + "\n\n\t\t\t\t  Server:\t " + magin);
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String magout;
            magout = tf.getText().trim();
            if (magout.equals("Bye")) {
                din.close();
                dout.close();
                s.close();
                System.exit(0);
            }
            if (magout.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Write Something");
            } else {
                dout.writeUTF(magout);
                ta.setText(ta.getText().trim() + "\n\n Client:\t" + magout);

                tf.setText("");
            }
        } catch (IOException e) {
        }

    }

}
