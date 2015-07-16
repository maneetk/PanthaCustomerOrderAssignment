# PanthaCustomerOrderAssignment

API for customer
=================
baseUriCustomer=/api/customer

GET - baseUri - Fetches a list of all customers and orders
GET - baseUri/{customerId} - Fetches a specific customer data
GET - baseUri[?name=searchParameter, page=pageNumber, size=noOfResultsPerPage] - Fetches a list of customers and orders 
name - searched partially and case insensitive in firstName, lastName or emailId
PUT - baseUri/{customerId} and data in JSON - Updates an existing record with given customerId
POST - baseUri and data in JSON - Adds a new record
DELETE - baseUri/{customerId} -  Deletes an existing record with given customerId


API for order
=================
baseUriCustomer=/api/order

GET - baseUri - Fetches a list of all orders
GET - baseUri/{orderId} - Fetches a specific order data
PUT - baseUri/{orderId} and data in JSON - Updates an existing record with given orderId
POST - baseUri and data in JSON - Adds a new order record
DELETE - baseUri/{orderId} -  Deletes an existing record with given orderId
