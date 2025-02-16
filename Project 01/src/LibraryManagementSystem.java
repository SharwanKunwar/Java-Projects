import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
//Voice lib
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

//Author: Sharwan jung kunwar
//Purpose: Project 01 library management system

class InputMethod{
    Scanner sc;

    public InputMethod()
    {
        sc = new Scanner(System.in);
    }
}

public class LibraryManagementSystem {
    public static int menu(int count){
        // Setting up the voice
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        // Getting the voice manager instance
        VoiceManager vm = VoiceManager.getInstance();
        // Listing available voices
        Voice voice = vm.getVoice("kevin16");

        // Adjust the speed of the voice
        voice.setRate(190);


        InputMethod input = new InputMethod();
        space(1);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd || ⌚ hh:mm:ss");
        DateTimeFormatter formatter02 = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedDateTime = now.format(formatter);
        String fd = now.format(formatter02);
        int op;
        space(2);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("\t\tL I B R A R Y   M A N A G E M E N T   S Y S T E M");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                 \t\t\t          📅 "+formattedDateTime);
        space(3);
        System.out.println("\t 1️⃣ Add Books    \t2️⃣ Issue Book   \t3️⃣ Return Book\n");
        System.out.println("\t 4️⃣ Delete Book \t5️⃣ Display Books\t0️⃣ Exit");
        space(3);
        System.out.println("-------------------------------------------------------------------");

        if(0 == count){
            voice.allocate();
            voice.speak("It's "+fd+" Sir.");

            voice.speak("You are good to go. select Operation");
        }




        try{
            System.out.print("Chose: ");
            op = input.sc.nextInt();
            return op;
        }catch (InputMismatchException e){
            return 404;
        }

    }
    public static void space(int n){
        for(int i=0; i<n; i++){
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd || ⌚ hh:mm:ss");
        DateTimeFormatter formatter02 = DateTimeFormatter.ofPattern("hh");
        String formattedDateTime = now.format(formatter);
        String fd = now.format(formatter02);
        int numTime;

        numTime= Integer.parseInt(fd);





        // Setting up the voice
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        // Getting the voice manager instance
        VoiceManager vm = VoiceManager.getInstance();
        // Listing available voices
        Voice voice = vm.getVoice("kevin16");



        if (voice != null) {
            voice.allocate();
            // Adjust the speed of the voice
            voice.setRate(160);
            voice.speak("    Welcome, to the Library management system");

        } else {
            System.out.println("Voice not found");
        }


        Library_System lib = new Library_System();
        InputMethod input = new InputMethod();
        int userChoice;
        char ch;
        int count = 0;

        int AddBookCount;
        boolean addTrack=true;
        boolean flag = true;

        do{
            userChoice = menu(count);
            count++;

            if(userChoice == 0)
            {
                space(40);
                System.out.println("\t\t\tThank you.\n\t\t\twe appreciate your presence !!!");
                space(13);
                if (voice != null) {
                    voice.allocate();
                    voice.speak("   Thank you.  we appreciate your presence !!!");
                    voice.deallocate();
                } else {
                    System.out.println("Voice not found");
                }

                flag = false;
            }
            else if(userChoice == 1)
            {
                AddBookCount = 1;
                space(40);

                while(addTrack)
                {
                    System.out.format("\n\nBook %d ------------------------------------------------------------\n",AddBookCount);
                    System.out.println("----------------------------------------------- Adding Process ----");
                    System.out.print("Book Name   : ");
                    String inputBook = input.sc.nextLine();
                    System.out.print("Author Name : ");
                    String inputAuthor = input.sc.nextLine();
                    Book wholeBookDetails = new Book(inputBook,inputAuthor);
                    lib.addBook(wholeBookDetails);

                    System.out.print("Add more [y/n]: ");
                    ch = input.sc.next().charAt(0);
                    input.sc.nextLine();            //buffer handler

                    if(ch == 'n' || ch == 'N')
                    {
                        addTrack = false;
                    }
                    AddBookCount++;


                }
                addTrack = true;

            }
            //issue book sell
            else if(userChoice == 2)
            {
                space(40);

                if(lib.BookList.isEmpty()){
                    System.out.println("\nBooks are not added yet");
                }else {
                    lib.DisplayBooks();
                    System.out.println("\n\n--------------------------------------------- Issuing Process ----");
                    System.out.print("Book name    : ");
                    String BName = input.sc.nextLine();
                    System.out.print("Student Name : ");
                    String StudentName = input.sc.nextLine();
                    lib.IssueBook(BName, StudentName);
                }

            }
            else if(userChoice == 3)
            {

                space(40);
                if(lib.BookList.isEmpty()){
                    System.out.println("\nBooks are not added yet");
                }else {

                    boolean issueStatus = lib.DisplayBooks(userChoice);

                    if(issueStatus){
                        System.out.println("\n\n--------------------------------------------- Returning Process ----");
                        System.out.print("Book Name : ");
                        String BName = input.sc.nextLine();
                        lib.ReturnBooks(BName);

                    }else{
                        System.out.println("\n\n\nNOT Available any book to return.");
                        System.out.println("-------------------------------------------- Process Uncompleted ----");
                    }

                }

            }
            else if(userChoice == 4)
            {
                space(40);
                if(lib.BookList.isEmpty()){
                    System.out.println("\nBooks are not added yet");
                }else {
                    lib.DisplayBooks();
                    int sn;
                    boolean DeleteResult = false;
                    System.out.println("\n\n--------------------------------------------- Deleting Process ----");
                    try {

                        System.out.print("Book Index : ");
                        sn = input.sc.nextInt();
                        input.sc.nextLine();
                        if (sn == 0) {
                            System.out.println("\nError: Seeking index 0 not found !!! ");

                        } else {
                            DeleteResult = lib.DeleteBook(sn);
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("\nSomething wrong We except Integer. \nReason: " + e);
                        input.sc.nextLine();
                    }

                    if (DeleteResult) {
                        System.out.println("----------------------------------------------- Process Completed ----");
                    } else {
                        System.out.println("-------------------------------------------- Process Uncompleted ----");
                    }
                }


            }
            else if(userChoice == 5)
            {
                space(40);
                lib.DisplayBooks();

            }
            else
            {
                space(40);
                System.out.println("---------------------------------------------\n");
                System.out.println("fucked up !!!   ||  check your input.");
                assert voice != null;
                voice.speak("Errororororororororororororor motherFucker");
            }

        }while(flag);


    }
}

/**
 * this is book class
 * @author sharwan jung kuwnar
 * @see <a href="https://www.youtube.com/watch?v=u7hFzG1n4mg&list=PLu0W_9lII9agS67Uits0UnJyrYiXhDS6q&index=106" target="_blank">JavaDocs</a>
 *
 */

class Book{
    String BookName;
    String BookAuthor;
    String IssueTo;
    Date IssueOn;
    String Status;

    public Book(String bookName, String bookAuthor){
        this.BookName = bookName;
        this.BookAuthor = bookAuthor;
        this.IssueTo = null;
        this.IssueOn = null;
    }

    public void IssueBook(String studentName)
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        if(IssueTo == null){
            IssueTo = studentName;
            Status = "Not Available";
            IssueOn = new Date();
            System.out.println("\nBook: " + BookName + "\nIs Issued to " + IssueTo + " At " + formattedDateTime);
            System.out.println("----------------------------------------------- Process Completed ----");
        }else{
            System.out.println("\nBook: "+BookName+"\nIs already issued to "+IssueTo);
            System.out.println("-------------------------------------------- Process Uncompleted ----");
        }
    }

    public void ReturnBook(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        if(IssueTo != null){
            System.out.println("\nBook: "+BookName+"\nIs Return by "+IssueTo+" At "+formattedDateTime);
            IssueTo = null;
            IssueOn = null;
            Status = "Available";
            System.out.println("----------------------------------------------- Process Completed ----");
        }else{
            System.out.println("\nBook: "+BookName+"\nIs not Issued to anyone");
            System.out.println("-------------------------------------------- Process Uncompleted ----");
        }
    }

    public boolean ReturnBookDisplay(){
        return IssueTo != null;
    }

    @Override
    public String toString() {
        if(IssueTo == null){
            return "⭕ Book Name   : "+BookName+"\n⭕ Book Author : "+BookAuthor+"\n⭕ Status      : "+Status;
        }else{
            return "⭕ Book Name   : "+BookName+"\n⭕ Book Author : "+BookAuthor+"\n⭕ IssuedTo    : "+IssueTo+"\n⭕ IssuedOn    : "+IssueOn+"\n⭕ Status      : "+Status;
        }
    }
}

class Library_System{
    ArrayList<Book> BookList = new ArrayList<>();


