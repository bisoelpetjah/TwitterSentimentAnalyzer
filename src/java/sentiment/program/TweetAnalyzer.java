/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sentiment.program;

import twitter4j.Status;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kevin
 */
public class TweetAnalyzer {
    private String tweetKeyword;
    private String[] positifKeyword;
    private String[] negatifKeyword;
    private List<MyTweet> myTweets;
    private TwitterRetriever tweetRetriever;
    public TweetAnalyzer(String tweet, String positif, String negatif) throws Exception{
        tweetKeyword = tweet;
        positifKeyword = positif.trim().split(";");
        negatifKeyword = negatif.trim().split(";");
        initializeTweets();
    }

    private void initializeTweets() throws Exception{
        myTweets = new ArrayList<MyTweet>();
        tweetRetriever = new TwitterRetriever();
        List<Status> statuses = tweetRetriever.searchQuery(tweetKeyword);
        for (Status s: statuses) {
            myTweets.add(new MyTweet(s));
        }
    }
    public String[] getPositifKeyword(){
        return positifKeyword;
    }
    public String[] getNegatifKeyword(){
        return negatifKeyword;
    }
    public String getKeyword() {
        return tweetKeyword;
    }
    public void divideTweet(List<MyTweet> positif, List<MyTweet> negatif, List<MyTweet> neutral){
        for (MyTweet t: myTweets) {
            int p = t.countExistance(getPositifKeyword());
            int n = t.countExistance(getNegatifKeyword());
            if (p > n) {
                positif.add(t);
            } else if (n > p){
                negatif.add(t);
            } else {
                neutral.add(t);
            }
        }
    }
}
