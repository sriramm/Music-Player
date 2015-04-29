/*  
 * Alex and Sriram 
 * ICS4U0 - A 
 * May 07, 2014          
 * Sayed, A.          
 *                          
 * Link - List Program v1.0          
 * This program features a Play list and Songs system, which allows the user to create new Play list (infinite number of Play list can be created) at any index, delete an existing Play list, change the 
 * name of the Play list at any given time, create new songs, delete songs through the use of the Songs title or index, change the details of the songs at any given time, clear the Play list of all 
 * its songs. The program can display all the songs and highest rated songs in the any given Play list and the user can also scroll through the songs in the given Play list to have more information 
 * about the songs displayed (The program displays the current song and next song in the given Play list). The program has also been thoroughly tested to get rid of an sudden glitches and errors that 
 * can cause the program to malfunction. HAPPY USING! 
 * 
 * Bugs List:
 * No bugs found so far (all the bugs found have been fixed)
 */

// The "GUI" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;

public class GUI extends Applet implements ActionListener{
        // Place instance variables here

        //The width and height of the Applet Screen
        int width = 600;
        int height = 440;

        //Declares the Font variables
        Font smallerFont;
        Font smallFont;
        Font largeFont;

        //Declares the Color variable
        Color lightBlue; 

        //Declares variables to import images
        Image speaker,musicNote;
        //Coordinates of the imported Images
        int picWidth, picHeight,picWidth2, picHeight2, picWidth3, picHeight3;
        //The paths of the pictures imported
        private final String PICTURE_PATH = "speaker.png";
        private final String PICTURE_PATH2 = "music note.png";

        //Variables for the Main Screen (Play List screen)

        //Creates a vector to keep track of the Playlists
        Vector list = new Vector();

        //Creates a variable that keeps track of the current Playlist selected
        Playlist temp;

        //Creates a variable that keeps track of the selected song
        Song tempSong;

        //Creates a variable that keeps track of the number of total Playlists
        int numOfPlayList;
        //Creates a variable that keeps track of the current Playlist
        int playListIndex;
        //Keeps track of how times a specific song occurs in a given Playlist
        int numOfTimesSongOccurs;

        //Creates variables that keep track of which screens are shown on the Applet
        boolean mainScreen; //Main Screen (Playlist Screen)
        boolean songScreen; //Song screen
        //Keeps track of whether "OR" is displayed on the Song Screen
        boolean songScreenDelete;

        //Keeps track of whether buttons need to be removed in Song Screen
        boolean removeButtons;

        //Keeps track of whether all the Songs are displayed
        boolean songScreenList;
        //Keeps track of whether the Highest Rated Songs are displayed
        boolean songScreenHighestRated;
        //This boolean dictates whether the number of times the songs occurs in the given Playlist should be displayed
        boolean screenNumOfTimes; 


        //Keeps track of all the error messages displayed
        boolean ratingError; //triggers if letters are entered in the rating Text Field
        boolean indexError; //triggers if letters are entered in the index Text Field
        boolean indexError2; //triggers if letters are entered in the index Text Field (displayes at a different location)
        boolean songNotFoundError; //triggers if the Song is not found
    	
        //Displays the current Playlist
        Button playList; 
        //Changes the current Playlist to the previous Playlist in the list
        Button playListLeft;
        //Changes the current Playlist to the next Playlist in the list
        Button playListRight;

        //Button thats used to create a new Playlist
        Button createPlayList;
        //Button thats used to delete the current Playlist
        Button deletePlayList;
        //Button that changes the name of the current Playlist
        Button changePlayListName;

        //Takes in the name of the new Playlist
        TextField createPlayListField;
        //Takes in the new name of the current Playlist (Changing Playlist name)
        TextField changePlayListNameField;

        //Variables for the Songs Screen
        
        //Keeps tracks of the Y - Position of all the songs displayed
        int yVal;
        int tempYVal;

        //Keeps tracks of the Y - Position of the Highest Rated songs displayed
        int yVal2;
        int tempYVal2;

        //Goes to the next song in the Playlist
        Button nextSong;
        //Clears the current Playlist of any songs
        Button clearList;
        //Adds a new song to the Playlist
        Button addSong;
        //After all the information is entered in the Text Fields, this button is pressed to finalize the addition of the song
        Button finishAddingSong;
        //Deletes a song from the current Playlist
        Button deleteSong;
        //After all the information is entered in the Text Fields, this button is pressed to finalize the deletion of the song
        Button finishDeletingSong;
        //Changes details of a song in the current Playlist
        Button changeSongDetails;
        //Confirms the song that will gets its details changed
        Button confirmSong;
        //After all the information is entered in the Text Fields, this button is pressed to finalize the changing of the song details
        Button finishChangingSongDetails;
        //Gets the number of Time a Song occurs in a given playlist
        Button numOfTimeSongOccurs;
        //After all the information is entered in the Text Fields, this button is pressed to finalize getting the song
        Button finishNumOfTimeSongOccurs;

