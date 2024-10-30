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
    // Where the daily access counts are calculated
    private int[] dayCounts;
    // Where the monthly access counts are calculaetd
    private int[] monthCounts;
    // Where the yearly access counts are calculaetd
    private int[] yearCounts;
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
        yearCounts = new int[7];
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
        yearCounts = new int[7];
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
     * Analyze the yearly access data from the log file.
     */
    public void analyzeAnnualData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int year = entry.getYear();
            yearCounts[year - 2018]++;
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
     * Return the month on which the page was accessed
     * the lowest number of times.
     * 
     * @return The month in which the page is accessed the least.
     */
    public int quietestMonth()
    {
        int quietMonth = 1;
        for (int month = 0; month < 12; month++) {
            if (monthCounts[quietMonth] < monthCounts[month]) {
                quietMonth = month + 1;
            }
        }
        return quietMonth;
    }
    
    /**
     * Return the month on which the page was accessed
     * the greatest number of times.
     * 
     * @return The month in which the page is accessed the most.
     */
    public int busiestMonth()
    {
        int busyMonth = 1;
        for (int month = 0; month < 12; month++) {
            if (monthCounts[busyMonth] > monthCounts[month]) {
                busyMonth = month + 1;
            }
        }
        return busyMonth;
    }
    
    /**
     * Return the average number of accesses per month.
     * 
     * @return The average number of accesses per month.
     */
    public double averageAccessesPerMonth()
    {
        double average = (double) numberOfAccesses() / (yearCounts.length * 12);
        return average;
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
