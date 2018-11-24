package ch.stair.hackday.packhack.player.AStar;

public class Node {

    protected static final int MOVEMENT_COST = 10;
    private int x;
    private int y;
    private boolean walkable;
    private Node parent;
    private int g; //The cost of getting from the first node to this node.
    private int h; //estimated coast

    public Node(int x, int y, Boolean walkable){
        this.x = x;
        this.y = y;
        this.walkable = walkable;
    }

    /**
     * @return estimated total coast
     */
    public int getF(){
        return g + h;
    }
    public int getH(){
        return h;
    }
    public int getG(){
        return g;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isWalkable(){
        return walkable;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getParent(){
        return parent;
    }
    public void setG(Node parent){
        this.g = this.calculateG(parent);
    }
    public int calculateG(Node parent){
        return (parent.getG() + MOVEMENT_COST);
    }
    public void setH(Node goal)
    {
        h = (Math.abs(getX() - goal.getX()) + Math.abs(getY() - goal.getY())) * MOVEMENT_COST;
    }


    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if (!(o instanceof Node))
            return false;
        if (o == this)
            return true;

        Node n = (Node) o;
        if (n.getX() == x && n.getY() == y && n.isWalkable() == walkable)
            return true;
        return false;
    }
}
