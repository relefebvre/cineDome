all: film cine menu main alphaCmp noteCmp dateCmp

main: Main.java
	javac Main.java

film: Film.java
	javac Film.java

cine: Cine.java
	javac Cine.java

menu: Menu.java
	javac Menu.java

alphaCmp: AlphaComparator.java
	javac AlphaComparator.java

noteCmp: NoteComparator.java
	javac NoteComparator.java

dateCmp: DateComparator.java
	javac DateComparator.java

clean: 
	rm *.class

run:
	java Main
