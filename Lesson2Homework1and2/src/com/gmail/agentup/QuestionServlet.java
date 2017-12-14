//1. Разобраться с HTML.
//2. Создать проект «Анкета». Сделать возможность
//ввода пользователем его имени, фамилии,
//возраста и ответов на 2-3 вопроса. Данные
//должны отправляться на сервер, который в ответ
//должен вернуть статистику по ответам в виде
//HTML документа.

package com.gmail.agentup;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionServlet extends javax.servlet.http.HttpServlet {

    private List<Member> users = new ArrayList<>();
    private int manCount = 0;
    private int womanCount = 0;
    private int adultCount = 0;
    private int youngCount = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String html = "Members<br><table border='2' cellpadding='5' ><tr><th>Surname</th><th>Name</th></tr>";
        users.add(new Member(name,surname,sex,age));
        for (Member user : users){
            if(user.getSex() != null){
                if(user.getSex().equals("m")){
                    manCount++;
                } else if (user.getSex().equals("w")) {
                    womanCount++;
                }

            }
            if(user.getAge() != null){
                if(user.getAge().equals("y")){
                    adultCount++;
                } else if (user.getAge().equals("n")) {
                    youngCount++;
                }

            }
            html += "<tr>";
            html += "<td>" + user.getName() + "</td>";
            html += "<td>" + user.getSurname() + "</td>";
            html += "</tr>";
        }

        html += "<table border='2' cellpadding='5' ><tr><th>Men</th><th>Women</th><th>Adult</th><th>Young</th></tr>";
        html += "<tr><th>"+manCount+"</th><th>"+womanCount+"</th><th>"+adultCount+"</th><th>"+youngCount+"</th></tr>";
        html += "</table>";
        HttpSession session = request.getSession(true);
        session.setAttribute("html", html);
        response.sendRedirect("index.jsp");
    }

}
