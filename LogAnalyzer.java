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
    private int[] dayCounts;
    private int[] monthCounts;
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
        
        dayCounts = new int[28];
        monthCounts = new int[12];
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
        
        dayCounts = new int[28];
        monthCounts = new int[12];
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
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month - 1]++;
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
     * Return the day on which the page was accessed
     * the lowest number of times.
     * 
     * @return The day of month in which the page is accessed the least.
     */
    public int quietestDay()
    {
        int quietDay = 1;
        for (int day = 1; day <= 28; day++) {
            if (dayCounts[quietDay] < dayCounts[day]) {
                quietDay = day;
            }
        }
        return quietDay;
    }
    
    /**
     * Return the day on which the page was accessed
     * the greatest number of times.
     * 
     * @return The day of month in which the page is accessed the most.
     */
    public int busiestDay()
    {
        int busyDay = 1;
        for (int day = 1; day <= 28; day++) {
            if (dayCounts[busyDay] > dayCounts[day]) {
                busyDay = day;
            }
        }
        return busyDay;
    }
    
    /**
     * Return an array of the number of accesses per month.
     * 
     * @return An array containing how often the page was accessed each month.
     */
    public int[] totalAccessesPerMonth()
    {
        return monthCounts;
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
