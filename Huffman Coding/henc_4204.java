/*CONNOR WATSON  cs610  4204  prp  */

//NOTE 2megabytes should run about 15-30 seconds encoding

import java.io.*;
import java.util.*;
import java.nio.file.Paths;

public class henc_4204
{ 
  //MergeS method
  public static ArrayList<Character> MS(ArrayList<Character> arr) 
  {
    ArrayList<Character> leftArr = new ArrayList<Character>();
    ArrayList<Character> rightArr = new ArrayList<Character>();
    int center;
 
    if (arr.size() == 1) 
    {    
        return arr;
    } 
    else 
    {
        center = arr.size()/2;
        //Copy the leftArr half of arr into the leftArr
        for (int i = 0; i < center; i++) 
        {
          leftArr.add(arr.get(i));
        }
 
        //Copy the rightArr half of arr into the new arraylist
        for (int i = center; i < arr.size(); i++) 
        {
          rightArr.add(arr.get(i));
        }
 
        //S the leftArr and rightArr halves of the arraylist
        leftArr  = MS(leftArr);
        rightArr = MS(rightArr);
 
        //Merge the results back together
        merge(leftArr, rightArr, arr);
    }
    return arr;
  }//End MS method
  
  //Merging method for MergeS
  private static void merge(ArrayList<Character> leftArr, ArrayList<Character> rightArr, ArrayList<Character> arr) 
  {
    int leftIndex = 0;
    int rightIndex = 0;
    int arrIndex = 0;
    
    while (leftIndex < leftArr.size() && rightIndex < rightArr.size()) 
    {
        if ((leftArr.get(leftIndex).compareTo(rightArr.get(rightIndex))) < 0) 
        {
          arr.set(arrIndex, leftArr.get(leftIndex));
          leftIndex++;
        } 
        else 
        {
          arr.set(arrIndex, rightArr.get(rightIndex));
          rightIndex++;
        }
        arrIndex++;
    }
 
    ArrayList<Character> rest;
    int restIndex;
    if (leftIndex >= leftArr.size()) 
    {
      //leftArr ArrayList is done
      rest = rightArr;
      restIndex = rightIndex;
    } 
    else 
    {
      //rightArr ArrayList is done
      rest = leftArr;
      restIndex = leftIndex;
    }
 
    //Copy the rest of leftArr or rightArr
    for (int i=restIndex; i<rest.size(); i++) 
    {
      arr.set(arrIndex, rest.get(i));
      arrIndex++;
    }
  }//End merge method
  
  //MergeS method
  public static ArrayList<Integer> MSI(ArrayList<Integer> arr) 
  {
    ArrayList<Integer> leftArr = new ArrayList<Integer>();
    ArrayList<Integer> rightArr = new ArrayList<Integer>();
    int center;
 
    if (arr.size() == 1) 
    {    
        return arr;
    } 
    else 
    {
        center = arr.size()/2;
        //Copy the leftArr half of arr into the leftArr
        for (int i = 0; i < center; i++) 
        {
          leftArr.add(arr.get(i));
        }
 
        //Copy the rightArr half of arr into the new arraylist
        for (int i = center; i < arr.size(); i++) 
        {
          rightArr.add(arr.get(i));
        }
 
        //S the leftArr and rightArr halves of the arraylist
        leftArr  = MSI(leftArr);
        rightArr = MSI(rightArr);
 
        //Merge the results back together
        mergeI(leftArr, rightArr, arr);
    }
    return arr;
  }//End MS method
  
  //Merging method for MergeS
  private static void mergeI(ArrayList<Integer> leftArr, ArrayList<Integer> rightArr, ArrayList<Integer> arr) 
  {
    int leftIndex = 0;
    int rightIndex = 0;
    int arrIndex = 0;
    
    while (leftIndex < leftArr.size() && rightIndex < rightArr.size()) 
    {
        if ((leftArr.get(leftIndex).compareTo(rightArr.get(rightIndex))) < 0) 
        {
          arr.set(arrIndex, leftArr.get(leftIndex));
          leftIndex++;
        } 
        else 
        {
          arr.set(arrIndex, rightArr.get(rightIndex));
          rightIndex++;
        }
        arrIndex++;
    }
 
    ArrayList<Integer> rest;
    int restIndex;
    if (leftIndex >= leftArr.size()) 
    {
      //leftArr ArrayList is done
      rest = rightArr;
      restIndex = rightIndex;
    } 
    else 
    {
      //rightArr ArrayList is done
      rest = leftArr;
      restIndex = leftIndex;
    }
 
    //Copy the rest of leftArr or rightArr
    for (int i=restIndex; i<rest.size(); i++) 
    {
      arr.set(arrIndex, rest.get(i));
      arrIndex++;
    }
  }//End mergeI method
  
