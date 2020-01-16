package com.zm.cj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Describle This Class Is
 * @Author ZengMin
 * @Date 2020/1/9 20:28
 */
@Controller
public class HomeController {

    @RequestMapping({"/", "index.html", "index"})
    public String toIndex() {
        return "index";
    }

    @RequestMapping({"/mgr.html", "mgr"})
    public String toMgr() {
        return "mgr";
    }

    @RequestMapping({"/result.html", "result"})
    public String toResult() {
        return "result";
    }
}
