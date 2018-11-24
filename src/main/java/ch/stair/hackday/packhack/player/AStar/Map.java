package ch.stair.hackday.packhack.player.AStar;

import ch.stair.hackday.packhack.dto.Direction;
import ch.stair.hackday.packhack.dto.FieldTypes;

import java.util.LinkedList;
import java.util.List;

public class Map {
    private int width;
    private int height;

    private Node[][] nodes;

    public Map(FieldTypes[][] game) {
        this.width = game[0].length;
        this.height = game.length;
        nodes = new Node[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                nodes[x][y] = new Node(x, y, game[y][x] != FieldTypes.WALL);
            }
        }
    }

    public Node getNode(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return nodes[x][y];
        } else {
            return null;
        }
    }

    public final List<Node> findPath(int startX, int startY, int goalX, int goalY) {
        // If our start position is the same as our goal position ...
        if (startX == goalX && startY == goalY) {
            // Return an empty path, because we don't need to move at all.
            return new LinkedList<Node>();
        }

        // The set of nodes already visited.
        List<Node> openList = new LinkedList<Node>();
        // The set of currently discovered nodes still to be visited.
        List<Node> closedList = new LinkedList<Node>();

        // Add starting node to open list.
        openList.add(nodes[startX][startY]);

        // This loop will be broken as soon as the current node position is
        // equal to the goal position.
        while (true) {
            // Gets node with the lowest F score from open list.
            Node current = lowestFInList(openList);
            // Remove current node from open list.
            openList.remove(current);
            // Add current node to closed list.
            closedList.add(current);

            // If the current node position is equal to the goal position ...
            if ((current.getX() == goalX) && (current.getY() == goalY)) {
                // Return a LinkedList containing all of the visited nodes.
                return calcPath(nodes[startX][startY], current);
            }

            List<Node> adjacentNodes = getAdjacent(current, closedList);
            for (Node adjacent : adjacentNodes) {
                // If node is not in the open list ...
                if (!openList.contains(adjacent)) {
                    // Set current node as parent for this node.
                    adjacent.setParent(current);
                    // Set H costs of this node (estimated costs to goal).
                    adjacent.setH(nodes[goalX][goalY]);
                    // Set G costs of this node (costs from start to this node).
                    adjacent.setG(current);
                    // Add node to openList.
                    openList.add(adjacent);
                }
                // Else if the node is in the open list and the G score from
                // current node is cheaper than previous costs ...
                else if (adjacent.getG() > adjacent.calculateG(current)) {
                    // Set current node as parent for this node.
                    adjacent.setParent(current);
                    // Set G costs of this node (costs from start to this node).
                    adjacent.setG(current);
                }
            }

            // If no path exists ...
            if (openList.isEmpty()) {
                // Return an empty list.
                return new LinkedList<Node>();
            }
            // But if it does, continue the loop.
        }
    }

    private List<Node> calcPath(Node start, Node goal) {
        LinkedList<Node> path = new LinkedList<Node>();

        Node node = goal;
        boolean done = false;
        while (!done) {
            path.addFirst(node);
            node = node.getParent();
            if (node.equals(start)) {
                done = true;
            }
        }
        return path;
    }

    /**
     * @param list The list to be checked.
     * @return The node with the lowest F score in the list.
     */
    private Node lowestFInList(List<Node> list) {
        Node cheapest = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getF() < cheapest.getF()) {
                cheapest = list.get(i);
            }
        }
        return cheapest;
    }

    /**
     * @param node       The node to be checked for adjacent nodes.
     * @param closedList A list containing all of the nodes already visited.
     * @return A LinkedList with nodes adjacent to the given node if those
     * exist, are walkable and are not already in the closed list.
     */
    private List<Node> getAdjacent(Node node, List<Node> closedList) {
        List<Node> adjacentNodes = new LinkedList<Node>();
        int x = node.getX();
        int y = node.getY();

        Node adjacent;

        // Check left node
        if (x > 0) {
            adjacent = getNode(x - 1, y);
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)) {
                adjacentNodes.add(adjacent);
            }
        }

        // Check right node
        if (x < width) {
            adjacent = getNode(x + 1, y);
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)) {
                adjacentNodes.add(adjacent);
            }
        }

        // Check top node
        if (y > 0) {
            adjacent = this.getNode(x, y - 1);
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)) {
                adjacentNodes.add(adjacent);
            }
        }

        // Check bottom node
        if (y < height) {
            adjacent = this.getNode(x, y + 1);
            if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)) {
                adjacentNodes.add(adjacent);
            }
        }
        return adjacentNodes;
    }
    public int getPathLenght(int startX, int startY, int goalX, int goalY){
        List<Node> list = findPath(startX,startY,goalX,goalY);
        return list.size();
    }
    public Direction getFirstDirection(int startX, int startY, int goalX, int goalY) {
        List<Node> list = findPath(startX, startY, goalX, goalY);

        Node next = list.get(0);

        if(startY>next.getY()) return Direction.NORTH;
        if(startX<next.getX()) return Direction.EAST;
        if(startY<next.getY()) return Direction.SOUTH;
        if(startX>next.getX()) return Direction.WEST;
        return Direction.STOP;
    }
}
