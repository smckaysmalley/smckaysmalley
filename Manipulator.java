import java.io.File;

public class Manipulator
   extends Thread
{

   private int mFileNumber;

   public Manipulator()
   {
      mFileNumber = 0;
   }

   public File newFileN(int number)
   {
      return new File(String.format("file.%05d", number));
   }
}