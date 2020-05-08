package P2;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

import P1.graph.ConcreteVerticesGraph;
import P1.graph.Graph;
import javafx.util.Pair;



public class FriendshipGraph {
	/**
	 * friendNetwork is defined as a Map,which the 'Key' is the person's name and the
	 * 'Value' is a List.This List contains some Pair.Each Pair contains the Key's
	 * friend and the cost between them.
	 */
	private Graph<Person> friendNetwork;
	public FriendshipGraph() {
		friendNetwork=new ConcreteVerticesGraph<>();
	}
	/**
	 * Insert a person
	 * 
	 * @param A a person to add in the network
	 * @return If the network doesn't have the same name,return false.
	 * 			else add a 'Key' into the friendNetwork.
	 */
	public Boolean addVertex(Person A) {
		return friendNetwork.add(A);
	}
	/**
	 * To add a relationship between A and B
	 * @param A the source person
	 * @param B the target person
	 * @return if A or B doesn't exit in the network return false
	 * 		   else return true
	 */
	public Boolean addEdge(Person A,Person B) {
		if(friendNetwork.vertices().contains(A)&&friendNetwork.vertices().contains(B)) {
			friendNetwork.set(A, B, 1);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * To get the distance between A and B
	 * @param A the source person
	 * @param B the target person
	 * @return a integer of the distance between A and B
	 * 			if their doesn't exit a road between A and B or 
	 * 			A or B doesn't exit in the network return -1 
	 */
	public int getDistance(Person A,Person B) {
		if(A.equals(B)) {
			return 0;
		}
		else if((!friendNetwork.vertices().contains(A))||(!friendNetwork.vertices().contains(B))) {
			System.out.println("Can't getDistance, The given name does not exist in the network.");
			return -1;
		}else {
			Queue<Pair<Person, Integer>> toPersonQueue=new LinkedBlockingDeque<Pair<Person, Integer>>();
			toPersonQueue.add(new Pair<>(A,(Integer)1));
			Set<Person> visitPersonSet=new HashSet<Person>();
			visitPersonSet.add(A);
			while(!toPersonQueue.isEmpty()) {
				Pair<Person, Integer> topOfQueue=new Pair<>(toPersonQueue.peek().getKey(),toPersonQueue.peek().getValue());
				toPersonQueue.remove();
				for(Person tempPerson:friendNetwork.targets(topOfQueue.getKey()).keySet()) {
					if(visitPersonSet.contains(tempPerson)) {
						continue;
					}
					visitPersonSet.add(tempPerson);
					if(tempPerson.equals(B)) {
						return topOfQueue.getValue();
					}else {
						toPersonQueue.add(new Pair<>(tempPerson,topOfQueue.getValue()+1));
					}
				}
			}
			return -1;
		}
	}
	public static void main(String argc[]) {
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
		System.out.println(graph.getDistance(rachel, ross));
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel));
		System.out.println(graph.getDistance(rachel, kramer));
	}
}
