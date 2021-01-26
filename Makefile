up:
	- mvn -f ./ratpack-payments clean package
	- docker-compose up

down:
	- docker-compose down