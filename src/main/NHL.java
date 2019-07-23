package main;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * NHL pulls data using Sportradar API
 * @author Zed Chance
 */
public class NHL
{
   private String query;
   private JsonElement results;

   /**
    * Construct a new NHL object with a current date
    * @param year to search
    * @param month to search
    * @param day to search
    */
   NHL(String year, String month, String day)
   {
      try
      {
         // Build URL
         String urlString = "http://api.sportradar.us/nhl/simulation/v6/en/games/" + year + "/" + month + "/" +
                 day + "/schedule.json" + "?api_key=" + APIKeys.NHL_KEY;
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

   /**
    * Gets the date of search
    * @return date as String
    */
   String getDate()
   {
      return results.getAsJsonObject().get("date").getAsString();
   }

   /**
    * Gets the league of search
    * @return league as String
    */
   String getLeague()
   {
      return results.getAsJsonObject().get("league").getAsJsonObject().get("name").getAsString();
   }

   /**
    * Gets the game from the JSON object
    * @param index of game
    * @return game as JsonObject
    */
   private JsonObject getGame(int index)
   {
      return results.getAsJsonObject().get("games").getAsJsonArray().get(index).getAsJsonObject();
   }

   /**
    * Returns name of team
    * @param index of game
    * @param team "home" or "away"
    * @return name of team as String
    */
   String getGamesTeam(int index, String team)
   {
      return getGame(index).get(team).getAsJsonObject().get("name").getAsString();
   }

   /**
    * Gets the venue for a game
    * @param index of game
    * @return venue as String
    */
   String getGamesVenue(int index)
   {
      return getGame(index).get("venue").getAsJsonObject().get("name").getAsString();
   }
}