        //Changes the Y - Position of the current Song list displayed (All Songs displayed or the Highest Rated Songs displayed)
        Button pageUp;
        Button pageDown;

        //Displays all the Songs in the current Playlist
        Button songList;
        //Displays all the Highest Rated songs
        Button highestRatedSongList;

        //Goes backs to the Main Screen (Playlist Screen)
        Button back;

        //Text Fields related to the addition of a new song or changing the details of a current song in the Playlist
        TextField songTitle; //Song Title
        TextField songArtist; //Song Artist
        TextField songAlbum; //Song Album
        TextField songYear; //Song Year
        TextField songRating; //Song Rating
        TextField songIndex; //Song Index

        //Text Fields related to the deletion of a song or finding a song in the Playlist
        TextField deleteSongTitle;
        TextField deleteSongIndex;

        public void init ()
        {
                // Place the body of the initialization method here
                //Instantiates of all the variables
                
                //Gives the Path to the picture variables
                speaker = getToolkit ().getImage (PICTURE_PATH);
                musicNote = getToolkit ().getImage (PICTURE_PATH2);
                
                //Prepares the images to be loaded for this Applet
                prepareImage (speaker, this);
                prepareImage (musicNote, this);

                // Now, it can actually take some time to load the image, and
                // it could fail (image not found, etc).  The following checks for
                // all that.
                MediaTracker tracker = new MediaTracker (this);
                // Add the picture to the list of images to be tracked
                tracker.addImage (speaker, 0);
                tracker.addImage (musicNote, 0);
                // Wait until all the images are loaded.  This can throw an
                // InterruptedException although it's not likely, so we ignore
                // it if it occurs.
                try
                {
                        tracker.waitForAll ();

                }
                catch (InterruptedException e)
                {
                }
                // If there were any errors loading the image, then abort the
                // program with a message.
                if (tracker.isErrorAny ())
                {
                        System.out.println("Couldn't load " + PICTURE_PATH+" and/or "+PICTURE_PATH2);
                        return;
                }

                // Initialize the picture size
                picWidth = (speaker.getWidth (null))-30;
                picHeight = (speaker.getHeight (null))-30;
                picWidth2 = musicNote.getWidth (null);
                picHeight2 = musicNote.getHeight (null);

                //Sets the size of the Applet
                setSize(width, height);
                //Sets the Layout of the Applet to null
                setLayout(null);

                //Small Size font
                smallerFont = new Font ("Calibri",Font.PLAIN,15);
                //Medium size font
                smallFont = new Font ("Calibri",Font.PLAIN,20);
                //Large size font
                largeFont = new Font ("Calibri",Font.BOLD,30);

                //Creates a Royal Blue color
                lightBlue = new Color (100, 149, 237);

                //Variables for the Main Screen (Play List screen)
                //Created Play List

                //Adds a new Playlist called "Favorite"
                temp = new Playlist ("Favorite");
                //Creates a few new songs
                temp.addSong("Hahaha", "Sri", "1996", "HUGO!", 4.33);
                temp.addSong("Haha", "Sri", "1995", "HUGO!", 3.33);
                temp.addSong("Ha", "Sri", "1994", "HUGO!", 2.33);

                //Adds the created Songs to the Playlist called "Favorite"
                list.add(temp);
                list.add(new Playlist ("Hip-Hop"));
                list.add(new Playlist ("Heavy Metal"));

                //Changes the size of total Playlist to 2
                numOfPlayList = 2;
                //Current Playlsit thats selected is at Index 0
                playListIndex = 0;

                //Instantiates the variables for the Main Screen (Playlist Screen)
                
                //temp is set equal to the current Playlist thats selected
                temp = (Playlist) list.get(playListIndex);

                playList = new Button (temp.getName());
                playList.setBounds(190, 100, 220, 50);
                playList.addActionListener(this);

                playListLeft = new Button ("<<");
                playListLeft.setBounds(130, 100, 50, 50);
                playListLeft.addActionListener(this);

                playListRight = new Button (">>");
                playListRight.setBounds(420, 100, 50, 50);
                playListRight.addActionListener(this);

                deletePlayList = new Button ("Delete Play List");
                deletePlayList.setBounds(190, 160, 220, 50);
                deletePlayList.addActionListener(this);

                createPlayList = new Button ("Create Play List");
                createPlayList.setBounds(190, 220, 220, 50);
                createPlayList.addActionListener(this);

                changePlayListName = new Button ("Change Play List Name");
                changePlayListName.setBounds(190, 280, 220, 50);
                changePlayListName.addActionListener(this);

                createPlayListField = new TextField("101");
                createPlayListField.setBounds(190, 115, 220, 20);
                createPlayListField.addActionListener(this);

                changePlayListNameField = new TextField("101");
                changePlayListNameField.setBounds(190, 115, 220, 20);
                changePlayListNameField.addActionListener(this);

                //Instantiates the variables for the Songs Screen
                yVal = 120;
                yVal2 = 120;

                pageUp = new Button("Page Up");
                pageUp.setBounds(width-85, height-90, 75, 25);
                pageUp.addActionListener(this);

                pageDown = new Button("Page Down");
                pageDown.setBounds(width-85, height-35, 75, 25);
                pageDown.addActionListener(this);

                songList = new Button("Songs List");
                songList.setBounds(width-410, height-90, 150, 25);
                songList.addActionListener(this);

                highestRatedSongList = new Button("Highest Rated Songs");
                highestRatedSongList.setBounds(width-250, height-90, 150, 25);
                highestRatedSongList.addActionListener(this);

                back = new Button("Go Back to Playlist Screen");
                back.setBounds(width-410, height-35, 310, 25);
                back.addActionListener(this);

                nextSong = new Button(">>");
                nextSong.setBounds(width-60, 10, 50, 25);
                nextSong.addActionListener(this);

                clearList = new Button("Clear List");
                clearList.setBounds(95, height-35, 75, 25);
                clearList.addActionListener(this);

                addSong = new Button("Add Song");
                addSong.setBounds(10, height-90, 75, 25);
                addSong.addActionListener(this);

                finishAddingSong = new Button("Add!");
                finishAddingSong.setBounds(10, height-125, 150, 25);
                finishAddingSong.addActionListener(this);

                deleteSong = new Button("Delete Song");
                deleteSong.setBounds(10, height-35, 75, 25);
                deleteSong.addActionListener(this);

                finishDeletingSong = new Button("Delete!");
                finishDeletingSong.setBounds(10, height-125, 150, 25);
                finishDeletingSong.addActionListener(this);

                changeSongDetails = new Button("Change Song");
                changeSongDetails.setBounds(95, height-90, 75, 25);
                changeSongDetails.addActionListener(this);

                confirmSong = new Button("Get Song");
                confirmSong.setBounds(10, height-125, 150, 25);
                confirmSong.addActionListener(this);

                finishChangingSongDetails = new Button("Change!");
                finishChangingSongDetails.setBounds(10, height-125, 150, 25);
                finishChangingSongDetails.addActionListener(this);
                
                numOfTimeSongOccurs = new Button ("# Of Times Song Occurs");
                numOfTimeSongOccurs.setBounds(10, height-62, 160, 25);
                numOfTimeSongOccurs.addActionListener(this);
                
                finishNumOfTimeSongOccurs = new Button ("Get Song!");
                finishNumOfTimeSongOccurs.setBounds(10, height-125, 150, 25);
                finishNumOfTimeSongOccurs.addActionListener(this);

                songTitle = new TextField("");
                songTitle.setBounds(10, 100, 150, 20);
                songTitle.addActionListener(this);

                songArtist = new TextField("");
                songArtist.setBounds(10, 130, 150, 20);
                songArtist.addActionListener(this);

                songAlbum = new TextField("");
                songAlbum.setBounds(10, 160, 150, 20);
                songAlbum.addActionListener(this);

                songYear = new TextField("");
                songYear.setBounds(10, 190, 150, 20);
                songYear.addActionListener(this);

                songRating = new TextField("");
                songRating.setBounds(10, 220, 150, 20);
                songRating.addActionListener(this);

                songIndex = new TextField("");
                songIndex.setBounds(10, 260, 150, 20);
                songIndex.addActionListener(this);

                deleteSongTitle = new TextField("");
                deleteSongTitle.setBounds(10, 100, 150, 20);
                deleteSongTitle.addActionListener(this);

                deleteSongIndex = new TextField("");
                deleteSongIndex.setBounds(10, 170, 150, 20);
                deleteSongIndex.addActionListener(this);

                //Calls the mainScreen method
                mainScreen();

        } // init method

