*BY: John Gabriel Cabatu-an*

# Morser
A java program the allows you to encode or decode written Morse code.

## How to operate the program
there are two modes:

- Encode
- Decode

### Encode
 Lets you input words or letters and encode them into Morse code.
 
### Decode
 input Morse code to decode the letter they represent.
 
## How to use the package
you can also use the package ***com.morser.MorseTree*** to use the MorseTree class to make use of the Encode and Decode methods

### Example

```java
import com.morser.MorseTree;

MorseTree tree = new MorseTree();
String encodedString = tree.Encode("Letters");
String decodedString =tree.Decode("-.-");

system.out.println(encodedString);
system.out.println(decodedString);
```
