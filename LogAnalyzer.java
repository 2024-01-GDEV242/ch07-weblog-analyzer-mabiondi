/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    /**
     * Create an object to analyze hourly web accesses,
     * given the name of the log file.
     * 
     * @param fileName The name of the log file to be analyzed.
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
    * Return the number of accesses recorded in the log file.
    * 
    * @return The total number of times the page has been accessed.
    */
    public int numberOfAccesses()
    {
        int total = 0;
        for (int accesses : hourCounts) {
            total += accesses;
        }
        return total;
    }
    
    /**
     * Return the hour in which the page was accessed
     * the greatest number of times.
     * 
     * @return The hour in which the page is accessed the most.
     */
    public int busiestHour()
    {
        int busyHour = 0;
        for (int hour = 0; hour < 24; hour++) {
            if (hourCounts[busyHour] > hourCounts[hour]) {
                busyHour = hour;
            }
        }
        return busyHour;
    }
    
    /**
     * Return the hour in which the page was accessed
     * the greatest number of times.
     * 
     * @return The hour in which the page is accessed the most.
     */
    public int busiestTwoHour()
    {
        int firstHalf = 0;
        for (int hour = 0; hour < 24; hour++) {
            if (hourCounts[firstHalf] + hourCounts[(firstHalf + 1) % 24]
            >
            hourCounts[hour] + hourCounts[(hour + 1) % 24]) {
                firstHalf = hour;
            }
        }
        return firstHalf;
    }
    
    /**
     * Return the hour in which the page was accessed
     * the lowest number of times.
     * 
     * @return The hour in which the page is accessed the least.
     */
    public int quietestHour()
    {
        int quietHour = 0;
        for (int hour = 0; hour < 24; hour++) {
            if (hourCounts[quietHour] < hourCounts[hour]) {
                quietHour = hour;
            }
        }
        return quietHour;
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
