
import java.awt.Color;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;

public class MyServer implements ActionListener {

    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    static JTextArea ta;
    static JTextField tf;

    MyServer() {
        JFrame f = new JFrame("Server");
        f.setSize(500, 600);
        f.getContentPane().setBackground(Color.BLUE);
        tf = new JTextField();
        tf.setBounds(10, 480, 380, 50);
        tf.setBackground(new Color(240, 150, 150));
        ta = new JTextArea();
        ta.setSize(480, 450);
        ta.setEditable(false);
        ta.setBackground(new Color(240, 180, 255));

        JButton bt = new JButton("Send");
        bt.addActionListener(this);
        bt.setBounds(400, 480, 80, 50);
        bt.setBackground(new Color(222, 45, 199));

        JPanel p = new JPanel();
        p.add(bt);
        p.add(tf);

        f.add(ta);
        f.add(tf);
        f.add(bt);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        MyServer myServer = new MyServer();
        String magin = "";
        try {
            ss = new ServerSocket(6666);
            s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while (!magin.equals("bye")) {
                magin = din.readUTF();
                ta.setText(ta.getText().trim() + "\n\n\t\t\t\t  Client:\t" + magin);
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        try {
            String magout;
            magout= tf.getText().trim();
            if(magout.equals("Bye")){
            din.close();
            dout.close();
            ss.close();
           s.close();
           System.exit(0);
            }
            if(magout.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Write Something");
            }
            else{
            dout.writeUTF(magout);
              ta.setText(ta.getText().trim() + "\n\nServer:\t" + magout);
            tf.setText("");
            }
        } catch (IOException e) {
        }

    }

}
