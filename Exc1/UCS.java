//We need to put all code in the same file
import java.util.*;
public class UCS {

    public static void PrintList(char[] List){
        System.out.println();
        for (int i =0 ; i < 9; i+=3) {
            System.out.println(List[i] + "|" + List[i+1] + "|" + List[i+2]);
        }
    }
    public static char[] MoveTR(char[] List, int CurrPos) {
        if(CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || CurrPos == 5 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos - 2] != ' ' ) {
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
    public static char[] MoveTL(char[] List, int CurrPos){
        if(CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || CurrPos == 3 || CurrPos == 6  || List[CurrPos] == ' ' || List[CurrPos -4] != ' ' ) {
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
    public static char[] MoveUP(char[] List, int CurrPos){
        if(CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || List[CurrPos] == ' ' || List[CurrPos -4] != ' ' ){
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
    public static char[] MoveDOWN(char[] List, int CurrPos){
        if(CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos -3] != ' ' ){
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
    public static char[] MoveDL(char[] List, int CurrPos){
        if(CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || CurrPos == 0 || CurrPos == 3 || List[CurrPos] == ' ' || List[CurrPos + 2] != ' ' ) {
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
    public static char[] MoveDR(char[] List, int CurrPos){
        if(CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || CurrPos == 2 || CurrPos == 5 || List[CurrPos] == ' ' || List[CurrPos + 4] != ' ' ) {
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
    public static char[] MoveL(char[] List, int CurrPos){
        if(CurrPos == 0 || CurrPos == 3 || CurrPos == 6 || List[CurrPos] == ' ' || List[CurrPos -1] != ' ' ){
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
    public static char[] MoveR(char[] List, int CurrPos){
        if(CurrPos == 2 || CurrPos == 5 || CurrPos == 8 || List[CurrPos] == ' ' || List[CurrPos + 1] != ' ' ) {
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


        Node(char name) {
            this.name = name;
            this.children = new ArrayList<>();
            //this.cost = cost;

        }
    }

    static void UcsSearch(char[] StartState) {//StartState is the input of the user
        Node Start = new Node('S');
        Start.contents = StartState;
        Start.cost = 0;
        Start.parent = null;

    }


    static void GenerateChildren(Node CurrNode) {
        ArrayList<Node> children = new ArrayList<>();
        CurrNode.children = children;

        //TR
        for (int i = 0; i < 9; i++) {
            //TR
            if (CurrNode.contents[i] >= 1 && CurrNode.contents[i - 2] == ' ') {
                Node child = new Node('c');
                child.contents = MoveTR(CurrNode.contents, i);
                child.cost = 1;
                children.add(child);
            }
            //TL
            if (CurrNode.contents[i] >= 1 && CurrNode.contents[i - 4] == ' ') {
                Node child = new Node('c');
                child.contents = MoveTL(CurrNode.contents, i);
                child.cost = 1;
                children.add(child);
            }
            //UP
            if (CurrNode.contents[i] >= 1 && CurrNode.contents[i - 3] == ' ') {
                Node child = new Node('c');
                child.contents = MoveUP(CurrNode.contents, i);
                child.cost = 1;
                children.add(child);
            }
            //LEFT
            if (CurrNode.contents[i] >= 1 && CurrNode.contents[i - 1] == ' ') {
                Node child = new Node('c');
                child.contents = MoveL(CurrNode.contents, i);
                child.cost = 1;
                children.add(child);
            }
            //RIGHT
            if (CurrNode.contents[i] >= 1 && CurrNode.contents[i +1] == ' ') {
                Node child = new Node('c');
                child.contents = MoveR(CurrNode.contents, i);
                child.cost = 1;
                children.add(child);
            }
            //BL
            if (CurrNode.contents[i] >= 1 && CurrNode.contents[i + 2] == ' ') {
                Node child = new Node('c');
                child.contents = MoveDL(CurrNode.contents, i);
                child.cost = 1;
                children.add(child);
            }
            //DOWN
            if (CurrNode.contents[i] >= 1 && CurrNode.contents[i + 3] == ' ') {
                Node child = new Node('c');
                child.contents = MoveDOWN(CurrNode.contents, i);
                child.cost = 1;
                children.add(child);
            }
            //DR
            if (CurrNode.contents[i] >= 1 && CurrNode.contents[i + 4] == ' ') {
                Node child = new Node('c');
                child.contents = MoveDR(CurrNode.contents, i);
                child.cost = 1;
                children.add(child);
            }

        }

        Node Goal = new Node('G');
        char[] Goalist = {'6', '5', '4', '7', ' ','3', '8', '1', '2'};
        Goal.contents = Goalist;


    }



    public static void main(String[] args) {
        Scanner StartStateScan = new Scanner(System.in);
        System.out.println("Please give me the desired starting state");
        String InputList = StartStateScan.nextLine();
        char[] ListArray = InputList.toCharArray();
        PrintList(ListArray);






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




*/


