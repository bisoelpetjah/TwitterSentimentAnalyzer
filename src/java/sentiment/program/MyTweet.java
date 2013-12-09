/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sentiment.program;

import twitter4j.Status;
import java.util.Random;

/**
 *
 * @author Kevin
 */
public class MyTweet {
    private static Random r = new Random();
    private Status content = null;

    //TODO this should be fixed and integrate with okihita
    public MyTweet(Status status) {
        content = status;
    }
    public String getContent(){
        return content.getText();
    }
    public String getTweetURL() {
        StringBuffer address = new StringBuffer();
        address.append("http://twitter.com/#!/");
        address.append(content.getUser().getScreenName());
        address.append("/status/");
        address.append(content.getId());
        return address.toString();
    }
    public String getUser() {
        return content.getUser().getScreenName();
    }
    public String getTweet() {
        return "@" + getUser() + ": " + getContent();
    }
    public int countExistance(String[] word){
        int ret = 0;
        for (int i=0; i<word.length; i++) {
            ret += Algorithm.S().countWord(getContent(), word[i]);
        }
        return ret;
    }
}
