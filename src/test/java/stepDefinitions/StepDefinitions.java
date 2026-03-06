package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.messages.types.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StepDefinitions {
    @Given("this is a basic test Scenario.")
    public void basicTest() {
        System.out.println("Cucumber setup is successful!");
        Set<String> s= new HashSet<String>();
        s.add("rama");
        s.add("Ayra");
        s.add("Akshada");
        s.add("Aryan");
        s.add("Karthekeyan");
        s.add("jeevan");

        List<Product> productList =new ArrayList<>();
        productList.add(new Product("rama","rama"));

    }



}