package pl.beny.lm6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.beny.lm6.dto.Result;
import pl.beny.lm6.logic.Parser;

import java.util.LinkedList;

@Controller
public class ParserController {

    private final LinkedList<Result> history = new LinkedList<>();

    @GetMapping("/")
    public String index(Model model) {
        history.clear();
        model.addAttribute("history", history);
        return "index";
    }

    @PostMapping("/")
    public String checkExpression(String expression, Model model) {
        Result result = new Result(expression);

        try {
            result.setResult(new Parser(expression.replaceAll("\\s+","")).parse());
        } catch (Exception e) {
            result.setResult(false);
        }

        history.addFirst(result);
        model.addAttribute("history", history);
        return "index";
    }

}
