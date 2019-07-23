package main;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * FetchNHL pulls NHL data using Sportradar API
 * @author Zed Chance
 */
public class FetchNHL
{
   private String query;
   private JsonElement results;

   FetchNHL(String team)
   {
      query = team;
      try
      {
         // Build URL
         String urlString = "http://api.sportradar.us/nhl/simulation/v6/en/games/2019/7/22/schedule.json" +
                 "?api_key=" + APIKeys.NHL_KEY; /* TODO Fix the date in this url */
         URL url = new URL(urlString);

         // Open streams
         InputStream is = url.openStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);

         // Parse JSON
         results = new JsonParser().parse(br);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   String getDate()
   {
      return results.getAsJsonObject().get("date").getAsString();
   }

   String getLeague()
   {
      return results.getAsJsonObject().get("league").getAsJsonObject().get("name").getAsString();
   }
}
