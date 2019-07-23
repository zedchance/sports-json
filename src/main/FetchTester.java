package main;

public class FetchTester
{
   public static void main(String[] args)
   {
      FetchNHL f = new FetchNHL("Red Wings"); /* TODO Team isn't being taken into account */
      System.out.println("Date: " + f.getDate());
      System.out.println("League: " + f.getLeague());
   }
}
