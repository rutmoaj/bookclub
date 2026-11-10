package se.yrgo.bookclub.controller;

import se.yrgo.bookclub.domain.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private List<Book> books = List.of(
            new Book("Good Omens", "Terry Pratchett", "comedy"),
            new Book("Blade: Playing Dead", "Tim Bowler", "action"),
            new Book("Je m’appelle Agneta", "Emma Hamberg", "comedy"),
            new Book("Coraline", "Neil Gaiman", "fantasy"),
            new Book("Fahrenheit 451", "Rad Bradbury", "dystopian"),
            new Book("1984", "George Orwell", "dystopian"),
            new Book("Divergent", "Veronica Roth", "dystopian"),
            new Book("Alice's Adventures in Wonderland", "Lewis Carrol", "fantasy"));

    @GetMapping("/home")
    public String welcomeMessage(Model model) {
        String greeting = "Välkommen till bokklubben!";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.forLanguageTag("sv"));
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(formatter);

        model.addAttribute("dateAndTime", formattedDate);
        model.addAttribute("message", greeting);

        return "home";
    }

    @GetMapping("/books")
    public String allBooks(Model model) {
        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/genre")
    public String filterGenre(Model model, @RequestParam(required = false) String type) {
        List<Book> filteredList = new ArrayList<>();
        if (type == null || type.isEmpty()) {
            filteredList = books;
        } else {

            for (Book b : books) {
                if (b.getGenre().equalsIgnoreCase(type)) {
                    filteredList.add(b);
                }
            }

        }

        model.addAttribute("books", filteredList);
        return "genre";
    }

}
