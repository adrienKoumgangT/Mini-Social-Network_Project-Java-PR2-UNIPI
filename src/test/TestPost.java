package test;


import java.util.Iterator;
import my.all.exceptions.PostException;
import pubblication.Post;

/**
 * test della classe Post
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 *
 */
public class TestPost {

	/**
	 * 
	 */
	public TestPost() {
		System.out.println("===> Batterie di test per la classe Post\n\n");
	}
	
	/**
	 * test del costruttore della classe Post
	 */
	@SuppressWarnings("unused")
	public static void testPostCostruttore() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test dell'unico costruttore della classe Post.\n\n");
		
		try {
			try {
				Post p = new Post(null, "testo");
			}catch(NullPointerException e) {
				sb.append("Valore null passato al primo parametro del costruttore della classe Post " +
									"--> Post p = new Post(null, \"testo\") : " + e + "\n");
			}
			
			try {
				Post p = new Post("", "testo");
			}catch(PostException e) {
				sb.append("Stringa vuota passata come primo parametro al costruttore della classe Post " +
									"--> Post p = new Post(\"\") : " + e + "\n");
			}
			
			try {
				Post p = new Post("   ", "testo");
			}catch(PostException e) {
				sb.append("Stringa contenente solo spazi vuoti passata come primo parametro al costruttore della classe Post " +
									"--> Post p = new User(\"  \", \"testo\") : " + e + "\n");
			}
			
			try {
				Post p = new Post("adrien", null);
			}catch(NullPointerException e) {
				sb.append("Valore null passato al secondo parametro del costruttore della classe Post " +
									"--> Post p = new Post(\"adrien\",null) : " + e + "\n");
			}
			
			try {
				Post p = new Post("adrien", "");
			}catch(PostException e) {
				sb.append("Stringa vuota passata come secondo parametro al costruttore della classe Post " +
									"--> Post p = new Post(\"adrien\", \"\") : " + e + "\n");
			}
			
			try {
				Post p = new Post("adrien", " ");
			}catch(PostException e) {
				sb.append("Stringa contenente solo spazi vuoti passata come secondo parametro al costruttore User " +
									"--> Post p = new Post(\"adrien\", \"  \") : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del costruttore ad un parametro della classe User.\n\n");
		
		System.out.println(sb);
	}
	
	
	/**
	 * test del metodo getMentioned() della classe Post
	 */
	public static void testGetMentioned(){
		try {
		
			StringBuffer sb = new StringBuffer();
		
			sb.append("-------> test del metodo getMentioned() della classe Post.\n\n");
		
			try {
				String author = "adrien";
				String text = "Allora, questo è il mio testo per fare il mio test. Venite a guardare @Giovanni @Federico @Maria";

				Post p = new Post(author, text);
				Iterator<String> it = p.getMentioned();
			
				while(it.hasNext()) {
					sb.append("Persona menzionata nel post : " + it.next() + "\n");
				}
			}catch(NullPointerException e) {
				sb.append("valore null passato al parametro : " + e + "\n");
			}
		
			sb.append("\n<------- fine del test del metodo getMentioned() della classe User.\n\n");
		
			System.out.println(sb);
		}catch(Exception e) {
			System.out.println("Questo è un bel problema : " + e + "\n");
		}
	}
}
