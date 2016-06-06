
import java.net.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class UrlReader
{
    public static void main(String[] args)
    {

        String output = getUrlContents("https://www.reddit.com");
        String finalString = TakeAll(output);
        System.out.println(finalString);
        makeLog(finalString);
    }
    
    private static String getUrlContents(String theURL) 
    {
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL(theURL);
            URLConnection urlConnection = url.openConnection();
            BufferedReader buff = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = buff.readLine()) != null)
            {
                content.append(line + "\n");          
            }
            buff.close();   
        }
            catch (Exception e)
            {
                System.out.println("URL cannot be accessed");
            return content.toString();
            } 
        return content.toString();
    }
    
    private static String TakeAll (String output)
    {
       String swoop = null;
       StringBuilder fullParsed = new StringBuilder();
         for(int i = 0; i < output.length(); i++)
            {
                       //Enter the if statement when our loop realizes that it has come across
                       //the HTML leading up to a title
                 if ((output.charAt(i) == 't') && (output.charAt(i + 1) == 'a') && (output.charAt(i + 2) == 'b') && (output.charAt(i + 3) == 'i') && (output.charAt(i + 4) == 'n')
                          && (output.charAt(i + 5) == 'd') && (output.charAt(i + 6) == 'e') && (output.charAt(i + 7) == 'x') && (output.charAt(i + 8) == '=') && (output.charAt(i + 9) == '"')
                          && (output.charAt(i + 10) == '1') && (output.charAt(i + 11) == '"') && (output.charAt(i + 12) == ' ') && (output.charAt(i + 13) == '>'))
                 {
                     while((output.charAt(i) != '<'))
                     {
                         fullParsed.append(output.charAt(i));
                            i++;
                     }
                                fullParsed.append(output.charAt(i));
                     
                 }   
                    swoop = fullParsed.toString();
            }

         return swoop;
    }
    
    private static void makeLog(String finalString)
    {
                 String giveString = finalString;
                 
                        //Logging our results and naming the log after the date
                     Date getDate = new Date();
                     SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-SS");
                     File scrapeLog = new File(formatDate.format(getDate) + ".txt");
                     try
                     {
                     scrapeLog.createNewFile();
                     FileWriter fileCreate = new FileWriter(scrapeLog.getAbsolutePath());
                     BufferedWriter startBuffer = new BufferedWriter(fileCreate);
                     fileCreate.write(giveString);
                     fileCreate.close();
                     }
                     
                     catch(IOException e)
                     {
                         System.out.println("Failed to write log");
                     }
         
    }
    
    
    
    
    
    
    
    
}
