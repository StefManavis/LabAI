//Possible solution: Modify the MoveTR, moveL,... methods so they do not change the List they receive as an argument, but they change a duplicate of it. Might help.
//This program at the moment is not good at NOT moving the item when it is in its correct position. I'm out of ideas
//SEND HELP
import java.util.*;

import static java.lang.System.arraycopy;

public class UCS {

    public static void PrintList(char[] List) {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.println(List[i] + "|" + List[i + 1] + "|" + List[i + 2]);
        }
    }

    public static void MoveTR(char[] List, int CurrPos) {

        if (CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || CurrPos == 5 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos - 2] != ' ') {
            System.out.println("This movement is not permitted [TR]");
            System.exit(-1);
        } else {
            //Next pos = CurrPos - 2
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 2] = temp;
            //System.out.println("Moved TR");

        }
    }

    //MoveTopLeft
    public static void MoveTL(char[] List, int CurrPos) {
        if (CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || CurrPos == 3 || CurrPos == 6 || List[CurrPos] == ' ' || List[CurrPos - 4] != ' ') {
            System.out.println("This movement is not permitted [TL]");

            System.exit(-1);
        } else {
            //Next pos = CurrPos - 4
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 4] = temp;
            //System.out.println("Moved TL");

        }
    }

    //MoveUp
    public static void MoveUP(char[] List, int CurrPos) {
        if (CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || List[CurrPos] == ' ' || List[CurrPos - 3] != ' ') {
            System.out.println("This movement is not permitted [UP]");

            System.exit(-1);
        } else {
            //Next pos = CurrPos - 3
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 3] = temp;
            //System.out.println("Moved UP");

        }
    }

    //MoveDown
    public static void MoveDOWN(char[] List, int CurrPos) {
        if (CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos + 3] != ' ') {
            System.out.println("This movement is not permitted [DOWN]");
            System.exit(-1);

        } else {
            //Next pos = CurrPos +3
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 3] = temp;
            //System.out.println("Moved DOWN");

        }
    }

    //MoveDownLeft
    public static void MoveDL(char[] List, int CurrPos) {
        if (CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || CurrPos == 0 || CurrPos == 3 || List[CurrPos] == ' ' || List[CurrPos + 2] != ' ') {
            System.out.println("This movement is not permitted [DL]. You're trying to move pos " + CurrPos + " and its contents are" + List[CurrPos] + "!!!!!");

            System.exit(-1);
        } else {
            //Next pos = CurrPos + 2
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 2] = temp;
            //System.out.println("Moved DL");

        }
    }

    //MoveDownRight
    public static void MoveDR(char[] List, int CurrPos) {
        if (CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || CurrPos == 2 || CurrPos == 5 || List[CurrPos] == ' ' || List[CurrPos + 4] != ' ') {
            System.out.println("This movement is not permitted [DR]");
            System.exit(-1);
        } else {
            //Next pos = CurrPos + 4
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 4] = temp;
            //System.out.println("Moved DR");

        }
    }

    //MoveLeft
    public static void MoveL(char[] List, int CurrPos) {
        if (CurrPos == 0 || CurrPos == 3 || CurrPos == 6 || List[CurrPos] == ' ' || List[CurrPos - 1] != ' ') {
            System.out.println("This movement is not permitted [L]");
            System.exit(-1);
        } else {
            //Next pos = CurrPos - 1
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 1] = temp;
            //System.out.println("Moved L");

        }
    }

    //MoveRight
    public static void MoveR(char[] List, int CurrPos) {
        if (CurrPos == 2 || CurrPos == 5 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos + 1] != ' ') {
            System.out.println("This movement is not permitted [R]");
            System.exit(-1);
        } else {
            //Next pos = CurrPos + 1
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 1] = temp;
            //System.out.println("Moved R");

        }
    }

    static class Node {
        char name;
        char[] contents;
        int cost;
        Node parent;
        ArrayList<Node> children;


        Node(char name) {
            this.name = name;
            this.children = new ArrayList<>();

        }
    }

    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }
    }

    public static PriorityQueue<Node> FrontierList = new PriorityQueue<>(new NodeComparator());//this need to be a priority queue, java already has one
    public static ArrayList<char[]> ExploredList = new ArrayList<>();


    static void UcsSearch(char[] StartState) {
        int howmanychildren = -1;


        Node Root = new Node('R');
        char[] RootContents = new char[StartState.length];
        arraycopy(StartState, 0, RootContents, 0, StartState.length);
        Root.contents =  RootContents;
        Root.cost = 0;
        Root.parent = null;

        char[] GoalContents = {'6', '5', '4', '7', ' ', '3', '8', '1', '2'};
        Node Goal = new Node('G');
        Goal.contents = GoalContents;

        FrontierList.add(Root);

        while(!FrontierList.isEmpty()) {

            Node CurrentNode = FrontierList.poll();
            howmanychildren++;

            System.out.println("CHILDREN ARE: " + howmanychildren);

            PrintList(CurrentNode.contents);

            if (Arrays.equals(CurrentNode.contents, Goal.contents)){//Original check if the user gave me the Goal state as input
                System.out.println("Solution found instantly!");
                System.exit(1);

            }

            if(!ExploredList.contains(CurrentNode.contents)) {
                //System.out.println("LEGOOo");
                GenerateChildren(CurrentNode);
                //ExploredList.add(CurrentNode.contents);
            }
            ExploredList.add(CurrentNode.contents);
            if (Arrays.equals(CurrentNode.contents, Goal.contents)) {
                System.out.println("Found");
                System.exit(666);
            }
        }

        System.out.println("No solution found.");
        System.exit(555);
    }



    static void GenerateChildren(Node CurrNode) {
        CurrNode.children = new ArrayList<>();
        ExploredList.add(CurrNode.contents);

        char[] GoalContents = {'6', '5', '4', '7', ' ', '3', '8', '1', '2'};
        Node Goal = new Node('G');
        Goal.contents = GoalContents;

        for (int j = 0; j < 9; j++) {
            //System.out.println("J is " + j);
            //PrintList(CurrNode.contents);
            if (CurrNode.contents[j] == Goal.contents[j]){

            } else if (/*!ExploredList.contains(CurrNode.contents) &&*/ (CurrNode.contents[j] != GoalContents[j]) && (CurrNode.contents[j] != ' ')) {
                System.out.println("Entered");
                // Check TR
                if (j > 2 && j != 5 && j != 8 && CurrNode.contents[j] != ' ' && CurrNode.contents[j - 2] == ' ' && (CurrNode.contents[j] != GoalContents[j])) {
                    Node child = new Node('c');
                    Node Copy = new Node('G');
                    Copy.contents = Arrays.copyOf(CurrNode.contents , 9);
                    MoveTR(Copy.contents, j);
                    child.contents = Copy.contents;
                    child.parent = CurrNode;
                    child.cost = child.parent.cost + 1;
                    CurrNode.children.add(child);
                    //System.out.println("Trying TR");
                    if(!ExploredList.contains(child.contents) && child.contents[j] != Goal.contents[j]) {
                        FrontierList.add(child);
                        //ExploredList.add(child.contents);
                        //System.out.println("Moved TR");

                    }
                    if(Arrays.equals(child.contents, Goal.contents)){
                        System.out.println("Solution Found!!!");
                        System.exit(888);
                    }



                    break;
                }

                // Check TL
                else if (j > 3 && j != 6 && CurrNode.contents[j] != ' ' && CurrNode.contents[j - 4] == ' ' && (CurrNode.contents[j] != GoalContents[j])) {
                    Node child = new Node('c');
                    Node Copy = new Node('G');
                    Copy.contents = Arrays.copyOf(CurrNode.contents , 9);
                    MoveTL(Copy.contents, j);
                    child.contents = Copy.contents;
                    child.parent = CurrNode;
                    child.cost = child.parent.cost + 1;
                    CurrNode.children.add(child);
                    //System.out.println("Trying TL");
                    if(!ExploredList.contains(child.contents) && child.contents[j] != Goal.contents[j]) {
                        FrontierList.add(child);
                        //System.out.println("Added TL");

                    }
                    if(Arrays.equals(child.contents, Goal.contents)){
                        System.out.println("Solution Found!!!");
                        System.exit(888);
                    }

                    break;
                }

                // Check UP
                else if (j >= 3 && CurrNode.contents[j] != ' ' && CurrNode.contents[j - 3] == ' ' && (CurrNode.contents[j] != GoalContents[j])) {
                    Node child = new Node('c');
                    Node Copy = new Node('G');
                    Copy.contents = Arrays.copyOf(CurrNode.contents , 9);
                    MoveUP(Copy.contents, j);
                    child.contents = Copy.contents;
                    child.parent = CurrNode;
                    child.cost = child.parent.cost + 1;
                    CurrNode.children.add(child);
                    //System.out.println("Trying UP");
                    if(!ExploredList.contains(child.contents) && child.contents[j] != Goal.contents[j]) {
                        FrontierList.add(child);
                        //System.out.println("Added UP");
                    }
                    if(Arrays.equals(child.contents, Goal.contents)){
                        System.out.println("Solution Found!!!");
                        System.exit(888);
                    }
                    break;

                }

                // Check LEFT
                else if (j > 0 && j != 3 && j != 6 && CurrNode.contents[j] != ' ' && CurrNode.contents[j - 1] == ' ' && (CurrNode.contents[j] != GoalContents[j])) {
                    Node child = new Node('c');
                    Node Copy = new Node('G');
                    Copy.contents = Arrays.copyOf(CurrNode.contents , 9);
                    MoveL(Copy.contents, j);
                    child.contents = Copy.contents;
                    child.parent = CurrNode;
                    child.cost = child.parent.cost + 1;
                    CurrNode.children.add(child);
                    //System.out.println("Trying L");
                    if(!ExploredList.contains(child.contents) && child.contents[j] != Goal.contents[j]) {
                        FrontierList.add(child);
                        //System.out.println("Added L");
                    }
                    if(Arrays.equals(child.contents, Goal.contents)){
                        System.out.println("Solution Found!!!");
                        System.exit(888);
                    }
                    break;

                }

                // Check RIGHT
                else if (j > 0 && j != 2 && j != 5 && j != 8 && CurrNode.contents[j] != ' ' && CurrNode.contents[j + 1] == ' ' && (CurrNode.contents[j] != GoalContents[j])) {
                    Node child = new Node('c');
                    Node Copy = new Node('G');
                    Copy.contents = Arrays.copyOf(CurrNode.contents , 9);
                    MoveR(Copy.contents, j);
                    child.contents = Copy.contents;
                    child.parent = CurrNode;
                    child.cost = child.parent.cost + 1;
                    CurrNode.children.add(child);
                    //System.out.println("Trying R");
                    if(!ExploredList.contains(child.contents) && child.contents[j] != Goal.contents[j]) {
                        FrontierList.add(child);
                        //System.out.println("Added R");
                    }
                    if(Arrays.equals(child.contents, Goal.contents)){
                        System.out.println("Solution Found!!!");
                        System.exit(888);
                    }

                    break;
                }


                // Check DL
                else if (j >0 && j != 3 && j <= 5 && CurrNode.contents[j] != ' ' && CurrNode.contents[j + 2] == ' ' && (CurrNode.contents[j] != GoalContents[j])) {
                    Node child = new Node('c');
                    Node Copy = new Node('G');
                    Copy.contents = Arrays.copyOf(CurrNode.contents , 9);
                    MoveDL(Copy.contents, j);
                    child.contents = Copy.contents;
                    child.parent = CurrNode;
                    child.cost = child.parent.cost + 1;
                    CurrNode.children.add(child);
                    //System.out.println("Trying DL");
                    if(!ExploredList.contains(child.contents) && child.contents[j] != Goal.contents[j]) {
                        FrontierList.add(child);
                        //System.out.println("Added DL");
                    }
                    if(Arrays.equals(child.contents, Goal.contents)){
                        System.out.println("Solution Found!!!");
                        System.exit(888);
                    }

                    break;
                }

                // Check DOWN
                else if (j <= 5 && CurrNode.contents[j] != ' ' && CurrNode.contents[j + 3] == ' ' && (CurrNode.contents[j] != GoalContents[j])) {
                    Node child = new Node('c');
                    Node Copy = new Node('G');
                    Copy.contents = Arrays.copyOf(CurrNode.contents , 9);
                    MoveDOWN(Copy.contents, j);
                    child.contents = Copy.contents;
                    child.parent = CurrNode;
                    //System.out.println("CHILDCONTENTS ARE: " + Arrays.toString(childContents) + "!");
                    child.cost = child.parent.cost + 1;
                    CurrNode.children.add(child);
                    System.out.println("Trying DOWN");
                    if(!ExploredList.contains(child.contents) && child.contents[j] != Goal.contents[j]) {
                        FrontierList.add(child);
                        System.out.println("Added DOWN");
                    }
                    if(Arrays.equals(child.contents, Goal.contents)){
                        System.out.println("Solution Found!!!");
                        System.exit(888);
                    }
                    break;
                }

                // Check DR
                else if (j <= 4 && j != 2 && CurrNode.contents[j] != ' ' && CurrNode.contents[j + 4] == ' ' && (CurrNode.contents[j] != GoalContents[j])) {
                    Node child = new Node('c');
                    Node Copy = new Node('G');
                    Copy.contents = Arrays.copyOf(CurrNode.contents , 9);
                    MoveDR(Copy.contents, j);
                    child.contents = Copy.contents;
                    child.parent = CurrNode;
                    child.cost = child.parent.cost + 1;
                    CurrNode.children.add(child);
                    //System.out.println("Trying DR");
                    if(!ExploredList.contains(child.contents) && child.contents[j] != Goal.contents[j]) {
                        FrontierList.add(child);
                        //System.out.println("Added DR");
                    }
                    if(Arrays.equals(child.contents, Goal.contents)){
                        System.out.println("Solution Found!!!");
                        System.exit(888);
                    }

                    break;
                }

            }


        }
        ExploredList.add(CurrNode.contents);
    }



    public static void main(String[] args) {
        Scanner StartStateScan = new Scanner(System.in);
        System.out.println("Please give me the desired starting state");
        String InputList = StartStateScan.nextLine();
        char[] ListArray = InputList.toCharArray();
        //PrintList(ListArray);
        UcsSearch(ListArray);








    }


}

/*
cost of child = parent.cost + 1;    DONE
ucs generates and checks children DONE

I need a process so that when a number is in the correct position, then it doesn't move from there, and only the remaining


*/


