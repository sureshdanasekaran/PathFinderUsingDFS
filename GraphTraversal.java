import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class GraphTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String s[] = scanner.nextLine().split(" ");
		int row = Integer.valueOf(s[0]);
		int column = Integer.valueOf(s[1]);
		Node[][] matrix = new Node[row][column];
		Location end =null;
		for (int i = 0; i < row; i++) {
			String rowval[] = scanner.nextLine().split(" ");
			for (int j = 0; j < column; j++) {
				if (rowval[j].equalsIgnoreCase("O")) {
					matrix[i][j] = new Node("O", "O");
					end = new Location(i, j, 0);
					continue;
				}
				String[] element = rowval[j].split("-");
				matrix[i][j] = new Node(element[0], element[1]);
			}
		}
		Location start = new Location(0, 0, 0);
		//writematrix(matrix);
		populateAdjacency(matrix);
		//writeAdjacency(matrix);
//		
		Stack<Location> container = new Stack<Location>();
		Stack<Integer> ctnrIdx = new Stack<Integer>();
		ArrayList<Location> li = new ArrayList<Location>();
		scout(matrix,start,end,container,ctnrIdx,null,0,li);
		if(container.size()>0 && container.peek().getRowIdx()== end.getRowIdx() && container.peek().getColumnIdx()==end.getColumnIdx())
		{
				Iterator<Location> i = container.iterator();
				while (i.hasNext())
					System.out.print(i.next().toString() + "||");
			}
		else
		{
			System.out.println("Could not find path");
			Iterator<Location> i = container.iterator();
			while (i.hasNext())
				System.out.print(i.next().toString() + "||");

		}
		}
	

	private static void scout(Node[][] matrix, Location start, Location end, Stack<Location> container,
			Stack<Integer> ctnrIdx, ArrayList<Location> adjacency, int adjIdx, ArrayList<Location> li) {
		Location temp = null;
		if (adjacency == null) {
			adjacency = matrix[start.getRowIdx()][start.getColumnIdx()].getAdjacency();
		}
		if (adjacency.isEmpty()) {
			temp = peekFromStack(container, ctnrIdx, start);
			if (temp == null) {
				return;
			}
			if(matrix[temp.getRowIdx()][temp.getColumnIdx()].getAdjacency().size()<ctnrIdx.peek())
			{
				ctnrIdx.pop();
				int val = ctnrIdx.pop() + 1;
				ctnrIdx.push(val);
				container.pop();
			}
			
		}
		boolean flag = false;
		// boolean lastVisited = false;
		if (!adjacency.isEmpty()) {
			if (adjacency.size() > adjIdx) {
				flag = addInStack(container, ctnrIdx, adjacency.get(adjIdx), li);
			} else {
				//li.add(container.peek());
				if (ctnrIdx.size() < 2)
					return;
				ctnrIdx.pop();
				int val = ctnrIdx.pop() + 1;
				ctnrIdx.push(val);
				container.pop();

			}
			if (flag && (adjacency.get(adjIdx).getRowIdx() == end.getRowIdx()
					&& adjacency.get(adjIdx).getColumnIdx() == end.getColumnIdx()))
				return;
			//if (!flag) {
				// ctnrIdx.pop();
				//ctnrIdx.push(ctnrIdx.pop() + 1);
				// container.pop();

			//}
		}
		scout(matrix, container.peek(), end, container, ctnrIdx, null, ctnrIdx.peek(), li);

	}

	private static Location peekFromStack(Stack<Location> container, Stack<Integer> ctnrIdx, Location start) {
		if(container.isEmpty()||ctnrIdx.isEmpty())
			return null;
		else
			{
			Location location =container.peek();
			int newIdx = ctnrIdx.pop()+1;
			//ctnrIdx.push(ctnrIdx.pop() + 1);
			ctnrIdx.push(newIdx);
			return location;
			
			}
		
		
		
	}

	private static boolean addInStack(Stack<Location> container, Stack<Integer> ctnrIdx, Location start,
			ArrayList<Location> li) {
		if (container.size() < 1) {
			container.add(start);
			ctnrIdx.add(0);
			return true;
		} else {
			Iterator<Location> i = container.iterator();
			while (i.hasNext()) {
				Location temp = i.next();
				if (temp.getRowIdx() == start.getRowIdx() && temp.getColumnIdx() == start.getColumnIdx()) {
					ctnrIdx.push(ctnrIdx.pop() + 1);
					return false;
				}

			}
			/*
			 * Iterator<Location> i2 = li.iterator(); while (i2.hasNext()) { Location temp =
			 * i2.next(); if (temp.getRowIdx() == start.getRowIdx() && temp.getColumnIdx()
			 * == start.getColumnIdx()) { ctnrIdx.push(ctnrIdx.pop()+1); return true; } }
			 */
			container.add(start);
			ctnrIdx.add(0);
			return true;
		}

	}

	private static void writeAdjacency(Node[][] matrix) {

		int row = matrix.length;
		System.out.println("length " + row);
		int column = matrix[0].length;
		System.out.println("length " + column);
		for (Node[] eachRow : matrix)
			for (Node ele : eachRow) {
				System.out.println("\n" + ele.getColor() + "+" + ele.getDirection() + " :");
				ArrayList<Location> dq = ele.getAdjacency();
				if (null != dq) {
					Iterator<Location> i = dq.iterator();
					while (i.hasNext())
						System.out.print(i.next().toString() + "||");

				}
			}
	}

	private static void populateAdjacency(Node[][] matrix) {
		int row = matrix.length;
		System.out.println("length " + row);
		int column = matrix[0].length;
		System.out.println("length " + column);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				int p = i, q = j;
				ArrayList<Location> dq = new ArrayList<Location>();
				String color = matrix[i][j].getColor();
				String direction = matrix[i][j].getDirection();
				if (color.equalsIgnoreCase("R")) {
					if (direction.equalsIgnoreCase("N")) {
						for (p--; p >= 0; p--) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("R"))) {
								dq.add(new Location(p, q, i - p));
							}
						}
					} else if (direction.equalsIgnoreCase("S")) {
						for (p++; p < row; p++) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("R"))) {
								dq.add(new Location(p, q, p - i));
							}
						}
					} else if (direction.equalsIgnoreCase("E")) {
						for (q++; q < column; q++) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("R"))) {
								dq.add(new Location(p, q, q - j));
							}
						}
					} else if (direction.equalsIgnoreCase("W")) {
						for (q--; q >= 0; q--) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("R"))) {
								dq.add(new Location(p, q, j - q));
							}
						}
					} else if (direction.equalsIgnoreCase("NE")) {
						for (p--, q++; p >= 0 && q < column; p--, q++) {

							if (!(matrix[p][q].getColor().equalsIgnoreCase("R"))) {
								dq.add(new Location(p, q, i - p));
							}
						}
					} else if (direction.equalsIgnoreCase("NW")) {
						for (p--, q--; p >= 0 && q >= 0; p--, q--) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("R"))) {
								dq.add(new Location(p, q, i - p));
							}
						}
					} else if (direction.equalsIgnoreCase("SE")) {
						for (p++, q++; p < row && q < column; p++, q++) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("R"))) {
								dq.add(new Location(p, q, p - i));
							}
						}
					} else if (direction.equalsIgnoreCase("SW")) {
						for (p++, q--; p < row && q >= 0; p++, q--) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("R"))) {
								dq.add(new Location(p, q, p - i));
							}
						}
					}
				} else if (color.equalsIgnoreCase("B")) {

					if (direction.equalsIgnoreCase("N")) {
						for (p--; p >= 0; p--) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("B"))) {
								dq.add(new Location(p, q, i - p));
							}
						}
					} else if (direction.equalsIgnoreCase("S")) {
						for (p++; p < row; p++) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("B"))) {
								dq.add(new Location(p, q, p - i));
							}
						}
					} else if (direction.equalsIgnoreCase("E")) {
						for (q++; q < column; q++) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("B"))) {
								dq.add(new Location(p, q, q - j));
							}
						}
					} else if (direction.equalsIgnoreCase("W")) {
						for (q--; q >= 0; q--) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("B"))) {
								dq.add(new Location(p, q, j - q));
							}
						}
					} else if (direction.equalsIgnoreCase("NE")) {
						for (p--, q++; p >= 0 && q < column; p--, q++) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("B"))) {
								dq.add(new Location(p, q, i - p));
							}
						}
					} else if (direction.equalsIgnoreCase("NW")) {
						for (p--, q--; p >= 0 && q >= 0; p--, q--) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("B"))) {
								dq.add(new Location(p, q, i - p));
							}
						}
					} else if (direction.equalsIgnoreCase("SE")) {
						for (p++, q++; p < row && q < column; p++, q++) {
							if (!(matrix[p][q].getColor().equalsIgnoreCase("B"))) {
								dq.add(new Location(p, q, p - i));
							}
						}
					} else if (direction.equalsIgnoreCase("SW")) {
						for (p++, q--; p < row && q >= 0; p++, q--) {

							if (!(matrix[p][q].getColor().equalsIgnoreCase("B"))) {
								dq.add(new Location(p, q, p - i));
							}
						}
					}

				}
				matrix[i][j].setAdjacency(dq);
			}
		}

	}

	private static void writematrix(Node[][] matrix) {
		int row = matrix.length;
		System.out.println("length " + row);
		int column = matrix[0].length;
		System.out.println("length " + column);
		for (int i = 0; i < row; i++) {
			System.out.println();
			for (int j = 0; j < column; j++)
				System.out.print(matrix[i][j].getColor() + "-" + matrix[i][j].getDirection() + " ");
		}
	}

}

class Node {
	private String direction;
	private String color;
	private ArrayList<Location> adjacency;

	Node(String color, String direction) {
		this.direction = direction;
		this.color = color;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<Location> getAdjacency() {
		return adjacency;
	}

	public void setAdjacency(ArrayList<Location> adjacency) {
		this.adjacency = adjacency;
	}
}

class Location {
	private int rowIdx;
	private int columnIdx;
	private int space;

	Location(int rowIdx, int columnIdx, int space) {
		this.rowIdx = rowIdx;
		this.columnIdx = columnIdx;
		this.space = space;
	}

	public int getRowIdx() {
		return rowIdx;
	}

	public void setRowIdx(int rowIdx) {
		this.rowIdx = rowIdx;
	}

	public int getColumnIdx() {
		return columnIdx;
	}

	public void setColumnIdx(int columnIdx) {
		this.columnIdx = columnIdx;
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	@Override
	public String toString() {
		return rowIdx + "," + columnIdx + " Space " + space;
	}

}
