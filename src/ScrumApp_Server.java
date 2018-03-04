
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenneth
 */
/**
 * A server program which connects to a local MySQL server and lets remote
 * clients ask for values stored in the MySQL server database. Client has a
 * login-feature, user credentials are entered on the client side and verified
 * from the server side (MySQL database) The server runs in an infinite loop and
 * allows multiple simultaneous connections
 * 
 * Version 1.3.1, see Wiki in Moodle for Changelog
 
 */
public class ScrumApp_Server {
    
    /**
     * Application method to run the server runs in an infinite loop listening
     * on port 9898. When a connection is requested, it spawns a new thread to
     * do the servicing and immediately returns to listening. The server keeps a
     * unique client number for each client that connects for logging purposes.
     */
    public static void main(String[] args) throws Exception {
        
        
        // Create date format for txt-file
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43

        // Create txt-logfile and redirect console messages to the file
        PrintStream fileStream = new PrintStream("serverlog_" + dateFormat.format(date) + ".txt");
        System.setOut(fileStream);

        System.out.println("The server is running...");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);

        try {
            while (true) {
                new Serverconnect(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    /**
     * A private thread to handle incoming client requests. The client can
     * terminate the socket by sending "quit" to the server
     */
    private static class Serverconnect extends Thread {

        private Socket socket;
        private int clientNumber;
        private String user;
        private String pass;
        private ResultSet rs = null;
        Statement stmt = null;

        public Serverconnect(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);

        }

        /**
         * Services this thread's client by first checking the client for valid
         * credentials (user/password), and then sending the client a welcome
         * message. After that the client is given multiple options to choose
         * from, and the rTemp()-method will run with those choices as arguments
         * and retrieve temperature data stored on the server-side MySQL
         * database
         *
         */
        @Override
        public void run() {
            try {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // read client credentials
                String userInput = in.readLine();
                String passInput = in.readLine();
                System.out.println("user " + userInput + " logged in");  // Print user to server console, logging and debugging purposes only

                // Verify user credentials in MySQL-database by running login()-method
                boolean loginSuccess = login(userInput, passInput);
                boolean adminLogin = false;
                // If login()-method returns false, close client socket, else continue                
                if (loginSuccess != true) {
                    out.println("quit");
                    socket.close();
                }

                if ("admin".equals(userInput)) {
                    adminLogin = true;
                    out.println("\nWelcome " + userInput + "!");
                    out.println("To quit, just type \"quit\"");
                    out.println("Choose Temperature sensor 1-5");
                    out.println("");
                    out.println("OR");
                    out.println("");
                    out.println("type \"SCRUM\" to generate data");
                    out.println("");
                } else {
                    adminLogin = false;
                    out.println("\nWelcome " + userInput + "!");
                    out.println("To quit, just type \"quit\"");
                    out.println("Retrieve data from temperature sensor:");
                    out.println("1: Living room");
                    out.println("2: Hall");
                    out.println("3: Kitchen");
                    out.println("4: Master bedroom");
                    out.println("5: Small bedroom");
                }

                // Loop infinitely until client breaks with "quit"
                while (true) {
                    String room = "";
                    String input = in.readLine();

                    // if input is blank -> Try again
                    if (input == "" && adminLogin == true) {
                        out.println("Choose Temperature sensor 1-5 or type SCRUM to generate data");
                    }
                    
                    if (input == "" && adminLogin == false) {
                        out.println("Choose Temperature sensor 1-5");
                    }

                    if (input.equals("quit")) {
                        out.println("quit");
                        break;
                    }
                    if (input.equals("1")) {
                        room = "temp_living";
                        rTemp(room);

                    }
                    if (input.equals("2")) {
                        room = "temp_hall";
                        rTemp(room);

                    }
                    if (input.equals("3")) {
                        room = "temp_kitchen";
                        rTemp(room);

                    }
                    if (input.equals("4")) {
                        room = "temp_bed1";
                        rTemp(room);

                    }
                    if (input.equals("5")) {
                        room = "temp_bed2";
                        rTemp(room);

                    }
                    if (input.equals("SCRUM") && adminLogin == true) {
                        // go to data adding function
                        out.println("Which temperature sensor data do you want to generate? 1-5");
                        input = in.readLine();

                        // if input is blank -> Try again
                        if (input == "") {
                            out.println("Which temperature sensor data do you want to generate? 1-5");
                        }

                        if (input.equals("quit")) {
                            out.println("quit");
                            break;
                        }
                        if (input.equals("1")) {
                            room = "temp_living";
                            sTemp(room);

                        }
                        if (input.equals("2")) {
                            room = "temp_hall";
                            sTemp(room);

                        }
                        if (input.equals("3")) {
                            room = "temp_kitchen";
                            sTemp(room);

                        }
                        if (input.equals("4")) {
                            room = "temp_bed1";
                            sTemp(room);

                        }
                        if (input.equals("5")) {
                            room = "temp_bed2";
                            sTemp(room);

                        }
                        if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5")) {
                            out.println("Which temperature sensor data do you want to generate? 1-5");
                        }

                    }

                    // if input is not a number between 1-5 -> Try again
                    if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && adminLogin == false) {
                        out.println("Choose Temperature sensor 1-5");
                    }
                    if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && adminLogin == true) {
                        out.println("Choose Temperature sensor 1-5 or type SCRUM to generate data");
                    }
                }
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);

                // When break occurs, try to close client socket
                // if closing fails, call log() method with error message
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }

