public class Main {

    public static void main(String[] args) {
    
        BinaryTree tree = new BinaryTree();
        
        tree.Add(5,105);
        tree.Add(2,102);
        tree.Add(7,107);
        tree.Add(1,101);
        tree.Add(8,108);
        tree.Add(6,106);
        tree.Add(3,103);
        tree.Add(10,105);
        tree.Add(5,110);
    
        for (int i : tree)
            System.out.println("next value " + i);
        
        System.out.println("mid");
        
        for (int i : tree)
            System.out.println("next value " + i);
    }
}
