# TravelAgency
Travel Agency
This application is designed to manage the activity inside a travel agency. Using this application, travel agencies can  information
Business requirements
1.	There are several tourism agencies in the database.
2.	 The agents who work for a specific agency help the customers to choose from the available offers.
3.	 Customers can book offers based on their requirements.
4.	 An offer contain information about accommodation and transportation for the desired destination.
5.	 Customers can buy multiple offers with different agents' help.
6.	 Customers can view information about what type of accommodation(hotel, hostel, etc.), time for check-in and check-out, price, and where is located.
7.	 Customers can view information about what type of transport(plane, car, train, etc.), arrival and departure time, price, and destination.
8.	 Customers provide personal information( first name, last name, email, birth date) relevant to make a reservation.
9.	 Information about a travel agency's address, name, and email is stored in the database.
10.	 An agent works only for one agency. He has a first name, last name, email, phone number, and salary.
11.	The administrator can view all offers made available by a specific travel agency.
 
The functionality of the application is demonstrated using Postman. I created a Postman collection of API for each endpoint. This collection is exported as JSON.

Swagger Documentation can be found at the following address http://localhost:8095/swagger-ui/index.html#/

Endpoints:

AgencyEndpoints:


GET: Display All Agencies

-path: "/agencies"

-pathParameters: None


GET: Display Agency By Id

-path: "/agencies/{id}"

-pathParameters: Long id


POST: Add New Agency

-path: "/agencies"

-requestBody: Agency agency


PATCH: Update Agency Name

-path: "/agencies/{id}/{name}"

--pathParameters: Long id, String Name


DELETE: Delete Agency By Id

-path: "/agencies/{id}"

-pathParameters: Long id


AgentEndpoints:

GET: Display All Agents

-path: "/agents"

-pathParameters: None


GET: Display Agent By Id

-path: "/agents/{id}"

-pathParameters: Long id


POST: Add New Agency

-path: "/agents"

-requestBody: Agent agent


PATCH: Update Salary

-path: "/agents/{id}/{percent}/{shouldIncrease}"

--pathParameters: Long id, Double percent, Boolean shouldIncrease


DELETE: Delete Agent By Id

-path: "/agents/{id}"

-pathParameters: Long id


CustomerEndpoints:

GET: Display All Customers

-path: "/customers"

-pathParameters: None


GET: Display Customer By Id

-path: "customers/{id}"

-pathParameters: Long id


GET: Display Customers Born After a specific Year

-path: "customers/year/{year}"

-pathParameters: Long Year


POST: Add New Customer

-path: "/customers"

-requestBody: Customer Customer


DELETE: Delete CustomerBy Id

-path: "/customers/{id}"

-pathParameters: Long id


TransportationEndpoints:

GET: Display All Transportations

-path: "/transportations"

-pathParameters: None


GET: Display Transportation By Id

-path: "/transportations/{id}"

-pathParameters: Long id


POST: Add New Transportation

-path: "/transportations"

-requestBody: Transportation transportation


DELETE: Delete Transportation By Id

-path: "/transportations/{id}"

-pathParameters: Long id


AccommodationEndpoints:

GET: Display All Accommodations

-path: "/accommodations"

-pathParameters: None

GET: Display Accommodation By Id

-path: "/accommodations/{id}"

-pathParameters: Long id


POST: Add New Accommodation

-path: "/accommodations"

-requestBody: Accommodation accommodation


DELETE: Delete Accommodation By Id

-path: "/accommodations/{id}"

-pathParameters: Long id
