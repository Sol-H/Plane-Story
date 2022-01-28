package sol.planecrashstory;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class NodeMap {

    private Node head;
    private Node currentNode;

/****************************************************/
/**************      NAVIGATE       *****************/
/****************************************************/
/****************************************************/
    public Node currentNode() { return currentNode;}


    public void decision(int decision) {
        if (twoChoices()) {
            switch (decision) {
                case 1:
                    currentNode = currentNode.getNodeOne();
                    break;
                case 2:
                    currentNode = currentNode.getNodeTwo();
                    break;
            }
        }
        else {
            switch (decision) {
                case 1:
                    currentNode = currentNode.getNodeOne();
                    break;
                case 2:
                    currentNode = currentNode.getNodeTwo();
                    break;
                case 3:
                    currentNode = currentNode.getNodeThree();
                    break;
            }
        }
    }

    public boolean twoChoices(){
        return currentNode.getOption3ID() == -1;
    }

    public boolean noDecision(){
        return currentNode.getQuestion().equals("-");
    }

    public boolean endOfGame(){
        return currentNode.getOption1ID() == -1 || currentNode.getOption1ID() == -2;
    }

/****************************************************/
/**************         BUILD      ******************/
/****************************************************/
/****************************************************/

    public NodeMap(InputStream prc)  {
        NodeCollection nodeCollection;   //scope: constructor only, part of process, no requirement to keep;
        try {
            nodeCollection = new NodeCollection(prc);
            head = nodeCollection.get(0);
        } catch (FileNotFoundException e) {
            //message
            return;
        }
        buildMap(nodeCollection);
        currentNode = head;
    }


    private void buildMap(NodeCollection nodeCollection)   {
        if (nodeCollection == null) {return;}
        for(Node source : nodeCollection.arrayList()){
            int option1ID = source.getOption1ID();
            int option2ID = source.getOption2ID();
            int option3ID = source.getOption3ID();
            Node nodeOne = nodeCollection.locateNodeBy(option1ID);
            Node nodeTwo = nodeCollection.locateNodeBy(option2ID);
            Node nodeThree = nodeCollection.locateNodeBy(option3ID);
            source.setNodeOne(nodeOne);
            source.setNodeTwo(nodeTwo);
            source.setNodeThree(nodeThree);
        }
    }

    public String toString(){
        String string = "";
        string += pathOne() + "\n";
        string += pathTwo() + "\n";
        string += pathThree() + "\n";
        return string;
    }

    public String pathOne(){
        Node node = head;
        String string = "YES PATH\n";
        while(node != null) {
            string += node.toString() + "\n";
            node = node.getNodeOne();
            if (node.getID() == 0) { node = null;}
        }
        return string;
    }

    public String pathTwo(){
        Node node = head;
        String string = "NO PATH\n";
        while(node != null) {
            string += node.toString() + "\n";
            node = node.getNodeTwo();
            if (node.getID() == 0) { node = null;}
        }
        return string;
    }

    public String pathThree(){
        Node node = head;
        String string = "NO PATH\n";
        while(node != null) {
            string += node.toString() + "\n";
            node = node.getNodeThree();
            if (node.getID() == 0) { node = null;}
        }
        return string;
    }


}