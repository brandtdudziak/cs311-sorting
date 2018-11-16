
public interface AssignmentList {

	//This list is a To-Do List that keeps track of homework assignments
	
	
	//Add assignments to the list. Takes an Assignment object that, when first created, takes parameters for a String name,
	//String dueDate (represented yyyy-mm-dd), and String classFor (the name of the class it's for).
	//Additionally, four fields have defaults. String assignedDate is automatic (based on internal clock),
	//String link is null, and boolean priority and boolean completed are automatically set to false. 
	//Adds the Assignment to the end of the list.
	public void add(Assignment a);
	
	//Mark an Assignment completed. Changes completed to true in the Assignment and moves the Assignment to index
	//0 of the list.
	public void markComplete(Assignment a);
	
	//Change priority of an Assignment. If priority was false, changes priority in the Assignment to true and vice versa.
	public void changePriority(Assignment a);
	
	//Attach a Moodle link to an Assignment. Changes String link in the Assignment to the Moodle URL.
	public void link(Assignment a, String link);
	
	//Organize list by due date. Takes incomplete Assignments and orders them back into the list
	//"alphabetically" (earlier dates first) by dueDate, then alphabetically by name.
	public void organizeDue();
	
	//Organize list by assigned date. Takes incomplete Assignments and orders them back into the list
	//by assignedDate, then alphabetically by name.
	public void organizeAssigned();
	
	//Organize list by class (then due date within classes). Takes incomplete Assignments and orders them back
	//into the list first by classFor, then by dueDate, then alphabetically by name.
	public void organizeClass();
	
	//Returns Assignment in order to access information.
	public Assignment get(Assignment a);
	
	//Prints the uncompleted Assignments in the list with the format:
	//Name, class, due date, priority (if true), and link (if not null).
	public void print();
	
	//Returns number of incomplete Assignments left.
	public int left();
	
}
