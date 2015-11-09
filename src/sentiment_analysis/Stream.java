package try_sentiment;
import java.util.HashSet;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Stream {
	
	
	// static HashSet<String> container=new HashSet<String>();
	
    public static void main(String[] args) throws TwitterException {

    	ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("syqo7kML7SUC7v7T7HwtVVoZF")
		  .setOAuthConsumerSecret("2oOvEbsLrmX5DcGCwlmCWIbDoBMGGttiN3SwiRUbowR3ui0AIN")
		  .setOAuthAccessToken("1012551798-sspPUYedIfArC5f45ZTfEIZ5s3A0rfLMDMGTIsX")
		  .setOAuthAccessTokenSecret("bfSkmUt53ArZYk0pHfIQZCAoh60c7Ks2M9M0zxR9dXLzV");

    TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
  
   
    StatusListener listener = new StatusListener() {

        public void onStatus(Status status) {
        	String big="@"+status.getUser().getScreenName() + " - " + status.getText();
        	//container.add(big);
        	System.out.println(big);
            //System.out.println(container.size());
        }

        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
        }

        public void onScrubGeo(long userId, long upToStatusId) {
            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        public void onException(Exception ex) {
            ex.printStackTrace();
        }

		@Override
		public void onStallWarning(StallWarning arg0) {
			// TODO Auto-generated method stub
			
		}
    };

    FilterQuery fq = new FilterQuery();
    String keywords[] = {"walmart price low"};

    fq.track(keywords);

    twitterStream.addListener(listener);
    twitterStream.filter(fq);      
    
   // System.out.println("\n \n HEY MADHU HERE "+container.size());
}
}