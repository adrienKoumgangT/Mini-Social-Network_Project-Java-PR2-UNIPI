package test;

import my.all.exceptions.InvalidParameterException;
import my.all.exceptions.UserException;
import my.app.SocialNetwork;
import pubblication.Post;

/**
 * classe di test della classe SocialNetwork
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 *
 */
public class TestSocialNetwork {
	
	
	/**
	 * test basato sulla classe SocialNetwork
	 */
	public TestSocialNetwork() {
		System.out.println("===> Batterie di test per la classe SocialNetwork\n\n");
	}
	
	/**
	 * test del costruttore della classe SocialNetwork
	 */
	public static void testCostruttore() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test dell'unico costruttore della classe SocialNetwork.\n\n");
		
		try {
			@SuppressWarnings("unused")
			SocialNetwork sn = new SocialNetwork();
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
	}
	
	/**
	 * test del metodo addUser(String user) della classe SocialNetwork
	 */
	public static void testAddUser() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo addUser(String user) della classe SocialNetwork.\n\n");
		
		try {
			
			SocialNetwork sn = new SocialNetwork();
			
			try {
				sn.addUser(null);
			}catch(NullPointerException e) {
				sb.append("Valore null passato come parametro al metodo addUser(String user) : addUser(null) : " + e + "\n");
			}
			
			try {
				sn.addUser("");
			}catch(UserException e) {
				sb.append("Stringa vuota passata come parametro al metodo addUser(String user) : addUser(\"\") : " + e + "\n");
			}
			
			try {
				sn.addUser("  ");
			}catch(UserException e) {
				sb.append("Stringa contenente solo spazzi passata come parametro al metodo addUser(String user) : addUser(\"   \") :"+ e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo addUser(String user) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo addPost(Post p) della classe SocialNetwork
	 */
	public static void testAddPost() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo addPost(Post p) della classe SocialNetwork.\n\n");
		
		try {
			SocialNetwork sn = new SocialNetwork();
			sn.addUser("adrien");
			
			try {
				sn.addPost(null);
			}catch(NullPointerException e) {
				sb.append("Valore null passato come parametro al metodo addPost(Post p) : addPost(null) : "+ e +"\n");
			}
			
			try {
				sn.addPost(new Post("user", "Pubblicazione"));
			}catch(UserException e) {
				sb.append("Post con utente non registrato passato in parametro al metodo addPost(Post p) : addPost(new Post(\"user\", \"Pubblicazione\" : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo addPost(String user) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo removePost(Post p) della classe SocialNetwork
	 */
	public static void testRemovePost() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo removePost(Post p) della classe SocialNetwork.\n\n");
		
		try {
			try {
				
				SocialNetwork sn = new SocialNetwork();
				sn.addUser("adrien");
				Post p = new Post("adrien", "Post di adrien!");
				sn.addPost(p);
				
				sn.removePost(null);
			}catch(NullPointerException e) {
				sb.append("valore null passato come parametro al metodo removePost(Post p) della classe SocialNetwork : removePost(null) : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo addPost(String user) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo removeAllPost(Post p) della classe SocialNetwork
	 */
	public static void testRemoveAllPost() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo removeAllPost(Post p) della classe SocialNetwork.\n\n");
		
		try {
			
			try {
				SocialNetwork sn = new SocialNetwork();
				sn.addUser("adrien");
				Post p = new Post("adrien", "Post di adrien!");
				sn.addPost(p);
				
				sn.removeAllPost(null);
			}catch(NullPointerException e) {
				sb.append("valore null passato come parametro al metodo removeAllPost(Collection<Post>) della classe SocialNetwork : removeAllPost(null) : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo addPost(String user) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo addLike(Post p, String name) della classe SocialNetwork
	 */
	public static void testAddLike() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo addLike(Post p, String name) della classe SocialNetwork.\n\n");
		
		try {
			SocialNetwork sn = new SocialNetwork();
			sn.addUser("adrien");
			
			try {
				sn.addLike(null, "adrien");
			}catch(NullPointerException e) {
				sb.append("Valore null passato come parametro al metodo addLike(Post p, String name) : addPost(null) : " + e + "\n");
			}
			
			try {
				sn.addLike(new Post("adrien", "Pubblicazione"), null);
			}catch(NullPointerException e) {
				sb.append("Valore null passato come parametro al metodo addLike(Post p, String name) : addLike(new Post(\"adrien\", \"Pubblicazione\"), null) : " + e + "\n");
			}
			
			try {
				sn.addLike(new Post("adrien", "Pubblicazione"), "");
			}catch(InvalidParameterException e) {
				sb.append("Stringa vuota passata come parametro al metodo addLike(Post p, String name) : addLike(new Post(\"adrien\", \"Pubblicazione\"), \"\") : "+ e +"\n");
				}
			
			try {
				sn.addLike(new Post("adrien", "Pubblicazione"), "    ");
			}catch(InvalidParameterException e) {
				sb.append("Stringa vuota passata come parametro al metodo addLike(Post p, String name) : addLike(new Post(\"adrien\", \"Pubblicazione\"), \"    \") : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo addPost(String user) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo writtenBy(string username) della classe SocialNetwork
	 */
	public static void testWrittenBy() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo writtenBy(String username) della classe SocialNetwork.\n\n");
	
		try {
			
			SocialNetwork sn = new SocialNetwork();
			
			try {
				sn.writtenBy(null);
			}catch(NullPointerException e) {
				sb.append("Valore null passato come parametro al metodo writtenBy(String username) : writtenBy(null) : " + e + "\n");
			}
			
			try {
				sn.writtenBy("");
			}catch(InvalidParameterException e) {
				sb.append("Stringa vuota passata come parametro al metodo writtenBy(String username) : writtenBy(\"\") : "+ e +"\n");
				}
			
			try {
				sn.writtenBy("    ");
			}catch(InvalidParameterException e) {
				sb.append("Stringa vuota passata come parametro al metodo writtenBy(String username) : writtenBy(\"    \") : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo writtenBy(String username) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo containing(List<String words) della classe SocialNetwork
	 */
	public static void testContaining() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo containing(List<String> words) della classe SocialNetwork.\n\n");
	
		try {
			SocialNetwork sn = new SocialNetwork();
			
			try {
				sn.containing(null);
			}catch(NullPointerException e) {
				sb.append("Valore null passato in parametro al metodo containing(List<String> words) della classe SocialNetwork :"
						+ " containing(null) : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo containing(List<String> words) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
}
