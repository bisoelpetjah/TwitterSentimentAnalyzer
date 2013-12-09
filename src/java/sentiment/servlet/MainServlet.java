/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sentiment.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import sentiment.program.MyTweet;
import sentiment.program.TweetAnalyzer;

/**
 *
 * @author ize
 */
public class MainServlet extends HttpServlet {
    private static List <MyTweet> positifTweet = new ArrayList<MyTweet>();
    private static List <MyTweet> negatifTweet = new ArrayList<MyTweet>();
    private static List <MyTweet> netralTweet = new ArrayList<MyTweet>();
    
    private void Analyze (String Tweet, String Positif, String Negatif){
        try {
            TweetAnalyzer TA = new TweetAnalyzer(Tweet, Positif, Negatif);
            TA.divideTweet(positifTweet, negatifTweet, netralTweet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject json = new JSONObject();
        Analyze(request.getParameter("tweet"), request.getParameter("positif"), request.getParameter("negatif"));
        List<String> positif = new ArrayList<String>();
        List<String> negatif = new ArrayList<String>();
        List<String> netral = new ArrayList<String>();
        for (MyTweet mytweet : positifTweet) {
            positif.add(mytweet.getTweet());
        }
        for (MyTweet mytweet : negatifTweet) {
            negatif.add(mytweet.getTweet());
        }
        for (MyTweet mytweet : netralTweet) {
            netral.add(mytweet.getTweet());
        }
        PrintWriter out = response.getWriter();
        try {
            json.put("status", "success");
            json.put("positif", new JSONArray(positif).toString());
            json.put("negatif", new JSONArray(negatif).toString());
            json.put("netral", new JSONArray(netral).toString());
            out.println(json.toString());
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
