# Module 1
## Reflection 1.1

Coding standards learnt from this module :
- Optimized functions : abstraction of methods, for example I started off writing the edit and delete function with a built in for loop to search for Products by ID and then I separated it to make it clearer.
- Comments : I seldom wrote any comments or not at all since they already explain themselves, minimizing commentation clutter.
- Meaningful names : Clear and concise names such as `deleteProduct()` and `findById()`.
- Branching : Seperating the features via git branches and minimizing "push to base branch"(s).
- Flow : Understanding the flow of command and methods used in each feature 
`Controller` - > `service` -> `serviceImpl` -> `Repository`

Mistakes : 
- Not using exception throws
- Not using the setter provided by Lombok

How to fix :
- Read more documentation about the tools, imports, and library I am and will be working with.

## Reflection 1.2

1) - I feel like my code could use a lot of work in the future if I were to add more complex features, but for a starting point the bases are hopefully fine. 
    - First of all cover the basic functions and move up to more complex and edge case scenarios. There's no fix number on it because users may be unpredictable in the use of our projects. A good above average code coverage would be 80 - 85% code coverage.
    - 100% Code coverage only covers the basic functions. It can maybe cover more complex or niche scenarios but again users are unpredictable.

2)  It will be too wasteful and potentially deter from the clean code mentality, checking a quantity still counts as checking the attribute of a created product like name of product. If I were to add a new functionality test to check the amount of items then I would just combine it with the normal create product functional test, or if the scenario doesn't include the `create` function then I would make a new test in the same java class with combining any similar scenarios in the future to that test.

<br>
<br>

# Module 2
## Reflection
deployment link : https://tutorial-1-eshop-mradhityautomo.koyeb.app/

1) List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

- Unused/ambiguous .remove(i) usage : My `delete` function has a `.remove` that removes elements from an array while looping it. To fix it I added a boolean trigger instead that will do the intended correct option if the boolean is true.
- Understanding Mock : I use Mocks and InjectMocks without understanding it beforehand, It didn't create a proper instance of the `productRepository` class inside the `service` testing. I added `when`, `thenReturn`, and `verify` statements from mockito.
- Sonar detected issues from the table html such as no caption lable, The solution was to add `<caption>` in it.
- ProductRepository had a problem in the `save` method with no default statement, the solution was to add a default statement and modify the code to have a return value of `product` and made testing easier.

<br>

2) Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Yes :
- Continuous Integration : With using ci.yml, the code already has a built in self test mechanism that runs the unit test on push and pull. `Sonar` and `scorecard` also has an inbuilt CI sistem that checks for issues (code cleanliness) after pushing.
- Continuous Delivery : `Koyeb`, the deployment service I used,  already has an inbuilt CI/CD system in it that helps in issue testing when deploying.

I do acknowledge some of the code for this module might be a bit unclean for the professional eye and I hope to have that vision in the future.

<br>
<br>

# Module 3
## Reflection

1) Explain what principles you apply to your project!

- SRP : SRP (Single Responsibility Principle) is the concept that each module or class should be responsible for ONLY 1 (their own) function or job. The example that I implemented onto my code are listed below :

    - Seperated the `findById()` functions in both repositories with their own `save()` (`update()` in car) and `delete()` function. Now the update and delete methods only focus on editing an element or removing an element and no longer have their own iterating loops to search.
    Examples :
    Before : 

    ```java
        public Car update(String id, Car updatedCar) {
        for (int i = 0; i < carData.size(); i++) {
            Car car = carData.get(i);
            if (car.equals(id)) {
                // Update information of car
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        return null; // Handle the case where the car is not found
    }
    ```
    After : 

    ```java
        public Car update(String id, Car updatedCar) {
        Car requestedCar = findById(id);
        if (requestedCar != null){
            // Update information of car
            requestedCar.setCarName(updatedCar.getCarName());
            requestedCar.setCarColor(updatedCar.getCarColor());
            requestedCar.setCarQuantity(updatedCar.getCarQuantity());
            return requestedCar;
        }
        return null; // Handle the case where the car is not found
    }
    ```

    - Seperated the car and product controller to their own seperate classes/files (`CarController.java` and `ProductController.java` respectively).

