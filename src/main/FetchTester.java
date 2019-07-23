package main;

/**
 * Tester class for API calls
 */
public class FetchTester
{
   public static void main(String[] args)
   {
      // NHL
      NHL nhl = new NHL("2019", "7", "22");
      System.out.println("Success: " + nhl.isSuccessful());
      System.out.println("Date: " + nhl.getDate());
      System.out.println("League: " + nhl.getLeague() + "\n");
      for (int i = 0; i < 4; i++)
      {
         System.out.println(nhl.getGamesTeam(i, "away", "alias") + " @ " +
                            nhl.getGamesTeam(i, "home", "alias"));
         System.out.println("Time: " + nhl.getTime(i));
         System.out.println(nhl.getGamesVenue(i, "name") + "\n" + nhl.getGamesVenue(i, "city") +
                 ", " + nhl.getGamesVenue(i, "state"));
         System.out.println("\n");
      }
   }
}
