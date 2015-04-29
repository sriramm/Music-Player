public class Node //object class that controls the node objects which point to the song object references
{
	//Stores the reference to this nodes song
	private Song currentSong; //private Song object variable which controls which song object is the current one
	//Stores the reference to the node that follows this one in the list
	private Node next; //private Song object variable which controls which song object is the next one 

	//The constructor
	public Node (Song s, Node n) //constructor for the node object class which takes inputs of current song
	//reference and the node reference to the next node
	{
		currentSong = s; //sets the currentSong object variable from this class to the one input by the user
		next = n; //sets the next object variable from this class to the one input by the user
	}




	//Returns the reference to this nodes song
	public Song getSong () //method that gets the song and returns the song reference of the song that 
	//is currently being played
	{
		return currentSong; //returns the reference to the current song (the currentSong variable)
	}


	//Returns the reference to the next node in the list
	public Node getNext () //method that gets the next node and returns the node reference of the node that 
	//is next
	{
		return next; //returns the reference to the next node (the 'next' song variable)
	}


	//set a new next node in the list
	public void setNext (Node n) //method that sets the next node to the any node input by the user
	{
		next = n; //sets the 'next' node found in this class to the one the user inputs
	}
}
