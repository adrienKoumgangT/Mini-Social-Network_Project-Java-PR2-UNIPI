package test;


/**
 * Classe principale per fare i test sul progetto intero
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 */
public class Test {

	/**
	 * @param args argomento del main
	 */
	public static void main(String[] args) {
		
		// test della classe Post
		@SuppressWarnings("unused")
		TestPost tp = new TestPost();
		TestPost.testPostCostruttore();
		TestPost.testGetMentioned();
		
		// test dell'interfaccia SocialNetworkInterface
		TestSocialNetworkInterface.testGuessFollowers();
		TestSocialNetworkInterface.testInfluencers();
		TestSocialNetworkInterface.testGetMentionedUsers();
		TestSocialNetworkInterface.testWrittenBy();
		
		// test della classe SocialNetwork
		@SuppressWarnings("unused")
		TestSocialNetwork tsn = new TestSocialNetwork();
		TestSocialNetwork.testCostruttore();
		TestSocialNetwork.testAddLike();
		TestSocialNetwork.testAddPost();
		TestSocialNetwork.testAddUser();
		TestSocialNetwork.testRemoveAllPost();
		TestSocialNetwork.testRemovePost();
		TestSocialNetwork.testContaining();
		TestSocialNetwork.testWrittenBy();
		
		// test della classe SocialNetworkExtend
		@SuppressWarnings("unused")
		TestSocialNetworkExtend tsne = new TestSocialNetworkExtend();
		TestSocialNetworkExtend.testAddWord();
		TestSocialNetworkExtend.testAddWords();
		TestSocialNetworkExtend.testContaintBadWords();
		TestSocialNetworkExtend.testCostruttore();
		TestSocialNetworkExtend.testRemoveWord();
		TestSocialNetworkExtend.testRemoveWords();
		TestSocialNetworkExtend.testReportedPost();
		
	}

}
