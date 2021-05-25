package jp.co.seattle.library.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.service.BooksService;

@Controller
public class SearchController {
    final static Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private BooksService booksService;

    @Transactional
    @RequestMapping(value = "/searchResult", method = RequestMethod.POST)
    public String searchBooks(Locale locale, @RequestParam("searchTitle") String title, Model model) {

        

        model.addAttribute("searchResult", booksService.searchTitle(title));
        return "searchResult";
    }

}
