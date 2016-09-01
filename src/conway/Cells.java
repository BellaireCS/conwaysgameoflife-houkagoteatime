package conway;

public class Cells {

	public boolean[][]prev;
	public boolean[][]next;
	public int height;
	public int width;
	
	public static void main(String[]args) {
		
		Cells cells = new Cells(10,10);
		cells.populate(); // 1/3 chance to be alive
		int cycles = 5;
		for(int i = 0; i < cycles; i++) {
			cells.cycle();
		}
	}
	
	public Cells(int height, int width) {
		this.height = height;
		this.width = width;
		prev = new boolean[height][width];
		next = new boolean[height][width];
	}
	
	public void populate() {
		
		for(int y = 0; y < prev.length; y++) {
			for(int x = 0; x < prev.length; x++) {
				int r = (int)(Math.random() * 3);
				if(r == 1) {
					prev[y][x] = true;
				}
			}
		}
	}
	
	public void nextToPrev() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				next[i][j] = prev[i][j];
			} 
		}
	}
	
	public void cycle() {
		int adj;
		System.out.println("PREV");
		for(boolean[] i:prev) {
			for(boolean cell : i) {
				if(cell) {
					System.out.print("*");
				} else {
					System.out.print("#");
				}
			}
			System.out.println("");
		}
		System.out.println("");
		for(int y = 0; y < width; y++) {
			for(int x = 0; x < height; x++) {
				String debug = "";
				//System.out.println(y + "," + x);
				adj = 0;
				if(getElement(y - 1,x - 1)) {
					debug += y + "," + x + " matches " + (y - 1) + "," + (x - 1) + "\n";
			         adj++;              
				}
				if(getElement(y,x - 1)) {
					debug += y + "," + x + " matches " + (y) + "," + (x - 1) + "\n";
			         adj++;              
				}
			    if(getElement(y + 1,x - 1)) {
			    	debug += y + "," + x + " matches " + (y + 1) + "," + (x - 1) + "\n";
			         adj++;          
			    }
			    if(getElement(y - 1,x)) {
			    	debug += y + "," + x + " matches " + (y - 1) + "," + (x) + "\n";
			         adj++;
			    }
			    if(getElement(y + 1,x)) {
			    	debug += y + "," + x + " matches " + (y + 1) + "," + (x) + "\n";
			         adj++;
			    }
			    if(getElement(y - 1,x + 1)) {
			    	debug += y + "," + x + " matches " + (y - 1) + "," + (x + 1) + "\n";
			         adj++;
			    }
			    if(getElement(y,x + 1)) {
			    	debug += y + "," + x + " matches " + (y) + "," + (x + 1) + "\n";
			         adj++;
			    }
			    if(getElement(y + 1,x + 1)) {
			    	debug += y + "," + x + " matches " + (y + 1) + "," + (x + 1) + "\n";
			         adj++;
			    }
			    if(!debug.equals("")) {
			    //System.out.print(debug);
			    }
			    if(prev[y][x]) {
			    	 if(adj < 2) {
						    next[y][x] = false;
						    continue;
						}
			    	if(adj == 2 || adj == 3) {
					   next[y][x] = true;
					   continue;
			    	}
			    	 if(adj > 3) {
			    		 next[y][x] = false;
						 continue;
			    	 }
			    } else {
			    	 if(adj == 3) {
					    next[y][x] = true;
					    continue;
			    	 }
			    }
			}
		}

		System.out.println("NEXT");
		for(boolean[] i:next) {
			for(boolean cell : i) {
				if(cell) {
					System.out.print("*");
				} else {
					System.out.print("#");
				}
			}
			System.out.println("");
		}
		nextToPrev();
	}
	
	public boolean getElement(int y, int x) {
		int oldy = y;
		int oldx = x;
		if(y < 0) {
			y = width + y;
			if(y == oldy) {
				return false;
			}
		}
		if(x < 0) {
			x = height + x;
			if(x == oldx) {
				return false;
			}
		}
	    return prev[y % height][x % width];
	}
	
}
