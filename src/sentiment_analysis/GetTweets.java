package try_sentiment;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;



public class GetTweets{
	
	public static void display(HashSet<String> container)
	{    
        Iterator<String> it=container.iterator();
        try {	
		//	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
			//Date date = new Date();
			
		//	String dateappend=dateFormat.format(date);
			//System.out.println(dateappend);
		//	String name="C:/Users/marunmo/Downloads/Tweet/raw_"+dateappend+".txt";
			File file = new File("C:/Users/marunmo/Downloads/Tweet/full_raw.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			while(it.hasNext())
			{
				bw.write(it.next());
				bw.newLine();
			}
			
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashSet<String> fill()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("syqo7kML7SUC7v7T7HwtVVoZF")
		  .setOAuthConsumerSecret("2oOvEbsLrmX5DcGCwlmCWIbDoBMGGttiN3SwiRUbowR3ui0AIN")
		  .setOAuthAccessToken("1012551798-sspPUYedIfArC5f45ZTfEIZ5s3A0rfLMDMGTIsX")
		  .setOAuthAccessTokenSecret("bfSkmUt53ArZYk0pHfIQZCAoh60c7Ks2M9M0zxR9dXLzV");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		//System.out.println("heyyyyy");
		HashSet<String> container=new HashSet<String>();
        try {      		
        		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marunmo/Downloads/Tweet/maxid.txt"));
        		String lineread;
        		Long stored=Long.parseLong(String.valueOf("-1"));
        		if((lineread=reader.readLine())!=null)
        		stored=Long.parseLong(lineread);
        		reader.close();
	        	Query k;
	            Query query = new Query("walmart price low");
	            query.count(100);
	            if(stored!=-1)
	            { query.sinceId(stored);
	            	query.sinceId(stored);
	            }
	            Long last=Long.parseLong(String.valueOf("0"));
	            Long first=Long.parseLong(String.valueOf("0"));
	            QueryResult result;
	            int ki;
	            int p=0;
	            do {
		                result = twitter.search(query);
		                List<Status> tweets = result.getTweets();
		                int i=1;
		                for (Status tweet : tweets) {
		                	if(first<tweet.getId()) first=tweet.getId();
		                	if(last>tweet.getId()) last=tweet.getId();
		                	//container.add(analyzedString(tweet.getText())+"\t"+tweet.getId()+"\t"+tweet.getCreatedAt()+"\t"+tweet.getGeoLocation()+"\t"+tweet.getUser().getScreenName() + "\t" +tweet.getFavoriteCount()+"\t"+ tweet.getText());
		                	container.add(tweet.getText());
		                }    
			             System.out.println( (container.size()-p)+" added   Max ID : "+last+" Since Id : "+result.getSinceId());
			             ki=container.size() - p;
			             query=new Query("walmart price OR walmart dollars OR walmart discount or walmart black friday");
			             query.count(100);
			             query.maxId(last);
			             query.setMaxId(last);
			             p=container.size();
	            } while (ki>5);
	            
				File file = new File("C:/Users/marunmo/Downloads/Tweet/maxid.txt");
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				//System.out.println(last);
				bw.write(Long.toString(first));
				bw.newLine();
				//bw.write("hiiii");
				bw.close();
            
        } catch (TwitterException|IOException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        return container;
	}
	
	public static String analyzedString(String word) throws IOException {
        StringReader tReader = new StringReader(word);
        Analyzer analyzer = new EnglishAnalyzer(Version.LUCENE_46);
        TokenStream tStream = analyzer.tokenStream("contents", tReader);
        CharTermAttribute charTermAttribute = tStream.addAttribute(CharTermAttribute.class);
        tStream.reset();
        StringBuffer term = new StringBuffer();
       // System.out.println("StringBuffer is "+term);
        while (tStream.incrementToken()) {
        	 String k=charTermAttribute.toString();
             term.append(k).append(" ");
             
        }
        // String term = charTermAttribute.toString();
        tStream.close();
        tReader.close();
        String stemWord = term.toString().trim();
        return stemWord;
  }
	
	
	static void put_data(HashSet<String> container)
	{
		Iterator it=container.iterator();
		
		 try {	
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
				Date date = new Date();	
				String dateappend=dateFormat.format(date);
				System.out.println(dateappend);
				String name="C:/Users/marunmo/Downloads/Tweet/cleaned_"+dateappend+".txt";
				File file = new File(name);
	 
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);			
				while(it.hasNext())
				{
					bw.write(analyzedString(it.next().toString()));
					bw.newLine();
				}
				
				bw.close();
	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}



	public static void main(String[] args) throws IOException{
		HashSet<String> container=fill();
		display(container);		
		//put_data(container);
	}


}