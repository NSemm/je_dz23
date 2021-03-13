package com.k7;

import com.k7.entyties.Book;
import com.k7.entyties.Contact;
import com.k7.entyties.ContactType;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        QueryControl queryControl = new QueryControl();
        JdbcTemplate dbContact = queryControl.createConnectionJdbs(
                "jdbc:postgresql://localhost:5432/contact_book",
                "postgres",
                "12345678");

        JdbcTemplate dbBooks = queryControl.createConnectionJdbs(
                "jdbc:postgresql://localhost:5432/book_sales01",
                "postgres",
                "12345678");

        String contactByName = "SELECT c.contact_name, t.type, c.contact_value " +
                "FROM contacts c " +
                "INNER JOIN contact_type t ON t.id = c.contact_type " +
                "WHERE c.contact_name like ? ";
        Condition conditionsContactByName = new Condition();
        conditionsContactByName.set("Jack");

        String contactAll = "SELECT c.contact_name, t.type, c.contact_value " +
                "FROM contacts c " +
                "INNER JOIN contact_type t ON t.id = c.contact_type ";

        String contactDelete = "DELETE FROM contacts WHERE contact_name = ?";
        Condition conditionsDeleteContact = new Condition();
        conditionsDeleteContact.set("Vasya");

        String contactAdd = "INSERT INTO contacts (contact_name, contact_type, contact_value, user_id) " +
                "SELECT ?, t.id, ?, ? " +
                "FROM contact_type t " +
                "WHERE t.type = ? ";

        Condition conditionsInsertContact = new Condition();
        conditionsInsertContact.set("Ira");
        conditionsInsertContact.set("Ira@gmail.com");
        conditionsInsertContact.set(1);
        conditionsInsertContact.set("EMAIL");

        String sqlBook = "SELECT b.name book, a.name||' '||a.surname author, g.name janr,  b.price, b.circulation, b.public_date, b.pages " +
                "FROM books b " +
                "INNER JOIN ganres g " +
                "ON g.ganres_id = b.ganre " +
                "INNER JOIN authors a " +
                "ON b.author = a.author_id " +
                "WHERE b.pages > ?;";

        Condition conditionsbook = new Condition();
        conditionsbook.set(200);


        RowMapper contactMapper = rs -> {
            Contact contact = new Contact();
            contact.setName(rs.getString("contact_name"));
            contact.setType(ContactType.valueOf(rs.getString("type")));
            contact.setValue(rs.getString("contact_value"));
            return contact;
        };
        RowMapper bookMapper = rs -> {
            Book book = new Book();
            book.setBook(rs.getString("book"));
            book.setAuthor(rs.getString("author"));
            book.setJanr(rs.getString("janr"));
            return book;
        };

        List<Book> bookList = dbBooks.query(sqlBook, conditionsbook.getList(), bookMapper);
        bookList.forEach(System.out::println);

        List<Contact> contactListAll = dbContact.query(contactAll, contactMapper);
        contactListAll.forEach(System.out::println);

//        dbContact.update(contactAdd, conditionsInsertContact.getList());
//        System.out.println("---------------------");
//        List<Contact> contactListAll2 = dbContact.query(contactAll, contactMapper);
//        contactListAll2.forEach(System.out::println);

//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }
}
