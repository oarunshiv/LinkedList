package linked.lists.merge;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import linked.lists.normal.LinkedList;
import linked.lists.normal.Node;
//import java.io.*;

public class MergeSortedLists{
	public static LinkedList merge(LinkedList L1,  LinkedList L2){
		if(L1.size == 0)
			return L2;
		else if(L2.size == 0)
			return L1;
		Node p = L1.Head, q = L2.Head;
		LinkedList result = new LinkedList();
		Node node;
		int pData = ((Integer) p.data).intValue(), qData = ((Integer) q.data).intValue();
		if( pData < qData){
			result.Head = p;
			p = p.next;
		}
		else{
			result.Head = q;
			q = q.next;
		}		
		node = result.Head;
		while( p != null && q != null){
			pData = ((Integer) p.data).intValue();
			qData = ((Integer) q.data).intValue();
			if( pData < qData){
				node.next = p;
				p = p.next;
			}
			else{
				node.next = q;
				q = q.next;
			}
			node = node.next;
		}
		if(p == null){
			node.next = q;
			q = q.next;
		}
		else if(q == null){
			node.next = p;
			p = p.next;
		}
		result.size = L1.size + L2.size;
		return result;
	}
	
	public static void main(String[] args){
		int choice = 0,val;
		LinkedList L1 = new LinkedList(), L2 = new LinkedList();
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(istream);
		try{
			while(choice != 6){
				System.out.println("Enter choice: \n1.Insert into L1\n2.Insert into L2\n3.View L1\n4.View L2\n5.Merge\n6.Exit");
									choice = Integer.parseInt(br.readLine());
				switch(choice){
					case 1: System.out.println("Enter the element to be inserted: ");
							val = Integer.parseInt(br.readLine());
							L1.insert(val);
							break;
					case 2: System.out.println("Enter the element to be inserted: ");
							val = Integer.parseInt(br.readLine());
							L2.insert(val);
							break;
					case 3: System.out.println(L1 + " : " + L1.size);
							break;
					case 4: System.out.println(L2 + " : " + L2.size);
							break;
					case 5: LinkedList result = MergeSortedLists.merge(L1,L2);
							System.out.println(result + " : " + result.size);
							break;
					case 6: return;
					default:System.out.println("Invalid choice!");
				}
			}
		}
		catch(IOException err){
			System.err.println("Error reading line");
		}
	}
}