        public void mainScreen(){
                //Sets the screens, buttons and text fields according to the Main Screen (Playlist Screen)
                
                //Sets the mainScreen to appear and the other screen to false (not appear)
                mainScreen = true;
                songScreen = false;
                songScreenDelete = false;

                //Removes some of the buttons from the Song Screen
                remove(pageUp);
                remove(pageDown);
                remove(songList);
                remove(highestRatedSongList);
                remove(back);
                remove(nextSong);
                remove(clearList);
                remove(addSong);
                remove(deleteSong);
                remove(changeSongDetails);
                remove(numOfTimeSongOccurs);

                //Removes some of the buttons from the Song Screen
                removeSubButtons();

                //Adds the buttons and text fields related the Main Screen (Playlist Screen)
                add(playList);
                add(playListLeft);
                add(playListRight);
                add(deletePlayList);
                add(createPlayList);
                add(changePlayListName);
        }

        public void songScreen (){
                //Sets the screens, buttons and text fields according to the Song Screen
                
                //Sets the songScreen to appear and the other screen to false (not appear)
                mainScreen = false;
                songScreen = true;
                songScreenList = true;

                //Removes the buttons related to the Main Screen (Playlist Screen)
                remove(playList);
                remove(playListLeft);
                remove(playListRight);
                remove(createPlayList);
                remove(deletePlayList);
                remove(changePlayListName);
                remove(createPlayListField);
                remove(changePlayListNameField);

                //Adds the buttons related to the Song Screen
                add(pageUp);
                add(pageDown);
                add(songList);
                add(highestRatedSongList);
                add(back);
                add(nextSong);
                add(clearList);
                add(addSong);
                add(deleteSong);
                add(changeSongDetails);
                add(numOfTimeSongOccurs);
        }

