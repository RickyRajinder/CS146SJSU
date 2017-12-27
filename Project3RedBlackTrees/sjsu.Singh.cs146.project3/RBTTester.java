
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;


public class RBTTester {


    /*
	@Test
	public void test() {
		RedBlackTree rbt = new RedBlackTree("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        assertEquals("DBACFEHGIJ", rbt.printValues());
        }
        /*
        String str=
"Color: 1, Key:D Parent: \n"+
"Color: 1, Key:B Parent: D\n"+
"Color: 1, Key:A Parent: B\n"+
"Color: 1, Key:C Parent: B\n"+
"Color: 1, Key:F Parent: D\n"+
"Color: 1, Key:E Parent: F\n"+
"Color: 0, Key:H Parent: F\n"+
"Color: 1, Key:G Parent: H\n"+
"Color: 1, Key:I Parent: H\n"+
"Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
*/


    public static String makeString(RedBlackTree t)
    {
       class MyVisitor implements RedBlackTree.Visitor {
          String result = "";
          public void visit(RedBlackTree.Node n)
          {
             result = result + n.key;
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    public static String makeStringDetails(RedBlackTree t) {
    	{
    	       class MyVisitor implements RedBlackTree.Visitor {
    	          String result = "";
    	          public void visit(RedBlackTree.Node n)
    	          {
    	        	  if(!(n.key).equals(""))
    	        		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
    	             
    	          }
    	       };
    	       MyVisitor v = new MyVisitor();
    	       t.preOrderVisit(v);
    	       return v.result;
    	 }
    }



    public static void main(String[] args) throws FileNotFoundException {
        RedBlackTree rbt = new RedBlackTree();
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        System.out.println(rbt.printValues());
        System.out.println(rbt.printColors());

        /*
	    File testFile = new File("");
        String currentPath = testFile.getAbsolutePath();
        System.out.println("current path is: " + currentPath);
	     File file = new File("dictionary.txt");
	    Scanner scan = new Scanner(file);
        RedBlackTree rbt1 = new RedBlackTree("");
        long before = System.currentTimeMillis();
        while (scan.hasNext()){
            String word = scan.next();
            rbt.insert(word);
        }
        long duration = (System.currentTimeMillis() - before)/1000;
        System.out.println("Duration: " + duration/60 + " min");
        System.out.println("done");
    */
    }
  // add this in your class  
  //  public static interface Visitor
  //  {
  //  	/**
  //     This method is called at each node.
  //     @param n the visited node
  //  	 */
  //  	void visit(Node n);
  //  }
 
  
  // public void preOrderVisit(Visitor v)
  //  {
  //  	preOrderVisit(root, v);
  //  }
 
 
  // private static void preOrderVisit(Node n, Visitor v)
  //  {
  //  	if (n == null) return;
  //  	v.visit(n);
  //  	preOrderVisit(n.left, v);
  //  	preOrderVisit(n.right, v);
  //  }
    
    
 }
  
