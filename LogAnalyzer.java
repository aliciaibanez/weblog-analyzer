import java.util.ArrayList;

/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
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
        dayCounts = new int[32];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    public LogAnalyzer(String name)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(name);
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

    public void busiestTimeSlot() {
        int maxAccesos = hourCounts[0];
        int franjaMasOcupada = 0; // franjas de 2 horas
        for (int i = 0; i < hourCounts.length; i+=2 ) {
            int AccesosFranjaHoraria = hourCounts[i] + hourCounts[i+1];
            if (AccesosFranjaHoraria > maxAccesos) {
                maxAccesos = AccesosFranjaHoraria;
                franjaMasOcupada = i;
            }
        }
        System.out.println ("La franja horaria con más accesos fue a las : " + franjaMasOcupada + " " + "con " + maxAccesos);
    }
    
    public void quietesAndBusiestDay() {
        int maxAccesos = dayCounts[0];
        int diaMasOcupado = 0;
        for (int i = 0; i < dayCounts.length; i++) {
            if (dayCounts[i] > maxAccesos) {
                maxAccesos = dayCounts[i];
                diaMasOcupado = i;
            }
        }
        int minAccesos = dayCounts[0];
        int diaMenosOcupado = 0;
        for (int i = 0; i < dayCounts.length; i++) {
            if (dayCounts[i] < minAccesos) {
                minAccesos = dayCounts[i];
                diaMenosOcupado = i;
            }
        }
        System.out.println ("El día más ocupado fue : " + diaMasOcupado + " " + "con " + maxAccesos);
        System.out.println ("El día menos ocupado fue : " + diaMenosOcupado + " " + "con " + minAccesos);
    }
    
    public void busiestHour() {
        int maxAccesos = hourCounts[0];
        int horaMasOcupada = 0;
        for (int i = 0; i < hourCounts.length; i++) {
            if (hourCounts[i] > maxAccesos) {
                maxAccesos = hourCounts[i];
                horaMasOcupada = i;
            }
        }
        System.out.println ("Hora de mayor ocupación: " + horaMasOcupada);
    }

    public void quietestHour () {
        int MinAccesos = hourCounts[0];
        int horaMenosOcupada = 0;
        for (int i = 0; i < hourCounts.length; i++) {
            if (hourCounts[i] < MinAccesos) {
                MinAccesos = hourCounts[i];
                horaMenosOcupada = i;
            }
        }
        System.out.println ("Hora de mayor ocupación: " + horaMenosOcupada);
    }
    
     public void numberOfAccesses() {
        int numeroDeAccesos = 0;
        for (int cuenta : hourCounts) {
            numeroDeAccesos += cuenta;
        }
        System.out.println("Número total de accesos: " + numeroDeAccesos);

    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int i = 0;
        while (i < hourCounts.length) {
            System.out.println ( i + ": " + hourCounts[i++]);
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    /** 
     * Analyze the hourly accesses in the given date
     *
     * @param day   The given day
     * @param month The given month
     * @param year  The given year+
     *
     */
    public void analyzeHourlyDataGivenDate(int day, int month, int year) {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            if (entry.getDay() == day && entry.getMonth() == month && entry.getYear() == year) {
                hourCounts[hour]++;
            }
        }
    }

    public void analyzeDailyData() {
        while (reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }

    public void printDailyCounts() {
        System.out.println("Day: Count");
        int i = 0;
        while (i < dayCounts.length) {
            System.out.println ( i + ": " + dayCounts[i++]);
        }
    }
}
