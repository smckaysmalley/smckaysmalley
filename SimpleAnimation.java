import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleAnimation
{
   private int x;
   private int y;
   private JButton startButton;
    private JButton stopButton;
    private JButton directionButton;
   private MyDrawPanel drawPanel;
    private Boolean move;
    private JFrame frame;
    private Boolean direction; // true is forward -- false is backwards
    private JFrame southFrame;

   public static void main (String [] args)
   {
       new SimpleAnimation().go();
   }

    public SimpleAnimation()
    {
	x = 0;
	y = 0;
	move = false;
	direction = true;
	frame = new JFrame();
	drawPanel = new MyDrawPanel();
	startButton = new JButton("Start!");
	stopButton = new JButton();       
	directionButton = new JButton();
    }

   public void go()
   {
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      startButton.addActionListener(new ButtonAction());

      frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
      frame.getContentPane().add(BorderLayout.NORTH, startButton);
      frame.setSize(250, 300);
      frame.setVisible(true);
      frame.getContentPane().setBackground(Color.white);

   }


    class ButtonAction
	implements ActionListener
    {
	public void actionPerformed(ActionEvent event)
	{
	    move = true;
            startButton.setText("FASTER!!!");

	    //	    frame.getContentPane().add(BorderLayout.SOUTH, southFrame);

	    //code to add stop button
	    stopButton.addActionListener(new StopAction());
	    stopButton.setText("MAKE IT STOP!!!");
	    frame.getContentPane().add(BorderLayout.SOUTH, stopButton);

	    //code to add the direction button
	    //directionButton.addActionListener(new DirectionAction());
	    //directionButton.setText("PARTY TIME!");
	    //	    southFrame.getContentPane().add(BorderLayout.WEST, directionButton);
	    
	    new Thread(drawPanel).start();
	}
    }
    
    class StopAction
	implements ActionListener
    {
	public void actionPerformed(ActionEvent event)
	{
	    move = false;
	    startButton.setText("Start!");
	}
    }

    class DirectionAction
	implements ActionListener
    {
	public void actionPerformed(ActionEvent event)
	{

	}
    }

       class MyDrawPanel
          extends JPanel
          implements Runnable
       {
	   public void paintComponent(Graphics g)
          {
             g.setColor(Color.white);
             g.fillOval(x-10,y-10,50,50);
	     g.fillOval(x+1,y+1,100,100);

             g.setColor(Color.red);            
             g.fillOval(x,y,40,40);	
          }


          public void run()
          {
             while(move) //move is the is the permision to animate the object
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


		else
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

