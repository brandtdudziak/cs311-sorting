
public class Assignment {
	
	//Name, due date, class - assigned date is automatic and priority and completed are automatically false
	private String name;
	private String due;
	private String classFor;
	private String assigned;
	private boolean priority;
	private boolean completed;
	private String link;
	
	public Assignment(String n, String d, String c){
		name=n;
		due=d;
		classFor=c;
		assigned="2017-01-01";
		priority=false;
		completed=false;
		link=null;
	}

}
