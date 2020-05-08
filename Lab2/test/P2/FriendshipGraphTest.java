package P2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FriendshipGraphTest {
	@Test
	void testAddVertex() {
		FriendshipGraph graph=new FriendshipGraph();
		Person rachel=new Person("Rachel");
		Person ross=new Person("Ross");
		Person ben=new Person("Ben");
		Person kramer=new Person("Kramer");
		assertEquals(true, graph.addVertex(rachel));
		assertEquals(true, graph.addVertex(ross));
		assertEquals(true, graph.addVertex(ben));
		assertEquals(true, graph.addVertex(kramer));
		assertEquals(false, graph.addVertex(kramer));
	}

	@Test
	void testAddEdge() {
		FriendshipGraph graph=new FriendshipGraph();
		Person rachel=new Person("Rachel");
		Person ross=new Person("Ross");
		Person ben=new Person("Ben");
		Person kramer=new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		assertEquals(true, graph.addEdge(rachel, ross));
		assertEquals(true, graph.addEdge(ross, rachel));
		assertEquals(true, graph.addEdge(ross, ben));
		assertEquals(true, graph.addEdge(ben, ross));
	}

	@Test
	void testGetDistance() {
		FriendshipGraph graph=new FriendshipGraph();
		Person rachel=new Person("Rachel");
		Person ross=new Person("Ross");
		Person ben=new Person("Ben");
		Person kramer=new Person("Kramer");
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
