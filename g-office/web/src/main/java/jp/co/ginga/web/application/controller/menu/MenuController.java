package jp.co.ginga.web.application.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニュクラス
 *
 */
@Controller
public class MenuController {

    @GetMapping("/menu")
    public String createMenu() {
        return "menu/menu";
    }
}