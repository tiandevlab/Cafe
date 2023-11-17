function calculateTotal() {
    var coffeeTypes = ['cappuccino', 'latte', 'mocha', 'americano', 'macchiato', 'flat white', 'espresso', 'doppio'];
    var total = 0;

    coffeeTypes.forEach(function(coffee) {
        var quantity = document.getElementById(coffee).value || 0;
        total += quantity * 3; // Each coffee costs 3€
    });

    document.getElementById('totalPrice').value = total.toFixed(2) + '€';

    // Prevent form submission for demonstration purposes
    return false;
}
