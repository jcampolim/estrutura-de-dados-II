//Integrantes - RA
//Enzo Guarnieri - 10410074
//Erika Borges Piaui - 10403716
//Júlia Compolim de Oste - 10408802

//Fontes de pesquisa:
//https://profkishimoto.github.io/edii04g11-2024-1/

public class Main {
	
	public static void main (String [] args) {
		
		 Node a = new Node();
	     Node b = new Node();
	     Node c = new Node();
	     Node d = new Node();
	     Node e = new Node();
	     Node f = new Node();
	       
	     BinaryTree t = new BinaryTree();
	     
	     t.setRoot(a);
	     
	     a.setLeft(b);
	     a.setRight(c);
	     a.setData("A");
	     
	     b.setParent(a);
	     b.setLeft(d);
	     b.setData("B");
	     
	     c.setParent(a);
	     c.setLeft(e);
	     c.setRight(f);
	     c.setData("C");
	     
	     d.setParent(b);
	     d.setData("D");
	     
	     e.setParent(c);
	     e.setData("E");
	     
	     f.setParent(c);
	     f.setData("F");
	     
	     System.out.print("************************************");
	     System.out.print("\nNode A: " );
	     System.out.print("\nIs leaf? " + a.isLeaf());
	     System.out.print("\nIs root? " + a.isRoot());
	     System.out.print("\nDegree: " + a.getDegree());
	     System.out.print("\nLevel: " + a.getLevel());
	     System.out.print("\nHeight: " + a.getHeight());
	     
	     System.out.print("\n\n************************************");
	     System.out.print("\nNode B: " );
	     System.out.print("\nIs leaf? " + b.isLeaf());
	     System.out.print("\nIs root? " + b.isRoot());
	     System.out.print("\nDegree: " + b.getDegree());
	     System.out.print("\nLevel: " + b.getLevel());
	     System.out.print("\nHeight: " + b.getHeight());
	     
	     System.out.print("\n\n************************************");
	     System.out.print("\nNode C: " );
	     System.out.print("\nIs leaf? " + c.isLeaf());
	     System.out.print("\nIs root? " + c.isRoot());
	     System.out.print("\nDegree: " + c.getDegree());
	     System.out.print("\nLevel: " + c.getLevel());
	     System.out.print("\nHeight: " + c.getHeight());
	     
	     System.out.print("\n\n************************************");
	     System.out.print("\nNode D: " );
	     System.out.print("\nIs leaf? " + d.isLeaf());
	     System.out.print("\nIs root? " + d.isRoot());
	     System.out.print("\nDegree: " + d.getDegree());
	     System.out.print("\nLevel: " + d.getLevel());
	     System.out.print("\nHeight: " + d.getHeight());
	     
	     System.out.print("\n\n************************************");
	     System.out.print("\nNode E: " );
	     System.out.print("\nIs leaf? " + e.isLeaf());
	     System.out.print("\nIs root? " + e.isRoot());
	     System.out.print("\nDegree: " + e.getDegree());
	     System.out.print("\nLevel: " + e.getLevel());
	     System.out.print("\nHeight: " + e.getHeight());
	     
	     System.out.print("\n\n************************************");
	     System.out.print("\nNode F: " );
	     System.out.print("\nIs leaf? " + f.isLeaf());
	     System.out.print("\nIs root? " + f.isRoot());
	     System.out.print("\nDegree: " + f.getDegree());
	     System.out.print("\nLevel: " + f.getLevel());
	     System.out.print("\nHeight: " + f.getHeight());
	     
	     System.out.print("\n\n************************************");
	     System.out.print("\nBinaryTree information: " );
	     System.out.print("\nIs empty? " + t.isEmpty());
	     System.out.print("\nDegree: " + t.getDegree());
	     System.out.print("\nHeight: " + t.getHeight());
	     System.out.print("\nPre order: ");
	     t.preOrderTraversal();

	     System.out.print("\nIn order: ");
	     t.inOrderTraversal();

	     System.out.print("\nPos order: ");
	     t.posOrderTraversal();

	     System.out.print("\nLevel order: ");
	     t.levelOrderTraversal();

	     System.out.println("\n");
	        
	}

}
