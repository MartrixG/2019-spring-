package P3;

public class Person {
	private String name;
	public Person(){
		this.name=null;
	}
	public Person (String s) {
		this.name=s;
	}
	public void info() {
		if(name!=null) {
			System.out.println(this.name);
		}
		else {
			System.out.println("This person is not initialized\n");
		}
	}
	public int hashcode() {
		return name.hashCode();
	}
	public boolean equals(Object anObject) {
		if(this==anObject) {
			return true;
		}
		if(anObject instanceof Person) {
			Person anotherPerson = (Person)anObject;
			if(this.hashcode()!=anotherPerson.hashcode()) {
				return false;
			}else {
				if(anotherPerson.equals(this)) {
					return true;
				}else {
					return false;
				}
			}
		}else {
			return false;
		}
	}
}
