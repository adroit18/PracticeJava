// <!-- Imagine you are the team that maintains the Atlassian employee directory. 
// At Atlassian - there are multiple groups, and each can have one or more groups. Every employee is part of a group.
// You are tasked with designing a system that could find the closest common parent group  given a target set of employees in the organization. 
// Assumptions : (We expect the candidate to ask these proactively, if not try and guide them towards these) 
// a group node can have both employees and groups
// We don't have employees shared across different groups 
// We don't have repeats of the same Employee name ( i.e: node value) at this stage.
// We dont have 2 isolated trees in the company ( i.e: everything rolls up to a single root) 
//  As an extension to discussions, you can probe candidates on what they would do one or many of the assumptions where not present. ( the next level up removes the second assumption mentioned here )  
// The Atlassian hierarchy sometimes can have shared group across an org or employees shared across different groups - How will the code evolve in this case if the requirement is to provide ONE closest common group? 
// The system now introduced 4 methods to update the structure of the hierarchy in the org. Suppose these dynamic updates are done in separate threads while getCommonGroupForEmployees is being called, How will your system handled reads and writes into the system efficiently such that at any given time getCommonGroupForEmployees always reflects the latest updated state of the hierarchy ? -->

import java.lang.reflect.Array;
import java.util.*;

class Node
{
    public int id;
    public String identifier;
    public String name;
    public List<Node> next = new ArrayList<>();

    public Node(int id, String identifier, String name)
    {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
    }

    @Override
    public String toString()
    {
        return ("Name: " + this.name + " Identifier: " + this.id + " id: "+ this.id);
    }

    @Override
    public boolean equals(Object obj)
    {
        Node other = (Node) obj;
        return this.id == other.id && this.identifier == other.identifier && this.name == other.name;
    }
}

class OrganizerService
{
    private static List<List<Node>> uniqueBranches = new ArrayList<>();
    
    public static Node grp0;
    public static Node emp1;
    public static Node emp2;

    public static List<Node> getEmployees()
    {
        return List.of(emp1, emp2);
    }
    public static void intializeOrg()
    {
       emp1 = new Node(1, "EMPLOYEE", "EMPLOYEE1");
       emp2 = new Node(2, "EMPLOYEE", "EMPLOYEE2");
       Node emp3 = new Node(3, "EMPLOYEE", "EMPLOYEE3");
       Node emp4 = new Node(4, "EMPLOYEE", "EMPLOYEE4");
       Node emp5 = new Node(5, "EMPLOYEE", "EMPLOYEE5");
       Node emp6 = new Node(6, "EMPLOYEE",  "EMPLOYEE6");
       Node emp7 = new Node(7, "EMPLOYEE", "EMPLOYEE7");
       Node emp8 = new Node(8, "EMPLOYEE", "EMPLOYEE8");
       Node emp9 = new Node(9, "EMPLOYEE", "EMPLOYEE9");
       Node emp10 = new Node(10, "EMPLOYEE", "EMPLOYEE10");

       grp0 = new Node(0, "GROUP", "GROUP0");
       Node grp1 = new Node(1, "GROUP","GROUP1");
       Node grp2 = new Node(2, "GROUP","GROUP2");
       Node grp3 = new Node(3, "GROUP","GROUP3");
       Node grp4 = new Node(4, "GROUP","GROUP4");
       Node grp5 = new Node(5, "GROUP","GROUP5");

       grp1.next.add(emp1);
       grp1.next.add(emp2);
    
       grp2.next.add(emp3);
       grp2.next.add(emp4);

       grp3.next.add(emp5);
       grp3.next.add(emp6);

       grp4.next.add(emp7);
       grp4.next.add(emp8);

       grp5.next.add(emp9);
       grp5.next.add(emp10);

       grp0.next.add(grp1);
       grp0.next.add(grp2);
       grp0.next.add(grp3);
       grp0.next.add(grp4);
       grp0.next.add(grp5);
    }

    public static Node findCommonGrp(List<Node> emps)
    {
        createMetadata(grp0, new ArrayList<>());
        System.out.println("uniqueBranches final " + uniqueBranches);

        HashSet<Integer> empids = new HashSet<>();
        
        for(Node emp : emps)
        {
            empids.add(emp.id);
        }
        List<List<Node>> commonPaths = new ArrayList<>();
        System.out.println(commonPaths);

        for(List<Node> branch : uniqueBranches)
        {
            for(Node node : branch)
            {
                System.out.println(node.id + " :" + node.name);
            }
            System.out.println("----------------");
        }
        for(List<Node> branch : uniqueBranches)
        {
            for(Node node : branch)
            {
                if(empids.contains(node.id) && node.identifier.equals("EMPLOYEE"))
                {
                    commonPaths.add(branch);
                    break;
                }
            }
        }
        System.out.println(commonPaths + "commonPaths .....");
        int i = 0;
        boolean isCommonForward = true;
        Node commonElement = commonPaths.get(0).get(i);
        int j = 1;
        while(isCommonForward && j<commonPaths.size())
        {
            System.out.println(j +":"+commonPaths.size());
            if(commonPaths.get(j).get(i).equals(commonPaths.get(j-1).get(i)))
                            {
                commonElement = commonPaths.get(j).get(i);
            }
            else{
                isCommonForward = false;
                break;
            }
            j++;
        }

        return commonElement;
    }

    public static void createMetadata(Node currNode, List<Node> chain)
    {
        if(currNode == null) return;
        
        List<Node> connections = currNode.next;
        System.out.println("currNode : "  + currNode + " : " + connections);
        
        System.out.println(".........." + chain);

        chain.add(currNode);

        if(currNode.next.isEmpty())
        {
            uniqueBranches.add(List.copyOf(chain));
            System.out.println("....uniqueBranches......" + uniqueBranches);
        }

        for(Node node : connections)
        {
            createMetadata(node, chain);
        }
        chain.remove(chain.size()-1);
        System.out.println("....uniqueBranches......after removal ......." + uniqueBranches);
    }
}

class OrgChart
{
    public static void main(String[] args) {
        OrganizerService.intializeOrg();
        List<Node> emps = OrganizerService.getEmployees();
        Node n = OrganizerService.findCommonGrp(emps);
        System.out.println("common is :" +n.id);
    }
}