all: univC dataC allC
clean: cleanUniv cleanDatabase cleanAll

univC:
	javac -cp .:mysql-connector-java-8.0.13.jar ./univ/*.java

dataC:
	javac -cp .:mysql-connector-java-8.0.13.jar ./database/*.java

allC:
	javac -cp .:mysql-connector-java-8.0.13.jar *.java

run:
	java -cp .:mysql-connector-java-8.0.13.jar Planner

cleanUniv:
	rm -rf ./univ/*.class

cleanDatabase:
	rm -rf ./database/*.class

cleanAll:
	rm -rf *.class
	