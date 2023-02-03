.PHONY : test build clean format package

include .env
$(eval export $(cat .env | sed 's/#.*//g' | xargs))

package:
	mvn clean package -Dmaven.test.skip

test:
	mvn test -Dspring.profiles.active=test

cover:
	# mvn -Dspring.profiles.active=test -Dsonar.host.url=http://localhost:9000 -Dsonar.login=squ_73e9e52e8f9a0e6fe2603f8769c089925dc18446 clean verify sonar:sonar
	mvn -Dspring.profiles.active=test -Dsonar.host.url=${SONAR_HOST} -Dsonar.login=${SONAR_LOGIN} clean verify sonar:sonar
	# mvn -Dspring.profiles.active=test clean verify sonar:sonar