import java.util.*;

public class Main {
    public static void PrintList(char[] List){

        for (int i =0 ; i < 9; i+=3) {
            System.out.println(List[i] + "|" + List[i+1] + "|" + List[i+2]);
        }
    }

    //This is only when the List[CurrPos] isn't ' ', the [NextPos] == ' ', and the item can be moved in the way asked;
    public static void MoveTR(char[] List, int CurrPos){
        if(CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || CurrPos == 5 || CurrPos == 8 ) {
            System.out.println("This movement is not permitted [TR]");
        } else {
            //Next pos = CurrPos - 2
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 2] = temp;
        }
    }

    public static void MoveTL(char[] List, int CurrPos){
        if(CurrPos == 0 || CurrPos == 1 || CurrPos == 2 || CurrPos == 3 || CurrPos == 6 ) {
            System.out.println("This movement is not permitted [TL]");
        } else {
            //Next pos = CurrPos - 4
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 4] = temp;
        }
    }

    public static void MoveUP(char[] List, int CurrPos){
        if(CurrPos == 0 || CurrPos == 1 || CurrPos == 2) {
            System.out.println("This movement is not permitted [UP]");
        } else {
            //Next pos = CurrPos - 3
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 3] = temp;
        }
    }
    public static void MoveDOWN(char[] List, int CurrPos){
        if(CurrPos == 6 || CurrPos == 7 || CurrPos == 8) {
            System.out.println("This movement is not permitted [DOWN]");
        } else {
            //Next pos = CurrPos +3
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 3] = temp;
        }
    }
    public static void MoveDL(char[] List, int CurrPos){
        if(CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || CurrPos == 0 || CurrPos == 3 ) {
            System.out.println("This movement is not permitted [DL]");
        } else {
            //Next pos = CurrPos + 2
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 2] = temp;
        }
    }

    public static void MoveDR(char[] List, int CurrPos){
        if(CurrPos == 6 || CurrPos == 7 || CurrPos == 8 || CurrPos == 2 || CurrPos == 5 ) {
            System.out.println("This movement is not permitted [DR]");
        } else {
            //Next pos = CurrPos + 4
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 4] = temp;
        }
    }
    public static void MoveL(char[] List, int CurrPos){
        if(CurrPos == 0 || CurrPos == 3 || CurrPos == 6) {
            System.out.println("This movement is not permitted [L]");
        } else {
            //Next pos = CurrPos - 1
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos - 1] = temp;
        }
    }
    public static void MoveR(char[] List, int CurrPos){
        if((CurrPos == 2 || CurrPos == 5 || CurrPos == 8) && (List[CurrPos] != ' ' && List[CurrPos + 1] == ' ' )) {
            System.out.println("This movement is not permitted [R]");
        } else {
            //Next pos = CurrPos + 1
            char temp;
            temp = List[CurrPos];
            List[CurrPos] = ' ';
            List[CurrPos + 1] = temp;
        }
    }
    public static int IsMovementAllowed(char[] List, int CurrentPos, int NextPos){
            if && (List[CurrentPos] != ' ' && List[NextPos] == ' ' )
        return 0;
    }

    public static void main(String[] args) {
        Scanner StartStateScan = new Scanner(System.in);
        System.out.println("Please give me the desired starting state");
        String InputList = StartStateScan.nextLine();
        char[] ListArray = InputList.toCharArray();
        PrintList(ListArray);

        //The input for these is 1 2345678
        MoveTR(ListArray, 3);
        System.out.println();
        PrintList(ListArray);
        MoveL(ListArray, 4);
        System.out.println();
        PrintList(ListArray);
        //

    }
}