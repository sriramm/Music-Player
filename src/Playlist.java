import java.io.*;
import java.util.*;
public class Playlist
{


        //Stores the reference to a playlists head
        private Node head;
        //Stores the reference to a playlists tail
        private Node tail;
        //Stores the playlists name
        private String name;
        //Stores the number of songs a playlist has
        private int sizeOfList;
        //Stores the reference to the current node
        private Node current;
        //Stores the position to the current node
        private int pos;

        //The constructor: takes the initial name for the play list and sets the name variable from this class to that string
        public Playlist (String listName)
        {
                //your code here
                name = listName;

                head = new Node (null, tail); //sets the head node, the song reference to null, and the next node reference to the tail (to make size of array 0 initially)
                tail= null; //tail is null
        }


        /*
    1) Create a Playlist and be able to give it a name (i.e. Bryan's Workout Blast, DJ Dani's Party Remix, Srirams's Slow Jams)
    3)Add a song to a given list given the list name. If the index is specified, insert the song at that index, if none is specified append to the end of the list
    4)Delete a song from the list given the list name, and the given index or the given name of the song
    5)Modify the details of an existing song given the index
    5)Return what song is to be played next, or what song is at a specific spot in the list
    6)Return what song is last in the list
    7)Print out all the given songs in a list
    8)Get the size of a given playlist
    9)Clear all the songs from a playlist
    10)Return the number of times a song occurs in a given list- lists can have multiples of the same song
         */

        public void setPlayListName (String name){ //method that sets the name of the current play list changing
                //the name variable from this class to the name input from the user
                this.name = name;
        }
        
        //method that finds the number of times a song occurs in a specific play list
        public int numTimesSong (String name){ //parameter of the song's title
        	Node tempNode = head.getNext(); //creates a new temporary node that starts at the location
        	//after the head node
        	int counter=0; //new variable that keeps track of the number of occurrences of a song in a 
        	//play list and starts at value 0
        	while(tempNode!=null){ //if the current temporary node is not null then the loop continues
        		if(tempNode.getSong().getTitle().equalsIgnoreCase(name)){ //gets the song title from the
        			//current node and compares it to the string name entered by the user
        			counter++;//if name matches, increases the counter value by 1
        		}
        		tempNode=tempNode.getNext();//goes through all of the nodes like this by getting the next node
        	}
        	return counter;//returns the value of counter at the end of the method, and if song never existed
        	//then returned counter value is 0
        }

        //Add a new song to the end of the list.
        public void addSong (String nameSong, String artist, String year, String nmOfAlbum, double numStars) //a method that adds a song given the song name, year, name of album,
        //rating
        {
                Node tempNode = head; //creates a temporary node and sets it to the head

                while (tempNode.getNext()!=null){ //runs through the nodes as long as one node is not null (i.e. does not reach tail)
                        tempNode = tempNode.getNext(); //gets the next node in the list (going through list)
                }

                Node tempNode101 = new Node (new Song (nameSong, artist, year, nmOfAlbum, numStars), tail); //creates a new temporary node for
                //use in adding the song, given the information that the user provided
                tempNode.setNext(tempNode101); //sets the node after the current temporary node to be the node, containing the song, that has to be added
                if (sizeOfList == 0){ //if size of the play list is not 0
                        current = head.getNext(); //the current node is the node after the head (for song playing purposes)
                        pos = 1; //value of variable position is set to 1
                }

                sizeOfList++; //once song is successfully added, increases the size of the list by 1
        }


        //Add a new song to the list at the specified index
        public void addSong (int index, String nameSong, String artist, String year, String nmOfAlbum, double numStars) //method that adds a song given
        //the required information and the index to add the song
        {
                if (index > sizeOfList){ //if the index specified by the user is bigger than the size of the list then...
                        addSong(nameSong, artist, year, nmOfAlbum, numStars); //calls the previous addSong method that will add the song to the end of the list
                }
                else if (index <= 1){ //otherwise, if the index specified is less then or equal to 1 then...
                        Node tempNode101 = new Node (new Song (nameSong, artist, year, nmOfAlbum, numStars), head.getNext()); //new temporary node that
                        //contains all of the inputs from the user
                        head.setNext(tempNode101); //sets the new song to the beginning of the list (sets it after the head)

                        if (sizeOfList == 0){ //if the size of the list is 0 (no songs added)
                                current = head.getNext(); //current song is set to the song after the head
                                pos = 1; //value of variable position is set to 1
                        }

                        sizeOfList++; //once song is successfully added, increases the size of the list by 1
                }
                else{ //if both conditions above are not met then...

                        Node tempNode = head; //temporary node is set to the head
                        int counter=0; //value of new variable counter is set to 0

                        while (counter < (index-1)){ //runs up to 2 spots below the index specified by the user
                                tempNode = tempNode.getNext(); //runs through the nodes in the linked list
                                counter++; //counter increases by 1 for each iteration of the while loop
                        }

                        Node tempNode101 = new Node (new Song (nameSong, artist, year, nmOfAlbum, numStars), tempNode.getNext()); //new node
                        //containing all of the inputs given by the user
                        tempNode.setNext(tempNode101); //sets the node to add at the spot 1 after the current node the list is on

                        if (sizeOfList == 0){ //if the size of the list is 0 (no songs added)
                                current = head.getNext(); //current song (position) will be the spot after the head
                                pos = 1; //value of position variable is set to 1
                        }

                        sizeOfList++; //once song is successfully added, increases the size of the list by 1
                }
        }


