/*CONNOR WATSON  cs610  4204  prp  */

//NOTE 2megabytes should run about 15-30 seconds encoding

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.nio.file.Paths;

public class hdec_4204
{
  //main method
  public static void main(String[] args) throws IOException
  {
    String hufName = args[0];
    String outName = "";
    String [] arr = hufName.split(".huf");
    outName = arr[0];
    
    ArrayList<String> chars = new ArrayList<String>();
    ArrayList<String> prefixes = new ArrayList<String>();
    
    File file = new File(hufName);
    File outFile = new File(outName);
    boolean cr = outFile.createNewFile();
    /*if (outFile.createNewFile())
    {
      System.out.println(outName + " was created successfully.");
    }
    else
    {
      System.out.println(outName + " was NOT created.");
    }*/
		
    Scanner scan = new Scanner(file);
    while (scan.hasNext()) 
    {
      String line = scan.next();
      if (line.contains("_____"))
      {
        break;
      }
      String [] arr2 = line.split("SPLIT");
      if (arr2[0] == null)
      {
        chars.add("\n");
      }
      else
      {
        chars.add(arr2[0]);
      }
      prefixes.add(arr2[1]);
    }
    
    FileWriter out = new FileWriter(outFile);
    
    for (int i = 0; i < chars.size(); i++)
    {
      out.write(chars.get(i));
      out.write("|||");
      out.write(prefixes.get(i));
      out.write("\n");
    }
    
    out.write(scan.next());
    out.close();
    
    //code to delete input file
    boolean del = file.delete();
    /*
    if (del)
    {
      System.out.println(inName + " was deleted.");
    }
    else
    {
      System.out.println(inName + " was NOT deleted.");
    }*/
  }//End main method
}//End henc_4204 class