import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class Main{
    

    public static void viewTree(Digraphable myCollection){
        String s = "https://dreampuf.github.io/GraphvizOnline/#";
		
		String start = "digraph G{";
		
		String contents = "";
		

		contents = (myCollection.getDigraph());
		
		String tail = "}";
				
		String url = s + start + contents + tail;
		url = url.replace(" ", "%20");
		url = url.replace("{", "%7B");
		url = url.replace("}", "%7D");
		url = url.replace("<", "%3C");
		url = url.replace(">", "%3E");
		url = url.replace("\n", "%0D");
		url = url.replace("\"", "%22");
				
		
		Desktop desktop = Desktop.getDesktop();
		try {
            desktop.browse(URI.create(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        MyCollection<String> myCollection = new BinaryTree();

        // Simple left rotation
        myCollection.add("mike");
        myCollection.add("sierra");
        myCollection.add("zulu");

        // Simple right rotation
        // myCollection.add("mike");
        // myCollection.add("golf");
        // myCollection.add("alpha");

        // Level 2 left rotation
        // myCollection.add("mike");
        // myCollection.add("golf");
        // myCollection.add("sierra");
        // myCollection.add("romeo");
        // myCollection.add("xray");
        // myCollection.add("zulu");

        // Level 2 right rotation
        // myCollection.add("mike");
        // myCollection.add("zulu");
        // myCollection.add("golf");
        // myCollection.add("hotel");
        // myCollection.add("bravo");
        // myCollection.add("alpha");

        //Deep right rotation 1
        // myCollection.add("mike");
        // myCollection.add("golf");
        // myCollection.add("sierra");
        // myCollection.add("oscar");
        // myCollection.add("charlie");
        // myCollection.add("november");

        //Deep right rotation 2
        // myCollection.add("mike");
        // myCollection.add("golf");
        // myCollection.add("sierra");
        // myCollection.add("oscar");
        // myCollection.add("charlie");
        // myCollection.add("alpha");

        //Deep left rotation 1
        // myCollection.add("mike");
        // myCollection.add("golf");
        // myCollection.add("sierra");
        // myCollection.add("xray");
        // myCollection.add("charlie");
        // myCollection.add("zulu");

        //Deep left rotation 2
        // myCollection.add("mike");
        // myCollection.add("golf");
        // myCollection.add("sierra");
        // myCollection.add("xray");
        // myCollection.add("hotel");
        // myCollection.add("india");
        

        
    }
}
