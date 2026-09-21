This web application allows companies to post new job listings, as well as update, delete or 
see all job postings.

The front end is built with React.js, while the backend is developed with Spring Boot.
The communication method is RESTful API.

The dependencies for the backend needed are:
- Lombok: https://mvnrepository.com/artifact/org.projectlombok/lombok
- Spring-boot-starter-webmvc: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-webmvc

To run the front-end:
1. Run the React app: Open a new terminal and type: npm install
2. And then: npm start (Starts the React application)

The local host sends a request from: http://localhost:3000
The server runs on http://localhost:8080
To send a get request: http://localhost:8080/jobPosts (GET)

You can simulate the backend server in frontend React at http://localhost:8000/posts 
but this fake server contains fake data in db.json (runs the fake server with: json-server --watch db.json --port 8000)  

## Why @RestController?
When you use the annotation @Controller, Spring Boot will think that you still return a view name.
But we want data, and return that data as JSON and not simple JSP view.

You instruct Spring with annotation @ResponseBody in the get/post/etc. method 
to tell what I send here is just data.

If you know that all the methods in the controller are for REST, 
you don’t need to add @ResponseBody.
You just need to change @Controller with @RestController.
  
## Connect React with Spring
React runs on localhost:3000 while Spring Boot runs on localhost:8080.
CORS (Cross-Origins Resource Sharing) prevents websites from requesting data 
from different domains/origins.
Different ports count as different origins, so the browser blocks these requests.

We need to add one more annotation in the Controller to allow cross-origin, 
and mention the front end port (3000).
This means: “please allow request from this particular URL”, so the Spring Boot application 
is able to accept requests from our React application coming from port 3000,
and React can get the data from the server at port 8000.
You can reach out the requests at the URL: http://localhost:3000/ 

## Get a specific job posting

We need to add a new method for our controller to get the job with a specified identifier.
That is, we need to create a new API endpoint that:
- Takes an ID from the URL {postId}
- Returns only the matching job post with id

1. {postId} in the URL path defines a variable part of the URL
2. @PathVariable("postId") extracts that variable and passes it to the method as id
3. Spring automatically converts the string from the URL to an integer
4. Path variables are defined with curly braces in the URL pattern: {postId}
5. The @PathVariable annotation connects the URL variable to a method parameter

## Add a specific job
The @RequestBody annotation is crucial for receiving data in REST APIs:
- It converts JSON data from the request body into a Java object.
- Spring automatically maps JSON fields to matching properties in JobPost class.
- No need to manually parse JSON - Spring does it automatically.
- Data comes in as the exact object type we need. 
- @PostMapping("/jobPost") creates an endpoint that accepts POST requests
- @RequestBody JobPost jobPost converts the incoming JSON to a JobPost object 










