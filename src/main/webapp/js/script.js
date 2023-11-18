function calculateTotal() {
    var coffeeTypes = ['cappuccino', 'latte', 'mocha', 'americano', 'macchiato', 'flatWhite', 'espresso', 'doppio'];
    var total = 0;

    coffeeTypes.forEach(function(coffee) {
        var quantity = document.getElementById(coffee).value || 0;
        total += quantity * 3; // Each coffee costs 3€
    });

    // Ensure we are setting a numeric value only
    document.getElementById('totalPrice').value = total.toFixed(2);

    // Return true to allow form submission
    return true;
}
