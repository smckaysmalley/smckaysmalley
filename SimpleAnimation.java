import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleAnimation
{
   private int x;
   private int y;
   private JButton startButton;
    private JButton stopButton;
   private MyDrawPanel drawPanel;
    private Boolean move;
    private JFrame frame;

   public static void main (String [] args)
   {
       new SimpleAnimation().go();
   }

    public SimpleAnimation()
    {
	x = 0;
	y = 0;
	move = false;
	frame = new JFrame();
    }

   public void go()
   {
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


      drawPanel = new MyDrawPanel();
      startButton = new JButton("CLICK ME");
      stopButton = new JButton();

      startButton.addActionListener(new ButtonAction());

      frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
      frame.getContentPane().add(BorderLayout.NORTH, startButton);
      frame.setSize(250, 300);
      frame.setVisible(true);


   }


       class ButtonAction
       implements ActionListener
       {
          public void actionPerformed(ActionEvent event)
          {
	      move = true;
            startButton.setText("OUCH!");
	    stopButton.addActionListener(new StopAction());
	    stopButton.setText("MAKE IT STOP!!!");
	    frame.getContentPane().add(BorderLayout.SOUTH, stopButton);

             new Thread(drawPanel).start();
          }
       }

    class StopAction
	implements ActionListener
    {
	public void actionPerformed(ActionEvent even)
	{
	    move = false;
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
             while(move == true)
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

