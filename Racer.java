import java.awt.event.*;
import javax.swing.Timer;
import java.io.*;

public class Racer
   implements Runnable
{
   private FileToucher mMonitor;
   private FileManipulator mCreator;
   private FileManipulator mDestroyer;
   private static final int DEFAULT_DELAY = 1000;

   public Racer()
   {
      //initialize mMonitor
      mMonitor = new OutputtingFileToucher();
      mCreator = new FileManipulator(true);
      mDestroyer = new FileManipulator(false);

   }

   ActionListener sequencePrinter =  new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            mMonitor.run();
            System.out.println("----------------------\n");
         }
      };

   public void run()
   {
      new Timer(DEFAULT_DELAY, sequencePrinter).start();
      
      mMonitor.setFileFilter(new PrefixFilter("file."));

      mCreator.start();
      mDestroyer.start();
   }

   public static void main(String [] args)
   {
      new Racer().run();
   }
}

class FileManipulator
   extends Thread
{
   private int mFileNumber;
   private Boolean liveElseDie;

   public FileManipulator()
   {
      this(false); //default instantiates a destroyer
   }

   public FileManipulator(Boolean type)
   {
      mFileNumber = 0;
      liveElseDie = type;
   }

   public File newFile(int num)
   {
      return new File(String.format("file.%05d", num));
   }

   public void run()
   {
      try
      {
         
         if (!liveElseDie)
         {
            Thread.sleep(2000);
         }

         while (true)
         {
            if (liveElseDie)
            {
               newFile(mFileNumber).createNewFile();
            }
            else
            {
               newFile(mFileNumber).delete();
            }

            ++mFileNumber;
            Thread.sleep(500);
         }
      }
      catch (Exception e)
      {         
      }
   }
}

class PrefixFilter
   implements FileFilter
{
   private String prefix;

   public PrefixFilter(String pre)
   {
      prefix = pre;
   }

   public boolean accept(File pFile)
   {
      return (pFile.isDirectory() || pFile.getName().startsWith(prefix));
   }
}