        // prints log messages to Server console
        private void log(String message) {
            System.out.println("Client #" + clientNumber + ": " + message);
        }

        // Receive temperature 
        // Constructs a message with client-defined arguments and calls DBreader.java
        // Returned values from DBreader.java are sent to the client as output
        public void rTemp(String r) {

            String room = r;
            dateCheck tarkista = new dateCheck();
            boolean check = false;

            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String input = "";
//Create new database reader object "dbGet" to read from the database
                DBreader dbGet = new DBreader();
                dbGet.LoadDriver();
                boolean dataFetched = false;
                // Ask client what kind of data should be fetched from the database

                do {

                    out.println("Maximum temp (1), Minimum temp (2), Average temp (3)?");
                    input = in.readLine();

                    if (input == "") {
                        out.println("Maximum temp (1), Minimum temp (2), Average temp (3)?");
                    }

                    // if client sends quit, server responds with quit, and client automatically exits (implemented in Client.java)
                    if (input.equals("quit")) {
                        out.println("quit");
                        break;

                        // Fetch data from DB based on input from client (1,2,3)
                    }
                    if (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                        out.println("Maximum temp (1), Minimum temp (2), Average temp (3)?");
                    }
                    if (input.equals("1")) {
                        out.println("Please enter start date in format yyyy-mm-dd");
                        String startDate = in.readLine();

                        // dateCheck for input
                        check = tarkista.checkDate(startDate);//checks that date is valid
                        while (check != true) {//if inserted date is not valid, ask for a new date
                            out.println("The date you inserted does not exist. Insert Start Date in format yyyy-mm-dd: ");
                            startDate = in.readLine();
                            check = tarkista.checkDate(startDate);
                        }

                        out.println("Please enter end date in format yyyy-mm-dd");
                        String endDate = in.readLine();

                        // dateCheck for input
                        check = tarkista.checkDate(endDate);//checks that date is valid
                        while (check != true) {//if inserted date is not valid, ask for a new date
                            out.println("The date you inserted does not exist. Insert End Date in format yyyy-mm-dd: ");
                            endDate = in.readLine();
                            check = tarkista.checkDate(endDate);
                        }

                        out.println(dbGet.getTempMax(startDate, endDate, room));
                        dataFetched = true;
                        input = in.readLine();
                    }
                    if (input.equals("2")) {
                        out.println("Please enter start date in format yyyy-mm-dd");
                        String startDate = in.readLine();

                        // dateCheck for input
                        check = tarkista.checkDate(startDate);//checks that date is valid
                        while (check != true) {//if inserted date is not valid, ask for a new date
                            out.println("The date you inserted does not exist. Insert Start Date in format yyyy-mm-dd: ");
                            startDate = in.readLine();
                            check = tarkista.checkDate(startDate);
                        }

                        out.println("Please enter end date in format yyyy-mm-dd");
                        String endDate = in.readLine();

                        // dateCheck for input
                        check = tarkista.checkDate(endDate);//checks that date is valid
                        while (check != true) {//if inserted date is not valid, ask for a new date
                            out.println("The date you inserted does not exist. Insert End Date in format yyyy-mm-dd: ");
                            endDate = in.readLine();
                            check = tarkista.checkDate(endDate);
                        }

                        out.println(dbGet.getTempMin(startDate, endDate, room));
                        dataFetched = true;
                        input = in.readLine();
                    }
                    if (input.equals("3")) {
                        out.println("Please enter start date in format yyyy-mm-dd");
                        String startDate = in.readLine();

                        // dateCheck for input
                        check = tarkista.checkDate(startDate);//checks that date is valid
                        while (check != true) {//if inserted date is not valid, ask for a new date
                            out.println("The date you inserted does not exist. Insert Start Date in format yyyy-mm-dd: ");
                            startDate = in.readLine();
                            check = tarkista.checkDate(startDate);
                        }

                        out.println("Please enter end date in format yyyy-mm-dd");
                        String endDate = in.readLine();

                        // dateCheck for input
                        check = tarkista.checkDate(endDate);//checks that date is valid
                        while (check != true) {//if inserted date is not valid, ask for a new date
                            out.println("The date you inserted does not exist. Insert End Date in format yyyy-mm-dd: ");
                            endDate = in.readLine();
                            check = tarkista.checkDate(endDate);
                        }

                        out.println(dbGet.getTempAvg(startDate, endDate, room));
                        dataFetched = true;
                        input = in.readLine();
                    }

                } while (dataFetched != true);

                do {
                    out.println("New search? Type (Y)es or (N)o");
                    input = in.readLine();
                } while (!input.equals("y") && !input.equals("Y") && !input.equals("n") && !input.equals("N"));

                if ("n".equals(input) || "N".equals(input)) {
                    out.println("quit");
                    socket.close();
                }

                if ("y".equals(input) || "Y".equals(input)) {
                    out.println("Choose Temperature sensor 1-5");
                    return;
                }         // return to run()

            } catch (IOException ex) {
                Logger.getLogger(Serverconnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void sTemp(String r) {
            tempGen pp = new tempGen();
            dateCheck tarkista = new dateCheck();
            String room = r;
            String date;
            String time;
            String value;
            int dataSent = 0;

            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String input = "";
                DBwriter dbSet = new DBwriter();
                dbSet.LoadDriver();

                out.println("Insert the first date, where to start data generation in format yyyy-mm-dd: ");
                input = in.readLine();
                pp.setDate(input);//sets the date
                boolean check = tarkista.checkDate(pp.getDate());//checks that date is valid
                while (check != true) {//if inserted date is not valid, ask for a new date
                    out.println("The date you inserted does not exist. Insert Date in format yyyy-mm-dd: ");
                    input = in.readLine();
                    pp.setDate(input);
                    check = tarkista.checkDate(pp.getDate());
                }

                out.println("Insert the amount of days you want to create data: ");
                input = in.readLine();
                int valinta = Integer.parseInt(input);
                int valintaCheck = valinta * 24;

                valinta = valinta - 1;//first day values are generated outside of the loop
                //where date+1

                //Generate values for the first day without addDate
                for (int i = 0; i < 24; i++) {
                    //System.out.println("Date       Time     Value ");
                    String ii = String.format("%02d", i);
                    //System.out.println(pp.getDate() + " " + ii + ":30:00" + " " + pp.getRandomtemp());

                    // Create string variables for date time and value
                    date = pp.getDate();
                    time = (ii + ":30:00");
                    value = Double.toString(pp.getRandomtemp());

                    // call database writer with these arguments
                    dataSent = dataSent + (dbSet.setTemp(room, date, time, value));

                }
                //Start of loop where 1 day is added to the Date
                for (int k = 0; k < valinta; k++) {
                    pp.setDate(pp.addDate());//+1 day
                    check = tarkista.checkDate(pp.getDate());//check that the Date is valid
                    while (check != true) {//if the date is not valid
                        pp.setDate(pp.newDate());//add 1 month change day to 1
                        check = tarkista.checkDate(pp.getDate());//check that the Date is valid
                        while (check != true) {//if the date is not valid
                            pp.setDate(pp.newYear());//add 1 year change month to 1 and day to 1
                            check = tarkista.checkDate(pp.getDate());
                        }
                    }
                    for (int j = 0; j < 24; j++) {//start of the loop for 24 values for one day
                        // System.out.println("Date       Time     Value ");
                        String jj = String.format("%02d", j);
                        // System.out.println(pp.getDate() + " " + jj + ":30:00" + " " + pp.getRandomtemp());

                        // Create string variables for date time and value
                        date = pp.getDate();
                        time = (jj + ":30:00");
                        value = Double.toString(pp.getRandomtemp());

                        // call database writer with these arguments
                        dataSent = dataSent + (dbSet.setTemp(room, date, time, value));
                    }
                }

                // debug
                log("Total data sent:" + dataSent);
                // System.out.println("ValintaCheck variable:" + valintaCheck);

                if (dataSent == 0) {
                    out.println("No data generated (Data already exists)");
                }
                if (dataSent == valintaCheck) {
                    out.println("Data successfully generated into MySQL Database");
                }
                if (dataSent < valintaCheck && dataSent != 0) {
                    out.println("Data was partially generated, check server log");
                }
                input = in.readLine();

                do {
                    out.println("New search? Type (Y)es or (N)o");
                    input = in.readLine();
                } while (!input.equals("y") && !input.equals("Y") && !input.equals("n") && !input.equals("N"));

                if ("n".equals(input) || "N".equals(input)) {
                    out.println("quit");
                    socket.close();
                }

                if ("y".equals(input) || "Y".equals(input)) {
                    out.println("Choose Temperature sensor 1-5 or type SCRUM to generate data");

                    return;
                }         // return to run() 

            } catch (IOException ex) {
                Logger.getLogger(Serverconnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // user credentials check
        private boolean login(String u, String p) {
            String user = u;
            String pass = p;

            DBreader dbGet = new DBreader();
            dbGet.LoadDriver();

            // if user and password contains something, pass them to the
            // db.Get.login method which returns true for correct credentials
            // and false for wrong username or password
            if (user != null && pass != null) {
                return dbGet.login(u, p);
            }
            return false;

        }

    }

}
