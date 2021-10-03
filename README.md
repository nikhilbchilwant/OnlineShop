# Problem statement
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt.
Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price (including tax),
finishing with the total cost of the items, and the total amounts of sales taxes paid.
The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets.
***

### Input:

Input 1:

1 book at 12.49

1 music CD at 14.99

1 chocolate bar at 0.85

Input 2:

1 imported box of chocolates at 10.00

1 imported bottle of perfume at 47.50

Input 3:

1 imported bottle of perfume at 27.99

1 bottle of perfume at 18.99

1 packet of headache pills at 9.75

1 box of imported chocolates at 11.25

### Output:

Output 1:

1 book : 12.49

1 music CD: 16.49

1 chocolate bar: 0.85

Sales Taxes: 1.50

Total: 29.83

Output 2:

1 imported box of chocolates: 10.50

1 imported bottle of perfume: 54.65

Sales Taxes: 7.65

Total: 65.15

Output 3:

1 imported bottle of perfume: 32.19

1 bottle of perfume: 20.89

1 packet of headache pills: 9.75

1 imported box of chocolates: 11.85

Sales Taxes: 6.70

Total: 74.68

# How to build and run
To build from source, run `mvn package` from the root directory `OnlineShop`. It should build a OnlineShop-0.1.jar in the target folder. You can get this jar directly from the GitHub release. Then run `OnlineShop-0.1.jar` as
```
java -jar OnlineShop-0.1.jar <absolute path to the .txt file with orders>
```
A sample `orders.txt` file:

```
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
```

# Design

The app was designed to emulate real life supermarket scenario. So, one goes to the supermarket with text description of the `Order`. Then we fetch `Item` from the shelf and add them to `Cart`. At the checkout counter, we `printBill`.

The application executes steps as below:
1. Reads orders from the text file. (`OnlineShop.java`)
2. Now, for each order, creates `Item` objects.
3. Passes the list of `Item`s to the billing app.
4. The billing app calculates taxes for each `Item`.
5. All `Item`s are pushed into the `Cart`.
6. `printBill`

# Development

- The app was built using bottom-up TDD approach.
- In real life, the app configuration should be fetched from Configuration server. For this demo, it has been kept into `Constants.java`.

# Assumptions

- The exempted items will have either 'book', 'chocolate' or 'pill' in their description. This list is assumed to be sufficient for this demo.
- The input file strictly adheres to the structure i.e. `<quantity> <item description> at <base price>`.
- The imported items will have `imported` in their description.
- The price numbers are given with two decimal places e.g. `20.00`.
