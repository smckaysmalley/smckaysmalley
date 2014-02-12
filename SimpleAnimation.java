import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleAnimation
{
   private int x;
   private int y;
   private JButton button;
   private MyDrawPanel drawPanel;


   public static void main (String [] args)
   {


       new SimpleAnimation().go();
   }


   public void go()
   {
      JFrame frame = new JFrame();
      x = 0;  
      y = 0;


      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


      drawPanel = new MyDrawPanel();
      button = new JButton("CLICK ME");


      button.addActionListener(new ButtonAction());


      frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
      frame.getContentPane().add(BorderLayout.NORTH, button);
      frame.setSize(250, 300);
      frame.setVisible(true);


   }


       class ButtonAction
       implements ActionListener
       {
          public void actionPerformed(ActionEvent event)
          {
             button.setText("OUCH!");


             new Thread(drawPanel).start();
          }
       }


       class MyDrawPanel
          extends JPanel
          implements Runnable
       {
          public void paintComponent(Graphics g)
          {
             g.setColor(Color.white);
             g.fillRect(0,0,this.getWidth(), this.getHeight());




             g.setColor(Color.green);            
             g.fillOval(x,y,40,40);
          }


          public void run()
          {
             Boolean direction = true; // true is forward -- false is backwards
             while(true)
             {
                if (direction)
                {
                   x++;
                   y++;
                   if (x == 210 || y == 260)
                   {
                      direction = false;
                   }
                }


                if (!direction)
                {
                   x--;
                   y--;
                   if (x == 0 || y == 0)
                   {
                      direction = true;
                   }
                }
                
                this.repaint();


                    try
                    {
                       Thread.sleep(10);
                    }
                    catch (Exception e)
                    {
                    }
             }
          }
       }
}

