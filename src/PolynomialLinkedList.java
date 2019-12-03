
/**
 * Chris Chromak
 */
public class PolynomialLinkedList {
	private static class PNode {
		private int coe;
		private int exp;
		private PNode next;

		public PNode(int c, int e) {
			this(c, e, null);
		}

		public PNode(int c, int e, PNode n) {
			coe = c;
			exp = e;
			next = n;
		}

		public void setCoe(int c) {
			coe = c;
		}

		public void setExp(int e) {
			exp = e;
		}

		public void setNext(PNode n) {
			next = n;
		}

		public int getCoe() {
			return coe;
		}

		public int getExp() {
			return exp;
		}

		public PNode getNext() {
			return next;
		}
	}

	private PNode head;

	public PolynomialLinkedList() {
		head = null;
	}

	private void add(int c, int e) {
		PNode tempn = new PNode(c, e, head);
		head = tempn;
	}

	public void removeDuplicate() {
		if (head == null)
			return;
		PNode lookUp, checkPrev;
		lookUp = checkPrev = head;
		while (lookUp.getNext() != null) {
			while (checkPrev.getNext() != null) {
				if (checkPrev.getNext().getExp() == lookUp.getExp()) {
					lookUp.setCoe(lookUp.getCoe() + checkPrev.getNext().getCoe());
					checkPrev.setNext(checkPrev.getNext().getNext());
				} else
					checkPrev = checkPrev.getNext();
			}
			lookUp = lookUp.getNext();
			checkPrev = lookUp;
		}
	}

	public PolynomialLinkedList add(PolynomialLinkedList pl) {
		PNode addTerm = this.head;
		PolynomialLinkedList ans = new PolynomialLinkedList();
		for (int i = 0; i < 2; i++) {
			while (addTerm != null) {
				ans.add(addTerm.getCoe(), addTerm.getExp());
				addTerm = addTerm.next;
			}
			addTerm = pl.head;
		}
		ans.removeDuplicate();
		return ans;
	}

	public PolynomialLinkedList multiply(PolynomialLinkedList pl) {
		PolynomialLinkedList ansr = new PolynomialLinkedList();
		PNode mTerm = this.head;
		PNode mTerm2 = pl.head;
		while (mTerm != null) {
			while (mTerm2 != null) {
				ansr.add(mTerm.getCoe() * mTerm2.getCoe(), mTerm.getExp() + mTerm2.getExp());
				mTerm2 = mTerm2.getNext();
			}
			mTerm = mTerm.getNext();
			mTerm2 = pl.head;
		}
		return ansr;
	}

	public void print() {
		if (head == null) {
			System.out.println();
			return;
		}
		removeDuplicate();
		PNode check = head;
		int i = 0;
		for (int n = 20; i < n; n--) {
			do {
				if (check.getExp() == n) {
					i = n;
					System.out.print("(" + check.coe + ")X^" + check.exp);
				}
				check = check.getNext();
			} while (check != null);
			check = head;
		}
		i--;
		for (; 0 < i; i--) {
			do {
				if (check.getExp() == i)
					System.out.print(" + (" + check.coe + ")X^" + check.exp);
				check = check.getNext();
			} while (check != null);
			check = head;
		}
		check = head;
		do {
			if (check.getExp() == 0)
				System.out.print(" + " + check.getCoe());
			check = check.getNext();
		} while (check != null);
		System.out.println();
	}

	public static void main(String argc[]) {
		PolynomialLinkedList pn1 = new PolynomialLinkedList();
		PolynomialLinkedList pn2 = new PolynomialLinkedList();
		pn1.add(1, 2);
		pn1.add(2, 3);
		pn2.add(1, 0);
		pn2.add(5, 1);
		pn2.add(-6, 1);
		pn2.add(4, 2);
		System.out.print("Polynomial 1: ");
		pn1.print();
		System.out.print("Polynomial 2: ");
		pn2.print();
		PolynomialLinkedList sum = pn1.add(pn2);
		PolynomialLinkedList prod = pn1.multiply(pn2);
		System.out.print("Sum: ");
		sum.print();
		System.out.print("Product: ");
		prod.print();
	}

}
