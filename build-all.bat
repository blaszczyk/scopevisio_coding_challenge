cd postcodeservice
call gradlew bootJar
call docker build -t postcodeservice .
cd ../praemienservice
call gradlew bootJar
call docker build -t praemienservice .
cd ../praemien-ui
call npm run build
cd ../webservice
call gradlew bootJar
call docker build -t webservice .
cd ..
