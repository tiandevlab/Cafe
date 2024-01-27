package com.example.cafetest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/TestDatabase";
        String user = "root";
        String password = "1996Allen";

        // Initialize all quantities
        int cappuccinoQuantity = Integer.parseInt(request.getParameter("cappuccino"));
        int latteQuantity = Integer.parseInt(request.getParameter("latte"));
        int mochaQuantity = Integer.parseInt(request.getParameter("mocha"));
        int americanoQuantity = Integer.parseInt(request.getParameter("americano"));
        int macchiatoQuantity = Integer.parseInt(request.getParameter("macchiato"));
        int flatWhiteQuantity = Integer.parseInt(request.getParameter("flatWhite"));
        int espressoQuantity = Integer.parseInt(request.getParameter("espresso"));
        int doppioQuantity = Integer.parseInt(request.getParameter("doppio"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice").replace("€", ""));

        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "JDBC driver not found");
            return;
        }

        // Insert order into database
        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            String insertOrderSQL = "INSERT INTO coffee_orders (cappuccino, latte, mocha, americano, macchiato, flat_white, espresso, doppio, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSQL)) {
                preparedStatement.setInt(1, cappuccinoQuantity);
                preparedStatement.setInt(2, latteQuantity);
                preparedStatement.setInt(3, mochaQuantity);
                preparedStatement.setInt(4, americanoQuantity);
                preparedStatement.setInt(5, macchiatoQuantity);
                preparedStatement.setInt(6, flatWhiteQuantity);
                preparedStatement.setInt(7, espressoQuantity);
                preparedStatement.setInt(8, doppioQuantity);
                preparedStatement.setDouble(9, totalPrice);

                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0) {
                    // Set attributes for all coffee types
                    request.setAttribute("cappuccinoQuantity", cappuccinoQuantity);
                    request.setAttribute("latteQuantity", latteQuantity);
                    request.setAttribute("mochaQuantity", mochaQuantity);
                    request.setAttribute("americanoQuantity", americanoQuantity);
                    request.setAttribute("macchiatoQuantity", macchiatoQuantity);
                    request.setAttribute("flatWhiteQuantity", flatWhiteQuantity);
                    request.setAttribute("espressoQuantity", espressoQuantity);
                    request.setAttribute("doppioQuantity", doppioQuantity);
                    request.setAttribute("totalPrice", totalPrice);
                    // Redirect to a confirmation page
                    request.getRequestDispatcher("OrderConfirmation.jsp").forward(request,response);
                } else {
                    // Handle the case where the insert fails
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing order");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        }

        System.out.println("Total Price (before parsing): " + request.getParameter("totalPrice"));

    }
}
