package linked.lists.normal;	
import java.io.*;
import java.util.HashMap;

public class LinkedList{
	public int size = 0;
	public Node Head = null;
	
	public void insert(Object value){
		Node node = new Node();
		node.data = value;
		node.next = Head;
		Head = node;
		size++;
	}
	
	public void sortedInsert(Object value){
		int val = ((Integer)value).intValue();
		Node node = new Node();
		node.data = val;
		node.next = null;
		if(Head == null)
			Head = node;
		else{
			Node p = Head,prev=null;
			int data = ((Integer)p.data).intValue();
			while(data < val){
				prev = p;
				p = p.next;
				if( p != null)
					data = ((Integer)p.data).intValue();
				else
					break;
			}
			node.next = p;
			if(prev == null)
				Head = node;
			else
				prev.next = node;
		}
		size++;
	}
	
	public void delete(Object val){
		if(Head == null){
			System.out.println("List is empty. Nothing to delete");
			return;
		}
		Node p = Head,prev = null;
		while( p.next != null && !p.data.equals(val)){
			prev = p;
			p = p.next;
		}
		if( p.next == null){
			if(!p.data.equals(val)){
				System.out.println("The list doesn't contain " + val);
				return;
			}
		}
		if( p == Head)
			Head = p.next;
		else
			prev.next = p.next;
		System.out.println("Deleted " + p.data.toString());
		size--;
	}
	
	public void delete(Node node){
		if(node == null){
			System.out.println("Node with given value doesn't exist");
			return;
		}
		Node nextNode = node.next;
		if(nextNode == null){
			System.out.println("Last element of the list cannot be deleted using this method");
			return;
		}
		node.data = nextNode.data;
		node.next = nextNode.next;
		size--;
	}
	
	public void removeDuplicatesFromSortedList(){
		if(Head == null || Head.next == null)
			return;
		Node p = Head.next,prev = Head;
		while( p != null){
			if(p.data.equals(prev.data)){
				prev.next = p.next;
				size--;
			}
			else
				prev = prev.next;
			p = p.next;
		}		
	}
	
	public void removeDuplicates(){
		HashMap<Object, Boolean> map = new HashMap<Object, Boolean> ();
		Node p = Head,prev=null;
		while(p != null){
			Integer data = (Integer)p.data;
			if(map.containsKey(data)){
				prev.next = p.next;
				size--;
			}
			else{
				map.put(p.data,true);
				prev = p;
			}
			p = p.next;
		}
	}
	
	public int getCount(int val){
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		Node p = Head;
		while( p != null){
			Integer value = (Integer) p.data;
			if(!map.containsKey(value)){
				map.put(value,new Integer(1));
			}
			else{
				Integer count = map.get(value);
				map.put(value, new Integer(count.intValue()+1));
			}
			p = p.next;
		}
		if(!map.containsKey(val))
			return 0;
		return (map.get(val)).intValue();
	}
	
	public Node getNode(int val){
		if(Head == null){
			return null;
		}
		Node p = Head;
		while( p.next != null && !p.data.equals(val)){
			p = p.next;
		}
		if( p.next == null){
			if(!p.data.equals(val)){
				System.out.println("The list doesn't contain " + val);
				return null;
			}
		}
		return p;
	}
	
	public void reverseList(){
		Node prev = null, current = Head, next;
		if(current == null)
			return;
		while(current != null){
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		Head  = prev;
	}
	
	public void pairwiseReverse(){
		Node prev = null, current = Head, next;
		if(current == null)
			return;
		while(current != null && current.next != null ){
			next = current.next.next;
			Object data = current.next.data;
			current.next.data = current.data;
			current.data = data;
			current = next;
		}
	}
	
	public void pairwiseReverse(Node head){
		Node current = head,prev = null,nextNode;
		if(current.next != null)
			Head = current.next;
		while(current != null && current.next !=null){
			nextNode = current.next.next;
			current.next.next  = current;
			if(prev != null)
				prev.next = current;
			current.next = nextNode;
			prev = current;
			current = nextNode;
		}
	}
	
	
	public void recursiveReverse(Node node,Node current){
		if(current == null)
			return;
		Head = current;
		recursiveReverse(current, current.next);
		current.next = node;
	}
	
	public void printReverse(Node current){
		if(current == null){
			System.out.print("[ ");
			if(Head == current)
				System.out.print(" ]");
			return;
		}
		printReverse(current.next);
		System.out.print(current.data);
		if(Head == current)
				System.out.print(" ]");
		else{
			System.out.print(" -> ");
		}
	}
	
	public String toString(){
		Node p = Head;
		String result = new String("[ ");
		if(Head != null){
			while( p.next != null){
				result += p.data.toString() +" -> ";
				p = p.next;
			}
			result += p.data.toString();
		}
		result += " ]";
		return result;
	}
	
	public boolean equals(Object arg){
		Node p = Head, q = (Node) arg;
		while( p != null && q != null){
			if(!p.data.equals(q.data)){
				return false;
			}
			p = p.next;
			q = q.next;
		}
		if( p != null || q != null)
			return false;
		else
			return true;
	}
	
	public static void main(String[] args){
		int choice = 0,val;
		LinkedList list = new LinkedList();
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(istream);
		try{
			while(choice != 8){
				System.out.println("Enter choice: \n1.Insert\n2.Delete\n3.Delete Node\n4.View List\n5.Count of a value\n6.Reverse the list\n7.Remove Duplicates\n8.Exit");
									choice = Integer.parseInt(br.readLine());
				switch(choice){
					case 1: System.out.println("Enter the element to be inserted: ");
							val = Integer.parseInt(br.readLine());
							list.insert(val);
							break;
					case 2: System.out.println("Enter the element to be deleted: ");
							val = Integer.parseInt(br.readLine());
							list.delete(val);
							break;
					case 3: System.out.println("Enter the element to be deleted: ");
							val = Integer.parseInt(br.readLine());
							list.delete(list.getNode(val));
							break;
					case 4: System.out.println("Print in reverse order(Y/N)?: ");
							char ch;
							ch = br.readLine().charAt(0);
							if(ch == 'Y' || ch == 'y'){
								list.printReverse(list.Head);
								System.out.println(" : " + list.size);
							}
							else
								System.out.println(list + " : " + list.size);
							break;
					case 5: System.out.println("Enter the element to be counted: ");
							val = Integer.parseInt(br.readLine());
							System.out.println(val +" occurs " + list.getCount(val) +" times");
							break;
					case 6: System.out.println("Reverse using \n1.Iterative method\n2.Recursion: ");
							val = Integer.parseInt(br.readLine());
							if(val == 1){
								list.pairwiseReverse(list.Head);
								System.out.println("List is reversed!");
							}
							else if(val ==2){
								list.recursiveReverse(null,list.Head);
								System.out.println("List is reversed!");
							}	
							break;
					case 7: list.removeDuplicates();
							break;
					case 8: return;
					default:System.out.println("Invalid choice!");
				}
			}
		}
		catch(IOException err){
			System.err.println("Error reading line");
		}
	}
}