        //delete a song from this play list with this index. Return false if song not found, return true if successful
        public boolean deleteSong (int s) //method that deletes a song given its index
        {
                if (s <= 0 || s > sizeOfList) //if the specified index is bigger than the size of the list or smaller than 0, then exit method
                        return false;//exits method
                else{ //if the above case if false
                        Node tempNode = head; //sets the temporary node to the head
                        int index = 0; //declares a new variable index and sets initial value to 0

                        while (index < (s - 1)){ //runs through the nodes in the list and stops at the spot
                                //2 spots before the specified index
                                index++;//increases the index by 1 for each iteration of the while loop
                                tempNode = tempNode.getNext();//goes through all of the nodes in the list using the 'getNext()' method
                        }

                        tempNode.setNext(tempNode.getNext().getNext());//sets the reference of the current temporary node to the node 2 spots ahead
                        //so as to remove any references to the next node (effectively removing/deleting it)

                        sizeOfList--;//reduces the size of the list by 1 after successful deletion of a song

                        if (sizeOfList == 0) //if the size of the play list is 0
                                current = null;//make the current song null
                        else if (pos == s)//if value of position is equal to the index specified by the user
                                current = tempNode.getNext();//sets the current node to the node after the temporary node

                        return true;//indicates successful completion of the method
                }
        }


        //delete a song from this play list with this song name. Return false if song not found, return true if successful
        public boolean deleteSong (String name) //method that deletes a song given its name
        {
                Node prevtempNode = head;//new node that states that the previous node is the head initially
                Node tempNode = head.getNext();//temporary node is the node after the head

                while (tempNode!=null){//if temp node is not null (not the tail) then...
                        if (tempNode.getSong().getTitle().equalsIgnoreCase(name)){ //if name of the current temporary node's song and the user input's song name are the same then...
                                prevtempNode.setNext(tempNode.getNext());//sets the reference of the previous node to the node after the current temporary node, removing (deleting)
                                //the middle node
                                sizeOfList--;//if successfully deleted, reduce the size of the list by 1
                                return true;//indicates successful completion
                        }
                        else{ //if the name of the current song is not the same as the user input
                                prevtempNode = tempNode;//make the previous node this node..
                                tempNode = tempNode.getNext();//..and this node the next node in the list
                        }
                }

                return false;//indicates that no songs are in the list
        }

        public Song getSong (String name){ //gets the song from the play list given its name
                Node tempNode = head.getNext();//sets the temporary node to the node after the head 

                while (tempNode!=null){ //as long as the temporary node is not null
                        if (tempNode.getSong().getTitle().equalsIgnoreCase(name)){ //if the current node's song's name is the same as the user input then...
                                return tempNode.getSong(); //returns the song from the current node
                        }
                        tempNode = tempNode.getNext();//gets the next node in the list and sets that as the current temporary list
                }
                return null; //if nothing works, return null
        }
        public Song getSong (int s){ // gets the song from the play list given its index
                if (s <= 0 || s > sizeOfList) //if the specified index is bigger than the size of the list or smaller than 0, then exit method
                        return null;//exits method
                else{
                        Node tempNode = head;//sets the temporary node to the head
                        int index = 0;//declares a new variable index and sets initial value to 0

                        while (index < (s)){//while loop runs through nodes until 1 node less than the index
                                index++;//increases value of index by 1 for each 'while' iteration
                                tempNode = tempNode.getNext();//gets the next node in the list
                        }

                        return tempNode.getSong();//returns the temporary node's song
                }
        }

        //Current Node is equal to the next node in the list
        public void nextNode () //gets the next node in the list
        {
                //Current Node is equal to the next node in the list
                if (current.getNext()==null){ //if the node after the current is null (tail) then...
                        current = head.getNext();//current is set to the node after the head
                        pos = 1;//variable value of position is 1
                }
                else{//if the above condition if false
                        current = current.getNext();//current is set to the node after the current node
                        pos++;//value of position increases by 1
                }
        }


