.PHONY : test build clean format package

include .env
$(eval export $(cat .env | sed 's/#.*//g' | xargs))

package:
	mvn clean package -Dmaven.test.skip

test:
	mvn test -Dspring.profiles.active=test

cover:
	mvn -Dspring.profiles.active=test -Dsonar.host.url=${SONAR_HOST} -Dsonar.login=${SONAR_LOGIN} clean verify sonar:sonar