- [Honorable Mention] OCP : OCP (The Open/Closed Principle) is the principle where the code is abstract enough to have room for future addons but also closed enough to limit modification. I wanted to implement OCP by conjoining both `CarService.java` and `ProductService.java` since they share similar if not 1 to 1 methods, but due to the possibility of each car and product having their own seperate or standalone features, I will hold off on it.

- ISP : ISP (Interface Segregation Principle) is the principle which states that a class or interface should not be burdened with a multitude of methods only to be used seperately by different implementations. The implementation here is the `CarService.java` and `ProductService.java` being their own seperate interfaces. 
Following the concept prior to ISP, this interested me in the possibility that implementing a principle (OCP) too early on might conflict with implementing others in the future, in this case by implementing and conjoining both interfaces early might result int it being a complex monolithic interface  with unnecessary methods that it's serviceimpls might not need.

- DIP : DIP (Dependency Inversion Principle) is the principle where higher level classes should not depend on lower level classes i.e. both should depend on abstract classes instead of concrete ones. The implementation in this code is the change from `CarController.java`'s autowired on `CarServiceImpl.java` to `CarService.java` instead.

```java
@Controller
@RequestMapping("/car")
class CarController{

    @Autowired
    private CarServiceImpl carservice;
...
}
```

<br>

2) Explain the advantages of applying SOLID principles to your project with examples.

- SRP : By seperating modules to doing their own thing, the code becomes clear, readable, and open to craftmanship for future updates and methods. For example by seperating the `findById()` with `delete()` and `update()`, each module becomes easier to maintain or fix, same goes with seperating both controllers.

- ISP : By implementing ISP to `CarService.java` and `ProductService.java` rather than making 1 monolithic interface that is used by both car and product, they become maintainable seperately and each implementation doesn't have to implement methods unnecessary to their own class or needs (for example, in the future the Car implementation might have a refill gas method which product does not need).

- DIP : By making concrete classes rely only on the abstract class, the code becomes simpler to understand, enables reusablity of abstract modules, and improves ease of testing. In this case `CarController.java` now only relies on an abstract module rather than a concrete one.

<br>

3) Explain the disadvantages of not applying SOLID principles to your project with examples.

- SRP : Code becomes repetitive and applying updates to a module doesn't update another bug even though it should've done the same thing i.e. updating `findById()` might not update the searching mechanism  on `delete()` and `update()` if SRP is not implemented.

- ISP : Code becomes entangled and harder to read due to seperate implementations having methods they aren't even using. (previous example, in the future the Car implementation might have a refill gas method which product does not need).

- DIP : Code becomes too dependent on eachother's concrete classes thus making it hard to implement fixes, patches, or updates i.e. if `CarController.java` relies on another concrete module `Serviceimpl` then it creates a back and forth of adjustments when updates occur.

<br>
<br>

# Module 4
## Reflection

1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

- The procedure ([RED], [GREEN], [REFACTOR]) surely made me have a structured approach when doing the exercise, even if I wasn't used to it. For beginners it may be confusing why letting an error'd code be committed, but it does make sense where the workflow is identifying the possible flaws of the code first, then applying the main structure (skeleton) to fix some of the basic needs/flaws, then meet (Implementation), and finally refactoring to patch all holes. The flaw of this procedure might be it's lengthy process so in the future I may have to be wary of deadlines when applying it. 

2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

- Fast : The code is fast and provides immediate feedback after testing.
- Isolated/Independent : Tests are independent of one another using mock.
- Repeatable : The unit tests can be reused for other or future features, of course we can use careful REFACTORS to patch things up
- Self-Validating Kemudian : With the use of assertions to check the program's output, issues in the code become easily apparent based on the results of each assertion and the overall assertions in the created tests. This makes the tests inherently self-validating.
- Thorough/Timely : Timely because it was FAST, and thorough because it covers both Happy and Unhappy cases.