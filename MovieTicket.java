
/**
 * Write a description of class Ticket here.
 * 
 * @author Jyh-woei Yang
 * @version 11/05/2018
 */
public class MovieTicket
{
    // instance variables - replace the example below with your own
    private String location;
    private String cinema;
    private String timeSession;
    private String buyerEmail;
    private String buyerSuburb;
    private String isFull;
    private String weekday;
    private String seatNumber;

    /**
     * Constructor for objects of class User
     */
    public MovieTicket()
    {
        // initialise instance variables
        this.location = "";
        this.cinema = "";
        this.timeSession = "";
        this.buyerEmail = "";
        this.buyerSuburb = "";
        this.isFull = "";
        this.weekday = "";
        this.seatNumber = "";
    }
    
    public MovieTicket(String location, String cinema, String timeSession, String buyerEmail, String buyerSuburb,String isFull, String weekday, String seatNumber)
    {
        // initialise instance variables
        this.location = location;
        this.cinema = cinema;
        this.timeSession = timeSession;
        this.buyerEmail = buyerEmail;
        this.buyerSuburb = buyerSuburb;
        this.isFull = isFull;
        this.weekday = weekday;
        this.seatNumber = seatNumber;
    }

    public void setLocation(String location)
    {
        // setter of location
        this.location = location;
    }
    
    public void setCinema(String cinema)
    {
        // setter of cinema 
        this.cinema = cinema;
    }
    
    public void setTimeSession(String timeSession)
    {
        // setter of timeSession
        this.timeSession = timeSession;
    }

    public void setBuyerEmail(String buyerEmail)
    {
        // setter of buyerEmail
        this.buyerEmail = buyerEmail;
    }  

    public void setBuyerSuburb(String buyerSuburb)
    {
        // setter of buyerSuburb
        this.buyerSuburb = buyerSuburb;
    }

    public void setIsFull(String isFull)
    {
        // setter of isFull
        this.isFull = isFull;
    }

    public void setWeekday(String weekday)
    {
        // setter of weekday
        this.weekday = weekday;
    }

    public void setSeatNumber(String seatNumber)
    {
        // setter of seatNumber
        this.seatNumber = seatNumber;
    }    
    
    public String getLocation()
    {
        // getter of location
        return this.location;
    }
    
    public String getCinema()
    {
        // getter of cinema 
        return this.cinema;
    }
    
    public String getTimeSession()
    {
        // getter of timeSession
        return this.timeSession;
    }

    public String getBuyerEmail()
    {
        //getter of buyer's Email
        return this.buyerEmail;
    }    

    public String getBuyerSuburb()
    {
        //getter of buyer's Suburb
        return this.buyerSuburb;
    }    

    public String getIsFull()
    {
        //getter of isFull
        return this.isFull;
    }

        public String getWeekday()
    {
        //getter of seat number
        return this.weekday;
    }

    public String getSeatNumber()
    {
        //getter of seat number
        return this.seatNumber;
    }

    public void display(){
        
        System.out.println("Location:"+this.location);
        System.out.println("Cinema:"+this.cinema);
        System.out.println("TimeSession:"+this.timeSession);
        System.out.println("SeatNumber:"+this.seatNumber);
        System.out.println("BuyerEmail:"+this.buyerEmail);
        System.out.println("BuyerSuburb:"+this.buyerSuburb);
        System.out.println("IsFull:"+this.isFull);
        System.out.println("Weekday:"+this.weekday);
    }
}
