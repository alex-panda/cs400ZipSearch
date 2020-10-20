# --== CS400 File Header Information ==--
# Name: Alexander Peseckis
# Email: peseckis@wisc.edu
# Team: DC
# Role: Front End Developer
# TA: Yelun Bao
# Lecturer: Florian Heimerl

run: CommandLineDriver.class ZipCodeRBT.class RedBlackTree.class DataLoader.class Place.class
	java CommandLineDriver

compile: Place.class DataLoader.class RedBlackTree.class ZipCodeRBT.class CommandLineDriver.class DataWranglerTestSuite.class BackEndFrontEndTestSuite.class DataWranglerTestSuite.class BackEndFrontEndTestSuite.class TestData.class TestBackEndFrontEnd.class

test: junit5.jar DataWranglerTestSuite.class BackEndFrontEndTestSuite.class TestData.class TestBackEndFrontEnd.class
	# 	Xiaohan Shen's Tests
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTestSuite
	java -jar junit5.jar -cp . --scan-classpath -n BackEndFrontEndTestSuite
	# 	Allistair's Tests
	java -jar junit5.jar -cp . --scan-classpath -n TestData
	java -jar junit5.jar -cp . --scan-classpath -n TestBackEndFrontEnd

clean:
	rm *.class

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

# TestEngineers
# 	Xiaohan Shen's Tests
DataWranglerTestSuite.class: junit5.jar DataWranglerTestSuite.java
	javac -cp .:junit5.jar DataWranglerTestSuite.java

BackEndFrontEndTestSuite.class: junit5.jar BackEndFrontEndTestSuite.java
	javac -cp .:junit5.jar BackEndFrontEndTestSuite.java

# 	Allistair's Tests
TestData.class: junit5.jar DataWranglerTestSuite.java
	javac -cp .:junit5.jar TestData.java

TestBackEndFrontEnd.class: junit5.jar DataWranglerTestSuite.java
	javac -cp .:junit5.jar TestBackEndFrontEnd.java