        public Node currentNode () //returns the current node in the list
        {
                //return the current Node in the list here
                return current;
        }


        public Node getLastNode ()//returns the last node in the list
        {
                //return the last song in the list
                Node tempNode = head.getNext();//temporary node is set to the node after the head

                while (tempNode.getNext()!=null){//as long as the next node is not the tail
                        tempNode = tempNode.getNext();//gets the last node in the list before the tail
                }

                return tempNode;//returns this last node
        }



        //A method that finds the song  required using the song's title. Returns the position number
        public int findPos (String s)
        {
                Node tempNode = head.getNext();//sets the temporary node to the node after the head
                int index = 1;//sets the index value to 1

                while (tempNode!=null){//as long as the temporary node is not null (tail)...

                        if (tempNode.getSong().getTitle().equalsIgnoreCase(s)){//compares the node's song's name to the 
                                //input name and if the result is true
                                return index;//returns the index
                        }
                        else{//if node's song's name is not the input name
                                tempNode = tempNode.getNext();//gets the next node (goes through list like this)
                                index++;//increases the index value by 1
                        }
                }

                return index; //return this index value
        }


        //Returns a reference to the head of the play list
        public Node getListHead ()
        {
                return head;
        }


        //A method that prints out the names of all songs in the list.
        //Print out song name, Artist
        public Song[] listSongs ()
        {
                Song songList[]= new Song [sizeOfList];//new array of type song
                Node tempNode = head.getNext();//sets the temporary node to the node after the head
                if(sizeOfList!=0){//if the play list has songs in it (is not of size 0)
                        for(int i=0;i<sizeOfList;i++){
                                songList[i]=tempNode.getSong();//stores the names of the songs in the play list in the array
                                tempNode=tempNode.getNext();//goes through the nodes like this
                        }
                }
                return songList; //returns the array of songs
        }


        // Get the size of a given playlist
        public int getListSize ()
        {
                return sizeOfList;
        }


        // Clear all the songs from a playlist
        public void clearList()
        {
                head.setNext(tail);//sets the reference of the next node from the head to the tail
                sizeOfList=0;//sets the size of the list to 0
        }


        //Return the number of times a song occurs in a given list - lists can have multiples of the same song
        public int numTimes (String songName)
        {
                Node tempNode = head.getNext();//sets temporary node to the node after the head

                int numOfTimes = 0;//creates variable to track number of occurrences and sets intial value to 0

                while (tempNode.getNext()!=null){//as long as the tail is not reached (not equal to null)
                        if (tempNode.getSong().getTitle().equalsIgnoreCase(songName))//compares song names and if true
                                numOfTimes++;//increase the number of times by 1

                        tempNode = tempNode.getNext();//goes through nodes in play list like this
                }

                return numOfTimes;//returns this value
        }

        //return the highest rated song(s) in the list
        //remember, there can be songs that are tied
        public Song[] getHighestRated(){

                Node tempNode = head.getNext();//sets temporary node to the node after the head
                double highestRating = 0;//sets the highestRating to 0

                while (tempNode!=null){//as long as the tail is not reached (not equal to null)

                        if (highestRating < tempNode.getSong().getRating())//goes through ratings and sets the highestRating to the
                                //highest rating of any song in the list
                                highestRating = tempNode.getSong().getRating();

                        tempNode = tempNode.getNext();//goes through all the nodes like this
                }

                tempNode = head.getNext();//temporary node is the node after the head (resets position)
                int numOfSongs = 0;//new variable keeping track of the number of songs and sets value to 0

                while (tempNode!=null){//as long as the tail is not reached (not equal to null)

                        if (tempNode.getSong().getRating() >= (highestRating - 1))//sees the ratings of different songs and takes
                                //it into count if the song is up to 1 star less
                                numOfSongs++;//increase the value by 1

                        tempNode = tempNode.getNext();//goes through nodes like this
                }

                tempNode = head.getNext();//sets the temporary node to the node after the head
                Song songList[] = new Song [numOfSongs];//new array of size of the number of songs with the acceptable rating
                int songCounter = 0;//song counter variable with initial value of 0

                for (int i = 1; i <= sizeOfList; i++){//goes up to the size of the list

                        /*
                         * goes through the process of finding songs with the acceptable rating and storing them in an array
                         */
                        
                        if (tempNode.getSong().getRating() >= (highestRating - 1)){
                                songList[songCounter] = tempNode.getSong();
                                songCounter++;
                        }

                        tempNode = tempNode.getNext();
                }


                return songList; //returns the array with highest rated songs
        }



        //get the head of the list
        public Node getHead ()
        {
                return head;
        }


        //Set the tail of the list with given user input
        public void setTail (Node n)
        {
                tail = n;
        }


        //Return the name of this play list
        public String getName ()
        {
                return name;
        }
}
