package kuudos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Queue;

public abstract class Game {

  private Queue<Node> queue = new LinkedList<Node>();
  private Set<Node> queueSet = new HashSet<Node>();
  private char[] sybmols;

  public void solve() {
    while (queue.peek() != null) {
      Node node = queue.poll();
      node.update(this);
    }
  }

  public abstract void initialize();

  public void enqueueNode(Node node) {
    if (!queueSet.contains(node)) {
      queue.offer(node);
      queueSet.add(node);
    }
  }

  public static class Node {

    private List<Node> parents;
    private List<Node> children;
    private Set<Character> yes;
    private Set<Character> no;

    public Node(int num) {
      parents = new ArrayList<Node>();
      children = new ArrayList<Node>();
      yes = new HashSet<Character>();
      no = new HashSet<Character>();
    }

    public void attachToChild(Node child) {
      this.addChild(child);
      child.addParent(this);
    }

    public void addParent(Node parent) {
      parents.add(parent);
    }

    public void addChild(Node child) {
      children.add(child);
    }

    public void update(Game game) {
      for (Node child : children) {
        game.enqueueNode(child);
      }
    }
  }
}
