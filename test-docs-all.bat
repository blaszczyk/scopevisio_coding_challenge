cd postcodeservice
call gradlew asciidoc
cd ../praemienservice
call gradlew asciidoc
cd ../webservice
call gradlew test asciidoc
cd ..
del /S /Q /F docs\*
rmdir /S /Q docs
if not exist "docs" md docs
cd docs
if not exist "postcodeservice" md postcodeservice
if not exist "praemienservice" md praemienservice
if not exist "webservice" md webservice
cd ..

robocopy "postcodeservice/build/docs/asciidoc/" "docs/postcodeservice" /E
robocopy "praemienservice/build/docs/asciidoc/" "docs/praemienservice" /E
robocopy "webservice/build/docs/asciidoc/" "docs/webservice" /E
