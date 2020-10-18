run: CommandLineDriver.class CommandLineDriver.class ZipCodeRBT.class RedBlackTree.class DataLoader.class Place.class
	java CommandLineDriver

test: DataWranglerTestSuite.class 
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTestSuite

clean:
	rm *.class

DataWranglerTestSuite.class: junit5.jar
	javac -cp .:junit5.jar DataWranglerTestSuite.java

junit5.jar: 
	wget http://pages.cs.wisc.edu/~cs400/junit5.jar

# FrontEnd
CommandLineDriver.class:
	javac CommandLineDriver.java

# Backend
ZipCodeRBT.class:
	javac ZipCodeRBT.java

RedBlackTree.class:
	javac RedBlackTree.java

# DataWrangelers
DataLoader.class:
	javac DataLoader.java

Place.class:
	javac Place.java