        public void removeSubButtons (){
                //Makes it so, "OR" is not displayed anymore
                songScreenDelete = false;

                //Removes some of buttons and text fields with the song screen
                remove(songTitle);
                remove(songArtist);
                remove(songYear);
                remove(songAlbum);
                remove(songRating);
                remove(songIndex);
                remove(finishAddingSong);

                remove(deleteSongTitle);
                remove(deleteSongIndex);
                remove(finishDeletingSong);

                remove(confirmSong);
                remove(finishChangingSongDetails);
                remove(finishNumOfTimeSongOccurs);
        }

        public void paint (Graphics g)
        {
                // Place the body of the drawing method here
                
                //Displays the things related to the Main Screen (Playlist Screen)
                if(mainScreen){
                        //Changes the background color to Cyan
                        setBackground(Color.cyan);
                        //Creates a new font
                        Font fancy = new Font("Agency FB",Font.ROMAN_BASELINE,75);
                        //Sets the font to the newly created font
                        g.setFont(fancy);       
                        //Draws Music Player on the Main Screen (Playlist Screen)
                        g.drawString("Music Player",148,65);
                        //Sets the color to red
                        g.setColor(Color.red);
                        g.drawString("__________________________________________________",0,73);
                        //Sets the color to black
                        g.setColor(Color.black);
                        //===============
                        /*
                         * insert images code here 2 speakers on left and 1 music note on right
                         */
                        g.drawImage(speaker, 5, 150,null);
                        g.drawImage(speaker,340,150,null);
                        g.drawImage(musicNote,-70,100,null);
                        g.drawImage(musicNote,400,100,null);
                        //===============
                }
                //Displays the things related to the Song Screen
                else if (songScreen){
                        //Changes the background color to Cyan
                        setBackground(Color.CYAN);
                        //Changes the font to smallFont
                        g.setFont(smallFont);

                        //Displays all the songs in the current Playlist
                        if (songScreenList){

                                //Creates a new array thats set equal to all the songs in the current Playlist
                                Song tempSongList[] = new Song[temp.listSongs().length];
                                tempSongList = temp.listSongs();

                                tempYVal = yVal;
                                //goes through the entire tempSongList
                                for (int i = 0; i < tempSongList.length; i++){
                                        g.drawString(i+1 + ": " + tempSongList[i].getTitle() + " by " + tempSongList[i].getArtist(), 200, tempYVal);
                                        tempYVal+=30;
                                }
                        }
                        //Displays all the Highest Rated songs in the current Playlist
                        else if (songScreenHighestRated){

                                //Creates a new array thats set equal to all the Highest Rated songs in the current Playlist
                                Song tempHighestRatedSongList[] = new Song[temp.getHighestRated().length];
                                tempHighestRatedSongList = temp.getHighestRated();

                                //Only runs if there is alist one song in the Highest Rated songs list
                                if (tempHighestRatedSongList != null){
                                        tempYVal2 = yVal2;
                                        //goes through the entire tempSongList
                                        for (int i = 0; i < tempHighestRatedSongList.length; i++){
                                                g.drawString(tempHighestRatedSongList[i].getTitle() + " by " + tempHighestRatedSongList[i].getArtist() + ", Rating: " + tempHighestRatedSongList[i].getRating(), 200, tempYVal2);
                                                tempYVal2+=30;
                                        }
                                }
                        }
                        
                        

                        //Changes the background color to blue
                        g.setColor(Color.BLUE);
                        //Creates two blue rectangles
                        g.fillRect(0, 0, width, 100);
                        g.fillRect(0, height-100, width, 100);
                        //Changes the color to lightBlue
                        g.setColor(lightBlue);
                        //Creates a rectangle
                        g.fillRect(0, 100, 180, height-200);

                        //Changes the font to smallerFont
                        g.setFont(smallerFont);
                        //Changes the color to red
                        g.setColor(Color.RED);
                        
                        //Displays the error messages according to the errors given
                        if (ratingError){
                                g.drawString("*Only Enter Numbers", 10, 255);    
                        }
                        if (indexError){
                                g.drawString("*Only Enter Numbers", 10, 295); 
                        }
                        if (indexError2){
                                g.drawString("*Only Enter Numbers", 10, 205); 
                        }
                        if (songNotFoundError){
                                g.drawString("*Song Not Found", 10, 215);   
                        }
                        
                        //Displays the number of time(s) a song occurs in a given Playlist
                        if (screenNumOfTimes){
                        	g.drawString("Number Of Times the", 10, 115);
                        	g.drawString("Song occurs is : "+numOfTimesSongOccurs + " time(s)", 10, 130);
                        }


                        //Only runs if there is more than one song in the current Playlist
                        if (temp.getListSize()!=0){
                                //Changes the font to largeFont
                                g.setFont(largeFont);
                                //Changes the color to red
                                g.setColor(Color.RED);
                                //Displays all the information of the current song selected
                                g.drawString("Current Song: " + temp.currentNode().getSong().getTitle() + " by " + temp.currentNode().getSong().getArtist(), 22, 30);
                                //Changes the font to smallerFont
                                g.setFont(smallerFont);
                                g.drawString(temp.currentNode().getSong().getAlbum() + ", " + temp.currentNode().getSong().getYear() + ", " + temp.currentNode().getSong().getRating(), 200, 45);

                                //Displays information on the next song in the list
                                if (temp.currentNode().getNext()==null){
                                        //Changes the font to largeFont
                                        g.setFont(largeFont);
                                        g.drawString("Next Song: " + temp.getListHead().getNext().getSong().getTitle() + " by " + temp.getListHead().getNext().getSong().getArtist(), 58, 75);
                                        g.setFont(smallerFont);
                                        g.drawString(temp.getListHead().getNext().getSong().getAlbum() + ", " + temp.getListHead().getNext().getSong().getYear() + ", " + temp.getListHead().getNext().getSong().getRating(), 200, 90);
                                }
                                else{
                                        //Changes the font to largeFont
                                        g.setFont(largeFont);
                                        g.drawString("Next Song: " + temp.currentNode().getNext().getSong().getTitle() + " by " + temp.currentNode().getNext().getSong().getArtist(), 58, 75);
                                        g.setFont(smallerFont);
                                        g.drawString(temp.currentNode().getNext().getSong().getAlbum() + ", " + temp.currentNode().getNext().getSong().getYear() + ", " + temp.currentNode().getNext().getSong().getRating(), 200, 90);
                                }
                        }
                        //Displays "N/A" if the size of the current Playlist is 0
                        else{
                                //Changes the font to largeFont
                                g.setFont(largeFont);
                                //Changes the color to red
                                g.setColor(Color.RED);
                                g.drawString("Current Song: N/A", 22, 30);
                                g.drawString("Next Song: N/A", 58, 75);
                        }
                        //Changes the font to smallFont
                        g.setFont(smallFont);
                        //Changes the color to red
                        g.setColor(Color.RED);
                        //Displays the current size of the Playlist (number of songs in the Playlist)
                        g.drawString("Size: ", width-60, 55);
                        g.drawString(String.valueOf(temp.getListSize()), width-60, 80);

                        //Displays "OR"
                        if (songScreenDelete)
                                g.drawString("OR", 75, 155);
                }
        } // paint method


