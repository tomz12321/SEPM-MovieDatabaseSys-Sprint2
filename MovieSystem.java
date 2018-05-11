import java.lang.*;
import java.util.*;
import java.io.*;

/**
 * Create a class for Movie System.
 * 
 * @author Jyh-woei Yang 
 * @version 11/05/2018
 */
public class MovieSystem
{
    // instance variables 
    private ArrayList<User> userList;
    private User loginUser;
    private String weekday;
    private ArrayList<Ticket> ticketList;
    private ArrayList<MovieSession> bookedTicketList;
    private ArrayList<MovieSession> availableTicketList;
    private ArrayList<MovieSession> movieSessionList;
    private ArrayList<MovieTicket> movieTicketList;
    private MainController mainController;
    
    /**
     * Default Constructor for objects of class Movie System
     */
    public MovieSystem()
    {
        // initialise instance variables
        loginUser = new User();
        userList = new ArrayList<User>();
        movieTicketList = new ArrayList<MovieTicket>();
        mainController = new MainController();
    }    

    /**
     * Method to convert from String to Integer
     * 
     * @param a String of input
     * @return the Integer of out
     * @throws NumberFormatException if input is a non-number format
     */
    private int convertStringtoInt(String input) //method to convert String to Integer
    {
        //intialised variables
        String S = input;
        int i = 0;
        //try catch to handle NumberFormatException
        try
        {
            // the String to int conversion happens here
            i = Integer.parseInt(input.trim());

            // print out the value after the conversion
            //System.out.println("int i = " + i);
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage() + ", please input an integer!");
        }
        return i;
    }

    /**
     * Method to convert from Wx to Monday
     * 
     * @param a String of input
     * @return the String of output
     * @throws NullPointerException if input is a Null format
     */
    private String ConvertWxToMonday(String input) //method to convert Wx to Monday
    {
    //ConvertWxToMonday()
        //Today is
        
        try
        {
        if (input.equals("W1"))
            return "Monday";
        if (input.equals("W2"))
            return "Tuesday";
        if (input.equals("W3"))
            return "Wednesday";
        if (input.equals("W4"))
            return "Thursday";
        if (input.equals("W5"))
            return "Friday";
        if (input.equals("W6"))
            return "Saturday";
        if (input.equals("W7"))
            return "Sunday";
        }
        catch (NullPointerException nfe)
        {
            System.out.println("NullPointerException: " + nfe.getMessage() + ", please W1-W7!");
        }
        return "Wrong inputday";
    }
    
    /**
     * A method to display Booking and Delete Menu
     * 
     * @param
     * @return 
     */

    private void displayBookingAndDeleteMenu()
    {
        //interface
        System.out.println("");
        System.out.println("=====================");
        System.out.println("(1) Book ticket for a movie session");
        System.out.println("(2) Delete ticket for a movie session");
        
        System.out.println("(3) Display a list of cineplex theatres");
        System.out.println("(4) Display the corresponding movie session for the whole week");
        
        System.out.println("(5) Search available seats via a movie");
        System.out.println("(6) Search available seats via a cineplex");
        System.out.println("(7) Pay by Creditcard");//isCreditcard (y/n)
        System.out.println("(8) Exit");
        System.out.println("=====================");
        System.out.print("Choose an option :");
    }

    /**
     * A method to display Cineplex
     * 
     * @param
     * @return 
     */
    private void displayCineplex()
    {
        //interface
        System.out.println("");
        System.out.println("=====================");
        System.out.println("(1) Lilydale, Plaza theatre");
        System.out.println("(2) St Kilda, Plaza theatre");
        System.out.println("(3) Fitzroy , Plaza theatre");
        System.out.println("(4) Melbourne CBD, Plaza theatre");
        System.out.println("(5) Sunshine, Plaza theatre");
    }
    
    /**
     * A method to exit the system
     * 
     * @param
     * @return a boolean to make isOperating = false and break the while loop
     */
    private boolean exitSystem()
    {
        System.out.println("Exit System");
        //write into file
        //writeFile();
        //writeTicket();

        //reset all the attributes
        loginUser = new User();

        return false;
    }

    /**
     * A method to read from file
     * 
     * @param  
     * @return
     * @throws FileNotFoundException if file is not found
     * @throws IOException while exception during I/O actions
     */
    private void readFile()
    {
    }

    /**
     * A method to read movieSession from file and Create 2 ticket lists
     * 
     * @param  
     * @return
     * @throws FileNotFoundException if file is not found
     * @throws IOException while exception during I/O actions
     */
    private void loadMovieSessionFile(){
        
        String fileName = "myMovieSessions.txt";
        try{
            
            FileReader inputFile = new FileReader(fileName);
            Scanner console = new Scanner(inputFile);
            while(console.hasNextLine()){
                String movieSessionString = console.nextLine();
                String[] details = movieSessionString.split(",");
                MovieSession movieSession = new MovieSession(details[0],details[1],details[2],details[3],details[4],details[5]);
                
                //display test data
                //System.out.println("= test data =");
                //movieSession.display();
                
                movieSessionList.add(movieSession);
            }
            inputFile.close();
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(fileName + " not found");
        }
        catch(IOException e){
            System.out.println("Error: Invalid file");
        }
    }
    
    /**
     * A method to read ticket from file and test 500 tickets
     * 
     * @param  
     * @return
     * @throws FileNotFoundException if file is not found
     * @throws IOException while exception during I/O actions
     */
    private void loadTicketFile(){
        
        String fileName = "myTickets.txt";
        try{
            
            FileReader inputFile = new FileReader(fileName);
            Scanner console = new Scanner(inputFile);
            int counter = 0;
            while(console.hasNextLine()){
                String ticketString = console.nextLine();
                String[] details = ticketString.split(",");
                MovieTicket movieTicket = new MovieTicket(details[0],details[1],details[2],details[3],details[4],details[5],details[6],details[7]);
                //display test data
                counter++;
                //System.out.println("= test "+counter+" ticket data =");
                movieTicketList.add(movieTicket);
                
                //movieTicketList.get(counter-1).display();
                //movieSession.display();
                
                //ticketList.add(movieTicket);
            }
            inputFile.close();
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(fileName + " not found");
        }
        catch(IOException e){
            System.out.println("Error: Invalid file");
        }
    }

    private void createAvailableTicketlist(int ticketAmount, MovieSession movieSession)
    {
        for (int i = 0 ; i < ticketAmount ; i++)
        {
            mainController.addAvailableTicket(movieSession);
        }
        //writeAvailableTicket();
    }
    
    private void createBookedTicketlist(int ticketAmount)
    {
        System.out.println(ticketAmount + " ticket list has been created!");
        //writeBookedTicket();
    }
    
    /**
     * A method to read user from file
     * 
     * @param  
     * @return
     * @throws FileNotFoundException if file is not found
     * @throws IOException while exception during I/O actions
     */
    private void loadUserFile(){
        
        String fileName = "myClerks.txt";
        try{
            
            FileReader inputFile = new FileReader(fileName);
            Scanner console = new Scanner(inputFile);
            while(console.hasNextLine()){
                String userString = console.nextLine();
                String[] details = userString.split(",");
                User user = new User();
                user.setName(details[0]);
                user.setUserName(details[1]);
                user.setPassword(details[2]);
                userList.add(user);
            }
            inputFile.close();
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(fileName + " not found");
        }
        catch(IOException e){
            System.out.println("Error: Invalid file");
        }
    }

    /**
     * A method to match username and password
     * 
     * @param Arraylist<User> userList
     * @return Boolean match or not 
     */
    private Boolean matchUsernameAndPassword(ArrayList<User> userList, String insertUserName, String insertPassword) //not case-sensitive source.toLowerCase().contains(target.toLowerCase())
    {
        System.out.println(" ");
        System.out.println("=== Matching username and password ===");
        //input
        Scanner input = new Scanner(System.in);
        System.out.println("Search User , inserted keyword is passed to search by UserName!");
        String newUserName = insertUserName.toLowerCase();
        String newPassword = insertPassword.toLowerCase();
 
        //matchUsernameAndPassword()
        ArrayList<User> resultList = mainController.matchUsernameAndPassword(userList,newUserName,newPassword);

        //display User details
        System.out.println("== Search Result ==");
        for (int j = 0 ; j < resultList.size() ; j++)
        {
            resultList.get(j).display();
        }

        if (resultList.size() == 0)
        {
            System.out.println("No matched result");
            return false;
        }
        return true;
    }
    
    public void loginMenu()
    {
        //Scanner
        Scanner input = new Scanner(System.in);
        Boolean isOperating = true;
        
        //loadUserFile()
        userList = new ArrayList<User>();
        loadUserFile();
        
        //loadTicketFile()
        //ticketList = new ArrayList<Ticket>();
        loadTicketFile();

        movieSessionList = new ArrayList<MovieSession>();
        loadMovieSessionFile();

        availableTicketList = new ArrayList<MovieSession>();
        //loadAvailableTicketFile()
        
        bookedTicketList = new ArrayList<MovieSession>();
        //loadBookedTicketFile()
        
        //initialize data
        MovieTicket[] createAvailableTicketlist = new MovieTicket[20];
        MovieTicket[] createBookedTicketlist = new MovieTicket[20];

        System.out.println(userList.get(0).getUserName());
        System.out.println(userList.get(0).getPassword());
        
        String firstloginUserName = userList.get(0).getUserName();
        String firstloginPassword = userList.get(0).getPassword();
             
        System.out.println("== Welcome to jMoSS (Java-Based Movie Search System) ==");
        System.out.println(" Please insert your username");
        String username = input.nextLine(); 
        System.out.println(" Please insert your password");
        String password = input.nextLine();
        System.out.println(" Please input what weekday is today?");
        do 
        {
            this.weekday = input.nextLine();
        }
        while(!validWeekday(weekday));
        
        int weekdayCount = 1;
        weekdayCount = convertStringtoInt(weekday.charAt(1)+"");
        //System.out.println(weekday);
        
        //ConvertWxToMonday()
        //Today is
        if (weekdayCount == 1)
            System.out.println("Today is Monday.");
        if (weekdayCount == 2)
            System.out.println("Today is Tuesday.");
        if (weekdayCount == 3)
            System.out.println("Today is Wednesday.");
        if (weekdayCount == 4)
            System.out.println("Today is Thursday.");
        if (weekdayCount == 5)
            System.out.println("Today is Friday.");
        if (weekdayCount == 6)
            System.out.println("Today is Saturday.");
        if (weekdayCount == 7)
            System.out.println("Today is Sunday");
            
        
        loginUser.setUserName(username);
        loginUser.setPassword(password);
        //Tom
        loginUser.setName(userList.get(0).getName());
        //loginUser.display();
        
        System.out.println();
        //matchUsernameAndPassword()
        isOperating = matchUsernameAndPassword(userList, username, password);
        if (isOperating)
        {
            System.out.println("");
            System.out.println("== Welcome to the Movie system, "+ username +"! ==");
        }
        
        ticketList = new ArrayList<Ticket>();
        //create a ticket list to sell
        for (int i = 0; i < 20; i++)
        {
            Ticket newTicket = new Ticket(loginUser.getName(), loginUser.getUserName(), loginUser.getPassword(),"Seat"+String.valueOf((i+1)),"","");
            //newTicket.display();
            ticketList.add(i,newTicket);
        }
        
        while (isOperating)
        {
            //searchByUserName and Password()
            
            //display booking and display menu 
            displayBookingAndDeleteMenu();
            
            //insert case
            String iobuffer = input.nextLine(); 
            System.out.println("");

            //check console.nextLine() is not null or blank
            if (validBlank(iobuffer,"Option"))
            { 
                char option = iobuffer.charAt(0);

                //if option not in 1,2,3,4,5,6,7,8 Error message: please insert from (1) to (8)!
                if (validOption(option))
                {
                    switch (option)
                    {
                        case '1':
                        //searchCase();
                        //(1) Book ticket for a movie session
                        System.out.println("(1) Book ticket for a movie session");
                        
                        //mainController.display(all the movie session)
                        //mainController.addBookedTicket(movieSessionList.get(i));
                        //mainController.deleteAvailableTicket();//AvailableTicketList.remove();
                        String cinemax;

                        System.out.println("Please insert Cinema (Location):");
                            cinemax = input.nextLine();
                        //do while (!validCinemax)

                        String movieSession;

                        System.out.println("Please insert MovieSession (Time):");
                            movieSession = input.nextLine();
                        //do while (!validMovieSession)
                        
                        System.out.println("Please insert seat number:");
                        String seatNumber;
                        do{
                            seatNumber = input.nextLine();
                        }while(!validSeatNumber(seatNumber));
                        //record the email and suburb of the customers
                        System.out.println("Please insert customer's Email:");
                        String buyerEmail;
                        do{
                            buyerEmail = input.nextLine();
                        }while(!validEmail(buyerEmail));
                        System.out.println("Please insert customer's Suburb:");
                        String buyerSuburb = input.nextLine();
                        Ticket newTicket = new Ticket(loginUser.getName(),loginUser.getUserName(),"","seat"+seatNumber,buyerEmail,buyerSuburb);
                        
                        System.out.println("= Ticket booked =");
                        System.out.println("Cinema Location: " + cinemax);
                        newTicket.display();
                        writeFile();
                        break;
                        case '2':
                        //(2) Delete ticket for a movie session
                        System.out.println("(2) Delete ticket for a movie session");
                        
                        System.out.println("Please insert Cinema (Location):");
                        //String cinemax;
                        cinemax = input.nextLine();

                        System.out.println("Please insert your movie Session (Time)");
                        //String movieSession;
                        movieSession = input.nextLine();
                        //Display all occupied seat

                        System.out.println("Please insert customers'Email to confirm the deletion");
                        String customersEmail;
                        customersEmail = input.nextLine();

                        //if (customersEmail.equals())
                        //    movieTicket.setIsFull("Available");

                        String selectMovieSession;
                        selectMovieSession = "";//input.nextLine();
                        System.out.println("Booking " + selectMovieSession + " is deleted.");
                        //BookticketList.remove()
                        //AvailableTicketList.add();
                        break;
                        case '3':
                        //(3) Display a list of cineplex theatres
                        System.out.println("(3) Display a list of cineplex theatres");
                        //Ticket.displayCineplex();
                        displayCineplex();
                        break;
                        case '4':
                        //(4) Display the corresponding movie session for the whole week
                        System.out.println("(4) Display the corresponding movie session for the whole week");    
                        //MovieSession.displayWeek();
                        int index = ((weekdayCount-1)*25); 
                        for (int i = index; i < movieSessionList.size(); i++)
                        {
                            
                            if ((i) % 25 == 0 && (i+1) != 175)
                            {
                                System.out.println("");   
                                System.out.println("=== " + ConvertWxToMonday(movieSessionList.get(i).getWeekday()) + " Movie Sessions ===");
                            }
                            
                            if ((i) % 5 == 0 && (i+1) != 175)
                            {
                                System.out.println("=== " + ConvertWxToMonday(movieSessionList.get(i).getWeekday()) + " at " + movieSessionList.get(i).getLocation()+ " ===");
                                System.out.println("");
                            }
                            movieSessionList.get(i).displayMovieTitle();
                        }
                        break;
                        case '5':
                        //(5) Search available seats via a movie
                        System.out.println("(5) Search available seats via a movie");
                        //MovieSystem.searchSeat(MovieName)
                        System.out.println("Please input a movie name");
                        String keywordMovieName;
                        keywordMovieName = input.nextLine();
                        System.out.println(keywordMovieName+" is selected.");
                        
                        break;
                        case '6':
                        //(6) Search available seats via a cineplex
                        System.out.println("(6) Search available seats via a cineplex");
                        //MovieSystem.searchSeat(cineplexName);
                        for (int i = 0; i < movieTicketList.size(); i++)
                        {
                            int ticketCount = 0;
                            if (i < 20)
                            {
                            String isFull = movieTicketList.get(i).getIsFull();
                            if (isFull.equals("Available"))
                                {
                                    System.out.println("= Ticket Seatnumber " + (i%20+1) + " =");
                                    ticketCount++;
                                }
                            
                            if (ticketCount < 0)
                                System.out.println("= Error Msg: The Session are full, please select other timeSession! =");
                        
                            }
                        }
                        break;
                        case '7':
                        //(7) Pay by Creditcard
                        System.out.println("(7) Pay by Creditcard");
                        String confirmCreditCard;
                        System.out.println("Are you sure you are going to pay by credit card? (y/n)");
                        confirmCreditCard = input.nextLine();
                        //MovieSystem.PayCredit();
                        break;
                        
                        case '8':
                        //Exit system, and reset variables
                        writeFile();
                        isOperating = exitSystem();
                        break;
                    }
                }
            }
        }
        
        if (!isOperating)
                {
                    System.out.println("");
                    System.out.println("Thank you for using jMoSS (Java-Based Movie Search System), Goodbye!");
                }
    }

    /**
     * Method to check insert any emptys or blank
     * 
     * @param iobuffer the iobuffer
     * @param subject the subject
     * @return the boolean of checkBlank
     */
    private boolean validBlank(String iobuffer,String subject) //method to check insert any empties or blanks
    {
        if (subject.equals("Option"))
        {
            //if iobuffer isEmpty or iobuffer.length() > 1 , Error : please insert from (1) to (5)! and return false to break if condition
            if (iobuffer.isEmpty() || iobuffer.length() > 1)
            {
                System.out.println("Error : please insert from (1) to (8)!");
                return false;
            }
            return true;
        }
        else
        {    
            //iobuffer.trim().isEmpty(), "Error: subject's name shouldn't be blank! Please enter the name again:" and return true to keep while condition
            if (iobuffer.trim().isEmpty())
            {
                System.out.println("Error: " + subject + " shouldn't be blank! Please enter the name again:");
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check Delete selection is from 1 to size
     * 
     * @param index the index to be validated, the size the size
     * @return the boolean of checkDelSelection
     */
    private boolean validDelSelection(int index, int size) //method to check int index
    {
        //check if rating is from 1 to size  and return false to break while loop
        if (index < 0 || index > size)
        {
            System.out.println("Error : please insert from (1) to (" + size +")!");
            System.out.print("Please insert :");
            return true;
        }
        return false;
    }

    /**
     * Method to check char option
     * 
     * @param option the option
     * @return the boolean of checkOption
     */
    private boolean validOption(char option) //method to check char option
    {
        //check if option is in 1,2,3,4,5,6,7,8 , and return false to break if condition
        if (option < '1' || option > '8')
        {
            System.out.println("Error : please insert from (1) to (8)!");
            return false;
        }
        return true;        
    }

    /**
     * Method to check insert any space
     * 
     * @param iobuffer the iobuffer
     * @return the boolean of checkBlank
     */
    private boolean validSpace(String iobuffer) //method to check insert any space characters only on Actor2 or Actor3
    {
        //Actor2 or Actor3.charAt(0) == ' ', "Error: subject's name shouldn't be space only! Please enter the name again:" and return true to keep while condition
        if (iobuffer.isEmpty())
            return false;
        else if (iobuffer.charAt(0) == ' ')
        {
            System.out.println("Error: Actor2 or Actor3's name shouldn't be space only or start by space character! Please enter the name again:");
            return true;
        }
        return false;
    }

    /**
     * Method to check valid any seatnumber
     * 
     * @param seatNumber the seatNumber
     * @return the boolean of valid seatNumber
     */
    private boolean validSeatNumber(String seatNumber)
    {
        if (convertStringtoInt(seatNumber) < 1 || convertStringtoInt(seatNumber) > 20)
        {
            System.out.println("Please insert seatnumber from 1 to 20:");
            return false; // if seatnumber is not in the bound 0 - 20 , return false            
        }
        return true; // else return true
    }

    /**
     * Method to check valid any email
     * 
     * @param email the email
     * @return the boolean of valid email
     */
    private boolean validEmail(String email)
    {
        if (!email.contains("@"))
        {
            System.out.println("Please insert email again, format:xxx.@abc.com");
            return false; // if email doesnt contain "@" , return false            
        }
        return true; // else return true
    }

    /**
     * Method to check valid any weekday
     * 
     * @param weekday the weekday
     * @return the boolean of valid weekday
     */
    private boolean validWeekday(String weekday)
    {
        if (weekday.equals("W1") || weekday.equals("W2") || weekday.equals("W3") || weekday.equals("W4") || weekday.equals("W5") || weekday.equals("W6") || weekday.equals("W7"))
        {
            return true; // if weekday are not valid           
        }
        System.out.println(" Error Msg: Please insert format within W1-W7");
        return false; // else return false
    }
    
    /**
     * A method to write ticket to file
     * 
     * @param  
     * @return
     * @throws IOException while exception during I/O actions
     */
    private void writeFile()
    {
       String filename = ("mytickets.txt");
       String[] tickets = new String[8];
       Scanner input = new Scanner(System.in);
       String line = "";
       int numberOfTickets;
       MainController toWriteBookedList = new MainController();
       //toWriteBookedList.bookedTicketList
       //System.out.println("How many tickets your want to buy :");
       numberOfTickets = movieTicketList.size();//convertStringtoInt(input.nextLine());
       //System.out.println(numberOfTickets + ""); 

       //try catch to handle IOException
       try
       {
            PrintWriter outputFile = new PrintWriter (filename);

            for (int i = 0 ; i < numberOfTickets ; i++ )
            {
                //System.out.println("Please insert Tickets " + (i + 1) + "'s Location :");
                tickets[0] = movieTicketList.get(i).getLocation();
                //System.out.println(tickets[0]);

                //System.out.println("Please insert Tickets" + (i + 1) + "'s Cinema :");
                tickets[1] = movieTicketList.get(i).getCinema();
                //System.out.println(tickets[1]);

                //System.out.println("Please insert Tickets " + (i + 1) + "'s TimeSession :");
                tickets[2] = movieTicketList.get(i).getTimeSession();
                //System.out.println(tickets[2]);

                //System.out.println("Please insert Tickets" + (i + 1) + "'s BuyerEmail :");
                tickets[3] = movieTicketList.get(i).getBuyerEmail();
                //System.out.println(tickets[3]);

                //System.out.println("Please insert Tickets " + (i + 1) + "'s BuyerSuburb :");
                tickets[4] = movieTicketList.get(i).getBuyerSuburb();
                //System.out.println(tickets[4]);

                //System.out.println("Please insert Tickets" + (i + 1) + "'s getIsFull :");
                tickets[5] = movieTicketList.get(i).getIsFull();
                //System.out.println(tickets[5]);

                //System.out.println("Please insert Tickets " + (i + 1) + "'s getWeekday :");
                tickets[6] = movieTicketList.get(i).getWeekday();
                //System.out.println(tickets[6]);

                //System.out.println("Please insert Tickets" + (i + 1) + "'s Cinema :");
                tickets[7] = movieTicketList.get(i).getSeatNumber();
                //System.out.println(tickets[7]);

                //movieTicketList


                //combine elements into a line
                for (int k = 0 ; k < 8 ; k++ )
                {   
                    //line = tickets[0] + "," + tickets[1] + "," + tickets[2] + "," + tickets[3] + "," + tickets[4] + "," + tickets[5]+ "," + tickets[6]+ "," + tickets[7];
                    if (k != 7)
                        line = line + tickets[k] + ",";
                    else
                        line = line + tickets[k];
                }
                //display a message about write line
                int lineCount = i + 1;
                //System.out.println("");
                //System.out.println("Write a message in line "+ lineCount +" to a file");
                //System.out.println("");

                outputFile.println(line);
                //reset line
                line = "";
            }
            outputFile.close();    
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }
    
    /**
     * A method to write to file
     * 
     * @param  
     * @return
     * @throws IOException while exception during I/O actions
     */
    private void writeFilebk()
    {
    //    String filename = ("myvideos.txt");
        //use movie.getNumbersOfElement() to replace 6
    //    String[] videos = new String[6];
    //    Scanner input = new Scanner(System.in);
    //    String line = "";
    //    int numberOfVideos;
    //    MovieDatabase toWriteMovieList = new MovieDatabase();
        
        //print the result of inserting
    //    System.out.println("How many movies your want to insert :");
        //numberOfVideos = convertStringtoInt(input.nextLine());
    //    numberOfVideos = newMovieList.getNumbersOfMovies();
    //    System.out.println(numberOfVideos + "");
        //try catch to handle IOException
    //    try
    //    {
    //        PrintWriter outputFile = new PrintWriter (filename);

    //        for (int i = 0 ; i < numberOfVideos ; i++ )
    //        {
                //System.out.println("Please insert Videos " + (i + 1) + "'s Title :");
    //            videos[0] = newMovieList.getMovieList().get(i).getTitle();
                //System.out.println("Please insert Videos" + (i + 1) + "'s Director :");
    //            videos[1]  = newMovieList.getMovieList().get(i).getDirector();

                //for loop to replace 2,3,4
    //            for (int j = 2 ; j < newMovieList.getMovieList().get(i).getActorList().getNumbersOfActors() + 2 ; j++ )
    //            {
                    //videos[index] = newMovieList.getMovieList().get(i).getActorList().getListOfActors().get(j - 2).getName()
    //                videos[j]  = newMovieList.getMovieList().get(i).getActorList().getListOfActors().get(j - 2).getName();
    //            }

    //            videos[newMovieList.getMovieList().get(i).getNumbersOfElements() - 1]  = newMovieList.getMovieList().get(i).getRating() + "";

                //combine elements into a line
    //            for (int k = 0 ; k < newMovieList.getMovieList().get(i).getNumbersOfElements() ; k++ )
    //            {   
                    //line = videos[0] + "," + videos[1] + "," + videos[2] + "," + videos[3] + "," + videos[4] + "," + videos[5];
    //                if (k != (newMovieList.getMovieList().get(i).getNumbersOfElements() - 1))
    //                    line = line + videos[k] + ",";
    //                else
    //                    line = line + videos[k];
    //            }
                //display a message about write line
    //            System.out.println("");
    //            System.out.println("Write a message in line to a file");
    //            System.out.println("");

    //            outputFile.println(line);
                //reset line
    //            line = "";
    //        }
    //        outputFile.close();    
    //    }
    //    catch(IOException exception)
    //    {
    //        System.out.println("Unexpected I/O error occured");
    //    }
    }
}