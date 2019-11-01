/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Elizabeth.Bourke
 */
public class Book {

    private int ISBN;
    private String author;
    private double price;
    private int bookID;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }
    
    public Book(){
        
    }

    public Book(int ISBN, String author, double price, String title) {
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String Author) {
        this.author = Author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void getUserInput() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter ISBN");
        ISBN = s.nextInt();
        System.out.println("Enter Author");
        author = s.next();
        System.out.println("Enter Price");
        price = s.nextDouble();
        System.out.println("Enter Title");
        title = s.next();
    }

    public void print() {
        
        System.out.println("Book ID " + bookID);
        System.out.println("ISBN " + ISBN);
        System.out.println("Author " + author);
        System.out.println("Title " + title);
        System.out.printf("Price %.2f\n", price);
        System.out.println("****************************");

    }
    
    public ArrayList<Book> findAllBooks() throws SQLException, ClassNotFoundException{
        ArrayList<Book> allBooks = new ArrayList<Book>();
        
        Connection c = DBHelperClass.getConnection();
        String template = "SELECT * FROM books";
        
        if(c != null){
            try{
                PreparedStatement inserter = c.prepareStatement(template);
                ResultSet resultSet = inserter.executeQuery();
//                System.out.println("All books");
                while(resultSet.next()){
                    Book b = new Book();
                    b.bookID = resultSet.getInt("bookID");
                    b.author = resultSet.getString("author");
                    b.ISBN = resultSet.getInt("ISBN");
                    b.title = resultSet.getString("title");
                    b.price = resultSet.getFloat("price");
                    allBooks.add(b);
                }
                
//                System.out.println(inserter);
                inserter.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Error on find all " + ex);
            }
        }
        
        return allBooks;
    }
    
    public void addBook() throws SQLException, ClassNotFoundException{
        Scanner input = new Scanner(System.in);
        Connection c = DBHelperClass.getConnection();
        
        String template = "INSERT INTO BOOKS VALUES(?,?,?,?,?)";
        
        if(c != null){
            try{
                System.out.println("Enter ISBN");
                setISBN(input.nextInt());
                input.nextLine();
                System.out.println("Enter author");
                setAuthor(input.nextLine());
                System.out.println("Enter title");
                setTitle(input.nextLine());
                System.out.println("Enter price");
                setPrice(input.nextDouble());
                
                PreparedStatement inserter = c.prepareStatement(template);
                
                inserter.setInt(1, this.bookID);
                inserter.setInt(2, this.ISBN);
                inserter.setString(3, this.author);
                inserter.setString(4, this.title);
                inserter.setDouble(5, this.price);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch(SQLException e){
                System.out.println("Error while adding data " + e);
            }
        }   
    }
    
    public void deleteBook() throws SQLException, ClassNotFoundException{
        Connection c = DBHelperClass.getConnection();
        
        String template = "DELETE FROM BOOKS WHERE ISBN=?";
        
        if(c != null){
            try{
                PreparedStatement inserter = c.prepareStatement(template);
                
                inserter.setInt(1, this.ISBN);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch (SQLException e) {
                System.out.println("Error while deleting record " + e);
            }
        }
    }
    
    public void updateBook() throws SQLException, ClassNotFoundException{
        Scanner input = new Scanner(System.in);
        Connection c = DBHelperClass.getConnection();
        
        String template = "UPDATE BOOKS SET AUTHOR=?,TITLE=?,PRICE=? WHERE ISBN=?";
        
        if(c != null){
            try{
                System.out.println("Enter author");
                setAuthor(input.nextLine());
                System.out.println("Enter title");
                setTitle(input.nextLine());
                System.out.println("Enter price");
                setPrice(input.nextDouble());
                
                PreparedStatement inserter = c.prepareStatement(template);
                
                inserter.setString(1, this.author);
                inserter.setString(2, this.title);
                inserter.setDouble(3, this.price);
                inserter.setInt(4, this.ISBN);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch (SQLException e) {
                System.out.println("Error while updating " + e);
            }
        }
    }
}
