/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Elizabeth.Bourke
 */
public class BookShop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here

        ArrayList<Book> bookShop = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        int ISBNSearch = 0;
        int option = 0;
        do {
            System.out.println("1. Add");
            System.out.println("2. Display");
            System.out.println("3. Find");
            System.out.println("4. Delete");
            System.out.println("5. Update");
            System.out.println("6. Exit");
            option = s.nextInt();
            switch (option) {
                case 1:
                    Book b = new Book();
                    b.addBook();
                    break;

                case 2:
                    DisplayBook();
                    break;

                case 3:
                    System.out.println("Enter ISBN");
                    ISBNSearch = s.nextInt();
                    
                    b = new Book();
                    bookShop = b.findAllBooks();
                    b = findBook(bookShop, ISBNSearch);
                    
                    if (b != null) {
                        b.print();
                    } else {
                        System.out.println("Book Not Found");
                    }
                    break;

                case 4:
                    System.out.println("Enter ISBN to delete");
                    ISBNSearch = s.nextInt();
                    b = new Book();
                    bookShop = b.findAllBooks();
                    b = findBook(bookShop, ISBNSearch);
                    b.deleteBook();
                    break;

                case 5:
                    System.out.println("Enter ISBN to Update");
                    ISBNSearch = s.nextInt();
                    b = new Book();
                    bookShop = b.findAllBooks();
                    b = findBook(bookShop, ISBNSearch);
                    b.updateBook();
                    break;

            }
        } while (option != 6);
    }

    public static boolean updateBook(ArrayList<Book> list, Book b) {
        boolean done = false;
        try {
            b.getUserInput();
            done=true;
        } catch (Exception ex) {
            done = false;
        }
        return done;
    }

    public static boolean deleteBook(ArrayList<Book> list, Book b) {
        boolean done = false;
        Iterator i = list.iterator();
        while (i.hasNext()) {
            i.remove();
            done = true;
        }
        return done;
    }

    public static void DisplayBook() throws SQLException, ClassNotFoundException {
        Book b = new Book();
        ArrayList<Book> books = new ArrayList<Book>(b.findAllBooks());
        
        for (Book book : books) {
            book.print();
        }
    }

    public static Book findBook(ArrayList<Book> list, int isbnSearch) {

        for (Book b : list) {

            if (b.getISBN() == isbnSearch) {

                return b;
            }
        }
        return null;
    }
}
