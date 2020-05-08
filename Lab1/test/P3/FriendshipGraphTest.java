package P3;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class FriendshipGraphTest {
	Person rachel=new Person("Rachel");
	Person ross=new Person("Ross");
	Person ben=new Person("Ben");
	Person kramer=new Person("Kramer");	
	Person Mer=new Person("Mer");	
	@Test
	void testAddVertex() {
		FriendshipGraph graph=new FriendshipGraph();
		assertEquals(true, graph.addVertex(rachel));
		assertEquals(true, graph.addVertex(ross));
		assertEquals(true, graph.addVertex(ben));
		assertEquals(true, graph.addVertex(kramer));
		assertEquals(false, graph.addVertex(kramer));
	}

	@Test
	void testAddEdge() {
		FriendshipGraph graph=new FriendshipGraph();
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		assertEquals(true, graph.addEdge(rachel, ross));
		assertEquals(true, graph.addEdge(ross, rachel));
		assertEquals(true, graph.addEdge(ross, ben));
		assertEquals(true, graph.addEdge(ben, ross));
		assertEquals(false, graph.addEdge(ben, Mer));
	}

	@Test
	void testGetDistance() {
		FriendshipGraph graph=new FriendshipGraph();
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		assertEquals(1, graph.getDistance(rachel, ross));
		assertEquals(2, graph.getDistance(rachel, ben));
		assertEquals(0, graph.getDistance(rachel, rachel));
		assertEquals(-1, graph.getDistance(rachel, kramer));
	}

}
