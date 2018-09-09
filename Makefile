default: build

build:
	@mvn clean install

db-start:
	@docker-compose -f docker/postgres-db/compose.yml up

clean:
	@docker-compose -f docker/postgres-db/compose.yml down -v --remove-orphans