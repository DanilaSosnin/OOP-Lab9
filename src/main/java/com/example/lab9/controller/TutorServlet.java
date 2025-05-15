package com.example.lab9.controller;

import com.example.lab9.model.Tutor;
import com.example.lab9.repository.TutorRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TutorServlet", urlPatterns = {"/tutors"})
public class TutorServlet extends HttpServlet {
    private TutorRepository tutorRepository;

    @Override
    public void init() {
        String contextPath = getServletContext().getRealPath("/");
        tutorRepository = new TutorRepository(contextPath);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Tutor> tutors = tutorRepository.loadTutors();
        request.setAttribute("tutors", tutors);
        request.setAttribute("redirected", "true");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Tutor tutor = new Tutor();
        tutor.setFirstName(request.getParameter("firstName"));
        tutor.setLastName(request.getParameter("lastName"));
        tutor.setSubject(request.getParameter("subject"));
        tutor.setExperienceYears(Integer.parseInt(request.getParameter("experienceYears")));
        tutor.setHourlyRate(Double.parseDouble(request.getParameter("hourlyRate")));
        tutor.setPhoneNumber(request.getParameter("phoneNumber"));

        List<Tutor> tutors = tutorRepository.loadTutors();
        tutors.add(tutor);
        tutorRepository.saveTutors(tutors);

        response.sendRedirect(request.getContextPath() + "/tutors");
    }
}