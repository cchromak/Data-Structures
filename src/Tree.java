
import java.util.*;

/*Chris Chromak
this code prints preorder, in order, and postoder
as well as indicates the height of the trees.
*/



public class Tree {
	private static class TNode {
		private int data;
		private TNode parent;
		private List<TNode> children;

		public TNode() {
			this(0, null);
		}

		public TNode(int e) {
			this(e, null);
		}

		public TNode(int e, TNode p) {
			data = e;
			parent = p;
			children = new ArrayList<TNode>();
		}

		public int getData() {
			return data;
		}

		public TNode getParent() {
			return parent;
		}

		public List<TNode> getChildren() {
			return children;
		}
	}

	private TNode root;
	private int size;

	Tree() {
		root = null;
		size = 0;
	}

	public TNode createNode(int e, TNode p) {
		return new TNode(e, p);
	}

	public TNode addChild(TNode n, int e) {
		TNode temp = createNode(e, n);
		n.children.add(temp);
		size++;
		return temp;
	}

	public TNode addRoot(int e) throws IllegalArgumentException {
		if (root != null)
			throw new IllegalArgumentException("Root is full");
		root = createNode(e, null);
		size++;
		return root;
	}

	public void levelOrder() {
		List<TNode> queue = new LinkedList<>();
		queue.add(root); 
		levelOrderPrint(queue);
		System.out.println();
	}

	private void levelOrderPrint(List<TNode> l) {
		if (l.isEmpty())
			return;
		while (!l.isEmpty()) {
			System.out.print(l.get(0).getData() + " ");
			for (TNode cn : l.get(0).getChildren()) {
				l.add(cn);

			}
			l.remove(0);
		}
	}

	public void preOrder() {
		preOrderPrint(root);
		System.out.println();
	}

	private void preOrderPrint(TNode n) {
		if (n == null) {
			return;
		}
		System.out.print(n.getData() + " ");
		for (TNode cn : n.getChildren()) {
			preOrderPrint(cn);
		}
	}

	public void postOrder() {
		postOrderPrint(root);
		System.out.println();
	}

	private void postOrderPrint(TNode n) {
		if (n == null) {
			return;
		}
		for (TNode cn : n.getChildren()) {
			postOrderPrint(cn);
		}
		System.out.print(n.getData() + " ");

	}

	public boolean isSubTree(TNode tn) {
		return true;
	}

	public void makeTree() {
		TNode rt = addRoot(0);
		buildTree(rt, 3);
	}

	public void makeTree2() {
		TNode rt = addRoot(0);
		buildTree(rt, 1);
	}

	public TNode getRoot() {
		return root;
	}

	public int height() {
		return height(root);
	}

	private int height(TNode tn) {
		if (tn == null) {
			return 0;
		}
		int max = 0;
		for (TNode cn : tn.getChildren()) {
			int h = 1 + height(cn);
			if (h > max) {
				return h;
			}
		}
		return max;
	}

	private void buildTree(TNode n, int i) {
		if (i <= 0)
			return;
		TNode fc = addChild(n, size);
		TNode sc = addChild(n, size);
		TNode tc = addChild(n, size);
		buildTree(fc, i - 1);
		buildTree(sc, i - 2);
		if (i % 2 == 0)
			buildTree(tc, i - 1);
	}

	public static void main(String[] args) {
		Tree t = new Tree();
		Tree t2 = new Tree();
		t.makeTree();
		t.levelOrder();
		t.preOrder();
		t.postOrder();
		TNode subT = t.getRoot().getChildren().get(1);
		t.isSubTree(subT);
		t2.makeTree2();
		t2.levelOrder();
		t2.preOrder();
		t2.postOrder();
		t2.isSubTree(subT);
		t.isSubTree(t2.getRoot());
		System.out.println("the height of t is:" + t.height());
		System.out.println("the height of t2 is:" + t2.height());
	}

}
