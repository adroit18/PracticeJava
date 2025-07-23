// <!-- Imagine we have a customer support ticketing system. The system allows customers to rate the support agent out of 5. 
// To start with, write a function which accepts a rating, and another which will show me all of the agents and the average rating each one has received, ordered highest to lowest.
// Keep in mind:
// If necessary you can give function signatures, but try to encourage candidates to come up with their own, expecially 
// P40+

// void giveRating(String agentName, int rating)
// List<Agent> getRatings() -->


// Classes : Customer, SupportAgent
// Functions : showAllAgents and average rating each has received, ordered highest to lowest
// Accepts a rating

import  java.util.*;
import java.util.stream.Collectors;

class Customer
{
    public int id;
    private String Name;
    public SupportAgent supportAgent;

    public Customer(int id, String name)
    {
        this.id  = id; 
        this.Name = name;
    }

    public String getName()
    {
        return this.Name;
    }

    public boolean updateName(String name)
    {
        this.Name = name;
        return true;
    }

    public void assignSupport(SupportAgent supportAgent) throws Exception
    {
        if(this.supportAgent == null){
            this.supportAgent = supportAgent;
        }
        else{
            throw new Exception("Support agent already assigned");
        }
    }

    public void deassignSupport()
    {
        this.supportAgent = null;
    }

    public int getCustomerId()
    {
        return this.id;
    }
}

class SupportAgent
{
    private int id;
    private String Name;
    private int totalRatingSum;
    private int totalPeopleReviewed;
    private double avgRating;
    public List<Integer> assignedCustomerIds;

    public SupportAgent(int id, String name)
    {
        this.id  = id; 
        this.Name = name;
        this.assignedCustomerIds = new ArrayList<>();
    }

    public String getName()
    {
        return this.Name;
    }

    public boolean updateName(String name)
    {
        this.Name = name;
        return true;
    }

    public  void receiveRating(Customer customer, int rating) throws Exception
    {
        if(!this.assignedCustomerIds.contains(customer.getCustomerId()))
        {
            throw new Exception("Invalid customer");
        }

        if(rating > 5 || rating < 0)
        {
            throw new Exception("Invalid rating");
        }

        this.totalRatingSum += rating;
        this.totalPeopleReviewed += 1;
    }

    public double getAvgRating()
    {
        this.avgRating = this.totalRatingSum / (this.totalPeopleReviewed * 1.0);
        return this.avgRating;
    }

    public void addCustomerForSupport(Customer customer)
    {
        this.assignedCustomerIds.add(customer.getCustomerId());
    }

    public void endCustomerSupport(Customer customer)
    {
        this.assignedCustomerIds.remove((Integer)customer.getCustomerId());
    }
}

class CustomerSupport
{
    public static void main(String[] args) throws Exception {
        try
        {
            Customer customer1 = new Customer(1, "Deepak Uniyal");
            Customer customer2 = new Customer(1, "Deepak Uniyal 2");
            Customer customer3 = new Customer(1, "Deepak Uniyal 3");
            Customer customer4 = new Customer(1, "Deepak Uniyal 4");
            SupportAgent supportAgent1 = new SupportAgent(1, "SA");
            SupportAgent supportAgent2 = new SupportAgent(1, "SA 2");

            supportAgent1.addCustomerForSupport(customer1);
            customer1.assignSupport(supportAgent1);
            supportAgent1.receiveRating(customer1, 4);
            customer1.deassignSupport();
            supportAgent1.endCustomerSupport(customer1);

            supportAgent2.addCustomerForSupport(customer2);
            customer2.assignSupport(supportAgent2);
            supportAgent2.receiveRating(customer2, 1);
            customer2.deassignSupport();
            supportAgent2.endCustomerSupport(customer2);

            supportAgent1.addCustomerForSupport(customer3);
            customer3.assignSupport(supportAgent1);
            supportAgent1.receiveRating(customer3, 5);
            customer3.deassignSupport();
            supportAgent1.endCustomerSupport(customer3);

            supportAgent2.addCustomerForSupport(customer4);
            customer4.assignSupport(supportAgent2);
            supportAgent2.receiveRating(customer4, 3);
            customer4.deassignSupport();
            supportAgent2.endCustomerSupport(customer4);

            HashMap<SupportAgent, Double> ratingView = getRatingView(List.of(supportAgent1, supportAgent2));

            System.out.println(ratingView);
        }
        catch(Exception ex)
        {
            System.out.println("ex.message" + ex.getMessage());
        }
    }

    private static HashMap<SupportAgent, Double> getRatingView(List<SupportAgent> agentList)
    {
        HashMap<SupportAgent, Double> ratingView = new HashMap<>();
        for(SupportAgent supportagent : agentList)
        {
            double rating = supportagent.getAvgRating();
            ratingView.put(supportagent, rating);
        } 

        ratingView = ratingView.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))  // 🔧 fixed line
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
        return ratingView;
    }
}

class CustomerSupportService {
    private final Map<Integer, SupportAgent> agentMap = new HashMap<>();
    private final Map<Integer, Customer> customerMap = new HashMap<>();
    private final Map<Integer, Integer> customerToAgentMap = new HashMap<>();

    public void registerAgent(SupportAgent agent) {
        agentMap.put(agent.getId(), agent);
    }

    public void registerCustomer(Customer customer) {
        customerMap.put(customer.getCustomerId(), customer);
    }

    public void assignAgentToCustomer(int customerId, int agentId) throws Exception {
        if (customerToAgentMap.containsKey(customerId)) {
            throw new Exception("Customer already assigned to a support agent.");
        }
        SupportAgent agent = agentMap.get(agentId);
        Customer customer = customerMap.get(customerId);
        if (agent == null || customer == null) {
            throw new Exception("Agent or customer not found.");
        }

        agent.addCustomerForSupport(customer);
        customerToAgentMap.put(customerId, agentId);
    }

    public void deassignAgent(int customerId) {
        Integer agentId = customerToAgentMap.remove(customerId);
        if (agentId != null) {
            SupportAgent agent = agentMap.get(agentId);
            Customer customer = customerMap.get(customerId);
            if (agent != null && customer != null) {
                agent.endCustomerSupport(customer);
            }
        }
    }

    public void giveRating(int customerId, int rating) throws Exception {
        Integer agentId = customerToAgentMap.get(customerId);
        if (agentId == null) {
            throw new Exception("Customer not assigned to any agent.");
        }
        SupportAgent agent = agentMap.get(agentId);
        Customer customer = customerMap.get(customerId);
        agent.receiveRating(customer, rating);
    }

    public List<SupportAgent> getAgentsSortedByRating() {
        return agentMap.values().stream()
            .sorted((a1, a2) -> Double.compare(a2.getAvgRating(), a1.getAvgRating()))
            .collect(Collectors.toList());
    }
}
