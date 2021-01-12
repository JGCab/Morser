package com.morser;

public class MorseTree {

	// --- creating the binary tree for decyphering ---
	//the creation uses pre order traversal

	private String[] preordItems = 
	{
		null, "E", "I", "S", "H", "V",
		"U", "F", null, "A", "R", "L",
		null, "W", "P", "J", "T", "N",
		"D", "B", "X", "K", "C", "Y", 
		"M", "G", "Z", "Q", "O", null, null
	};
	
	// the generation of the tree is done procedurally
	// because the creator is too lazy to hardcode it.
	private int indx = 0;
	Node<String> root = new Node<String>();
	private void treeCreation(Node<String> curr, int level){
		// visit node
		curr.setData(preordItems[indx]);

		// visit left
		if (level < 4){
			Node<String> newNode = new Node<String>();
			curr.setLeft(newNode);
			indx++;
			treeCreation(newNode, level+1);
		}

		// vist right
		if (level < 4){
			Node<String> newNode = new Node<String>();
			curr.setRight(newNode);
			indx++;
			treeCreation(newNode, level+1);
		}
	}

	public Node<String> getRoot(){
		return root;
	}

	// Construct tree at the creation of object
	public MorseTree(){
		treeCreation(root, 0);
	}

	// Decryption ----
	private String DecryptSearch(Node<String> curr, char[] code, int binIndx){
		if(binIndx == code.length){
			return curr.getData();
		} else if (code[binIndx] == '.' && curr.getLeft()!= null){
			return DecryptSearch(curr.getLeft(), code, binIndx+1);
		} else if(code[binIndx] == '-'&& curr.getRight()!= null){
			return DecryptSearch(curr.getRight(), code, binIndx+1);
		} else{
			return null;
		}
	}

	public String Decode(String code){
		char[] splitCode = code.toCharArray();
		return DecryptSearch(this.getRoot(), splitCode, 0);
	}

	// Encryption ------
	private String EncryptSearch(Node<String> curr, String data, String symbol){
		if (curr.getData() != null && curr.getData().equalsIgnoreCase(data)){
			return symbol;
		}

		if (curr.getLeft() != null && EncryptSearch(curr.getLeft(), data, ".") != null){
			return  symbol + EncryptSearch(curr.getLeft(), data, ".");
		}

		if (curr.getRight() != null && EncryptSearch(curr.getRight(), data, "-") != null){
			return symbol + EncryptSearch(curr.getRight(), data, "-");
		}

		return null;
	}

	public String Encode(String data){
		String enMessage= "";
		data =  data.toUpperCase();
		String[] characs = data.split("");
		for (String a : characs) {
			if (EncryptSearch(this.root, a, "") != null){
				enMessage += EncryptSearch(this.root, a, "") + " ";
			} else {
				return null;
			}
			
		}
		return enMessage;
	}

}