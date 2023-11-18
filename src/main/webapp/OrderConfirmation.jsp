<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Order Confirmation</title>
  <link rel="stylesheet" href="CSS/styles.css">
</head>
<body>
<div class="confirmation-container">
  <h1>Order Confirmation</h1>
  <p>Your coffee order has been placed successfully!</p>
  <ul>
    <li>Cappuccino: ${cappuccinoQuantity}</li>
    <li>Latte: ${latteQuantity}</li>
    <li>Mocha: ${mochaQuantity}</li>
    <li>Americano: ${americanoQuantity}</li>
    <li>Macchiato: ${macchiatoQuantity}</li>
    <li>Flat White: ${flatWhiteQuantity}</li>
    <li>Espresso: ${espressoQuantity}</li>
    <li>Doppio: ${doppioQuantity}</li>
  </ul>
  <p>Total Price: ${totalPrice}€</p>
</div>
</body>
</html>


