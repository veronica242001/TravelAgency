# TravelAgency
:sun_with_face:  Travel Agency

This application is designed to manage the activity inside a travel agency. Using this application, travel agencies can track information about their business requirements.
1.	There are several tourism agencies in the database.
2.	The agents who work for a specific agency help the customers to choose from the available offers.
3.	Customers can book offers based on their requirements.
4.	An offer contains information about accommodation and transportation for the desired destination.
5.	Customers can buy multiple offers with different agents' help.
6.	Customers can view information about what type of accommodation(hotel, hostel, etc.), time for check-in and check-out, price, and location.
7.	Customers can view information about what type of transport(plane, car, train, etc.), arrival and departure time, price, and destination.
8.	Customers provide personal information( first name, last name, email, birth date) relevant to make a reservation.
9.	Information about a travel agency's address, name, and email is stored in the database.
10.	An agent works only for one agency. He has a first name, last name, email, phone number, and salary.
11.	The administrator can view all offers a specific travel agency makes available.
 
The functionality of the application is demonstrated using Postman. I created a Postman collection of API for each endpoint. This collection is exported as JSON.

:sun_with_face:  Swagger Documentation can be found at the following address http://localhost:8095/swagger-ui/index.html#/

:sun_with_face:  Endpoints:

 :sun_with_face: AgencyEndpoints:


:sun_with_face: GET: Display All Agencies

-path: "/agencies"

-pathParameters: None


:sun_with_face: GET: Display Agency By Id

-path: "/agencies/{id}"

-pathParameters: Long id


:sun_with_face: POST: Add New Agency

-path: "/agencies"

-requestBody: Agency agency


:sun_with_face: PATCH: Update Agency Name

-path: "/agencies/{id}/{name}"

--pathParameters: Long id, String Name


:sun_with_face: DELETE: Delete Agency By Id

-path: "/agencies/{id}"

-pathParameters: Long id


:sun_with_face: AgentEndpoints:

:sun_with_face: GET: Display All Agents

-path: "/agents"

-pathParameters: None


:sun_with_face: GET: Display Agent By Id

-path: "/agents/{id}"

-pathParameters: Long id


:sun_with_face: POST: Add New Agency

-path: "/agents"

-requestBody: Agent agent


:sun_with_face: PATCH: Update Salary

-path: "/agents/{id}/{percent}/{shouldIncrease}"

--pathParameters: Long id, Double percent, Boolean shouldIncrease


:sun_with_face: DELETE: Delete Agent By Id

-path: "/agents/{id}"

-pathParameters: Long id


:sun_with_face: CustomerEndpoints:

:sun_with_face: GET: Display All Customers

-path: "/customers"

-pathParameters: None

:sun_with_face: GET: Display Customer By Id

-path: "customers/{id}"

-pathParameters: Long id


:sun_with_face: GET: Display Customers Born After a specific Year

-path: "customers/year/{year}"

-pathParameters: Long Year


:sun_with_face: POST: Add New Customer

-path: "/customers"

-requestBody: Customer Customer


:sun_with_face: DELETE: Delete CustomerBy Id

-path: "/customers/{id}"

-pathParameters: Long id


:sun_with_face: TransportationEndpoints:

:sun_with_face: GET: Display All Transportations

-path: "/transportations"

-pathParameters: None


:sun_with_face: GET: Display Transportation By Id

-path: "/transportations/{id}"

-pathParameters: Long id


:sun_with_face: POST: Add New Transportation

-path: "/transportations"

-requestBody: Transportation transportation


:sun_with_face: DELETE: Delete Transportation By Id

-path: "/transportations/{id}"

-pathParameters: Long id


:sun_with_face: AccommodationEndpoints:

:sun_with_face: GET: Display All Accommodations

-path: "/accommodations"

-pathParameters: None

:sun_with_face: GET: Display Accommodation By Id

-path: "/accommodations/{id}"

-pathParameters: Long id


:sun_with_face:  POST: Add New Accommodation
-path: "/accommodations"

-requestBody: Accommodation accommodation


:sun_with_face: DELETE: Delete Accommodation By Id

-path: "/accommodations/{id}"

-pathParameters: Long id
