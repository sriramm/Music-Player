public class Song //object class that controls the song objects found in a particular play list, since these
//song objects are pointed to by the node
{
        //Stores the value for the information on the song.
        private String title; //private String variable that stores the title of the song
        private String artist; //private String variable that stores the artist of the song
        private String year; //private String variable that stores that year of the song
        private String album; //private String variable that stores the name of the album the song belongs to
        private double rating; //private double variable that stores the rating of the song
        
        /*
         * The constructor method of the song class that takes inputs of song name, artist, year, name of album,
         * and rating and sets the private variables of this class to the inputs of the user
         */
        public Song (String name, String art, String yr, String nmOfAlbum, double rating)
        {
        	/*
        	 * following lines equal variables from this class to the inputs from the user
        	 */
                title = name;
                artist = art;
                year = yr;
                album = nmOfAlbum;
                this.rating = rating; //'this.rating' refers to the 'rating' variable found in THIS class

        }


        //Returns the songs title
        public String getTitle () //method with return type 'String' that gets the title of the current song
        {
                return title; //returns variable title that contains the String title
        }


        //Returns the songs artist
        public String getArtist () //method with return type 'String' that gets the author of the current song 
        {
                return artist; //returns variable author that contains the String author
        }

        public String getAlbum (){ //method with return type 'String' that gets the album of the current song
                return album; //returns variable album that contains the String album
        }

        //Returns the songs year
        public String getYear () //method with return type 'String' that gets the year of the current song
        {
                return year; //returns variable year that contains the String year
        }


        public double getRating () //method with return type 'String' that gets the rating of the current song
        {

                return rating; //returns variable rating that contains the String rating
        }

        public void setTitle (String title){ //method that sets the title of the song
                this.title = title; //sets the string title found in this class to the one input by the user
        }

        public void setArtist (String artist){ //method that sets the author of the song
                this.artist = artist; //sets the string artist found in this class to the one input by the user
        }

        public void setAlbum (String album){ //method that sets the album of the song
                this.album = album; //sets the string album found in this class to the one input by the user
        }

        public void setYear (String year){ //method that sets the year of the song
                this.year = year; //sets the string year found in this class to the one input by the user
        }

        public void setRating (double rating){ //method that sets the rating of the song
                this.rating = rating; //sets the double rating found in this class to the one input by the user
        }
}

