/*  
    Author: Gaurav Gupta
    Date:   Oct 7, 2015
 */

class PathNode implements Comparable<PathNode> {
	public int maxDistance;
	public int slope;
	public int x;
	public int y;
	public int elevation;
	public boolean visited;

	public PathNode(){
		this.maxDistance = 1;
		this.slope = 0;
		this.x = -1;
		this.y = -1;
		this.elevation = -1;
		this.visited = false;
	}

	public PathNode(int x, int y, int elevation) {
		this.maxDistance = 1;
		this.slope = 0;
		this.x = x;
		this.y = y;
		this.elevation = elevation;
		this.visited = false;
	}

	public PathNode(PathNode p){
		this.maxDistance = p.maxDistance;
		this.slope = p.slope;
		this.x = p.x;
		this.y = p.y;
		this.elevation = p.elevation;
		this.visited = p.visited;
	}

	public PathNode copy(){
		return new PathNode(this);
	}

	//Compare the distance and the slope
	public int compareTo(PathNode path) {
		if (this.maxDistance > path.maxDistance) {
			return 1;
		}

		if (this.maxDistance < path.maxDistance) {
			return -1;
		}

		if (this.slope > path.slope) {
			return 1;
		}

		if (this.slope < path.slope) {
			return -1;
		}
		return 0;
	}


	public String toString() {
		return maxDistance + "," + slope + " ";
	}
}
