import java.util.Vector;


public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Playlist list[] = new Playlist[10];
		list[0] = new Playlist ("Ricky");
		list[0].addSong("Hahaha", "Sri", "1996", "HUGO!", 4.33);
		list[0].addSong("Haha", "Sri", "1996", "HUGO!", 4.33);
		list[0].addSong("Ha", "Sri", "1996", "HUGO!", 4.33);
		
		Vector list2 = new Vector();
		
		list2.add(new Playlist ("Hello"));
		
		Playlist so = (Playlist) list2.elementAt(0);
		
		System.out.print(list[0].getSong("ha"));
	}

}
