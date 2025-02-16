# Library Management System

## Introduction
The **Library Management System** is a Java-based application that helps manage a library's operations, including adding, issuing, returning, and deleting books. The system uses **Java's DateTime API**, **Scanner for user input**, and **FreeTTS (Text-to-Speech)** for voice feedback.

## Features
- **Add Books**: Allows the user to add books with details like name and author.
- **Issue Books**: Issues books to students, recording the issue date and student name.
- **Return Books**: Returns books and updates availability status.
- **Delete Books**: Removes books if they are not issued.
- **Display Books**: Shows available and issued books with details.
- **Voice Interaction**: Uses FreeTTS to provide audio feedback.

---

## Class Structure
### 1. **InputMethod Class**
Handles user input using `Scanner`.
```java
class InputMethod{
    Scanner sc;

    public InputMethod() {
        sc = new Scanner(System.in);
    }
}
```

### 2. **LibraryManagementSystem Class**
Manages the main program flow, menu display, and interaction with the user.

#### Key Methods:
- **menu(int count)**: Displays the menu and takes user input.
- **space(int n)**: Prints blank lines for better formatting.
- **main(String[] args)**: The entry point of the application.

#### Voice Integration:
Uses FreeTTS to provide voice feedback when selecting options.
```java
System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
VoiceManager vm = VoiceManager.getInstance();
Voice voice = vm.getVoice("kevin16");
```

---

### 3. **Book Class**
Represents a book in the library with attributes:
- `BookName`: Name of the book.
- `BookAuthor`: Author of the book.
- `IssueTo`: Name of the person who borrowed the book.
- `IssueOn`: Date of issue.
- `Status`: Availability status.

#### Key Methods:
- **IssueBook(String studentName)**: Issues a book to a student.
- **ReturnBook()**: Marks the book as returned.
- **ReturnBookDisplay()**: Checks if a book is issued.
- **toString()**: Displays book details.

---

### 4. **Library_System Class**
Manages the collection of books.

#### Key Methods:
- **addBook(Book bookName)**: Adds a new book to the collection.
- **IssueBook(String bookName, String studentName)**: Issues a book to a student.
- **ReturnBooks(String R_BookName)**: Returns a book.
- **DeleteBook(int BookIndex)**: Deletes a book if it's available.
- **DisplayBooks()**: Displays all books.
- **DisplayBooks(int sn)**: Displays only issued books.

```java
ArrayList<Book> BookList = new ArrayList<>(); // Stores all books
```

---

## Usage Flow
1. The program starts with a welcome message and voice feedback.
2. The user selects an option from the menu:
    - **1**: Add a new book.
    - **2**: Issue a book to a student.
    - **3**: Return a book.
    - **4**: Delete a book.
    - **5**: Display all books.
    - **0**: Exit.
3. The program interacts with the user to perform the chosen operation.
4. Voice feedback is provided for certain actions.
5. The loop continues until the user selects **Exit (0)**.

---

## Dependencies
- **Java Development Kit (JDK)**
- **FreeTTS Library** for voice interaction.

---

## Enhancements & Future Improvements
- Implement a **database** for persistent storage.
- Add **GUI (Graphical User Interface)**.
- Introduce **barcode scanning** for book tracking.
- Enhance voice interaction with **AI-based NLP**.

---

## Conclusion
This **Library Management System** effectively automates library operations with a simple **console-based UI** and **voice interaction**. It can be further extended with advanced features to improve efficiency and user experience.

```java
🎫 Author: Sharwan jung kunwar
🛒 Project: LibraryManagementSystem
📅 Date: 2081-11-3
```