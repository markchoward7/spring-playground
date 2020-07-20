package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() { return "home"; }

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

    @RequestMapping(value="/math/volume/{length}/{width}/{height}", method={ RequestMethod.GET, RequestMethod.POST })
    public String volume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
        return String.format("The volume of a %d, %d, %d rectangle is %d.", length, width, height, length * width * height);
    }

    @PostMapping("/math/area")
    public String area(WebRequest request, HttpServletResponse response) {
        float r = 0f;
        int w = 0;
        int h = 0;

        String type = request.getParameter("type");
        if (type.equals("circle")) {
            try {
                r = Float.parseFloat(request.getParameter("radius"));
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return "Invalid";
            }
            return String.format("Area of a circle with a radius of %.1f is %.1f", r, 3.14 * Math.pow(r, 2));
        } else {
            try {
                w = Integer.parseInt(request.getParameter("width"));
                h = Integer.parseInt(request.getParameter("height"));
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return "Invalid";
            }
            return String.format("Area of a %dx%d rectangle is %d", w, h, w*h);
        }
    }
}
