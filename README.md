# Sugar-Shack
Backend Spring Boot App to Manage a Maple Syrup Service

## Hello Everyone, Welcome to Sugar Shack!

Want to have your grandmother's favorite recipe on Sunday morning but lacks a delicious maple syrup?
Here you can look for your favorite delicious maple syrup and order it online!
# But Fabricio, How can I access Sugar-Shack System?
#### First of all:
1. Clone this repository!
2. Inside Sugar-Shack project, run `mvn clean install` to install dependencies
3. Run `mvn spring-boot:run` to start the application
4. Application will be available on port `8080`
5. Oops, almost forgot! User is "shack" and password is "pancakes". They are required in cart operations.

OR open it on your favorite IDE.

Required JAVA 11

## Features
All our endpoints are accessible (and documented) on the swagger page: http://localhost:8080/swagger-ui/index.html.
Tests are also ready to run on the IDE.
There is also a Postman collection on the repository root.

#### 1. Look  through our  *complete catalog* of maple syrup, or *filter by your type of choice*: AMBER, CLEAR or DARK.
Go to GET/products for the whole list of products.
Or use query parameters to select your type of choice. Just like this: GET/products?type=AMBER.

(Don't know which one to choose? Take a look on this site for reference:  https://ppaq.ca/en/maple-sirup/4-grades-maple-syrup/)
*OBS: We're out of *Very Dark* Maple Syrup type. Sorry :(

#### 2. Want to look into product details?
Currently we have 6 products available on our database, browse each one of them to have a sweet surprise and see which one you like the most!

Go to:
GET/products/1
GET/products/2
GET/products/3
GET/products/11
GET/products/22
GET/products/33

#### 3. You've made up your mind and want to *add*, *get*,*change* or *delete* a product from the cart!
That's easy too!
Just go to PUT/cart and add your favorite product
Want to change the quantity in your cart?
Call a PATCH/ method with the productId and desired quantity or call Delete to erase the cart (We'll feel sorry for you though! You deserve to taste our special products)

#### 4. Last but not least, don't be greedy!
Yeah, we have a high demand of products and in order to make our maple syrups available for the everybody we have a limit on the quantity of products allowed per order. Our systems will automatically check if your cart do not exceed this max quantity allowed.

#### 5. Enjoy!
Here is list of recipes that you can enjoy with your new maple syrup!
https://maplefromcanada.ca/recipes/