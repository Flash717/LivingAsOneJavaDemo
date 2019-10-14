# Living As One Demo
Demo App for Living As One

https://github.com/hawkescom/marsrover/blob/master/README.md

--- 
## Requirements:

The exercise we’d like to see is to use the NASA API described here to build a project in GitHub that calls the Mars Rover API and selects a picture on a given day. We want your application to download and store each image locally.

### Acceptance Criteria
- Please send a link to the GitHub repo via matt.hawkes@livingasone.com when you are complete.
- Use list of dates below to pull the images were captured on that day by reading in a text ﬁle:  
    - 02/27/17  
    - June 2, 2018   
    - Jul-13-2016  
    - April 31, 2018  
- Language can be Java, Python, JavaScript, or Go.   
- We should be able to run and build (if applicable) locally after you submit it  
- Include relevant documentation (.MD, etc.) in the repo
### Bonus  
- Bonus - Unit Tests, Static Analysis, Performance tests or any other things you feel are important for Deﬁnition of Done  
- Double Bonus - Have the app display the image in a web browser  
- Triple Bonus – Have it run in a Docker or K8s (Preferable)  
---

## Setup:

- Clone repo locally (`git clone https://github.com/Flash717/LivingAsOneJavaDemo.git`)
- To run locally:
    - Requires Java version 8 or later and Maven to be installed
    - Run `mvn spring-boot:run` to start http-server on localhost port 8081
    - Run `mvn test` to run unit tests
- To run in Docker container:
    - Requires Docker to be installed locally
    - Run `docker build -t <image-name-of-your-choice> .` to build Docker image
    - Run `docker run -d -p 8081:8081 <image-name-of-your-choice>` to run image in a container

---
## Endpoints:

- GET `/` 
    - Homepage (http://127.0.0.1:8081/)
    - Lists all images that have been loaded via API and are stored locally
- GET `/picture/<date>` 
    - Gets pictures for a specific date and stores locally
    - Returns JSON object with submitted date and list of loaded pictures
- GET `/acceptance`
    - Runs acceptance test for Living As One exercise. 
    - Loads dates stored in file
    - Retrieves related pictures from Mars Rover API
    - Returns JSON object with list of submitted dates and loaded pictures for each correct date