package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @GetMapping("/math/pi")
    public String helloWorld() {
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String calculate(WebRequest request, HttpServletResponse response) {
        int x = 0;
        int y = 0;
        try {
            x = Integer.parseInt(request.getParameter("x"));
            y = Integer.parseInt(request.getParameter("y"));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "";
        }
        String operator = request.getParameter("operation");
        int result = 0;
        switch (operator==null?"add":operator) {
            case "multiply":
                result = x * y;
                break;
            case "subtract":
                result = x - y;
                break;
            case "divide":
                result = x / y;
                break;
            case "add":
                result = x + y;
                break;
        }
        return String.valueOf(result);
    }

    @PostMapping("/sum")
    public String sum(WebRequest request, HttpServletResponse response) {
        String[] values = request.getParameterValues("n");
        if (values == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "";
        }
        int result = 0;
        for (String value : values) {
            result += Integer.parseInt(value);
        }
        return String.valueOf(result);
    }

    @GetMapping("/")
    public String home() { return "home"; }
}
