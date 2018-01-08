CLASS_PATH=.
JCFLAGS= #-Xlint
.PHONY: clean test

all:
	javac $(JCFLAGS) -cp $(CLASS_PATH) *.java

clean:
	rm -f *.class

# run with -ea:Heap for enable assertion
test:
	make all
	java -ea:Heap -cp $(CLASS_PATH) HeapTest
