run: compile
	java CommandLineDriver

# Data/Backend/FrontEnd
compile:
	javac Place.java CommandLineDriver.java DataLoader.java RedBlackTree.java ZipCodeRBT.java

#Tests

test: TestBackEndFrontEnd.class TestDataWranglerSuite.class compile
	java -jar junit5.jar -cp . --scan-classpath -n TestBackEndFrontEnd
test1: TestDataWranglerSuite.class compile
	java -jar junit5.jar -cp . --scan-classpath -n TestDataWranglerSuite
TestBackEndFrontEnd.class:
	javac -cp .:junit5.jar TestBackEndFrontEnd.java

TestDataWranglerSuite.class:
	javac -cp .:junit5.jar TestDataWranglerSuite.java


#Clean

clean:
	rm *.class

