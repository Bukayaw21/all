 
package computersecurity;
 
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client extends JFrame {
    JLabel l1,l2,l3;
    JTextField tf;
    JButton b1,b2,b3;
    JPanel p1;
    
    
    String str = "";
    Socket socket;
    String alphabet  = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
    String alphabets  = "abcdefghijklmnopqrstuvwxyz";
    String finEnc = "";
    String finDec = "";
    String result = "";
    public Client() throws Exception{
        setSize(600,500);  
        setTitle("Client");
        setLocation(650,50);
        setLayout(null);  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        l1 = new JLabel("Enter the text here");
        l1.setBounds(100,50,200,50);
        
        tf = new JTextField(15);
        tf.setBounds(100,120,400,100);
        
        l2 = new JLabel();
        l2.setBounds(150,20,200,50);
        
        
        l3 = new JLabel();
        l3.setBounds(150,50,200,50);
        
        socket = new Socket("localhost",8000);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        
        b1 = new JButton("send");
        b1.setBounds(250,250,100,50);
        
        b2 = new JButton("Encrypt");
        b2.setBounds(100,250,100,50);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                l2.setText(finEnc);
                 
            }
        });
        
        b3 = new JButton("Decryption");
        b3.setBounds(400,250,100,50);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                l3.setText(finDec);
                 
            }
        });
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String msg = tf.getText();
                    for(int i=0;i<msg.length();i++){
                        int pos = alphabets.indexOf(msg.charAt(i));
                        int enc = (pos + 3)%26;
                        char ind = alphabets.charAt(enc);
                        result += ind;
                        
                    }
                        output.writeUTF(result);
                       String  str2=(String)input.readUTF(); 
                        finEnc = str2;
                        //l2.setText(str2);
                        result = "";
                         for(int i=0;i<str2.length();i++){
                        int pos = alphabets.indexOf(str2.charAt(i));
                        int enc = (pos - 3)%26;
                         if(enc < 0){
                            enc = alphabets.length() + enc;
                        }
                        char ind = alphabets.charAt(enc);
                        result += ind;
                        finDec = result;
                    }
                         //l3.setText(result);
                        //output.flush();
                output.close();
                socket.close();
                } catch (IOException ex) {
                     }
                        
                    }  
        });
         
        p1 = new JPanel();
        p1.setBounds(100,330,400,100);
        p1.setBackground(Color.red);
        p1.setLayout(null);
        add(l1);
        add(tf);
        add(b1);
        add(p1);
        add(b2);
        add(b3);
        p1.add(l2);
        p1.add(l3);
        
          
        
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Client();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
    
}
