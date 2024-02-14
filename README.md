## Reflection 1

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

## Reflection 2

1. - I feel like my code could use a lot of work in the future if I were to add more complex features, but for a starting point the bases are hopefully fine. 
    - First of all cover the basic functions and move up to more complex and edge case scenarios. There's no fix number on it because users may be unpredictable in the use of our projects. A good above average code coverage would be 80 - 85% code coverage.
    - 100% Code coverage only covers the basic functions. It can maybe cover more complex or niche scenarios but again users are unpredictable.

2.  It will be too wasteful and potentially deter from the clean code mentality, checking a quantity still counts as checking the attribute of a created product like name of product. If I were to add a new functionality test to check the amount of items then I would just combine it with the normal create product functional test, or if the scenario doesn't include the `create` function then I would make a new test in the same java class with combining any similar scenarios in the future to that test.

<br>
<br>

## Module 2
# Reflection
deployment link : https://tutorial-1-eshop-mradhityautomo.koyeb.app/

1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

- Unused/ambiguous .remove(i) usage : My `delete` function has a `.remove` that removes elements from an array while looping it. To fix it I added a boolean trigger instead that will do the intended correct option if the boolean is true.
- Understanding Mock : I use Mocks and InjectMocks without understanding it beforehand, It didn't create a proper instance of the `productRepository` class inside the `service` testing. I added `when`, `thenReturn`, and `verify` statements from mockito.
- Sonar detected issues from the table html such as no caption lable, The solution was to add `<caption>` in it.
- ProductRepository had a problem in the `save` method with no default statement, the solution was to add a default statement and modify the code to have a return value of `product` and made testing easier.

<br>

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Yes :
- Continuous Integration : With using ci.yml, the code already has a built in self test mechanism that runs the unit test on push and pull. `Sonar` and `scorecard` also has an inbuilt CI sistem that checks for issues (code cleanliness) after pushing.
- Continuous Delivery : `Koyeb`, the deployment service I used,  already has an inbuilt CI/CD system in it that helps in issue testing when deploying.

I do acknowledge some of the code for this module might be a bit unclean for the professional eye and I hope to have that vision in the future.