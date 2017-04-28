package poly;

import java.io.*;
import java.util.StringTokenizer;

/**
 * This class implements a term of a polynomial.
 * 
 * @author runb-cs112
 *
 */
class Term {
	/**
	 * Coefficient of term.
	 */
	public float coeff;
	
	/**
	 * Degree of term.
	 */
	public int degree;
	
	/**
	 * Initializes an instance with given coefficient and degree.
	 * 
	 * @param coeff Coefficient
	 * @param degree Degree
	 */
	public Term(float coeff, int degree) {
		this.coeff = coeff;
		this.degree = degree;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		return other != null &&
		other instanceof Term &&
		coeff == ((Term)other).coeff &&
		degree == ((Term)other).degree;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (degree == 0) {
			return coeff + "";
		} else if (degree == 1) {
			return coeff + "x";
		} else {
			return coeff + "x^" + degree;
		}
	}
}

/**
 * This class implements a linked list node that contains a Term instance.
 * 
 * @author runb-cs112
 *
 */
class Node {
	
	/**
	 * Term instance. 
	 */
	Term term;
	
	/**
	 * Next node in linked list. 
	 */
	Node next;
	
	/**
	 * Initializes this node with a term with given coefficient and degree,
	 * pointing to the given next node.
	 * 
	 * @param coeff Coefficient of term
	 * @param degree Degree of term
	 * @param next Next node
	 */
	public Node(float coeff, int degree, Node next) {
		term = new Term(coeff, degree);
		this.next = next;
	}
}

/**
 * This class implements a polynomial.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Pointer to the front of the linked list that stores the polynomial. 
	 */ 
	Node poly;
	
	/** 
	 * Initializes this polynomial to empty, i.e. there are no terms.
	 *
	 */
	public Polynomial() {
		poly = null;
	}
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param br BufferedReader from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 */
	public Polynomial(BufferedReader br) throws IOException {
		String line;
		StringTokenizer tokenizer;
		float coeff;
		int degree;
		
		poly = null;
		
		while ((line = br.readLine()) != null) {
			tokenizer = new StringTokenizer(line);
			coeff = Float.parseFloat(tokenizer.nextToken());
			degree = Integer.parseInt(tokenizer.nextToken());
			poly = new Node(coeff, degree, poly);
		}
	}
	
	
	/**
	 * Returns the polynomial obtained by adding the given polynomial p
	 * to this polynomial - DOES NOT change this polynomial
	 * 
	 * @param p Polynomial to be added
	 * @return A new polynomial which is the sum of this polynomial and p.
	 */
	public Polynomial add(Polynomial p) {
		/** COMPLETE THIS METHOD **/
		Node ans= null;
		Polynomial test= new Polynomial();
		test.poly=new Node(0,0,null);
		Node tNode=test.poly;
		//test.poly=poly;
		if(p.poly==null&& poly==null){
			return null;
		}
		if(poly==null){
			return p;
		}else if(p.poly==null){
			return test;
		}
		Node ptr1=p.poly;
		Node ptr2=poly;
		while(poly!=null || p.poly!=null){
			if(p.poly==null&&poly!=null){
				//test.poly=new Node(poly.term.coeff, poly.term.degree, poly);
				tNode.term.coeff=poly.term.coeff;
				tNode.term.degree=poly.term.degree;
				tNode.term= poly.term;
				poly=poly.next;
				System.out.println(test);
				if(poly!=null){
					tNode.next= new Node(0, 0, null);
					tNode=tNode.next;
				}
				continue;
			}
			if(poly==null&&p.poly!=null){
				//test.poly=new Node(p.poly.term.coeff, p.poly.term.degree, poly);
				tNode.term.coeff=p.poly.term.coeff;
				tNode.term.degree=p.poly.term.degree;
				tNode.term= p.poly.term;
				p.poly=p.poly.next;
				System.out.println(test);
				if(p.poly!=null){
					tNode.next= new Node(0, 0, null);
					tNode=tNode.next;
				}
				continue;
			}
			if (p.poly.term.degree==poly.term.degree){
				if(poly.term.coeff+p.poly.term.coeff!=0.0){
					tNode.term.coeff=p.poly.term.coeff+poly.term.coeff;
					tNode.term.degree=p.poly.term.degree;
					p.poly=p.poly.next;
					poly=poly.next;
					if(p.poly!=null||poly!=null){
						tNode.next= new Node(0, 0, null);
						tNode=tNode.next;
					}
				}else{
					p.poly=p.poly.next;
					poly=poly.next;
				}
				continue;
			}else if(p.poly.term.degree<poly.term.degree){
				tNode.term.coeff=p.poly.term.coeff;
				tNode.term.degree=p.poly.term.degree;
				tNode.term= p.poly.term;
				p.poly=p.poly.next;
				if(p.poly!=null||poly!=null){
					tNode.next= new Node(0, 0, null);
					tNode=tNode.next;
				}
				continue;
			}else if(poly.term.degree<p.poly.term.degree){
				//tNode=new Node(poly.term.coeff, poly.term.degree, poly);
				tNode.term.coeff=poly.term.coeff;
				tNode.term.degree=poly.term.degree;
				tNode.term= poly.term;
				poly=poly.next;
				System.out.println(test);
				if(p.poly!=null||poly!=null){
					tNode.next= new Node(0, 0, null);
					tNode=tNode.next;
				}
				continue;
			}
		}
		return test;
	}
	
	/**
	 * Returns the polynomial obtained by multiplying the given polynomial p
	 * with this polynomial - DOES NOT change this polynomial
	 * 
	 * @param p Polynomial with which this polynomial is to be multiplied
	 * @return A new polynomial which is the product of this polynomial and p.
	 */
	public Polynomial multiply(Polynomial p) {
		/** COMPLETE THIS METHOD **/
		Polynomial ans= new Polynomial();
		Polynomial test= new Polynomial();
		//Polynomial ptr1= p;
		test.poly=new Node(0,0,null);
		Node tNode=test.poly;
		Node ptr1=p.poly;
		if(p==null||this==null){
			if(p==null)
				return p;
			if(this==null)
				return this;
		}
		while(poly!=null){
			test= new Polynomial();
			test.poly=new Node(0,0,null);
			tNode=test.poly;
			ptr1=p.poly;
			System.out.println("ptr1.poly "+ptr1);
			while(ptr1!=null){
				tNode.term.coeff=(poly.term.coeff*ptr1.term.coeff);
				tNode.term.degree=poly.term.degree+ptr1.term.degree;
				ptr1=ptr1.next;
				if(ptr1!=null){
					tNode.next= new Node(0, 0, null);
					tNode=tNode.next;
				}
			}
			ans=ans.add(test);
			poly=poly.next;
			ptr1=p.poly;
		}
		return ans;
	}
	
	/**
	 * Evaluates this polynomial at the given value of x
	 * 
	 * @param x Value at which this polynomial is to be evaluated
	 * @return Value of this polynomial at x
	 */
	public float evaluate(float x) {
		/** COMPLETE THIS METHOD **/
		Node ptr = poly;
		float ans=0;
		if(ptr==null){
			return ans;
		}
		while(ptr!=null){
			ans+=ptr.term.coeff*(Math.pow(x,ptr.term.degree));
			ptr=ptr.next;
		}
		return ans;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String retval;
		
		if (poly == null) {
			return "0";
		} else {
			retval = poly.term.toString();
			for (Node current = poly.next ;
			current != null ;
			current = current.next) {
				retval = current.term.toString() + " + " + retval;
			}
			return retval;
		}
	}
}
