package test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import my.all.exceptions.InvalidParameterException;
import my.app.SocialNetworkInterface;
import pubblication.Post;

/**
 * classe di test dei metodo statici dell'interfaccia SocialNetworkInterface
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 *
 */
public class TestSocialNetworkInterface {

	/**
	 * 
	 */
	public TestSocialNetworkInterface() {
		System.out.println("===> Batterie di test per l'interfaccia SocialNetworkInterface");
	}
	
	/**
	 * 
	 */
	public static void testGuessFollowers() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico guessFollowers(List<Post> ps) della classe SocialNetwork.\n\n");
		
		try {
			
			try {
				SocialNetworkInterface.guessFollowers(null);
			}catch(NullPointerException e) {
				sb.append("valore null passato in parametro al metodo statico guessFollowers(List<Post> ps) della classe SocialNetwork"
						+ " : SocialNetwork.guessFollowers(null) : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico guessFollowers(List<Post> ps) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * 
	 */
	public static void testInfluencers(){
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico influencers(Map<String, Set<String>> followers) della classe SocialNetwork.\n\n");
	
		try {
			
			try {
				SocialNetworkInterface.influencers(null);
			}catch(NullPointerException e) {
				sb.append("valore null passato in parametro al metodo statico influencers(Map<String, Set<String>> followers) della classe SocialNetwork"
						+ " : SocialNetwork.influencers(null) : " + e + "\n");
			}
			
			try {
				SocialNetworkInterface.influencers(new HashMap<String, Set<String>>());
			}catch(InvalidParameterException e) {
				sb.append("Insieme vuoto passato in parametro al metodo statico influencers(Map<String>, Set<String>> followers) della classe SocialNetwork"
						+ " : SocialNetwork.influencers(new HashMap<String, Set<String>>()) : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico influencers(Map<String, Set<String>> followers) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * 
	 */
	public static void testGetMentionedUsers() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico getMentionedUsers(List<Post> ps) della classe SocialNetwork.\n\n");
	
		try {
			
			try {
				SocialNetworkInterface.getMentionedUsers(null);
			}catch(NullPointerException e) {
				sb.append("Valore null passato come parametro al metodo getMentionedUsers(List<Post> ps) della classe SocialNetwork : "
						+ " : getMentionedUsers(null) : " + e + "\n");
			}
			
			try {
				SocialNetworkInterface.getMentionedUsers(new Vector<Post>());
			}catch(InvalidParameterException e) {
				sb.append("Parametro vuoto, non contenente elementi passato in parametro al metodo getMentionedUsers(List<Post> ps) della classe SocialNetwork"
						+ " : getMentionedUsers(new Vector<Post>()) : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico getMentionedUsers(List<Post> ps) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}
	
	/**
	 * 
	 */
	public static void testWrittenBy() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("-------> test del metodo statico writtenBy(List<Post> ps, String username) della classe SocialNetwork.\n\n");
	
		try {
			
			List<Post> lp = new Vector<Post>();
			lp.add(new Post("adrien", "Pubblicazione di adrien"));
			
			try {
				SocialNetworkInterface.writtenBy(null, "adrien");
			}catch(NullPointerException e) {
				sb.append("Valore null passato come primo parametro al metodo writtenBy(List<Post> ps, String username) : writtenBy(null, \"adrien\") : " + e + "\n");
			}
			
			try {
				SocialNetworkInterface.writtenBy(new Vector<Post>(), null);
			}catch(NullPointerException e) {
				sb.append("Valore null passato come secondo parametro al metodo writtenBy(List<Post> ps, String username) : writtenBy(new Vector<Post>(), null) : " + e + "\n");
			}
			
			try {
				SocialNetworkInterface.writtenBy(lp ,"");
			}catch(InvalidParameterException e) {
				sb.append("Stringa vuota passata come secondo parametro al metodo writtenBy(List<Post> ps, String username) : writtenBy((new Vector<Post>()).add(post), \"\") : "+ e +"\n");
				}
			
			try {
				SocialNetworkInterface.writtenBy(lp, "    ");
			}catch(InvalidParameterException e) {
				sb.append("Stringa contente solo spazzi passata come parametro al metodo writtenBy(List<Post> ps, String username) : writtenBy((new Vectro<Post>().add(post), \"    \") : " + e + "\n");
			}
			
		}catch(Exception e) {
			sb.append("Errore non ripertoriato : " + e + "\n");
		}
		
		sb.append("\n<------- fine del test del metodo statico writtenBy(List<Post>, String username) della classe SocialNetwork.\n\n");
		
		System.out.println(sb);
	}

}
