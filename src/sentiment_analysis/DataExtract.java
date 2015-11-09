package try_sentiment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataExtract {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marunmo/Downloads/Tweet/raw_2014-11-27_14_27_04"));	
			String line,res[];
			BufferedWriter bw = null;	
		     bw = new BufferedWriter(new FileWriter("C:/Users/marunmo/Downloads/Tweet/full.txt", true));
		     
			while((line=reader.readLine())!=null)
			{
	
				res=line.split("\t");
				//System.out.println(res[0]);
				if(res[0].isEmpty()) continue;
				//System.out.println(res[0]);
				 bw.write(res[0]);
			     bw.newLine();
			}
					
		}
		catch(Exception e)
		{
			
			e.getStackTrace();
		}

	}

}
