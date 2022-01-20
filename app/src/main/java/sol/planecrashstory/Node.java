package sol.planecrashstory;

public class Node {

    private int ID;
    private int option1ID;
    private int option2ID;
    private int option3ID;
    private String description;
    private String question;

    private Node nodeOne;
    private Node nodeTwo;
    private Node nodeThree;

    public Node(int ID, int option1ID, int option2ID, int option3ID, String description, String question) {
        this.ID = ID;
        this.option1ID = option1ID;
        this.option2ID = option2ID;
        this.option3ID = option3ID;
        this.description = description;
        this.question = question;
    }

    public Node() {}

    public int getID() {return ID;}
    public void setID(int ID) {this.ID = ID;}
    public int getOption1ID() {return option1ID;}
    public void setOption1ID(int option1ID) {this.option1ID = option1ID; }
    public int getOption2ID() {return option2ID;}
    public void setOption2ID(int option2ID) {this.option2ID = option2ID;}
    public int getOption3ID() { return option3ID; }
    public void setOption3ID(int option3ID) { this.option3ID = option3ID; }
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description; }
    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}
    public Node getNodeOne() {return nodeOne;}
    public void setNodeOne(Node nodeOne) {this.nodeOne = nodeOne;}
    public Node getNodeTwo() {return nodeTwo;}
    public void setNodeTwo(Node nodeTwo) {this.nodeTwo = nodeTwo;}
    public Node getNodeThree() { return nodeThree; }
    public void setNodeThree(Node nodeThree) { this.nodeThree = nodeThree; }

    @Override
    public String toString() {
        return "nodeID:" + ID +
                ", option1ID:" + option1ID +
                ", option2ID:" + option2ID +
                ", option3ID:" + option3ID +
                ", description:'" + description + '\'' +
                ", question:'" + question + '\'';
    }


}
