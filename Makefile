default: build

build:
	@mvn clean install

db-start:
	@docker-compose -f docker/postgres-db/compose.yml up

db-clean:
	@docker-compose -f docker/postgres-db/compose.yml down -v --remove-orphans

db-migrate:
	@mvn -f app-db-migration/pom.xml spring-boot:run

api-start:
	@mvn -f app-api/pom.xml spring-boot:run

backoffice-start:
	@mvn -f app-backoffice/pom.xml spring-boot:run

front-start:
	@cd app-front && npm run serve 
