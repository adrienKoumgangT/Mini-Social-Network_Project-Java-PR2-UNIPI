package test;

import java.util.HashSet;

import my.app.SocialNetworkExtend;

/**
 * Classe di test della classe SocialNetworkExtend
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 *
 */
public class TestSocialNetworkExtend {

	/**
	 * test bassato sulla classe SocialNetworkExtend
	 */
	public TestSocialNetworkExtend() {
		System.out.println("===> Batterie di test per la classe SocialNetworkExtend\n\n");
	}
	
	/**
	 * test del costruttore della classe SocialNetworlExtend
	 */
	public static void testCostruttore() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test dell'unico costruttore della classe SocialNetworkExtend.\n\n");
		
		try {
			@SuppressWarnings("unused")
			SocialNetworkExtend sne = new SocialNetworkExtend();
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
	}
	
	/**
	 * test del metodo addWord(String word) della classe SocialNetworkExtend
	 */
	public static void testAddWord() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico addWord(String word) della classe SocialNetworkExtend.\n\n");
		
		try {
			sb.append("test di SocialNetworkExtend.addWord(null)\n");
				SocialNetworkExtend.addWord(null);
			sb.append("test di SocialNetworkExtend.addWord(String word) con parametro word stringa vuota \n");
				SocialNetworkExtend.addWord("");
			sb.append("test di SocialNetworkExtend.addWord(String word) con parametro word contente solo spazzi\n");
				SocialNetworkExtend.addWord("  ");
			
			sb.append("Test del metodo addWord(String word) finito con successo\n");
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico addWord(String word) della classe SocialNetworkExtend.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo addWords(Collection<String> words) della classe SocialNetworkExtend
	 */
	public static void testAddWords() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico addWords(Collection<String> words) della classe SocialNetworkExtend.\n\n");
		
		try {
			
			sb.append("test di SocialNetworkExtend.addWords(null)\n");
				SocialNetworkExtend.addWords(null);
			sb.append("test di SocialNetworkExtend.addWord(String word) con parametro word stringa vuota \n");
				SocialNetworkExtend.addWords(new HashSet<String>());
			
			sb.append("Test del metodo addWord(String word) finito con successo\n");
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico addWords(String word) della classe SocialNetworkExtend.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo removeWord(String word) della classe SocialNetworkExtend
	 */
	public static void testRemoveWord() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico removeWord(String word) della classe SocialNetworkExtend.\n\n");
		
		try {
			sb.append("test di SocialNetworkExtend.removeWord(null)\n");
				SocialNetworkExtend.removeWord(null);
			sb.append("test di SocialNetworkExtend.removeWord(String word) con parametro word stringa vuota \n");
				SocialNetworkExtend.removeWord("");
			sb.append("test di SocialNetworkExtend.removeWord(String word) con parametro word contente solo spazzi\n");
				SocialNetworkExtend.removeWord("  ");
			
			sb.append("Test del metodo removeWord(String word) finito con successo\n");
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico removeWord(String word) della classe SocialNetworkExtend.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo removeWords(Collection<String> words) della classe SocialNetworkExtend
	 */
	public static void testRemoveWords() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico removeWords(Collection<String> words) della classe SocialNetworkExtend.\n\n");
		
		try {
			
			sb.append("test di SocialNetworkExtend.removeWords(null)\n");
				SocialNetworkExtend.removeWords(null);
			sb.append("test di SocialNetworkExtend.removeWord(String word) con parametro word stringa vuota \n");
				SocialNetworkExtend.removeWords(new HashSet<String>());
			
			sb.append("Test del metodo removeWord(String word) finito con successo\n");
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico removeWords(String word) della classe SocialNetworkExtend.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo statico containtBadWords della classe SocialNetworkExtend
	 */
	public static void testContaintBadWords() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico containtBadWords(Post p) della classe SocialNetworkExtend.\n\n");
		
		try {
			try {
				SocialNetworkExtend.containtBadWords(null);
			}catch(NullPointerException e) {
				sb.append("valore null passato come parametro al metodo containtBadWords(Post p) :"
						+ " SocialNetworkExtend.containtBadWords(null) : " + e + "\n");
			}
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico containtBadWords(Post p) della classe SocialNetworkExtend.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * test del metodo reportedPost(Post p) della classe SocialNetworkExtend
	 */
	public static void testReportedPost() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo reportedPost(Post p) della classe SocialNetworkExtend.\n\n");
		
		try {
			SocialNetworkExtend sne = new SocialNetworkExtend();
			
			try {
				sne.reportedPost(null);
			}catch(NullPointerException e) {
				sb.append("valore null passato come parametro al metodo reportedPost(Post p) :"
						+ " reportedPost(null) : " + e + "\n");
			}
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo reportedPost(Post p) della classe SocialNetworkExtend.\n\n");
		
		System.out.println(sb);
	}

}
