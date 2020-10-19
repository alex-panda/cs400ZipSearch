run: CommandLineDriver.class CommandLineDriver.class ZipCodeRBT.class RedBlackTree.class DataLoader.class Place.class
	java CommandLineDriver

testAll: testDataWrangler testBackEnd

testBackEnd: BackEndFrontEndTestSuite.class
	java -jar junit5.jar -cp . --scan-classpath -n BackEndFrontEndTestSuite

testDataWrangler: 
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTestSuite

clean:
	rm *.class

DataWranglerTestSuite.class: junit5.jar DataWranglerTestSuite.java
	javac -cp .:junit5.jar DataWranglerTestSuite.java

BackEndFrontEndTestSuite.class: junit5.jar BackEndFrontEndTestSuite.java
	javac -cp .:junit5.jar BackEndFrontEndTestSuite.java

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