        @Override
        public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
                //Sets the booleans accordingly
                removeButtons = true;
                ratingError = false;
                indexError = false;
                indexError2 = false;
                songNotFoundError = false;
                screenNumOfTimes = false;

                //If the playList button is pressed
                if (e.getSource()==playList){
                        temp = (Playlist) list.get(playListIndex);
                        //Calls the songScreen method
                        songScreen(); //Changes the screen to Song Screen
                }
                //If the playListLeft button is pressed
                if (e.getSource()==playListLeft && createPlayListField.getText().equals("101") && changePlayListNameField.getText().equals("101")){
                        //Changes the current Playlist displayed to the previous Playlist in the list
                        if (playListIndex == 0)
                                playListIndex = numOfPlayList;
                        else 
                                playListIndex--;

                        //Calls the changePlayList method
                        changePlayList();
                }
                //If the playListRight button is pressed
                else if (e.getSource()==playListRight && createPlayListField.getText().equals("101") && changePlayListNameField.getText().equals("101")){
                        //Changes the current Playlist displayed to the next Playlist in the list
                        if (playListIndex == numOfPlayList)
                                playListIndex = 0;
                        else 
                                playListIndex++;

                        //Calls the changePlayList method
                        changePlayList();
                }
                //If the delete Playlist buttons is pressed
                else if (e.getSource()==deletePlayList && createPlayListField.getText().equals("101") && changePlayListNameField.getText().equals("101")){
                        try {
                                list.remove(playListIndex);
                        } catch (Exception ArrayIndexOutOfBoundsException) {
                                // TODO Auto-generated catch block
                                System.out.println("No playlists to delete.");
                                numOfPlayList++;
                        }
                        numOfPlayList--;

                        //Calls the Playlist field
                        changePlayList();
                }
                //If the create Playlist buttons is pressed
                else if (e.getSource()==createPlayList){
                        //Sets the text on createPlayListField to "Playlist Name"
                        createPlayListField.setText("Playlist Name");
                        //Removes playList and changePlayListNameField buttons
                        remove(playList);
                        remove(changePlayListNameField);
                        //adds the creatPlayListField
                        add(createPlayListField);
                }
                //If the enter is pressed from the creatPlayListField
                else if (e.getSource()==createPlayListField && !createPlayListField.getText().equals("Playlist Name")){

                        //Adds a new Playlist object to list
                        //If the createPlayListField is empty, the new Playlist is called "Untitled"
                        if (createPlayListField.getText().equals(""))
                                list.add(playListIndex, new Playlist ("Untitled"));
                        else
                                list.add(playListIndex, new Playlist (createPlayListField.getText()));

                        //Changes the size of the number of total Playlists to one more
                        numOfPlayList++;
                        //Calls the changePlayList method
                        changePlayList();

                        //Removes createPlayListField Text Field
                        remove(createPlayListField);
                        //Adds playList buttons
                        add(playList);

                        //Sets the createPlayListField text to "101"
                        createPlayListField.setText("101");
                }
                //If the changePlaylistName buttons is pressed
                else if (e.getSource()==changePlayListName){
                        //Sets the changePlayListNameField text to "New PlaylistName"
                        changePlayListNameField.setText("New Playlist Name");
                        //Removes playList and createPlayListField
                        remove(playList);
                        remove(createPlayListField);
                        //Adds changePlayListNameField
                        add(changePlayListNameField);
                }
                //If entered is pressed from changePlayListNameField
                else if (e.getSource()==changePlayListNameField && !changePlayListNameField.getText().equals("New Playlist Name")){
                        //temp is set equal to current PlayList selected
                        temp = (Playlist) list.get(playListIndex);
                        //removes the current Playlist
                        list.remove(playListIndex);

                        //Adds temp Playlist object to list
                        //If the changePlayListNameField is empty, the Playlist is called "Untitled"
                        if (changePlayListNameField.getText().equals(""))
                                temp.setPlayListName("Untitled");
                        else
                                temp.setPlayListName(changePlayListNameField.getText());

                        //Adds the removed Playlist back
                        list.add(playListIndex, temp);

                        //Calls the changePlayList method
                        changePlayList();

                        //Removes the changePlayListNameField
                        remove(changePlayListNameField);
                        //Adds the playList button
                        add(playList);

                        //Changes the changePlayListNameField text to 101
                        changePlayListNameField.setText("101");
                }
                //If the nextSong button is Pressed
                else if (e.getSource()==nextSong && temp.getListSize()!=0){
                        //Goes to the next Node in the Linked Node List
                        temp.nextNode();
                }
                //If the pageUp button is pressed
                else if (e.getSource()==pageUp){
                        //Changes the value of yVal or yVal2 depending on which list is displayed
                        if (songScreenList)
                                yVal +=30;
                        else if (songScreenHighestRated)
                                yVal2 +=30;
                }
                //If the pageDown button is pressed
                else if (e.getSource()==pageDown){
                        //Changes the value of yVal or yVal2 depending on which list is displayed
                        if (songScreenList && yVal >120)
                                yVal -=30;
                        else if (songScreenHighestRated && yVal >120)
                                yVal2 -=30;
                }
                //If the songList button is pressed
                else if (e.getSource()==songList){
                        //Sets is so, the all the Songs List is displayed
                        songScreenList = true;
                        songScreenHighestRated = false;
                }
                //If the highestRatedSongList buttons is pressed
                else if (e.getSource()==highestRatedSongList){
                        //Sets is so, the Highest Rated Songs List is displayed
                        songScreenList = false;
                        songScreenHighestRated = true;
                }
                //If the back button is pressed
                else if (e.getSource()==back){ //Goes back to the Main Screen (Playlist Screen)
                        //Calls the mainScreen method
                        mainScreen();
                }
                //If the clearList button is pressed
                else if (e.getSource()==clearList){ //Clears the current Playlist of any songs
                        //Calls the clearList method
                        temp.clearList();
                }               
                //If the addSong button is pressed
                else if (e.getSource()==addSong){
                        //Sets the text fields accordingly
                        songTitle.setText("Title");
                        songArtist.setText("Artist");
                        songAlbum.setText("Album (Optional)");
                        songYear.setText("Year (Optional)");
                        songRating.setText("Rating (Optional)");
                        songIndex.setText("Index (Optional)");

                        //Calls the removeSubButtons method
                        removeSubButtons();

                        //Adds the buttons and text fields accordingly
                        add(songTitle);
                        add(songArtist);
                        add(songYear);
                        add(songAlbum);
                        add(songRating);
                        add(songIndex);
                        add(finishAddingSong);
                }
                //If the finishAddingSong button is pressed
                else if (e.getSource()==finishAddingSong && !songTitle.getText().equals("Title") || e.getSource()==finishAddingSong && !songArtist.getText().equals("Artist")){

                        //Creates arbitrary variables that keep track of the song album, year and rating
                        String album = "N/A";
                        String year = "N/A";
                        double rating = 0;

                        //If something else is entered in songAlbum text fields and its not empty
                        if (!songAlbum.getText().equals("Album (Optional)") && !songAlbum.getText().equals("")){
                                album = songAlbum.getText();
                        }
                        //If something else is entered in songYear text fields and its not empty
                        if (!songYear.getText().equals("Year (Optional)") && !songYear.getText().equals("")){
                                year = songYear.getText();
                        }
                        //If something else is entered in songRating text fields and its not empty
                        if (!songRating.getText().equals("Rating (Optional)") && !songRating.getText().equals("")){
                                try {
                                        //Gets the new song rating from the rating text field
                                        rating = Double.parseDouble(songRating.getText());
                                } catch (NumberFormatException e1) {
                                        // TODO Auto-generated catch block
                                        //Prints out the error
                                        System.out.println("Don't not enter leters, only numbers in Rating (Optional) TextField.");
                                        //The buttons and text fields stay and are not removed
                                        removeButtons = false;
                                        //The correct error is set to execute
                                        ratingError = true;
                                }
                        }

                        //If something else is not entered in songIndex text fields and its not empty and removeButtons is true
                        if (songIndex.getText().equals("Index (Optional)") && removeButtons || songIndex.getText().equals("") && removeButtons){
                                //adds the new song to the end of the list
                                temp.addSong(songTitle.getText(), songArtist.getText(), year, album, rating);
                        }                       
                        //If removeButtons is true
                        else if (removeButtons){
                                int index = 0;

                                try {
                                        //Gets the index of the new song from the songIndex text field
                                        index = Integer.parseInt(songIndex.getText());
                                } catch (NumberFormatException e1) {
                                        // TODO Auto-generated catch block
                                        //Prints out the error
                                        System.out.println("Don't not enter leters, only integers in Index (Optional) TextField.");
                                        //The buttons and text fields stay and are not removed
                                        removeButtons = false;
                                        //The correct error is set to execute
                                        indexError = true;
                                }
                                if (removeButtons)
                                        //adds the new song to the specified index
                                        temp.addSong(index, songTitle.getText(), songArtist.getText(), year, album, rating);
                        }

                        if (removeButtons){
                                //Calls the removeSubButtons method
                                removeSubButtons();
                        }
                }
                //If the deleteSong button is pressed
                else if (e.getSource()==deleteSong){
                        //Sets the text fields accordingly
                        deleteSongTitle.setText("Title");
                        deleteSongIndex.setText("Index");

                        //Calls the removeSubButtons method
                        removeSubButtons();

                        //Adds the buttons and text fields accordingly
                        add(deleteSongTitle);
                        add(deleteSongIndex);
                        add(finishDeletingSong);

                        //Sets the screen accordingly (displays "OR" on the Songs Screen)
                        songScreenDelete = true;
                }
                //If the finishDeletingSong button is pressed
                else if (e.getSource()==finishDeletingSong && !deleteSongTitle.getText().equals("Title") || e.getSource()==finishDeletingSong && !deleteSongIndex.getText().equals("Index")){

                        //Creates a new temporary variables that used to determine what the deleteSong method returns
                        boolean comparator;

                        //If the user has entered something in the deleteSongTitle
                        if (!deleteSongTitle.getText().equals("Title")){
                                comparator = temp.deleteSong(deleteSongTitle.getText());

                                //If the deleteSong method returns false (the song was not found in the current playList)
                                if (comparator == false){
                                        //The buttons and text fields stay and are not removed
                                        removeButtons = false;
                                        //The correct error is set to execute
                                        songNotFoundError = true;
                                }
                        }
                        else{
                                //Creates a arbitrary index
                                int index = -999999999;

                                try {
                                        //Gets the index of the new song from the songIndex text field
                                        index = Integer.parseInt(deleteSongIndex.getText());
                                } catch (NumberFormatException e1) {
                                        // TODO Auto-generated catch block
                                        //Prints out the error
                                        System.out.println("Do not enter leters, only integers in Index TextField.");
                                        removeButtons = false;
                                        //The correct error is set to execute
                                        indexError2 = true;
                                }
                                //If the index has not changes from "-999999999", that means that an error occured and the buttons and text fields will not be removed
                                if (index != -999999999){
                                        //If the deleteSong method returns false (the song was not found in the current playList)
                                        comparator = temp.deleteSong(index);
                                        if (comparator == false){
                                                //The buttons and text fields are not removed
                                                removeButtons = false;
                                                //The correct error is set to execute
                                                songNotFoundError = true;
                                        }
                                }

                        }

                        //If removeButtons is true
                        if(removeButtons){

                                //Calls the removeSubButtons method
                                removeSubButtons();
                        }
                }
                //If the changeSongDetails button is pressed
                else if (e.getSource()==changeSongDetails){
                        //Sets the text fields accordingly
                        deleteSongTitle.setText("Title");
                        deleteSongIndex.setText("Index");

                        //Calls the removeSubButtons method
                        removeSubButtons();

                        //Adds the buttons and text fields accordingly
                        add(deleteSongTitle);
                        add(deleteSongIndex);
                        add(confirmSong);

                        //Displays "OR"
                        songScreenDelete = true;
                }
                //If the confirmSong button is pressed
                else if (e.getSource()==confirmSong && !deleteSongTitle.getText().equals("Title") || e.getSource()==confirmSong && !deleteSongIndex.getText().equals("Index")){

                        //If something else is entered in deleteSongTitle text fields and its not empty
                        if (!deleteSongTitle.getText().equals("") && !deleteSongTitle.getText().equals("Title")){
                                //Tries getting the song specified by the user through the song title
                                tempSong = temp.getSong(deleteSongTitle.getText());

                                //If tempSong is equal to null (No song was found)
                                if (tempSong == null){
                                        //The buttons and text fields are not removed
                                        removeButtons = false;
                                        //The correct error is set to execute
                                        songNotFoundError = true;
                                }
                                //The buttons can be removed and the song if found
                                else
                                        removeButtons = true;
                        }

                        else{
                                try {
                                        //Tries getting the song specified by the user through the song index
                                        tempSong = temp.getSong(Integer.parseInt(deleteSongIndex.getText()));

                                        //If tempSong is equal to null (No song was found)
                                        if (tempSong == null){
                                                //The buttons and text fields are not removed
                                                removeButtons = false;
                                                //The correct error is set to execute
                                                songNotFoundError = true;
                                        }
                                        //The buttons can be removed and the song if found
                                        else
                                                removeButtons = true;
                                } catch (NumberFormatException e1) {
                                        // TODO Auto-generated catch block
                                        //Prints out the error
                                        System.out.println("Do not enter leters, only integers in Index TextField.");
                                        //The buttons and text fields are not removed
                                        removeButtons = false;
                                        //The correct error is set to execute
                                        indexError2 = true;
                                }
                        }



                        //If removeButtons is true
                        if (removeButtons){
                                //Sets the text fields accordingly
                                songTitle.setText(tempSong.getTitle());
                                songArtist.setText(tempSong.getArtist());
                                songAlbum.setText(tempSong.getAlbum());
                                songYear.setText(tempSong.getYear());
                                songRating.setText(String.valueOf(tempSong.getRating()));

                                //Calls the removeSubButtons method
                                removeSubButtons();

                                //Adds the buttons and text fields accordingly
                                add(songTitle);
                                add(songArtist);
                                add(songYear);
                                add(songAlbum);
                                add(songRating);
                                add(finishChangingSongDetails);
                        }
                }
                //If the finishChangingSongDetails button is pressed
                else if (e.getSource()==finishChangingSongDetails && !songTitle.getText().equals("") && !songArtist.getText().equals("")){

                        //Creates arbitrary variables that keep track of the song album, year and rating
                        String album = "N/A";
                        String year = "N/A";
                        double rating = 0;

                        //album is set equal to the text in songAlbum, if songAlbum is not empty
                        if (!songAlbum.getText().equals(""))
                                album = songAlbum.getText();
                        //album is set equal to the text in songYear, if songYear is not empty
                        if (!songYear.getText().equals(""))
                                year = songYear.getText();

                        try {
                                //Gets the new song rating from the rating text field
                                rating = Double.parseDouble(songRating.getText());
                        } catch (NumberFormatException e1) {
                                // TODO Auto-generated catch block
                                //Prints out the error
                                System.out.println("Don't not enter leters, only numbers in Rating (Optional) TextField.");
                                //The buttons and text fields are not removed
                                removeButtons = false;
                                //The correct error is set to execute
                                ratingError = true;
                        }

                        //If removeButtons is true
                        if (removeButtons){
                                //Sets the information of the song according to the text fields
                                tempSong.setTitle(songTitle.getText());
                                tempSong.setArtist(songArtist.getText());
                                tempSong.setAlbum(album);
                                tempSong.setYear(year);
                                tempSong.setRating(rating);

                                //sets tempSong to null, since the program is done using it
                                tempSong = null;

                                //Calls the removeSubButtons method
                                removeSubButtons();
                        }
                }
                //If the numOfTimeSongOccurs buttons is pressed
                else if (e.getSource()==numOfTimeSongOccurs){
                	//Sets the text fields according
                	songTitle.setText("Title");
                	
                	//Adds the buttons and text fields accordingly
                	add(songTitle);
                	add(finishNumOfTimeSongOccurs);
                }
                else if (e.getSource()==finishNumOfTimeSongOccurs && !songTitle.getText().equals("Title") && !songTitle.getText().equals("")){
                	//Gets and saves the number number of time(s) a song occurs in the given Playlist
                	numOfTimesSongOccurs = temp.numTimesSong(songTitle.getText());
                	//Displays the number of time(s) the song occurs in the given Playlist
                	screenNumOfTimes = true;
                	
                	//Calls the removeSubButtons method
                	removeSubButtons();
                }

                //Calls the paint method associated with Applet
                repaint();
        }

        //Changes the current Playlist displayed
        public void changePlayList(){
                try {
                        //Goes back to the first Playlist if true
                        if (playListIndex > numOfPlayList)
                                playListIndex = 0;

                        //temp is set equal to the current Playlist
                        temp = (Playlist) list.get(playListIndex);
                        //Changes the playList text to the name of the current Playlist
                        playList.setLabel(temp.getName());
                
                //Occurs if no Playlist are present
                } catch (Exception IndexOutOfBoundsExpection) {
                        // TODO Auto-generated catch block
                        //Changes the playList text to "No Playlists"
                        playList.setLabel("No Playlists");
                }
        }
} // GUI class
