run: compile
	java CommandLineDriver

compile: CommandLineDriver.class CommandLineDriver.class ZipCodeRBT.class RedBlackTree.class DataLoader.class Place.class

test: TestRedBlackTree.class
	java -jar junit5.jar -cp . --scan-classpath -n TestRedBlackTree

clean:
	rm *.class

TestRedBlackTree.class:
	javac -cp .:junit5.jar TestRedBlackTree.java

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