  //main method
  public static void main(String[] args) throws IOException
  {
    FileInputStream in = null;
    FileOutputStream out = null;
    PrintStream ps = null;
    String curDir = Paths.get(".").toAbsolutePath().normalize().toString();
    
    String inName = args[0];
    String hufNameT = inName + ".txt";
    String hufName = inName + ".huf";
    try
    {
      //Read each byte into characters and add them to a long string
      //Then turn the string into a chararray
      in = new FileInputStream(args[0]);
      String info = "";
      int c;
      while ((c = in.read()) != -1) 
      {
        info = info + Character.toString((char)c);
      }
      
      char[] chars = info.toCharArray();//Array of the characters in the string (len 23 for test input)
      
      ArrayList<Character> uniqueChars = new ArrayList<Character>();
      ArrayList<Character> allChars = new ArrayList<Character>();
      for (int i = 0; i < chars.length; i++)
      {
        allChars.add(chars[i]);
      }
      //Get a List of all the chars which are present in the string
      //No repeating the characters!
      
  		for (int i = 0; i < chars.length; i++) 
      {
  			if (!(uniqueChars.contains(chars[i]))) 
        {
  				uniqueChars.add(chars[i]);
  			}
  		}
      MS(allChars);
      MS(uniqueChars);
      String uniqueInfo = "";
      //Get the unique characters after MSing into a string
      //System.out.println("Arranged unique characters");
      for (int i = 0; i < uniqueChars.size(); i++)
      {
        String ch = Character.toString(uniqueChars.get(i));
        uniqueInfo = uniqueInfo + ch;
      }
      /*
      System.out.println("\n_____");
      System.out.println(uniqueInfo);
      System.out.println("All characters arranged");
      for (int i = 0; i < allChars.size(); i++)
      {
        System.out.print(Character.toString(allChars.get(i)));
       }
       System.out.println("\n_____");
      */
      //Initialize an array that will store the frequencies
      //of the uniqueChar
      ArrayList<Integer> freqs = new ArrayList<Integer>();
      int fIdx = 0; //index in the arraylist
      int f = 1;
      freqs.add(1);
      for (int x = 1; x < allChars.size(); x++)
      {
        if (allChars.get(x).compareTo(allChars.get(x-1)) == 0)
        {
          f += 1;
          freqs.set(fIdx, f);
        }
        else
        { 
          fIdx += 1;
          f = 1;
          freqs.add(f);
        }
      }
      
      MSI(freqs); //This arranges the frequencies in increasing order
      /*
      System.out.println("\nAll frequencies:");
      for (int i = 0; i < freqs.size(); i++)
      {
        System.out.println(Integer.toString(freqs.get(i)));
      }
      System.out.println("_____");
      */
      
      //Create an array representation of heap
      //This sets the prefix codes
      String[] prefixes = new String[freqs.size()];
      for (int i = 0; i < prefixes.length; i++)
      {
        prefixes[i] = "0";
      }
      for (int i = 0; i < prefixes.length; i++)
      {
        int left = 2*i + 1;
        int right = 2*i + 2;
        if (left <= prefixes.length -1)
        {
          prefixes[left] = prefixes[i] + "0";
        }
        else
        {
          break;
        }
        if (right <= prefixes.length -1)
        {
          prefixes[right] = prefixes[i] + "1";
        }
        else
        {
          break;
        }
      }
      
      File hufFileT = new File(hufNameT);
      File hufFile = new File(hufName);
      boolean createF = hufFileT.createNewFile();
      /*
      if (createF)
      {
        System.out.println(hufNameT + " was created successfully.");
      }
      else
      {
        System.out.println(hufNameT + " was NOT created.");
      }
      */
      //Code to delete input file
      File inF = new File(inName);
      
      boolean del = inF.delete();
      /*
      if (del)
      {
        System.out.println(inName + " was deleted.");
      }
      else
      {
        System.out.println(inName + " was NOT deleted.");
      }
      */
      out = new FileOutputStream(hufNameT);
      ps = new PrintStream(out);
      
      for (int i = 0; i < prefixes.length; i++)
      {
        String line = String.format("%sSPLIT%s\n", Character.toString(uniqueChars.get(i)), prefixes[i]);
        ps.print(line);
      }
      ps.print("_____\n");
      for (int i = 0; i < chars.length; i++)
      {
        String tC = Character.toString(chars[i]);
        int idx = uniqueInfo.indexOf(tC);
        ps.print(prefixes[idx]);
      }
      ps.print("\n_____\n");
      
      boolean ren = hufFileT.renameTo(hufFile);
      /*
      if (ren)
      {
        System.out.println("rename to " + hufName + " successful");
      }
      else
      {
        System.out.println("rename NOT successful");
      }
      */
    }//End try block
    finally
    {
      if (in != null) 
      {
      
        in.close();
      }
      if (out != null) 
      {
        out.close();
        ps.close();
      }
    }//End finally block
  }//End main method
}//End henc_4204 class