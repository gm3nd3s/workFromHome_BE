
  
  # Work From Home
  ## **Description**
  This program was developed as a study project in the Mindswap Academy. The program can do requests to an externel API (Teleport - https://developers.teleport.org/) with cities and their rankings of evaluation. As a User you can see a list of cities, you can add them to your list of visited cities, you can also make a review of a city you visited and have a living city in your profile. This project was made thinking on the people that want to explore the world living in different places for short periods of time.

  ---

  ## **Table of Contents**
  * [Installation](#installation)
  * [Usage](#usage)
  * [Credits](#credits)
  * [Questions](#questions)
   
  ---

  ## **Installation**
  
  Download the files. 
  Run the docker compose. 
  Change your schema name on aplication properties. 
  Both application container and the databse container will start.

  ---

  ## **Usage**
  
  The product uses REST API. Every end point is also visible to be analysed in the swagger link.
  List of possible requests:
  
  #### **RequestMapping("/teleportApi/“)**

  - Getters (from Teleport API)
      - Get a specific city: /city/{cityName}
      
	  - Get all cities: /cities
    
	  - Get a list of cities based on a category and a minimum score: /citiesFromApi/{category}/{minimum_score}

  #### **RequestMapping("/api/“)**
  
  - Getters

	  - Get all users: /users

	  - Get username of a user: /{username}
	
	  - Get a new token: /refreshToken

	  - Get a list of visited cities in database: /allCities

	  - Get a list of visited cities in database by username: /allCities/{username}

	  - Get the living city of a user: /livingCity/{username}

	  - Get a ordered list of visited cities: /citiesOrdered

	  - Get a list of cities based on a category and a minimum score: /cities/{category}/{score}
  
	  - Get a city by the city name on database: /cityDB/{cityName}


  - Posters 

	  - Creating a user: /user

	  - Creating a role: /role

	  - Creating a review to a living city /review/{username}/{cityName}


  - Updaters

	  - Update role of user: /role={name}/user={username}

	  - Update the living city of a user: /visited/{username}/{cityName}

	  - Update the review of a living city: /updateReview/{cityName}/{user}

	  - Update the living city of a user: /livingCity/{username}/{cityName} 


  - Deleters

	  - Delete user: /delete/user={username}

	  - Delete role: /delete/role={roleType}
    
  #### **RequestMapping("/api/user“)**
  
  - Updaters

	  - Update the password: /changepassword
    
  ---

  ## **Credits**
  Gonçalo Mendes, Ana Sequeira and Tiago Costa.
  
  ---

  ## **Questions?**

  ### **Github**
  [Repository](https://github.com/Personal-Organization-Gm3nd3s/backendproject)

  [Personal Page](https://gm3nd3s.github.io)

  If you have any questions, you can reach me at <mendes.gjl@gmail.com>
  
  <img width="1019" alt="Screenshot 2022-09-28 at 13 05 19" src="https://user-images.githubusercontent.com/104323577/192774539-876e745e-642f-4893-adad-4b2b96d8025d.png">

