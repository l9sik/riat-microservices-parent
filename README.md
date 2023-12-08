This repository is just my attempt in using spring boot/cloud/security. 

Also i tried microservice architecture here. 
For security i tried OAuth 2.0.

I used:
- Spring
- Docker
- Hibernate(MySQL)
- RabbitMQ
- WebClient
- Zipkin
- KeyCloack

The request first comes to api-gateway, then to security server. If authenticated< then the discovery-server brings actual ip address of microservice to send a reqeust.
