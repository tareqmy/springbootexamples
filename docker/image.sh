cd ../ || exit
rm target/springbootexamples*.jar
mvn clean package -DskipTests=true
cp target/springbootexamples-*.jar docker/springbootexamples.jar
cd docker || exit

docker rmi springbootexamples:latest
docker build -t springbootexamples:latest .
rm *.jar
