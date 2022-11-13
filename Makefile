JC = javac

Main.java: Main DMain

Main:
	$(JC) wordladder/Main.java wordladder/Graph.java wordladder/Vertex.java

DMain:
	$(JC) dijkstra/Main.java dijkstra/DijkstraGraph.java dijkstra/DijkstraVertex.java


clean: CMain CDMain

CMain :
	rm -rf wordladder/*.class

CDMain:
	rm -rf dijkstra/*.class
