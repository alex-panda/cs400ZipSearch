# --== CS400 File Header Information ==--
# Name: Xiaohan Shen
# Email: xshen97@wisc.edu
# Team: DC
# Role: Test Engineer
# TA: Yelun Bao
# Lecturer: Gary Dahl
# Notes to Grader: None

run: CommandLineDriver.class ZipCodeRBT.class RedBlackTree.class DataLoader.class Place.class
	java CommandLineDriver

compile: Place.class DataLoader.class RedBlackTree.class ZipCodeRBT.class CommandLineDriver.class DataWranglerTestSuite.class BackEndFrontEndTestSuite.class

test: testDataWrangler testBackEndFrontEnd

testBackEndFrontEnd: BackEndFrontEndTestSuite.class
	java -jar junit5.jar -cp . --scan-classpath -n BackEndFrontEndTestSuite

testDataWrangler: DataWranglerTestSuite.class
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


