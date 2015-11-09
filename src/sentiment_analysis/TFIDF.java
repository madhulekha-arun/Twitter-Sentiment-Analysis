package try_sentiment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TFIDF {
	
	 TreeMap<String, Integer> wordMap = new TreeMap<String, Integer>();
	 ArrayList<TreeMap<String,Integer>> lineMap=new ArrayList<TreeMap<String,Integer>>();
	 int num_of_lines=0;
	 LinkedList<Integer> words_per_line=new LinkedList<Integer>();
	 
	
	void find()
	{
			 
		try{
			BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marunmo/Downloads/Tweet/analysis/sample1.txt"));	
			String line;
			String res[];
			StringTokenizer st;
		
			while((line=reader.readLine())!=null)
			{
				
				//if(line.contains("https:")) continue;
				//System.out.println(line);
				res=line.split("\t");
				//System.out.println(res[0]);
				if(res[0].isEmpty()) continue;
				System.out.println(res[0]);
				
				st=new StringTokenizer(res[0]);
				String word;
				int w=0;
				TreeMap<String,Integer> temp=new TreeMap<String,Integer>();
				while(st.hasMoreTokens())
				{
					word=st.nextToken();
					//System.out.println(word);
					w++;
					int old_key;
					if(temp.containsKey(word))
					{
						
						old_key=temp.get(word);
						temp.put(word,old_key+1);
						//System.out.println("Old"+temp);
					}
					else
					{
						temp.put(word,1);
						//System.out.println("Created newly"+temp);
					}
					
					if(wordMap.containsKey(word))
					{
						old_key=wordMap.get(word);
						wordMap.put(word,old_key+1);
					}
					else
					{
						wordMap.put(word,1);
					}
				}
				lineMap.add(temp);
				words_per_line.add(w);
				num_of_lines++;
			}		
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
	}
	
	void display()
	{
		Iterator it=lineMap.iterator();
		TreeMap<String,Integer> temp;
		System.out.println("line map");
		while(it.hasNext())
		{
			//Iterator itt=it.next().
			//temp=it.next();
			System.out.println(it.next());
		}
		
		System.out.println("wordmap");
		  for (Map.Entry<String,Integer> entry : wordMap.entrySet()) {
		        Integer value = entry.getValue();
		        String key = entry.getKey();
		        System.out.print(key+"->"+value+"\t");
		   }
		Iterator itt=words_per_line.iterator();
		System.out.println("words per line");
		while(itt.hasNext())
		{
			System.out.print(itt.next());
		}
		System.out.println("Number of lines : "+num_of_lines);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TFIDF tf=new TFIDF();
		tf.find();
		tf.display();

	}

}
