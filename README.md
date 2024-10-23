Circuit Breaker Design Pattern 


•	Circuit Breaker => It is an electric concept.
•	It is used to protect us from High voltage or Low voltage.
•	It is used to divert traffic when some problem detected in normal execution flow.
•	We can use Circuit Breaker concept in our Microservice to implement foult tolerence system / resilence systems.

Note : When main logic is failing countinously then we have to execute fallback logic for sometime.

Pattern State :

A.	Closed State
B.	Open State
C.	Half-Open State
 
What is Resilience4j ?
It is light weight , easy to use fault tolerance library inspired by Netflix Hystrix.

Features :

•	Circuit Breaker – Fault Tolerence
•	Rate Limiter – block too many request
•	Time Limiter – limit time while calling remote operations
•	Retry Mechanism – automatic retry for failed operation
•	Bulkhead – limit number of concurrent requests
•	Cache – Store result of costly remote operation.

Configuration Properties :

spring:
  application:
    name: 06_Resilience4j_CircuitBreaker_App

# actuator end points 
management:
  endpoints:
    web:
      exposure:
        include:
        - '*' 
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true

# circuit breaker properties
resilience4j.circuitbreaker:
    configs:
      default:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true
        wait-duration-in-open-state: 5s
        failure-rate-threshold: 50
        event-consumer-buffer-size: 10
        



