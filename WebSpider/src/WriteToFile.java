//write content of zhihu to local file
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class WriteToFile {
	
   public static boolean createNewFile(String filePath)
   {
	   boolean isSuccess=true;
	   String filePathTurn=filePath.replaceAll("\\\\", "/");
	   int index = filePathTurn.lastIndexOf("/");  
       String dir = filePathTurn.substring(0, index);  
       // create directory
       File fileDir = new File(dir);  
       isSuccess = fileDir.mkdirs();  
       // create file
       File file = new File(filePathTurn);  
       try {  
           isSuccess = file.createNewFile();  
       } catch (IOException e) {  
           isSuccess = false;  
           e.printStackTrace();  
       }  
       return isSuccess;  
   }
	public static boolean writeIntoFile(String content,String filePath,boolean isAppend)
	{
		boolean isSuccess = true;  
        // get director
        int index = filePath.lastIndexOf("/");  
        String dir = filePath.substring(0, index);  
        // create director
        File fileDir = new File(dir);  
        fileDir.mkdirs();  
        // create file  
        File file = null;  
        try {  
            file = new File(filePath);  
            file.createNewFile();  
        } catch (IOException e) {  
            isSuccess = false;  
            e.printStackTrace();  
        }  
        // write into file
        FileWriter fileWriter = null;  
        try {  
            fileWriter = new FileWriter(file, isAppend);  
            fileWriter.write(content);  
            fileWriter.flush();  
        } catch (IOException e) {  
            isSuccess = false;  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fileWriter != null)  
                    fileWriter.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
  
        return isSuccess;  
	}
}