    public void addBook(Book bookName){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        BookList.add(bookName);
        bookName.Status="Available";
        System.out.println("\nBook: "+bookName.BookName+"\nAdded to the library"+"  At "+formattedDateTime);

    }
    public void IssueBook(String bookName, String studentName)
    {
        for (Book book : BookList)
        {
            if (book.BookName.equalsIgnoreCase(bookName)) {
                book.IssueBook(studentName);
                return;
            }

        }
        System.out.println("\nBook: " + bookName + "\nIs not found in the library.");
        System.out.println("-------------------------------------------- Process Uncompleted ----");
    }

    public void ReturnBooks(String R_BookName){
        for(Book book: BookList){
            if(book.BookName.equalsIgnoreCase(R_BookName)){
                book.ReturnBook();
                return;
            }
        }
    }

    public boolean DeleteBook(int BookIndex){
        boolean deleteProcess = false;
        if(BookList.get(BookIndex-1).IssueTo != null){
            System.out.println("\nError: This book is not available for deleting.\nReason: Issued to "+BookList.get(BookIndex-1).IssueTo);
        }else {
            deleteProcess = true;
            if (BookIndex <= BookList.size()) {
                System.out.println("\nBook: " + BookList.get(BookIndex - 1).BookName+"\nIs deleted from the library.");
                BookList.remove(BookIndex - 1);
            } else {
                deleteProcess = false;
                System.out.println("\nWe don't have this Book.");
            }
        }
        return deleteProcess;


    }

    public void DisplayBooks(){
        int i=1;
        for(Book book: BookList){
            System.out.format("\n\nBook %d ---------------------------------------------\n",i++);
            System.out.println("---------------------------------------- Details ---");
            System.out.println(book);
        }
        if(BookList.isEmpty()){
            System.out.println("Books are not added to the library yet.");
        }

    }
    public boolean DisplayBooks(int sn) {
        int i = 1;
        boolean isFindOneIssued = false;
        if (sn == 3) {
            for (Book book : BookList) {
                System.out.format("\n\nBook %d ---------------------------------------------\n", i++);
                System.out.println("---------------------------------------- Details ---");
                boolean track = book.ReturnBookDisplay();
                if (track) {
                    System.out.println(book);
                    isFindOneIssued = true;
                }else{
                    System.out.println("Book is Not Issued to anyone yet.");
                }

            }
            if (BookList.isEmpty()) {
                System.out.println("Books are not added to the library yet.");
            }
            return isFindOneIssued;
        }

        return isFindOneIssued;
    }
}


