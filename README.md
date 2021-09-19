#How to build and run
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

#Design
The app was designed to emulate real life supermarket scenario. So, one goes to the supermarket with text description of the `Order`. Then we fetch `Item` from the shelf and add them to `Cart`. At the checkout counter, we `printBill`.

#Development
- The app was built using bottom-up TDD approach.
- In real life, the app configuration should be fetched from Configuration server. For this demo, it has been kept into `Constants.java`.

#Assumptions
- The exempted items will have either 'book', 'chocolate' or 'pill' in their description. This list is assumed to be sufficient for this demo.
- The input file strictly adheres to the structure i.e. `<quantity> <item description> at <base price>`.
- The imported items will have `imported` in their description.
- The price numbers are given with two decimal places e.g. `20.00`.