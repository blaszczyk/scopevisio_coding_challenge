cd postcodeservice
call gradlew bootJar
call docker build -t postcode-service .
cd ../praemienservice
call gradlew bootJar
call docker build -t praemien-service .
cd ../praemien-ui
call npm run build
cd ../webservice
call gradlew bootJar
call docker build -t praemien-webservice .
cd ..
