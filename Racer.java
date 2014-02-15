import java.awt.even.ActionListener;
import javax.swing.Timer;

class Racer
   implements ActionListener
{
   private FileToucher mMonitor;
   private Creator create;
   private Creator destroy;

   public Racer()
   {
      //initialize mMonitor
      mMonitor = new FileToucher();
   }

   public void actionPerformed()
   {
      mMonitor.run();
   }

   public void main()
   {
      
   }
}

class Creator
   extends Manipulator, Thread
{
   public void run()
   {
      while (true)
      {
         try
         {
            if (newFileN(mFileNumber).createNewFile())
            {
               mFileNumber++;
            }
            Thread.sleep(500);
         }
         catch (Exception e)
         {
         }
      }
   }
}

class Destroyer
   extends Manipulator, Thread
{
   public void run()
   {
      while (true)
      {
         try
         {
            if (newFileN(mFileNumber).delete())
            {
               mFileNumber++;
            }
            Thread.sleep(500);
         }
         catch (Exception e)
         {
         }
      }
   }
}