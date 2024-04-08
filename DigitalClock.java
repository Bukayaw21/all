import java.awt.*;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
public class DigitalClock extends JFrame {
private JLabel timeLabel;
public DigitalClock() {
setTitle("Bukayaw's Digital Clock");
setSize(300, 100);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setResizable(true);
setLayout(new GridLayout(1, 1));
getContentPane().setBackground(Color.GREEN);
timeLabel = new JLabel();
timeLabel.setHorizontalAlignment(JLabel.CENTER);
timeLabel.setFont(new Font("Arial", Font.PLAIN, 36));
add(timeLabel);
setVisible(true);
// Update the time every second using a thread
Thread updateTimeThread = new Thread(() -> {
while (true) {
updateTime();
try {
Thread.sleep(1000);
} catch (InterruptedException e) {
e.printStackTrace();
}
}
});
updateTimeThread.start();
}
private void updateTime() {
Date now = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
String timeString = sdf.format(now);
timeLabel.setText(timeString);
}
public static void main(String[] args) {
new DigitalClock();
}
}


