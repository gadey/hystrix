# Microservice with eureka and hystrix

# clone the repo
  1. clone the repo with https://github.com/gadey/hystrix.git
  2. run the service-registry micro service
  3. run the remaining microservices
  4. open http://localhost:9009 will see the two service registered
  5. call the school micro service endpoint with http://localhost:9098/getSchoolDetails/abcschool this will call internally 
      with feignclient to student details  
