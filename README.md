[![Build Status](https://travis-ci.com/simonkarlsen/PG5100Enterprise1_exam.svg?branch=master)](https://travis-ci.com/simonkarlsen/PG5100Enterprise1_exam)

#PG5100 Enterprise Programming 1 - exam


### How to run the application
1. Run this command to install Maven dependencies and to run all tests: `mvn install`
2. Run this command to run all the tests and get the report of coverage (found in report module): `mvn clean verify`
(report --> target --> site --> jacoco-aggregate --> index.html --> right click --> open in browser)
3. To launch the application, run `LocalApplicationRunner` which will be found in the test folder in the frontend module 
(frontend --> src --> test --> java --> com.example.exam --> LocalApplicationRunner) 
4. To view the website, open your browser and go to: `localhost:8080`

---

### Application requirements
- I did requirements for R1, R2, R3 and tried R4 partially(did not manage to complete it) 
(pluss an extra feature (goes under R5, but did not complete this step either))

---

### Info on page
This application / website includes the following functions:


- The user can see the list of all movies on the home page, which includes some movie info,
 amount of average stars, and amount of reviews the movie has (to give some context to the average stars).
 (The movies are ordered by highest average star rating to lowest (from top to bottom)).
 (does not need to be logged in to view this).
 
- If a user wants to see the reviews, the user can navigate to the Movie Details page 
(does not need to be logged in) either by clicking the Movie Details button on the top of the page
or by clicking the link to movie details page on the movie entry.

- On the Movie Details page you can see all the reviews. The user can sort by either stars given or the time
the review was created (newest on top/first). 
(Note: the created time will be two hours off since the formatting of <f:convertDateTime 
is GMT+0 by default).

- If the user wants to make a review, the user has to either log in or sign up as a new user 
(password cannot be blank when signing up. If it is, an error will be shown and the signup will be aborted).
(Should also be aborted of the username is blank or if username already exists).
 (The movie names and it's review inputs are located underneath the table of reviews)
The user can then make a review by inputting a review text and the amount of stars (1 to 5) the user wants 
to give the movie.

- The user can only make one review per movie.

- After the review is submitted (by the submit button), the user can see the review being displayed with 
all the other reviews (on top if sorted by created time, which is default).

- If the user wants to see how his/her review impacted the average stars of the movie, the user can
navigate back to the homepage and look at the new average stars of the movie. 
(If the user has made no reviews, a message will be displayed.)

- If the user wants to see his/her review(s), the user can go to the dedicated user page (has to be logged in).
(this is something extra).

- When the user is logged in, a welcome message will be displayed.

- The user can log out (or log in / sign up) from all pages.

---

###Functionality:

####R1: 
- [x] Using flyway to initialize H2 database
- [x] 3 JPA entities: Review, Movie, Users
- [x] @Service classes for entities: ReviewService, MovieService, UserService
- [x] create a user
- [x] create/delete a movie
- [x] add review to a movie, with a 1 to 5 star
- [x] compute average of stars per movie
- [x] retrieve all movies, sorted by average stars
- [x] retrieve all reviews for a movie, sorted by either stars or creation time of the review 
(my appplication sorts by creation time by default)


###R2:
- [x] Write integration tests for each of the @Service classes using JUnit and @SpringBootTest annotation
- [x] Code coverage over 70%: 91% jacoco coverage (run tests with coverage --> line: 86%)

###R3:
Homepage:
- [x] display all movies
- [x] If the user is logged in, then display a welcome message
- [x] Each movie entry must show the average stars it has, and link to a “movie details” page

Movie details page:
- [x] show all reviews and their stars
- [x] Give possibility to sort reviews by time and by stars
- [x] If the user is logged in, enable possibility to write a new review.
- [x] A user can only write one review per movie.

User login/signup page:
- [x] based on Spring Security and storing of user info on the SQL database
- [x] It should be possible to logout from any of the pages
- [x] When a login/signup fails, you MUST show an error message.

###R4:
- [x] For each web page, implement a corresponding Page Object
- [x] testDefaultMovies: (verified all 10 movies that exists)
- [] testWriteReview (did not manage to do this)
- [] testStars (did not manage to do this)
- [] testSorting (did not manage to do this)
- [x] total code coverage of 80% 
    --> my total code coverage 85 %
    (frontend jacoco coverage is 65%)
        
---

### Coverage
All tests should run.

- Backend: 91% jacoco coverage (run tests with coverage --> line: 86%)
- Frontend: 65% coverage 
- Total: 85% coverage

---

###Extra 
- User page with info about the reviews the user has made.
---

###Links
- https://mkyong.com/java/java-display-double-in-2-decimal-points/
- ListIterator: https://stackoverflow.com/questions/18410035/ways-to-iterate-over-a-list-in-java

---

### Things that could be done better:
- No error message when the user tries to make a review on a movie that the user
    already has reviewed. (but the review gets cancelled)
 
- No error message when the user tries to enter a number for a review (with text) and text in the 
    rating input (number).(However, the review will not be created with these illegal inputs.
    (nothing happens at all). 
    
- TransactionSystemException when trying to give a review a number that's not 1-5. Could give error message instead.

- Time formatting ( <f:convertDateTime pattern="dd/MM/yyyy, HH:mm:ss"/>) gives wrong time because
of default GMT+0. Could be formatted to right timezone. You can remove the the convertDateTime, and 
the time will be right, but the formatting will be off (milliseconds will also show).

- The star value in the input review field is 5 by default. If I do not set a value, it will be 0.
(figured 5 would be better).
Should be blank or the value should reset after a review. 

- DataIntegrityViolationException error when review text is too long (exceeds the limit (varchar 255)). 
Should display error text instead.
---

### Things I did not manage to do:
- Integrate Selenium tests:
    *    --> testWriteReview 
    *    --> testStars 
    *    --> testSorting 
    * R5 
---   
    
### (Could be) Bugs:
- TransactionSystemException when trying to give a review a number that's not 1-5
- 

- Not necessarily a bug, but should be mentioned:
    * When you order by stars on the movie details page, then go to another page
and then go back again to the movie details page, the select will still display "Order by stars",
but the reviews will be sorted by created time by default (every time the page loads).
