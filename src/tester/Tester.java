package tester;
import com.morser.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class Tester {
	@Test
	public void testNode(){
		Node<String> curr = new Node<String>("A");
		assertEquals("A", curr.getData());
		Node<String> node1 = new Node<String>("B");
		assertEquals("B", node1.getData());
		curr.setRight(node1);
		assertEquals("B", curr.getRight().getData());
		node1.setLeft(curr);
		assertEquals("A", node1.getLeft().getData());
		Node<String> node2 = new Node<String>("C");
		Node<Node<String>> container = new Node<Node<String>>(node2);
		assertEquals("C", container.getData().getData());
	}

	@Test
	public void testMorseTree(){
		MorseTree mt = new MorseTree();
		Node<String> root = mt.getRoot();
		Node<String> curr = root;
		assertEquals("E", curr.getLeft().getData());
		assertEquals("T", curr.getRight().getData());
		curr = curr.getLeft();
		assertEquals("I", curr.getLeft().getData());
		assertEquals("A", curr.getRight().getData());
		curr = curr.getLeft();
		assertEquals("S", curr.getLeft().getData());
		assertEquals("U", curr.getRight().getData());
		curr = curr.getLeft();
		assertEquals("H", curr.getLeft().getData());
		assertEquals("V", curr.getRight().getData());
		curr = root.getLeft().getLeft().getRight();
		assertEquals("F", curr.getLeft().getData());
		assertEquals(null, curr.getRight().getData());
		curr = root.getLeft().getRight();
		assertEquals("R", curr.getLeft().getData());
		assertEquals("W", curr.getRight().getData());
		curr = curr.getLeft();
		assertEquals("L", curr.getLeft().getData());
		assertEquals(null, curr.getRight().getData());
		curr = root.getLeft().getRight().getRight();
		assertEquals("P", curr.getLeft().getData());
		assertEquals("J", curr.getRight().getData());
		curr = root.getRight();
		assertEquals("N", curr.getLeft().getData());
		assertEquals("M", curr.getRight().getData());
		curr = curr.getRight();
		assertEquals("G", curr.getLeft().getData());
		assertEquals("O", curr.getRight().getData());
		curr = curr.getRight();
		assertEquals(null, curr.getLeft().getData());
		assertEquals(null, curr.getRight().getData());
		curr = root.getRight().getLeft();
		assertEquals("D", curr.getLeft().getData());
		assertEquals("K", curr.getRight().getData());
		curr = curr.getLeft();
		assertEquals("B", curr.getLeft().getData());
		assertEquals("X", curr.getRight().getData());
		curr = root.getRight().getLeft().getRight();
		assertEquals("C", curr.getLeft().getData());
		assertEquals("Y", curr.getRight().getData());
		curr = root.getRight().getRight().getLeft();
		assertEquals("Z", curr.getLeft().getData());
		assertEquals("Q", curr.getRight().getData());
	}

	@Test
	public void testDecoder(){
		MorseTree mt = new MorseTree();
		assertEquals("A", mt.Decode(".-"));
		assertEquals("B", mt.Decode("-..."));
		assertEquals("C", mt.Decode("-.-."));
		assertEquals("D", mt.Decode("-.."));
		assertEquals("E", mt.Decode("."));
		assertEquals("F", mt.Decode("..-."));
		assertEquals("G", mt.Decode("--."));
		assertEquals("H", mt.Decode("...."));
		assertEquals("I", mt.Decode(".."));
		assertEquals("L", mt.Decode(".-.."));
		assertEquals("M", mt.Decode("--"));
		assertEquals("N", mt.Decode("-."));
		assertEquals("O", mt.Decode("---"));
		assertEquals("P", mt.Decode(".--."));
		assertEquals("Q", mt.Decode("--.-"));
		assertEquals("R", mt.Decode(".-."));
		assertEquals("S", mt.Decode("..."));
		assertEquals("T", mt.Decode("-"));
		assertEquals("U", mt.Decode("..-"));
		assertEquals("V", mt.Decode("...-"));
		assertEquals("W", mt.Decode(".--"));
		assertEquals("X", mt.Decode("-..-"));
		assertEquals("Y", mt.Decode("-.--"));
		assertEquals("Z", mt.Decode("--.."));
		assertEquals("J", mt.Decode(".---"));
		assertEquals("K", mt.Decode("-.-"));
		assertEquals(null, mt.Decode("......"));
	}

	@Test
	public void testEncoder(){
		MorseTree mt = new MorseTree();
		assertEquals(".- ", mt.Encode("A"));
		assertEquals("-... ", mt.Encode("B"));
		assertEquals("-.-. ", mt.Encode("C"));
		assertEquals("-.. ", mt.Encode("D"));
		assertEquals(". ", mt.Encode("E"));
		assertEquals("..-. ", mt.Encode("F"));
		assertEquals("--. ", mt.Encode("G"));
		assertEquals(".... ", mt.Encode("H"));
		assertEquals(".. ", mt.Encode("I"));
		assertEquals(".--- ", mt.Encode("J"));
		assertEquals("-.- ", mt.Encode("K"));
		assertEquals(".-.. ", mt.Encode("L"));
		assertEquals("-- ", mt.Encode("M"));
		assertEquals("-. ", mt.Encode("N"));
		assertEquals("--- ", mt.Encode("O"));
		assertEquals(".--. ", mt.Encode("P"));
		assertEquals("--.- ", mt.Encode("Q"));
		assertEquals(".-. ", mt.Encode("R"));
		assertEquals("... ", mt.Encode("S"));
		assertEquals("- ", mt.Encode("T"));
		assertEquals("..- ", mt.Encode("U"));
		assertEquals("...- ", mt.Encode("V"));
		assertEquals(".-- ", mt.Encode("W"));
		assertEquals("-..- ", mt.Encode("X"));
		assertEquals("-.-- ", mt.Encode("Y"));
		assertEquals("--.. ", mt.Encode("Z"));
	}
}
