package jp.co.seattle.library.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class HomeController1 {
    final static Logger logger = LoggerFactory.getLogger(HomeController1.class);




    /**
     * Homeボタンからホーム画面に戻るページ
     * @param model
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String transitionHome(Model model) {
        return "home1";
    }

    @Transactional
    @RequestMapping(value = "/searchResult", method = RequestMethod.POST)
    public String searchBooks(Locale locale, @RequestParam("searchTitle") String title, Model model) {


        return "search";

    }

}
