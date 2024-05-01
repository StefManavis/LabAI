//We need to put all code in the same file

import java.util.*;
public class UCS {

    public static void PrintList(char[] List) {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.println(List[i] + "|" + List[i + 1] + "|" + List[i + 2]);
        }
    }

    public static char[] MoveTR(char[] List, int CurrPos) {
        if (CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || CurrPos == 5 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos - 2] != ' ') {
            System.out.println("This movement is not permitted [TR]");
            return null;
        } else {
            //Next pos = CurrPos - 2
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 2] = temp;
            return List;
        }
    }

    //MoveTopLeft
    public static char[] MoveTL(char[] List, int CurrPos) {
        if (CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || CurrPos == 3 || CurrPos == 6 || List[CurrPos] == ' ' || List[CurrPos - 4] != ' ') {
            System.out.println("This movement is not permitted [TL]");
            return null;
        } else {
            //Next pos = CurrPos - 4
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 4] = temp;
            return List;
        }
    }

    //MoveUp
    public static char[] MoveUP(char[] List, int CurrPos) {
        if (CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || List[CurrPos] == ' ' || List[CurrPos - 4] != ' ') {
            System.out.println("This movement is not permitted [UP]");
            return null;
        } else {
            //Next pos = CurrPos - 3
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 3] = temp;
            return List;
        }
    }

    //MoveDown
    public static char[] MoveDOWN(char[] List, int CurrPos) {
        if (CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos + 3] != ' ') {
            System.out.println("This movement is not permitted [DOWN]");
            return null;
        } else {
            //Next pos = CurrPos +3
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 3] = temp;
            return List;
        }
    }

    //MoveDownLeft
    public static char[] MoveDL(char[] List, int CurrPos) {
        if (CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || CurrPos == 0 || CurrPos == 3 || List[CurrPos] == ' ' || List[CurrPos + 2] != ' ') {
            System.out.println("This movement is not permitted [DL]");
            return null;
        } else {
            //Next pos = CurrPos + 2
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 2] = temp;
            return List;
        }
    }

    //MoveDownRight
    public static char[] MoveDR(char[] List, int CurrPos) {
        if (CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || CurrPos == 2 || CurrPos == 5 || List[CurrPos] == ' ' || List[CurrPos + 4] != ' ') {
            System.out.println("This movement is not permitted [DR]");
            return null;
        } else {
            //Next pos = CurrPos + 4
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 4] = temp;
            return List;
        }
    }

    //MoveLeft
    public static char[] MoveL(char[] List, int CurrPos) {
        if (CurrPos == 0 || CurrPos == 3 || CurrPos == 6 || List[CurrPos] == ' ' || List[CurrPos - 1] != ' ') {
            System.out.println("This movement is not permitted [L]");
            return null;
        } else {
            //Next pos = CurrPos - 1
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 1] = temp;
            return List;
        }
    }

    //MoveRight
    public static char[] MoveR(char[] List, int CurrPos) {
        if (CurrPos == 2 || CurrPos == 5 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos + 1] != ' ') {
            System.out.println("This movement is not permitted [R]");
            return null;
        } else {
            //Next pos = CurrPos + 1
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 1] = temp;
            return List;
        }
    }

    static class Node {
        char name;
        char[] contents;
        int cost;
        Node parent;
        List<Node> children;


        Node(char name, char[] contents) {
            this.name = name;
            this.children = new ArrayList<>();
            this.contents = contents.clone();

        }
    }

    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }
    }

    public static PriorityQueue<Node> FrontierList = new PriorityQueue<>(new NodeComparator());//this need to be a priority queue, java already has one
    public static ArrayList<Node> ExploredList = new ArrayList<>();


    static void UcsSearch(char[] StartState) {
        Node CurrentNode;

        Node Root = new Node('R', StartState);
        //char[] RootNodeContents = StartState; // Cloning to avoid modifying the original array
       // Root.contents = RootNodeContents;
        Root.cost = 0;
        Root.parent = null;

        char[] GoalContents = {'6', '5', '4', '7', ' ', '3', '8', '1', '2'};
        Node Goal = new Node('G', GoalContents);

        //Goal.contents = GoalContents;

        FrontierList.add(Root);

        while (!FrontierList.isEmpty()) {
            CurrentNode = FrontierList.poll();

            if (Arrays.equals(CurrentNode.contents, Goal.contents)) {
                System.out.println("Solution found!");
                FrontierList.clear();
                // Print solution or perform any other actions
                return;
            }
            System.out.println("Adding children");
            GenerateChildren(CurrentNode);
            System.out.println("Added children");
            for (Node c : CurrentNode.children) {
                if (!ExploredList.contains(c) && !FrontierList.contains(c)) {
                    FrontierList.add(c);
                    System.out.println("cost of c is: " + c.cost + " || its parent is: " + c.parent.name);
                    System.out.println("added children to FrontierList");
                }
            }
            System.out.println("Adding current to explored");
            ExploredList.add(CurrentNode);
            System.out.println("added current to explored");
        }

        System.out.println("No solution found.");
    }



    static void GenerateChildren(Node CurrNode) {
        ArrayList<Node> children = new ArrayList<>();
        CurrNode.children = children;

        for (int i = 0; i < 9; i++) {
            if (i % 3 != 0) { // Check if it's not the leftmost column
                // Check TR
                if (i >= 2 && CurrNode.contents[i] >= 1 && CurrNode.contents[i - 2] == ' ') {
                    Node child = new Node('c', MoveTR(CurrNode.contents, i));
                    child.parent = CurrNode;
                    //child.contents = MoveTR(CurrNode.contents, i);
                    child.cost = child.parent.cost + 1;
                    children.add(child);
                }

                // Check TL
                if (i >= 4 && CurrNode.contents[i] >= 1 && CurrNode.contents[i - 4] == ' ') {
                    Node child = new Node('c', MoveTL(CurrNode.contents, i));
                    child.parent = CurrNode;
                    //child.contents = MoveTL(CurrNode.contents, i);
                    child.cost = child.parent.cost + 1;
                    children.add(child);
                }
            }

            // Check UP
            if (i >= 3 && CurrNode.contents[i] >= 1 && CurrNode.contents[i - 3] == ' ') {
                Node child = new Node('c');
                child.parent = CurrNode;
                child.contents = MoveUP(CurrNode.contents, i);
                child.cost = child.parent.cost + 1;
                children.add(child);
            }

            // Check LEFT
            if (i % 3 != 0 && CurrNode.contents[i] >= 1 && CurrNode.contents[i - 1] == ' ') {
                Node child = new Node('c');
                child.parent = CurrNode;
                child.contents = MoveL(CurrNode.contents, i);
                child.cost = child.parent.cost + 1;
                children.add(child);
            }

            // Check RIGHT
            if (i % 3 != 2 && CurrNode.contents[i] >= 1 && CurrNode.contents[i + 1] == ' ') {
                Node child = new Node('c');
                child.parent = CurrNode;
                child.contents = MoveR(CurrNode.contents, i);
                child.cost = child.parent.cost + 1;
                children.add(child);
            }

            if (i <= 5) { // Check if it's not the bottom row
                // Check DL
                if (i <= 5 && CurrNode.contents[i] >= 1 && CurrNode.contents[i + 2] == ' ') {
                    Node child = new Node('c');
                    child.parent = CurrNode;
                    child.contents = MoveDL(CurrNode.contents, i);
                    child.cost = child.parent.cost + 1;
                    children.add(child);
                }

                // Check DOWN
                if (i <= 5 && CurrNode.contents[i] >= 1 && CurrNode.contents[i + 3] == ' ') {
                    Node child = new Node('c');
                    child.parent = CurrNode;
                    child.contents = MoveDOWN(CurrNode.contents, i);
                    child.cost = child.parent.cost + 1;
                    children.add(child);
                }
            }

            // Check DR
            if (i <= 4 && CurrNode.contents[i] >= 1 && CurrNode.contents[i + 4] == ' ') {
                Node child = new Node('c');
                child.parent = CurrNode;
                child.contents = MoveDR(CurrNode.contents, i);
                child.cost = child.parent.cost + 1;
                children.add(child);
            }
        }


    Node Goal = new Node('G');
        char[] Goalist = {'6', '5', '4', '7', ' ','3', '8', '1', '2'};
        Goal.contents = Goalist;

        /* char[] InputList =  null; // Initlaize the root node, possibly remove these
        Node Root = new Node('R');
        char[] RootNode = InputList;
        Root.contents = RootNode;
        Root.cost = 0;
        Root.parent = null;
        */
    }



    public static void main(String[] args) {
        Scanner StartStateScan = new Scanner(System.in);
        System.out.println("Please give me the desired starting state");
        String InputList = StartStateScan.nextLine();
        char[] ListArray = InputList.toCharArray();
        PrintList(ListArray);
        UcsSearch(ListArray);






        /*The input for these is 1 2345678
        MoveTR(ListArray, 3);
        System.out.println("TopRight pos:3");
        PrintList(ListArray);
        MoveL(ListArray, 4);
        System.out.println("Left pos:4");
        System.out.println();
        PrintList(ListArray);
        */

    }


}

/*
cost paidiou = parent.cost + 1;
ucs kalei generate node kai tsekarei ta paidia


ta ekana ola, apla exo kapoies allages pou ofeilontai sto otti sto constructor to node exei kai to contents tou

*/


