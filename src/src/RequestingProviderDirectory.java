package src; 

public class RequestingProviderDirectory {
	
  /**
   * gets directory from ProviderDirectory and sends as email.
   * @author Henry Schenck
   */
	
  public void fetchandemailDirectory() {
	
    ProviderDirectory x;
    x = new ProviderDirectory();
    String y = x.setAndGetDirectory();
    System.out.print("the directory is located at:" + y + "\n");
  }
}
